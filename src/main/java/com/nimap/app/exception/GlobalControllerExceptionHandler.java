package com.nimap.app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * The logger.
     */
    private static Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    /**
     * Handle conflict.
     *
     * @param serviceException the rest call exception
     * @return the response entity
     */
    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity<Map<String, Object>> handleConflict(ServiceException serviceException) {
        Integer errorCode = 0;
        Map<String, Object> errorMap = new HashMap<String, Object>();

        errorMap.put(MsgConstant.MSG_CODE, serviceException.getMessageCode());
        errorMap.put(MsgConstant.MSG, serviceException.getMessage());

        LOG.debug("Throwing exception with error object: {}", errorMap.toString());

        return new ResponseEntity<Map<String, Object>>(errorMap,
                serviceException.getStatus() != null ? serviceException.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle validation exceptions.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
    }
}
