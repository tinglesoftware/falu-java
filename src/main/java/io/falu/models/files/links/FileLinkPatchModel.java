package io.falu.models.files.links;

import com.google.gson.annotations.JsonAdapter;
import io.falu.client.adapters.ISO8601DateAdapter;
import io.falu.models.core.AbstractCreationRequest;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * A model representing details that can be changed about a file link.
 */
@NoArgsConstructor
@SuperBuilder
public class FileLinkPatchModel extends AbstractCreationRequest {
    /**
     * Time at which the link expires
     */
    @JsonAdapter(ISO8601DateAdapter.class)
    public Date expires;
}
