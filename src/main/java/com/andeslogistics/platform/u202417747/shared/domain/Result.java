package com.andeslogistics.platform.u202417747.shared.domain;
/** A use-case outcome without coupling the application to HTTP.
 * @author Kevin Salazar
 */
public sealed interface Result<T> permits Result.Success, Result.Failure {
 record Success<T>(T value) implements Result<T> {}
 record Failure<T>(String code, String message) implements Result<T> {}
 static <T> Result<T> success(T value) {return new Success<>(value);}
 static <T> Result<T> failure(String code,String message) {return new Failure<>(code,message);}
}
