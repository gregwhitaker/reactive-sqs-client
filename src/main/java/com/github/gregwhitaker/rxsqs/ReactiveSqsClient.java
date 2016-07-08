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

package com.github.gregwhitaker.rxsqs;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import com.amazonaws.services.sqs.model.AddPermissionRequest;
import com.amazonaws.services.sqs.model.AddPermissionResult;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequest;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequestEntry;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchResult;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityResult;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequest;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.DeleteMessageBatchResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteMessageResult;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueResult;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesRequest;
import com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesResult;
import com.amazonaws.services.sqs.model.ListQueuesRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.PurgeQueueRequest;
import com.amazonaws.services.sqs.model.PurgeQueueResult;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.RemovePermissionRequest;
import com.amazonaws.services.sqs.model.RemovePermissionResult;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.SetQueueAttributesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * An {@link Observable} wrapper around the Amazon Web Services Simple Queue Service (SQS) client.
 */
public class ReactiveSqsClient {
    private static final Logger LOG = LoggerFactory.getLogger(ReactiveSqsClient.class);

    private final AmazonSQSAsyncClient sqsClient;
    private volatile boolean stopRequested = false;

    /**
     * Creates a new {@link ReactiveSqsClient} instance pointing to the default AWS region (us-east-1).
     */
    public ReactiveSqsClient() {
        this(Regions.US_EAST_1);
        LOG.info("No region was specified when creating the ReactiveSqsClient.  Using the default region: us-east-1");
    }

    /**
     * Creates a new {@link ReactiveSqsClient} instance pointing to the specified AWS region.
     *
     * @param region AWS region
     */
    public ReactiveSqsClient(Regions region) {
        this.sqsClient = new AmazonSQSAsyncClient(new DefaultAWSCredentialsProviderChain());
        this.sqsClient.setRegion(Region.getRegion(region));
    }

    public Observable<AddPermissionResult> addPermissionAsync(AddPermissionRequest addPermissionRequest) {
        return Observable.from(sqsClient.addPermissionAsync(addPermissionRequest));
    }

    public Observable<ChangeMessageVisibilityResult> changeMessageVisibilityAsync(ChangeMessageVisibilityRequest request) {
        return Observable.from(sqsClient.changeMessageVisibilityAsync(request));
    }

    public Observable<ChangeMessageVisibilityResult> changeMessageVisibilityAsync(String queueUrl, String receiptHandle, Integer visibilityTimeout) {
        return Observable.from(sqsClient.changeMessageVisibilityAsync(queueUrl, receiptHandle, visibilityTimeout));
    }

    public Observable<ChangeMessageVisibilityBatchResult> changeMessageVisibilityBatchAsync(ChangeMessageVisibilityBatchRequest request) {
        return Observable.from(sqsClient.changeMessageVisibilityBatchAsync(request));
    }

    public Observable<ChangeMessageVisibilityBatchResult> changeMessageVisibilityBatchAsync(String queueUrl, List<ChangeMessageVisibilityBatchRequestEntry> entries) {
        return Observable.from(sqsClient.changeMessageVisibilityBatchAsync(queueUrl, entries));
    }

    public Observable<CreateQueueResult> createQueueAsync(CreateQueueRequest request) {
        return Observable.from(sqsClient.createQueueAsync(request));
    }

    public Observable<CreateQueueResult> createQueueAsync(String queueName) {
        return Observable.from(sqsClient.createQueueAsync(queueName));
    }

    public Observable<DeleteMessageResult> deleteMessageAsync(DeleteMessageRequest request) {
        return Observable.from(sqsClient.deleteMessageAsync(request));
    }

    public Observable<DeleteMessageResult> deleteMessageAsync(String queueUrl, String receiptHandle) {
        return Observable.from(sqsClient.deleteMessageAsync(queueUrl, receiptHandle));
    }

    public Observable<DeleteMessageBatchResult> deleteMessageBatchAsync(DeleteMessageBatchRequest request) {
        return Observable.from(sqsClient.deleteMessageBatchAsync(request));
    }

    public Observable<DeleteMessageBatchResult> deleteMessageBatchAsync(String queueUrl, List<DeleteMessageBatchRequestEntry> entries) {
        return Observable.from(sqsClient.deleteMessageBatchAsync(queueUrl, entries));
    }

    public Observable<DeleteQueueResult> deleteQueueAsync(DeleteQueueRequest request) {
        return Observable.from(sqsClient.deleteQueueAsync(request));
    }

