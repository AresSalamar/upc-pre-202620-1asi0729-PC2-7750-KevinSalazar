package com.andeslogistics.platform.u202417747.logistics.domain.model.valueobjects;
/** Validated immutable business code.
 * @author Kevin Salazar
 */
public record DeliveryCode(String code) {
 public DeliveryCode {if(code==null||code.isBlank()) throw new IllegalArgumentException("Code cannot be blank"); code=code.trim();}
}
