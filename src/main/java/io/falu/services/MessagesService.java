package io.falu.services;

import io.falu.FaluClientOptions;
import io.falu.client.ResourceResponse;
import io.falu.client.patch.JsonPatchDocument;
import io.falu.models.messages.*;
import io.falu.models.messages.stream.MessageStream;
import io.falu.models.messages.stream.MessageStreamPatchModel;
import io.falu.models.messages.stream.MessageStreamsListOptions;
import io.falu.models.messages.template.*;
import io.falu.networking.RequestOptions;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

public class MessagesService extends BaseApiService {

    public MessagesService(@NotNull FaluClientOptions options) {
        super(options);
    }

    //region Messages

    /**
     * Get Messages.
     *
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<Message[]> getMessages(@Nullable MessagesListOptions listOptions, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().getMessages(listOptions, requestOptions);
    }

    /**
     * Create Message.
     *
     * @param request        the request object
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageResponse> createMessages(@NotNull MessageCreateRequest request, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().createMessage(request, requestOptions);
    }

    /**
     * Get Message.
     *
     * @param messageId      the unique identifier of the template
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<Message> getMessage(@NotNull String messageId, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().getMessage(messageId, requestOptions);
    }

    /**
     * Update Message.
     *
     * @param messageId      the unique identifier of the template.
     * @param patch          the patch request object.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<Message> updateMessage(@NotNull String messageId, @NotNull JsonPatchDocument<MessagePatchModel> patch, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().updateMessage(messageId, patch, requestOptions);
    }

    /**
     * Send bulk messages.
     *
     * @param messages       messages to send
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageResponse> sendBulkMessages(@NotNull List<MessageCreateRequest> messages, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().sendBulkMessages(messages, requestOptions);
    }
    //endregion

    //region Templates

    /**
     * Get Message Templates.
     *
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageTemplate[]> getMessageTemplates(@Nullable MessageTemplatesListOptions listOptions, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().getMessageTemplates(listOptions, requestOptions);
    }

    /**
     * Create Message Template.
     *
     * @param request        the request object
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageTemplate> createMessageTemplate(@NotNull MessageTemplateRequest request, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().createMessageTemplate(request, requestOptions);
    }

    /**
     * Get Message Template.
     *
     * @param templateId     the unique identifier of the template.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageTemplate> getMessageTemplate(@NotNull String templateId, @NonNull RequestOptions requestOptions) throws IOException {
        return getApiClient().getMessageTemplate(templateId, requestOptions);
    }

    /**
     * Update Message Template.
     *
     * @param templateId     the unique identifier of the template.
     * @param patch          the patch document.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageTemplate> updateMessageTemplate(@NotNull String templateId, @NotNull JsonPatchDocument<MessageTemplatePatchModel> patch, @NonNull RequestOptions requestOptions) throws IOException {
        return getApiClient().updateMessageTemplate(templateId, patch, requestOptions);
    }

    /**
     * Delete Message Template.
     *
     * @param templateId     the unique identifier of the template.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<?> deleteMessageTemplate(@NotNull String templateId, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().deleteMessageTemplate(templateId, requestOptions);
    }

    /**
     * Validate Message Template.
     *
     * @param request        the request object.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageTemplateValidationResponse> validateMessageTemplate(@NotNull MessageTemplateValidationRequest request, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().validateMessageTemplate(request, requestOptions);
    }
    //endregion

    //region Streams

    /**
     * Get Message Streams.
     *
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageStream[]> getMessageStreams(@Nullable MessageStreamsListOptions listOptions, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().getMessageStreams(listOptions, requestOptions);
    }

    /**
     * Get Message Stream.
     *
     * @param streamId       the result object for the request.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageStream> getMessageStream(@NotNull String streamId, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().getMessageStream(streamId, requestOptions);
    }

    /**
     * Update Message Stream.
     *
     * @param streamId       the result object for the request.
     * @param patch          the patch document object.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageStream> updateMessageStream(@NotNull String streamId, @NotNull JsonPatchDocument<MessageStreamPatchModel> patch, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().updateMessageStream(streamId, patch, requestOptions);
    }

    /**
     * Delete Message Stream.
     *
     * @param streamId       the unique identifier of the object.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<?> deleteMessageStream(@NotNull String streamId, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().deleteMessageStream(streamId, requestOptions);
    }

    /**
     * Archive Message Stream.
     *
     * @param streamId       the unique identifier of the object.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageStream> archiveMessageStream(@NotNull String streamId, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().archiveMessageStream(streamId, requestOptions);
    }

    /**
     * Unarchive Message Stream.
     *
     * @param streamId       the unique identifier of the object.
     * @param requestOptions additional info to add to the request.
     */
    public ResourceResponse<MessageStream> unarchiveMessageStream(@NotNull String streamId, @Nullable RequestOptions requestOptions) throws IOException {
        return getApiClient().unarchiveMessageStream(streamId, requestOptions);
    }
    //endregion
}
