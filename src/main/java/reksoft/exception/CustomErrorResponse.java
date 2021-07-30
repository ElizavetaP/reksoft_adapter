package reksoft.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Custom error response")
@Component
public class CustomErrorResponse {
    @ApiModelProperty(value = "Error code", required = true)
    private Long code;
    @ApiModelProperty(value = "Error message", required = true)
    private String message;
}
