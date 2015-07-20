package org.apache.usergrid.java.client.exception;

/**
 * Created by ApigeeCorporation on 7/20/15.
 */
public class ApiException extends Exception {
  public ApiException() {
  }

  public ApiException(String message) {
    super(message);
  }

  public ApiException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApiException(Throwable cause) {
    super(cause);
  }
}