    public Observable<DeleteQueueResult> deleteQueueAsync(String queueUrl) {
        return Observable.from(sqsClient.deleteQueueAsync(queueUrl));
    }

    public Observable<GetQueueAttributesResult> getQueueAttributesAsync(GetQueueAttributesRequest request) {
        return Observable.from(sqsClient.getQueueAttributesAsync(request));
    }

    public Observable<GetQueueAttributesResult> getQueueAttributesAsync(String queueUrl, List<String> attributeNames) {
        return Observable.from(sqsClient.getQueueAttributesAsync(queueUrl, attributeNames));
    }

    public Observable<GetQueueUrlResult> getQueueUrlAsync(GetQueueUrlRequest request) {
        return Observable.from(sqsClient.getQueueUrlAsync(request));
    }

    public Observable<GetQueueUrlResult> getQueueUrlAsync(String queueName) {
        return Observable.from(sqsClient.getQueueUrlAsync(queueName));
    }

    public Observable<ListDeadLetterSourceQueuesResult> listDeadLetterSourceQueuesAsync(ListDeadLetterSourceQueuesRequest request) {
        return Observable.from(sqsClient.listDeadLetterSourceQueuesAsync(request));
    }

    public Observable<ListQueuesResult> listQueuesAsync(ListQueuesRequest request) {
        return Observable.from(sqsClient.listQueuesAsync(request));
    }

    public Observable<ListQueuesResult> listQueuesAsync() {
        return Observable.from(sqsClient.listQueuesAsync());
    }

    public Observable<ListQueuesResult> listQueuesAsync(String queueNamePrefix) {
        return Observable.from(sqsClient.listQueuesAsync(queueNamePrefix));
    }

    public Observable<PurgeQueueResult> purgeQueueAsync(PurgeQueueRequest request) {
        return Observable.from(sqsClient.purgeQueueAsync(request));
    }

    public Observable<Message> receiveMessageAsync(ReceiveMessageRequest request) {
        return Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                while (!stopRequested) {
                    Future<ReceiveMessageResult> future = sqsClient.receiveMessageAsync(request);

                    try {
                        ReceiveMessageResult result = future.get();

                        if (result != null) {
                            result.getMessages().forEach(subscriber::onNext);
                        }
                    } catch (InterruptedException e) {
                        stopRequested = true;
                    } catch (ExecutionException e) {
                        subscriber.onError(e);
                    }
                }

                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

    public Observable<Message> receiveMessageAsync(String queueUrl) {
        return Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                while (!stopRequested) {
                    Future<ReceiveMessageResult> future = sqsClient.receiveMessageAsync(queueUrl);

                    try {
                        ReceiveMessageResult result = future.get();

                        if (result != null) {
                            result.getMessages().forEach(subscriber::onNext);
                        }
                    } catch (InterruptedException e) {
                        stopRequested = true;
                    } catch (ExecutionException e) {
                        subscriber.onError(e);
                    }
                }

                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

    public Observable<RemovePermissionResult> removePermissionAsync(RemovePermissionRequest request) {
        return Observable.from(sqsClient.removePermissionAsync(request));
    }

    public Observable<RemovePermissionResult> removePermissionAsync(String queueUrl, String label) {
        return Observable.from(sqsClient.removePermissionAsync(queueUrl, label));
    }

    public Observable<SendMessageResult> sendMessageAsync(SendMessageRequest request) {
        return Observable.from(sqsClient.sendMessageAsync(request));
    }

    public Observable<SendMessageResult> sendMessageAsync(String queueUrl, String messageBody) {
        return Observable.from(sqsClient.sendMessageAsync(queueUrl, messageBody));
    }

    public Observable<SendMessageBatchResult> sendMessageBatchAsync(SendMessageBatchRequest request) {
        return Observable.from(sqsClient.sendMessageBatchAsync(request));
    }

    public Observable<SendMessageBatchResult> sendMessageBatchAsync(String queueUrl, List<SendMessageBatchRequestEntry> entries) {
        return Observable.from(sqsClient.sendMessageBatchAsync(queueUrl, entries));
    }

    public Observable<SetQueueAttributesResult> setQueueAttributesAsync(SetQueueAttributesRequest request) {
        return Observable.from(sqsClient.setQueueAttributesAsync(request));
    }

    public Observable<SetQueueAttributesResult> setQueueAttributesAsync(String queueUrl, Map<String, String> attributes) {
        return Observable.from(sqsClient.setQueueAttributesAsync(queueUrl, attributes));
    }
}
