package fr.kevpdev.swapi.exceptions;

import java.util.Date;
import lombok.Data;

@Data
public class ErrorDetail {

  private Date timestamp;
  private int statusCode;
  private String message;

  public ErrorDetail(int statusCode, String message) {
    this.timestamp = new Date();
    this.statusCode = statusCode;
    this.message = message;
  }
}
