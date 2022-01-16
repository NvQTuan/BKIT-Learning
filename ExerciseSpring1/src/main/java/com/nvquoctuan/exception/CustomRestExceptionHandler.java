package com.nvquoctuan.exception;

import com.nvquoctuan.dto.CustomErrorResponseDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    final BindingResult bindingResult = ex.getBindingResult();
    List<String> errorMessage = new ArrayList<>();
    bindingResult.getAllErrors().forEach(objectError -> errorMessage.add(objectError.getDefaultMessage()));
    CustomErrorResponseDto errorResponseDto = new CustomErrorResponseDto(HttpStatus.BAD_REQUEST,
        errorMessage.toString());
    return new ResponseEntity<>(errorResponseDto, new HttpHeaders(), errorResponseDto.getStatus());
  }
}
