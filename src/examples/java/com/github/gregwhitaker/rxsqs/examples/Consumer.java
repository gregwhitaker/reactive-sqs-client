/*
 * Copyright 2016 Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.gregwhitaker.rxsqs.examples;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.github.gregwhitaker.rxsqs.ReactiveSqsClient;
import rx.schedulers.Schedulers;

/**
 * A consumer that reads messages from an AWS SQS queue.
 */
public class Consumer implements Runnable {
    private final String name;
    private final String queueName;
    private final ReactiveSqsClient rxSqsClient;

    /**
     * Creates a {@link Consumer} instance that reads messages from an AWS SQS queue.
     *
     * @param name the name of the consumer
     * @param queueName the name of the queue from which to consume messages
     * @param region the region of the queue from which to consume messages
     */
    public Consumer(final String name, final String queueName, final String region) {
        this.name = name;
        this.queueName = queueName;

        this.rxSqsClient = new ReactiveSqsClient(Regions.fromName(region));
    }

    @Override
    public void run() {
        rxSqsClient.getQueueUrlAsync(queueName)
                .last()
                .map(GetQueueUrlResult::getQueueUrl)
                .subscribe(url -> {
                    rxSqsClient.receiveMessageAsync(url)
                            .subscribeOn(Schedulers.io())
                            .subscribe(message -> {
                                System.out.println(name + ": " + message.getBody() + "[" + message.getMessageId() + "]");

                                rxSqsClient.deleteMessageAsync(url, message.getReceiptHandle())
                                        .toBlocking()
                                        .subscribe(result -> {
                                            System.out.println("Acknowledged Message " + message.getMessageId());
                                        });
                            }, Throwable::printStackTrace);
                });
    }
}
