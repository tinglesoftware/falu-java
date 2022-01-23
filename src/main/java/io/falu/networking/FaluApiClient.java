package io.falu.networking;

import io.falu.FaluClientOptions;
import io.falu.client.AbstractHttpApiClient;
import io.falu.client.ResourceResponse;
import io.falu.client.patch.JsonPatchDocument;
import io.falu.common.BasicListOptions;
import io.falu.common.QueryValues;
import io.falu.models.evaluations.Evaluation;
import io.falu.models.evaluations.EvaluationPatchModel;
import io.falu.models.evaluations.EvaluationRequest;
import io.falu.models.evaluations.EvaluationsListOptions;
import io.falu.models.identity.IdentityRecord;
import io.falu.models.identity.IdentitySearchModel;
import io.falu.models.identity.MarketingListOptions;
import io.falu.models.identity.MarketingResult;
import io.falu.models.messages.*;
import io.falu.models.messages.stream.MessageStream;
import io.falu.models.messages.stream.MessageStreamCreateRequest;
import io.falu.models.messages.stream.MessageStreamPatchModel;
import io.falu.models.messages.stream.MessageStreamsListOptions;
import io.falu.models.messages.template.*;
import io.falu.models.moneyBalances.MoneyBalance;
import io.falu.models.payments.Payment;
import io.falu.models.payments.PaymentCreateRequest;
import io.falu.models.payments.PaymentPatchModel;
import io.falu.models.payments.authorization.PaymentAuthorization;
import io.falu.models.payments.authorization.PaymentAuthorizationPatchModel;
import io.falu.models.payments.refunds.PaymentRefund;
import io.falu.models.payments.refunds.PaymentRefundPatchModel;
import io.falu.models.payments.refunds.PaymentRefundRequest;
import io.falu.models.transfers.Transfer;
import io.falu.models.transfers.TransferCreateRequest;
import io.falu.models.transfers.TransferPatchModel;
import io.falu.models.transfers.reversals.TransferReversal;
import io.falu.models.transfers.reversals.TransferReversalCreateRequest;
import io.falu.models.transfers.reversals.TransferReversalPatchModel;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FaluApiClient extends AbstractHttpApiClient {
    private static final String HOST = "api.falu.io";
    private static final String SCHEME = "https";

    public FaluApiClient(FaluClientOptions options, AppDetailsInterceptor interceptor) {
        super(new FaluAuthenticationHeaderProvider(options.getApiKey()), interceptor);
    }

    //region Evaluations
    public ResourceResponse<Evaluation[]> getEvaluations(EvaluationsListOptions options, RequestOptions requestOptions) throws IOException {
        HttpUrl url = buildUrl("v1/evaluations", options);
        Request.Builder builder = buildRequest(requestOptions)
                .url(url)
                .get();
        return execute(builder, Evaluation[].class);
    }

    public ResourceResponse<Evaluation> createEvaluation(EvaluationRequest request, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/evaluations", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(request), MEDIA_TYPE_JSON));
        return execute(builder, Evaluation.class);
    }

    public ResourceResponse<Evaluation> getEvaluation(String evaluationId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/evaluations/" + evaluationId, null);
        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, Evaluation.class);
    }

    public ResourceResponse<Evaluation> updateEvaluation(String evaluationId, JsonPatchDocument<EvaluationPatchModel> patch, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/evaluations/" + evaluationId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .patch(RequestBody.create(makeJson(patch.getOperations()), MEDIA_TYPE_JSON));
        return execute(builder, Evaluation.class);
    }

    public ResourceResponse<Evaluation> scoreEvaluation(String evaluationId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/evaluations/" + evaluationId + "/scores", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(null), MEDIA_TYPE_JSON));
        return execute(builder, Evaluation.class);
    }
    //endregion

    //region Identity
    public ResourceResponse<IdentityRecord> searchIdentity(IdentitySearchModel searchModel, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/identity/search", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(searchModel), MEDIA_TYPE_JSON));

        return execute(builder, IdentityRecord.class);
    }

    public ResourceResponse<MarketingResult[]> fetchMarketingResults(MarketingListOptions marketingListOptions, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/identity/marketing", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(null), MEDIA_TYPE_JSON));

        return execute(builder, MarketingResult[].class);
    }
    //endregion

    //region Payments
    public ResourceResponse<Payment[]> getPayments(RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payments", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();

        return execute(builder, Payment[].class);
    }

    public ResourceResponse<Payment> getPayment(String paymentId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payments/" + paymentId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();

        return execute(builder, Payment.class);
    }

    public ResourceResponse<Payment> updatePayment(String paymentId, JsonPatchDocument<PaymentPatchModel> document, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payments/" + paymentId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .patch(RequestBody.create(makeJson(document.getOperations()), MEDIA_TYPE_JSON));

        return execute(builder, Payment.class);
    }

    public ResourceResponse<Payment> createPayment(PaymentCreateRequest request, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payments/", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(request), MEDIA_TYPE_JSON));

        return execute(builder, Payment.class);
    }

    public ResourceResponse<PaymentAuthorization[]> getPaymentAuthorizations(RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payment_authorizations", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();

        return execute(builder, PaymentAuthorization[].class);
    }

    public ResourceResponse<PaymentAuthorization> getPaymentAuthorization(String authorizationId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payment_authorizations/" + authorizationId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, PaymentAuthorization.class);
    }

    public ResourceResponse<PaymentAuthorization> updatePaymentAuthorization(String authorizationId, JsonPatchDocument<PaymentAuthorizationPatchModel> document, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payment_authorizations/" + authorizationId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .patch(RequestBody.create(makeJson(document.getOperations()), MEDIA_TYPE_JSON));
        return execute(builder, PaymentAuthorization.class);
    }

    public ResourceResponse<PaymentAuthorization> approvePaymentAuthorization(String authorizationId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payment_authorizations/" + authorizationId + "/approve", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(null), MEDIA_TYPE_JSON));
        return execute(builder, PaymentAuthorization.class);
    }

    public ResourceResponse<PaymentAuthorization> declinePaymentAuthorization(String authorizationId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payment_authorizations/" + authorizationId + "/decline", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(null), MEDIA_TYPE_JSON));
        return execute(builder, PaymentAuthorization.class);
    }

    public ResourceResponse<PaymentRefund[]> getPaymentRefunds(RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payment_refunds/", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();

        return execute(builder, PaymentRefund[].class);
    }

    public ResourceResponse<PaymentRefund> createPaymentRefund(PaymentRefundRequest request, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payment_refunds/", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(request), MEDIA_TYPE_JSON));

        return execute(builder, PaymentRefund.class);
    }

    public ResourceResponse<PaymentRefund> getPaymentRefund(String refundId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payment_refunds/" + refundId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, PaymentRefund.class);
    }

    public ResourceResponse<PaymentRefund> updatePaymentRefund(String refundId, JsonPatchDocument<PaymentRefundPatchModel> document, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/payment_refunds/" + refundId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .patch(RequestBody.create(makeJson(document.getOperations()), MEDIA_TYPE_JSON));
        return execute(builder, PaymentRefund.class);
    }
    //endregion

    //region Messages, Message Templates, and Message Streams
    public ResourceResponse<Message[]> getMessages(MessagesListOptions listOptions, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/messages", listOptions);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, Message[].class);
    }

    public ResourceResponse<MessageResponse> createMessage(MessageCreateRequest request, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/messages", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(request), MEDIA_TYPE_JSON));
        return execute(builder, MessageResponse.class);
    }

    public ResourceResponse<Message> getMessage(String messageId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/messages/" + messageId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();

        return execute(builder, Message.class);
    }

    public ResourceResponse<Message> updateMessage(String messageId, JsonPatchDocument<MessagePatchModel> document, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/messages/" + messageId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .patch(RequestBody.create(makeJson(document.getOperations()), MEDIA_TYPE_JSON));
        return execute(builder, Message.class);
    }

    public ResourceResponse<MessageResponse> sendBulkMessages(List<MessageCreateRequest> messages, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/messages/batch", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(messages), MEDIA_TYPE_JSON));
        return execute(builder, MessageResponse.class);
    }

    public ResourceResponse<MessageTemplate[]> getMessageTemplates(MessageTemplatesListOptions listOptions, RequestOptions requestOptions) throws IOException {
        HttpUrl url = buildUrl("v1/message_templates", listOptions);

        Request.Builder builder = buildRequest(requestOptions)
                .url(url)
                .get();
        return execute(builder, MessageTemplate[].class);
    }

    public ResourceResponse<MessageTemplate> createMessageTemplate(MessageTemplateRequest request, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_templates", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(request), MEDIA_TYPE_JSON));
        return execute(builder, MessageTemplate.class);
    }

    public ResourceResponse<MessageTemplate> getMessageTemplate(String templateId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_templates/" + templateId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, MessageTemplate.class);
    }

    public ResourceResponse<MessageTemplate> updateMessageTemplate(String templateId, JsonPatchDocument<MessageTemplatePatchModel> document, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_templates/" + templateId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .patch(RequestBody.create(makeJson(document.getOperations()), MEDIA_TYPE_JSON));
        return execute(builder, MessageTemplate.class);
    }

    public ResourceResponse<?> deleteMessageTemplate(String templateId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_templates/" + templateId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .delete();
        return execute(builder, ResourceResponse.class);
    }

    public ResourceResponse<MessageTemplateValidationResponse> validateMessageTemplate(MessageTemplateValidationRequest request, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_templates/validate", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(request), MEDIA_TYPE_JSON));
        return execute(builder, MessageTemplateValidationResponse.class);
    }

    public ResourceResponse<MessageStream[]> getMessageStreams(MessageStreamsListOptions listOptions, RequestOptions requestOptions) throws IOException {
        HttpUrl url = buildUrl("v1/message_streams", listOptions);

        Request.Builder builder = buildRequest(requestOptions)
                .url(url)
                .get();
        return execute(builder, MessageStream[].class);
    }

    public ResourceResponse<MessageStream> createMessageStream(MessageStreamCreateRequest request, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_streams", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(request), MEDIA_TYPE_JSON));
        return execute(builder, MessageStream.class);
    }

    public ResourceResponse<MessageStream> getMessageStream(String streamId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_streams/" + streamId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, MessageStream.class);
    }

    public ResourceResponse<MessageStream> updateMessageStream(String streamId, JsonPatchDocument<MessageStreamPatchModel> document, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_streams/" + streamId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .patch(RequestBody.create(makeJson(document.getOperations()), MEDIA_TYPE_JSON));

        return execute(builder, MessageStream.class);
    }

    public ResourceResponse<?> deleteMessageStream(String streamId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_streams/" + streamId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .delete();
        return execute(builder, ResourceResponse.class);
    }

    public ResourceResponse<MessageStream> archiveMessageStream(String streamId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_streams/" + streamId + "/archive", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(null), MEDIA_TYPE_JSON));
        return execute(builder, MessageStream.class);
    }

    public ResourceResponse<MessageStream> unarchiveMessageStream(String streamId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/message_streams/" + streamId + "/unarchive", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(null), MEDIA_TYPE_JSON));
        return execute(builder, MessageStream.class);
    }
    //endregion

    //region Money Balance
    public ResourceResponse<MoneyBalance> getMoneyBalances(RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/money_balances/", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, MoneyBalance.class);
    }

    public ResourceResponse<MoneyBalance> refreshMoneyBalances(RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/money_balances/refresh", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(null), MEDIA_TYPE_JSON));
        return execute(builder, MoneyBalance.class);
    }
    //endregion

    //region Transfers, Transfer Reversals
    public ResourceResponse<Transfer[]> getTransfers(RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/transfers", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, Transfer[].class);
    }

    public ResourceResponse<Transfer> createTransfer(TransferCreateRequest request, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/transfers", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(request), MEDIA_TYPE_JSON));
        return execute(builder, Transfer.class);
    }

    public ResourceResponse<Transfer> getTransfer(String transferId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/transfers" + transferId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, Transfer.class);
    }

    public ResourceResponse<Transfer> updateTransfer(String transferId, JsonPatchDocument<TransferPatchModel> document, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/transfers" + transferId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .patch(RequestBody.create(makeJson(document.getOperations()), MEDIA_TYPE_JSON));
        return execute(builder, Transfer.class);
    }

    public ResourceResponse<TransferReversal[]> getTransferReversals(RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/transfer_reversals", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, TransferReversal[].class);
    }

    public ResourceResponse<TransferReversal> createTransferReversal(TransferReversalCreateRequest request, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/transfer_reversals", null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(request), MEDIA_TYPE_JSON));
        return execute(builder, TransferReversal.class);
    }

    public ResourceResponse<TransferReversal> updateTransferReversal(String transferId, JsonPatchDocument<TransferReversalPatchModel> document, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/transfer_reversals/" + transferId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .post(RequestBody.create(makeJson(document.getOperations()), MEDIA_TYPE_JSON));

        return execute(builder, TransferReversal.class);
    }

    public ResourceResponse<TransferReversal> getTransferReversal(String transferId, RequestOptions options) throws IOException {
        HttpUrl url = buildUrl("v1/transfer_reversals/" + transferId, null);

        Request.Builder builder = buildRequest(options)
                .url(url)
                .get();
        return execute(builder, TransferReversal.class);
    }
    //endregion

    private static Request.Builder buildRequest(RequestOptions options) {
        Request.Builder builder = new Request.Builder();

        if (options.workspace != null && !options.workspace.isEmpty()) {
            builder.header("X-Workspace-Id", options.workspace);
        }

        if (options.idempotencyKey != null && !options.idempotencyKey.isEmpty()) {
            builder.header("X-Idempotency-Key", options.idempotencyKey);
        }

        boolean live = options.live != null && options.live;
        builder.header("X-Live-Mode", String.valueOf(live));


        return builder;
    }

    private static HttpUrl buildUrl(String path, @Nullable BasicListOptions listOptions) {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme(SCHEME);
        builder.host(HOST);
        builder.addPathSegments(path);

        if (listOptions != null) {
            QueryValues args = new QueryValues();
            listOptions.populate(args);
            Map<String, String> values = args.getValues();

            values.forEach(builder::addEncodedQueryParameter);
        }

        return builder.build();
    }
}
