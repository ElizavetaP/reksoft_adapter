package reksoft.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Service
@RequiredArgsConstructor
@Log4j2
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception ex) {
        log.error(ex);
        CustomErrorResponse customErrorResponse;
        if (ex.getMessage().contains("OverflowException")) {
            customErrorResponse = new CustomErrorResponse(ErrorCode.CODE_INVALID_INPUT_DATA,
                    "Arithmetic operation resulted in an overflow");
        } else {
            customErrorResponse = new CustomErrorResponse(ErrorCode.UNHANDLED_ERROR,
                    "Unhandled error. Call to support");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customErrorResponse);
    }

}
