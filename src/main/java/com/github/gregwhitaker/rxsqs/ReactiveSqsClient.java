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

import java.util.List;
import java.util.Map;

/**
 *
 */
public class ReactiveSqsClient {
    private static final Logger LOG = LoggerFactory.getLogger(ReactiveSqsClient.class);

    private final AmazonSQSAsyncClient sqsClient;

    /**
     *
     * @param arn
     */
    public ReactiveSqsClient(String arn) {
        this(arn, Regions.US_EAST_1);
        LOG.info("No region was specified when creating the ReactiveSqsClient.  Using the default region: us-east-1");
    }

    /**
     *
     * @param arn
     * @param region
     */
    public ReactiveSqsClient(String arn, Regions region) {
        this.sqsClient = new AmazonSQSAsyncClient(new DefaultAWSCredentialsProviderChain());
        this.sqsClient.setRegion(Region.getRegion(region));
    }

    public Observable<AddPermissionResult> addPermissionAsync(AddPermissionRequest addPermissionRequest) {
        return null;
    }

    public Observable<ChangeMessageVisibilityResult> changeMessageVisibilityAsync(ChangeMessageVisibilityRequest request) {
        return null;
    }

    public Observable<ChangeMessageVisibilityResult> changeMessageVisibilityAsync(String queueUrl, String receiptHandle, Integer visibilityTimeout) {
        return null;
    }

    public Observable<ChangeMessageVisibilityBatchResult> changeMessageVisibilityBatchAsync(ChangeMessageVisibilityBatchRequest request) {
        return null;
    }

    public Observable<ChangeMessageVisibilityBatchResult> changeMessageVisibilityBatchAsync(String queueUrl, List<ChangeMessageVisibilityBatchRequestEntry> entries) {
        return null;
    }

    public Observable<CreateQueueResult> createQueueAsync(CreateQueueRequest request) {
        return null;
    }

    public Observable<CreateQueueResult> createQueueAsync(String queueName) {
        return null;
    }

    public Observable<DeleteMessageResult> deleteMessageAsync(DeleteMessageRequest request) {
        return null;
    }

    public Observable<DeleteMessageResult> deleteMessageAsync(String queueUrl, String receiptHandle) {
        return null;
    }

    public Observable<DeleteMessageBatchResult> deleteMessageBatchAsync(DeleteMessageBatchRequest request) {
        return null;
    }

    public Observable<DeleteMessageBatchResult> deleteMessageBatchAsync(String queueUrl, List<DeleteMessageBatchRequestEntry> entries) {
        return null;
    }

    public Observable<DeleteQueueResult> deleteQueueAsync(DeleteQueueRequest request) {
        return null;
    }

    public Observable<DeleteQueueResult> deleteQueueAsync(String queueUrl) {
        return null;
    }

    public Observable<GetQueueAttributesResult> getQueueAttributesAsync(GetQueueAttributesRequest request) {
        return null;
    }

    public Observable<GetQueueAttributesResult> getQueueAttributesAsync(String queueUrl, List<String> attributeNames) {
        return null;
    }

    public Observable<GetQueueUrlResult> getQueueUrlAsync(GetQueueUrlRequest request) {
        return null;
    }

    public Observable<GetQueueUrlResult> getQueueUrlAsync(String queueName) {
        return null;
    }

    public Observable<ListDeadLetterSourceQueuesResult> listDeadLetterSourceQueuesAsync(ListDeadLetterSourceQueuesRequest request) {
        return null;
    }

    public Observable<ListQueuesResult> listQueuesAsync(ListQueuesRequest request) {
        return null;
    }

    public Observable<ListQueuesResult> listQueuesAsync() {
        return null;
    }

    public Observable<ListQueuesResult> listQueuesAsync(String queueNamePrefix) {
        return null;
    }

    public Observable<PurgeQueueResult> purgeQueueAsync(PurgeQueueRequest request) {
        return null;
    }

    public Observable<ReceiveMessageResult> receiveMessageAsync(ReceiveMessageRequest request) {
        return null;
    }

    public Observable<ReceiveMessageResult> receiveMessageAsync(String queueUrl) {
        return null;
    }

    public Observable<RemovePermissionResult> removePermissionAsync(RemovePermissionRequest request) {
        return null;
    }

    public Observable<RemovePermissionResult> removePermissionAsync(String queueUrl, String label) {
        return null;
    }

    public Observable<SendMessageResult> sendMessageAsync(SendMessageRequest request) {
        return null;
    }

    public Observable<SendMessageResult> sendMessageAsync(String queueUrl, String messageBody) {
        return null;
    }

    public Observable<SendMessageBatchResult> sendMessageBatchAsync(SendMessageBatchRequest request) {
        return null;
    }

    public Observable<SendMessageBatchResult> sendMessageBatchAsync(String queueUrl, List<SendMessageBatchRequestEntry> entries) {
        return null;
    }

    public Observable<SetQueueAttributesResult> setQueueAttributesAsync(SetQueueAttributesRequest request) {
        return null;
    }

    public Observable<SetQueueAttributesResult> setQueueAttributesAsync(String queueUrl, Map<String, String> attributes) {
        return null;
    }
}
