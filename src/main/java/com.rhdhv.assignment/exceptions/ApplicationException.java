package com.rhdhv.assignment.exceptions;

/**
 * Application Exception to be wrapped.
 */
public class ApplicationException extends RuntimeException {

  private static final long serialVersionUID = 5007482431025885013L;

  /**
   * Constructor for {@link ApplicationException}
   */
  public ApplicationException() {
    super();
  }

  /**
   * Constructor with message
   *
   * @param message {@link String}
   */
  public ApplicationException(String message) {
    super(message);
  }

  /**
   * Constructor with message
   *
   * @param message {@link String}
   * @param cause   {@link Throwable}
   */
  public ApplicationException(String message, Throwable cause) {
    super(message, cause);
  }
}
