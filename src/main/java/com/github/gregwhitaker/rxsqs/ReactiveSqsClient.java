/**
 * Copyright 2016 - 2020 Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.gregwhitaker.rxsqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.function.Consumer;

/**
 * A reactive wrapper, using Project Reactor, around the Amazon Web Services Simple Queue Service (SQS) client.
 */
public class ReactiveSqsClient {
    private static final Logger LOG = LoggerFactory.getLogger(ReactiveSqsClient.class);
    private static final Long DEFAULT_BACKOFF = 1000L;
    private static final Long MAX_BACKOFF = 64000L;

    private final SqsAsyncClient sqs;

    /**
     * Creates a new {@link ReactiveSqsClient} instance pointing to the default AWS region (us-east-1).
     */
    public ReactiveSqsClient() {
        this(Region.US_EAST_1);
        LOG.info("No region was specified when creating the ReactiveSqsClient.  Using the default region: us-east-1");
    }

    /**
     * Creates a new {@link ReactiveSqsClient} instance.
     *
     * @param sqs AWS SQS client
     */
    public ReactiveSqsClient(SqsAsyncClient sqs) {
        this.sqs = sqs;
    }

    /**
     * Creates a new {@link ReactiveSqsClient} instance pointing to the specified AWS region.
     *
     * @param region AWS region
     */
    public ReactiveSqsClient(Region region) {
        this.sqs = SqsAsyncClient.builder()
                .credentialsProvider(DefaultCredentialsProvider.builder().build())
                .region(region)
                .build();
    }

    public Mono<AddPermissionResponse> addPermission(AddPermissionRequest addPermissionRequest) {
        return Mono.fromFuture(sqs.addPermission(addPermissionRequest));
    }

    public Mono<AddPermissionResponse> addPermission(Consumer<AddPermissionRequest.Builder> addPermissionRequest) {
        return Mono.fromFuture(sqs.addPermission(AddPermissionRequest.builder().applyMutation(addPermissionRequest).build()));
    }

    public Mono<ChangeMessageVisibilityResponse> changeMessageVisibility(ChangeMessageVisibilityRequest changeMessageVisibilityRequest) {
        return Mono.fromFuture(sqs.changeMessageVisibility(changeMessageVisibilityRequest));
    }

    public Mono<ChangeMessageVisibilityResponse> changeMessageVisibility(Consumer<ChangeMessageVisibilityRequest.Builder> changeMessageVisibilityRequest) {
        return Mono.fromFuture(sqs.changeMessageVisibility(ChangeMessageVisibilityRequest.builder().applyMutation(changeMessageVisibilityRequest).build()));
    }

