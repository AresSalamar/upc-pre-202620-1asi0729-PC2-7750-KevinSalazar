package com.andeslogistics.platform.u202417747.shared.infrastructure;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;
import java.util.Map;
/** Global JSON error mapper with English/Spanish localization.
 * @author Kevin Salazar
 */
@RestControllerAdvice
public class ApiExceptionHandler {
 private final MessageSource messages;
 public ApiExceptionHandler(MessageSource messages) {this.messages=messages;}
 public String text(String code, Locale locale) {return messages.getMessage(code,null,code,locale);}
 @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
 public ResponseEntity<Map<String,String>> badRequest(Exception ex,Locale locale) {
   return ResponseEntity.badRequest().body(Map.of("message",text("error.invalid",locale)));
 }
 @ExceptionHandler(Exception.class)
 public ResponseEntity<Map<String,String>> unexpected(Exception ex,Locale locale) {
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message",text("error.internal",locale)));
 }
}
