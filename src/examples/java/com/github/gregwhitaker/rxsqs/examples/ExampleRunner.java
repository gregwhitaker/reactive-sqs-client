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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runs the Reactive SQS Client example.
 */
public class ExampleRunner {

    /**
     * Main entry-point for this example.  This starts the producer and consumer applications.
     *
     * @param args command line arguments
     */
    public static void main(String... args) throws Exception {
        final String queueName = System.getProperty("queueName");
        final String region = System.getProperty("region");

        if (queueName == null || queueName.isEmpty()) {
            System.out.println("Test");
            throw new RuntimeException("The name of the queue is required to run this demo!");
        }
        
        if (region == null || region.isEmpty()) {
            throw new RuntimeException("The AWS region where the queue is located is required to run this demo!");
        } else {
            try {
                Regions.fromName(region);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(String.format("Region '%s' is not a valid AWS region identifier.", region), e);
            }
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Producer("Producer1", queueName, region));
        executor.execute(new Consumer("Consumer1", queueName, region));

        Thread.currentThread().join();
    }
}
