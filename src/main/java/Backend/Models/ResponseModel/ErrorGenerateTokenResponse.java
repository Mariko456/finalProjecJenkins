package Backend.Models.ResponseModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorGenerateTokenResponse (@JsonProperty("code") String code,
                                     @JsonProperty ("message") String message) {
}