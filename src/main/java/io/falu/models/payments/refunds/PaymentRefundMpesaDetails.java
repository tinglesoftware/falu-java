package io.falu.models.payments.refunds;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;

/**
 * Details of the reversal if done via MPESA.
 */
@Data
@Getter
public class PaymentRefundMpesaDetails {
    /**
     * The target MPESA business short code.
     */
    @SerializedName("business_short_code")
    private String businessShortCode;

    /**
     * Unique identifier for request as issued by MPESA.
     * Only populated for flows that initiate the transaction instead of MPESA.
     * The value is only available after the request is sent to MPESA.
     */
    private String requestId;

    /**
     * Unique transaction identifier generated by MPESA.
     * Only populated for completed transactions.
     */
    private String receiptId;
}
