package Backend.Models.ResponseModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GenerateTokenResponse{

	@JsonProperty("result")
	private String result;

	@JsonProperty("expires")
	private String expires;

	@JsonProperty("token")
	private String token;

	@JsonProperty("status")
	private String status;
}