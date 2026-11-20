package com.andeslogistics.platform.u202417747.logistics.domain.model.valueobjects;
import java.time.LocalDate;
/** Inclusive date window; end must be strictly after start.
 * @author Kevin Salazar
 */
public record DeliveryWindow(LocalDate startDate, LocalDate endDate) {
 public DeliveryWindow {if(startDate==null||endDate==null||!endDate.isAfter(startDate)) throw new IllegalArgumentException("Invalid date window");}
 public boolean includes(LocalDate checkDate) {if(checkDate==null)throw new IllegalArgumentException("Check date is required");return !checkDate.isBefore(startDate)&&!checkDate.isAfter(endDate);}
}
