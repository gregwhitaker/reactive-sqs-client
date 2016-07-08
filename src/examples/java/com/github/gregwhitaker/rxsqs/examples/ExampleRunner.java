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
