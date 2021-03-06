package io.falu.models.identity;

import com.google.gson.annotations.JsonAdapter;
import io.falu.client.adapters.ISO8601DateAdapter;
import io.falu.models.core.FaluModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class MarketingResult extends FaluModel {
    /**
     * Unique identifier for the object.
     */
    private String id;

    /**
     * Three-letter ISO country code, in lowercase, where the record exists.
     */
    private String country;

    /**
     * The date of birth of the entity if specified.
     * This value is not guaranteed to be availed.
     * For entities that are not human (e.g. companies, businesses, organizations, this value represents the date of registration or incorporation.
     */
    @JsonAdapter(ISO8601DateAdapter.class)
    private Date birthday;

    /**
     * Phone numbers attached to the identity.
     */
    private String[] phones;

    /**
     * Email addresses attached to the identity.
     */
    private String[] emails;

    /**
     * The gender of the entity.
     * This value may be fixed or predicted.
     * This value may be fixed or predicted.
     * When predicted "GenderConfidence" will have a value.
     */
    private Gender gender;
}