    public Mono<ChangeMessageVisibilityBatchResponse> changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest changeMessageVisibilityBatchRequest) {
        return Mono.fromFuture(sqs.changeMessageVisibilityBatch(changeMessageVisibilityBatchRequest));
    }

    public Mono<ChangeMessageVisibilityBatchResponse> changeMessageVisibilityBatch(Consumer<ChangeMessageVisibilityBatchRequest.Builder> changeMessageVisibilityBatchRequest) {
        return Mono.fromFuture(sqs.changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest.builder().applyMutation(changeMessageVisibilityBatchRequest).build()));
    }

    public Mono<CreateQueueResponse> createQueue(CreateQueueRequest createQueueRequest) {
        return Mono.fromFuture(sqs.createQueue(createQueueRequest));
    }

    public Mono<CreateQueueResponse> createQueue(Consumer<CreateQueueRequest.Builder> createQueueRequest) {
        return Mono.fromFuture(sqs.createQueue(CreateQueueRequest.builder().applyMutation(createQueueRequest).build()));
    }

    public Mono<DeleteMessageResponse> deleteMessage(DeleteMessageRequest deleteMessageRequest) {
        return Mono.fromFuture(sqs.deleteMessage(deleteMessageRequest));
    }

    public Mono<DeleteMessageResponse> deleteMessage(Consumer<DeleteMessageRequest.Builder> deleteMessageRequest) {
        return Mono.fromFuture(sqs.deleteMessage(DeleteMessageRequest.builder().applyMutation(deleteMessageRequest).build()));
    }

    public Mono<DeleteMessageBatchResponse> deleteMessageBatch(DeleteMessageBatchRequest deleteMessageBatchRequest) {
        return Mono.fromFuture(sqs.deleteMessageBatch(deleteMessageBatchRequest));
    }

    public Mono<DeleteMessageBatchResponse> deleteMessageBatch(Consumer<DeleteMessageBatchRequest.Builder> deleteMessageBatchRequest) {
        return Mono.fromFuture(sqs.deleteMessageBatch(DeleteMessageBatchRequest.builder().applyMutation(deleteMessageBatchRequest).build()));
    }

    public Mono<DeleteQueueResponse> deleteQueue(DeleteQueueRequest deleteQueueRequest) {
        return Mono.fromFuture(sqs.deleteQueue(deleteQueueRequest));
    }

    public Mono<DeleteQueueResponse> deleteQueue(Consumer<DeleteQueueRequest.Builder> deleteQueueRequest) {
        return Mono.fromFuture(sqs.deleteQueue(DeleteQueueRequest.builder().applyMutation(deleteQueueRequest).build()));
    }

    public Mono<GetQueueAttributesResponse> getQueueAttributes(GetQueueAttributesRequest getQueueAttributesRequest) {
        return Mono.fromFuture(sqs.getQueueAttributes(getQueueAttributesRequest));
    }

    public Mono<GetQueueAttributesResponse> getQueueAttributes(Consumer<GetQueueAttributesRequest.Builder> getQueueAttributesRequest) {
        return Mono.fromFuture(sqs.getQueueAttributes(GetQueueAttributesRequest.builder().applyMutation(getQueueAttributesRequest).build()));
    }

    public Mono<GetQueueUrlResponse> getQueueUrl(GetQueueUrlRequest getQueueUrlRequest) {
        return Mono.fromFuture(sqs.getQueueUrl(getQueueUrlRequest));
    }

    public Mono<GetQueueUrlResponse> getQueueUrl(Consumer<GetQueueUrlRequest.Builder> getQueueUrlRequest) {
        return Mono.fromFuture(sqs.getQueueUrl(GetQueueUrlRequest.builder().applyMutation(getQueueUrlRequest).build()));
    }

    public Mono<ListDeadLetterSourceQueuesResponse> listDeadLetterSourceQueues(ListDeadLetterSourceQueuesRequest listDeadLetterSourceQueuesRequest) {
        return Mono.fromFuture(sqs.listDeadLetterSourceQueues(listDeadLetterSourceQueuesRequest));
    }

    public Mono<ListDeadLetterSourceQueuesResponse> listDeadLetterSourceQueues(Consumer<ListDeadLetterSourceQueuesRequest.Builder> listDeadLetterSourceQueuesRequest) {
        return Mono.fromFuture(sqs.listDeadLetterSourceQueues(ListDeadLetterSourceQueuesRequest.builder().applyMutation(listDeadLetterSourceQueuesRequest).build()));
    }

    public Mono<ListQueueTagsResponse> listQueueTags(ListQueueTagsRequest listQueueTagsRequest) {
        return Mono.fromFuture(sqs.listQueueTags(listQueueTagsRequest));
    }

    public Mono<ListQueueTagsResponse> listQueueTags(Consumer<ListQueueTagsRequest.Builder> listQueueTagsRequest) {
        return Mono.fromFuture(sqs.listQueueTags(ListQueueTagsRequest.builder().applyMutation(listQueueTagsRequest).build()));
    }

    public Mono<ListQueuesResponse> listQueues(ListQueuesRequest listQueuesRequest) {
        return Mono.fromFuture(sqs.listQueues(listQueuesRequest));
    }

    public Mono<ListQueuesResponse> listQueues(Consumer<ListQueuesRequest.Builder> listQueuesRequest) {
        return Mono.fromFuture(sqs.listQueues(ListQueuesRequest.builder().applyMutation(listQueuesRequest).build()));
    }

    public Mono<ListQueuesResponse> listQueues() {
        return Mono.fromFuture(sqs.listQueues());
    }

    public Mono<PurgeQueueResponse> purgeQueue(PurgeQueueRequest purgeQueueRequest) {
        return Mono.fromFuture(sqs.purgeQueue(purgeQueueRequest));
    }

    public Mono<PurgeQueueResponse> purgeQueue(Consumer<PurgeQueueRequest.Builder> purgeQueueRequest) {
        return Mono.fromFuture(sqs.purgeQueue(PurgeQueueRequest.builder().applyMutation(purgeQueueRequest).build()));
    }

    public Flux<Message> receiveMessage(ReceiveMessageRequest receiveMessageRequest) {
//        return Mono.fromFuture(sqs.receiveMessage(receiveMessageRequest))
//                .flatMapMany((Function<ReceiveMessageResponse, Publisher<Message>>) receiveMessageResponse -> Flux.fromIterable(receiveMessageResponse.messages()));

        return Flux.generate(sink -> {

        });
    }

    public Flux<Message> receiveMessage(Consumer<ReceiveMessageRequest.Builder> receiveMessageRequest) {
        return null;
    }

