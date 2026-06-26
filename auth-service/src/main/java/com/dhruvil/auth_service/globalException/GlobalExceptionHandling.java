package com.dhruvil.auth_service.globalException;

import com.dhruvil.auth_service.customExceptions.InvalidCredentialException;
import com.dhruvil.auth_service.customExceptions.UserIsAlreadyExistException;
import com.dhruvil.auth_service.customExceptions.UserIsNotFoundException;
import com.dhruvil.auth_service.dto.ErrorsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(UserIsAlreadyExistException.class)
    public ResponseEntity<ErrorsResponse> userIsAlreadyExistException(UserIsAlreadyExistException ex, WebRequest request) {

        ErrorsResponse errorResponse = new ErrorsResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(UserIsNotFoundException.class)
    public ResponseEntity<ErrorsResponse> userIsNotFoundException(UserIsNotFoundException ex, WebRequest request){

        ErrorsResponse errorsResponse = new ErrorsResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=","")
        );

        return new ResponseEntity<>(errorsResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<ErrorsResponse> invalidCredentialsException(InvalidCredentialException ex, WebRequest request){

        ErrorsResponse errorsResponse = new ErrorsResponse(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
      return new ResponseEntity<>(errorsResponse, HttpStatus.UNAUTHORIZED);
    }
}
