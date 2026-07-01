package com.dhruvil.product_service.globalException;

import com.dhruvil.product_service.customException.ProductIsAlreadyExistException;
import com.dhruvil.product_service.customException.ProductNotFoundException;
import com.dhruvil.product_service.dto.ErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorsResponse> productNotFoundException(ProductNotFoundException ex, WebRequest request){

        ErrorsResponse errorsResponse = new ErrorsResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=","")

        );

        return new ResponseEntity<>(errorsResponse,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ProductIsAlreadyExistException.class)
    public ResponseEntity<ErrorsResponse> productIsAlreadyExistException(ProductIsAlreadyExistException ex , WebRequest request){

        ErrorsResponse errorsResponse = new ErrorsResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorsResponse,HttpStatus.CONFLICT);
    }
}
