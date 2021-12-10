package io.falu.models.paymentAuthorizations;

import com.google.gson.annotations.SerializedName;

/**
 * Reason for a given status of payment authorization.
 */
public enum PaymentAuthorizationReason {
    /**
     * No authorization webhook endpoint is set.
     */
    @SerializedName("default")
    DEFAULT,

    /**
     * Authorization webhook endpoint is invalid.
     */
    @SerializedName("invalid")
    INVALID,

    /**
     * Authorization webhook endpoint was used to approve or decline.ß
     */
    @SerializedName("realtime")
    REALTIME,

    /**
     * Synchronous webhook delivery to the authorization webhook endpoint failed.
     */
    @SerializedName("failed")
    FAILED,
}