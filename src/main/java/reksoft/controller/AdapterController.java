package reksoft.controller;

import org.tempuri.CalculatorSoap;
import reksoft.dto.CalculatorToInputDto;
import reksoft.dto.CalculatorToOutputDto;
import reksoft.exception.CustomErrorResponse;
import reksoft.exception.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tempuri.Calculator;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@Api(tags = "adapter")
@RequestMapping("/v1.0/adapter")
@RequiredArgsConstructor
@Log4j2
public class AdapterController {

    @ApiOperation(value = "Sum of two numbers")
    @PostMapping("/add")
    @ApiResponse(responseCode = "200", description = "Sum of two numbers")
    @ApiResponse(responseCode = "400", description = "Client error\n" +
            " Possible error codes:\n" +
            " <ul>" +
            " <li>" + ErrorCode.CODE_INVALID_INPUT_DATA + ": Invalid input data</li>\n" +
            "</ul>", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CustomErrorResponse.class)
    ))
    public CalculatorToOutputDto addTwoIntegers(
            @ApiParam(value = "DTO for calculation", required = true)
            @RequestBody
                    CalculatorToInputDto inputValues) throws MalformedURLException {

        Integer result = getCalculatorSoap().add(inputValues.getIntA(), inputValues.getIntB());

        return new CalculatorToOutputDto(result);
    }

    @ApiOperation(value = "Dividing intA by intB")
    @PostMapping("/divide")
    @ApiResponse(responseCode = "200", description = "Result of dividing")
    @ApiResponse(responseCode = "400", description = "Client error\n" +
            " Possible error codes:\n" +
            " <ul>" +
            " <li>" + ErrorCode.CODE_INVALID_INPUT_DATA + ": Invalid input data</li>\n" +
            "</ul>", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CustomErrorResponse.class)
    ))
    public CalculatorToOutputDto divideTwoIntegers(
            @ApiParam(value = "DTO for calculation", required = true)
            @RequestBody
                    CalculatorToInputDto inputValues) throws MalformedURLException {
        Integer result = getCalculatorSoap().divide(inputValues.getIntA(), inputValues.getIntB());

        return new CalculatorToOutputDto(result);
    }

    @ApiOperation(value = "Multiply of two numbers")
    @PostMapping("/multiply")
    @ApiResponse(responseCode = "200", description = "Result of multiply")
    @ApiResponse(responseCode = "400", description = "Client error\n" +
            " Possible error codes:\n" +
            " <ul>" +
            " <li>" + ErrorCode.CODE_INVALID_INPUT_DATA + ": Invalid input data</li>\n" +
            "</ul>", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CustomErrorResponse.class)
    ))
    public CalculatorToOutputDto multiplyTwoIntegers(
            @ApiParam(value = "DTO for calculation", required = true)
            @RequestBody
                    CalculatorToInputDto inputValues) throws MalformedURLException {
        Integer result = getCalculatorSoap().multiply(inputValues.getIntA(), inputValues.getIntB());

        return new CalculatorToOutputDto(result);
    }

    @ApiOperation(value = "Subtracting intB from intA")
    @PostMapping("/subtract")
    @ApiResponse(responseCode = "200", description = "Result of subtracting")
    @ApiResponse(responseCode = "400", description = "Client error\n" +
            " Possible error codes:\n" +
            " <ul>" +
            " <li>" + ErrorCode.CODE_INVALID_INPUT_DATA + ": Invalid input data</li>\n" +
            "</ul>", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CustomErrorResponse.class)
    ))
    public CalculatorToOutputDto subtractTwoIntegers(
            @ApiParam(value = "DTO for calculation", required = true)
            @RequestBody
                    CalculatorToInputDto inputValues) throws MalformedURLException {
        Integer result = getCalculatorSoap().subtract(inputValues.getIntA(), inputValues.getIntB());

        return new CalculatorToOutputDto(result);
    }

    private CalculatorSoap getCalculatorSoap() throws MalformedURLException {
        Calculator client = new Calculator(new URL("http://www.dneonline.com/calculator.asmx?WSDL"));
        return client.getCalculatorSoap();
    }
}
