package reksoft.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "DTO for adds two integers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculatorToInputDto {

    @ApiModelProperty(notes = "integer intA", example = "23", required = true)
    @NotNull(message = "intA must be not null")
    private Integer intA;

    @ApiModelProperty(notes = "integer intB", example = "-4", required = true)
    @NotNull(message = "intB must be not null")
    private Integer intB;

}
