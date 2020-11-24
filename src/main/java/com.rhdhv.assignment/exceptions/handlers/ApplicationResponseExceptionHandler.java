package com.rhdhv.assignment.exceptions.handlers;

import com.rhdhv.assignment.exceptions.error.ApplicationCustomError;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller Advice for different Errors and their response.
 */
@Slf4j
@ControllerAdvice
public class ApplicationResponseExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      final HttpMessageNotReadableException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    log.error("Bad request", ex);
    return new ResponseEntity<>(
        new ApplicationCustomError("Invalid Car details", 400), HttpStatus.BAD_REQUEST);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    log.error("Bad request", ex);
    return new ResponseEntity<>(
        new ApplicationCustomError("Invalid Car details", 400), HttpStatus.BAD_REQUEST);
  }

  /**
   * {@link ApplicationCustomError} provided for a Constraint Violation Error
   *
   * @param ex {@link ConstraintViolationException} exception
   * @return {@link ResponseEntity} for {@link ApplicationCustomError}
   */
  @ExceptionHandler(value = {ConstraintViolationException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<ApplicationCustomError> handleBadInput(ConstraintViolationException ex) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    ConstraintViolation<?> violation =
        ex.getConstraintViolations().stream().findFirst().orElse(null);
    ApplicationCustomError error = new ApplicationCustomError(violation.getMessage(),
        HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
  }

  /**
   * {@link ApplicationCustomError} provided for any exception with a message
   *
   * @param ex {@link Exception} exception
   * @return {@link ResponseEntity} for {@link ApplicationCustomError}
   */
  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ResponseEntity<ApplicationCustomError> handleError(Exception ex) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    ApplicationCustomError error = new ApplicationCustomError(
        "Internal Server Error. Please try again later",
        HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(error, headers, HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
