package com.andeslogistics.platform.u202417747.logistics.domain.model.aggregates;
import com.andeslogistics.platform.u202417747.logistics.domain.model.valueobjects.*;
import java.time.Instant;
/** Domain aggregate root. No JPA annotations in domain.
 * @author Kevin Salazar
 */
public class DeliveryOrder {
 private final Long id;
 private final DeliveryCode code;
 private final WarehouseId locationId;
 private final DeliveryWindow window;
 private final PackageLoad load;
 private final Integer priorityLevel;
 private final DeliveryStatus status;
 private final String notes;
 private final Instant createdAt;
 private final Instant updatedAt;
 public DeliveryOrder(Long id,DeliveryCode code,WarehouseId locationId,DeliveryWindow window,PackageLoad load,Integer priorityLevel,DeliveryStatus status,String notes,Instant createdAt,Instant updatedAt) {
  if(priorityLevel==null||priorityLevel<1||priorityLevel>5)throw new IllegalArgumentException("Priority must be between 1 and 5");
  if(code==null||locationId==null||window==null||load==null)throw new IllegalArgumentException("Required domain value missing");
  this.id=id;this.code=code;this.locationId=locationId;this.window=window;this.load=load;this.priorityLevel=priorityLevel;this.status=status==null?DeliveryStatus.PENDING:status;this.notes=notes;this.createdAt=createdAt==null?Instant.now():createdAt;this.updatedAt=updatedAt==null?this.createdAt:updatedAt;
 }
 public Long id(){return id;} public DeliveryCode code(){return code;} public WarehouseId locationId(){return locationId;} public DeliveryWindow window(){return window;} public PackageLoad load(){return load;} public Integer priorityLevel(){return priorityLevel;} public DeliveryStatus status(){return status;} public String notes(){return notes;} public Instant createdAt(){return createdAt;} public Instant updatedAt(){return updatedAt;}
}