//    public Observable<Message> receiveMessageAsync(ReceiveMessageRequest request) {
//        return Observable.create(new Observable.OnSubscribe<Message>() {
//            @Override
//            public void call(Subscriber<? super Message> subscriber) {
//                long backoff = DEFAULT_BACKOFF;
//
//                while (!stopRequested) {
//                    Future<ReceiveMessageResult> future = sqs.receiveMessageAsync(request);
//
//                    try {
//                        ReceiveMessageResult result = future.get();
//
//                        if (result != null && !result.getMessages().isEmpty()) {
//                            backoff = DEFAULT_BACKOFF;
//                            result.getMessages().forEach(subscriber::onNext);
//                        } else {
//                            if (backoff < MAX_BACKOFF) {
//                                backoff = backoff * 2;
//                            }
//
//                            LOG.debug("No messages found on queue.  Sleeping for {} ms.", backoff);
//
//                            // This is to prevent rate limiting by the AWS api
//                            Thread.sleep(backoff);
//                        }
//                    } catch (InterruptedException e) {
//                        stopRequested = true;
//                    } catch (ExecutionException e) {
//                        subscriber.onError(e);
//                    }
//                }
//
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io());
//    }
//
//    public Observable<Message> receiveMessageAsync(String queueUrl) {
//        return Observable.create(new Observable.OnSubscribe<Message>() {
//            @Override
//            public void call(Subscriber<? super Message> subscriber) {
//                long backoff = DEFAULT_BACKOFF;
//
//                while (!stopRequested) {
//                    Future<ReceiveMessageResult> future = sqs.receiveMessageAsync(queueUrl);
//
//                    try {
//                        ReceiveMessageResult result = future.get();
//
//                        if (result != null && !result.getMessages().isEmpty()) {
//                            backoff = DEFAULT_BACKOFF;
//                            result.getMessages().forEach(subscriber::onNext);
//                        } else {
//                            if (backoff < MAX_BACKOFF) {
//                                backoff = backoff * 2;
//                            }
//
//                            LOG.debug("No messages found on queue.  Sleeping for {} ms.", backoff);
//
//                            // This is to prevent rate limiting by the AWS api
//                            Thread.sleep(backoff);
//                        }
//                    } catch (InterruptedException e) {
//                        stopRequested = true;
//                    } catch (ExecutionException e) {
//                        subscriber.onError(e);
//                    }
//                }
//
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io());
//    }

    public Mono<RemovePermissionResponse> removePermission(RemovePermissionRequest removePermissionRequest) {
        return Mono.fromFuture(sqs.removePermission(removePermissionRequest));
    }

    public Mono<RemovePermissionResponse> removePermission(Consumer<RemovePermissionRequest.Builder> removePermissionRequest) {
        return Mono.fromFuture(sqs.removePermission(RemovePermissionRequest.builder().applyMutation(removePermissionRequest).build()));
    }

    public Mono<SendMessageResponse> sendMessage(SendMessageRequest sendMessageRequest) {
        return Mono.fromFuture(sqs.sendMessage(sendMessageRequest));
    }

    public Mono<SendMessageResponse> sendMessage(Consumer<SendMessageRequest.Builder> sendMessageRequest) {
        return Mono.fromFuture(sqs.sendMessage(SendMessageRequest.builder().applyMutation(sendMessageRequest).build()));
    }

    public Mono<SendMessageBatchResponse> sendMessageBatch(SendMessageBatchRequest sendMessageBatchRequest) {
        return Mono.fromFuture(sqs.sendMessageBatch(sendMessageBatchRequest));
    }

    public Mono<SendMessageBatchResponse> sendMessageBatch(Consumer<SendMessageBatchRequest.Builder> sendMessageBatchRequest) {
        return Mono.fromFuture(sqs.sendMessageBatch(SendMessageBatchRequest.builder().applyMutation(sendMessageBatchRequest).build()));
    }

    public Mono<SetQueueAttributesResponse> setQueueAttributes(SetQueueAttributesRequest setQueueAttributesRequest) {
        return Mono.fromFuture(sqs.setQueueAttributes(setQueueAttributesRequest));
    }

    public Mono<SetQueueAttributesResponse> setQueueAttributes(Consumer<SetQueueAttributesRequest.Builder> setQueueAttributesRequest) {
        return Mono.fromFuture(sqs.setQueueAttributes(SetQueueAttributesRequest.builder().applyMutation(setQueueAttributesRequest).build()));
    }

    public Mono<TagQueueResponse> tagQueue(TagQueueRequest tagQueueRequest) {
        return Mono.fromFuture(sqs.tagQueue(tagQueueRequest));
    }

    public Mono<TagQueueResponse> tagQueue(Consumer<TagQueueRequest.Builder> tagQueueRequest) {
        return Mono.fromFuture(sqs.tagQueue(TagQueueRequest.builder().applyMutation(tagQueueRequest).build()));
    }

    public Mono<UntagQueueResponse> untagQueue(UntagQueueRequest untagQueueRequest) {
        return Mono.fromFuture(sqs.untagQueue(untagQueueRequest));
    }

    public Mono<UntagQueueResponse> untagQueue(Consumer<UntagQueueRequest.Builder> untagQueueRequest) {
        return Mono.fromFuture(sqs.untagQueue(UntagQueueRequest.builder().applyMutation(untagQueueRequest).build()));
    }
}
