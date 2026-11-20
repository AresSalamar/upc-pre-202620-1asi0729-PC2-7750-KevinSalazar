package com.andeslogistics.platform.u202417747.logistics.interfaces.rest;
import com.andeslogistics.platform.u202417747.logistics.domain.model.commands.CreateDeliveryOrderCommand;
import com.andeslogistics.platform.u202417747.logistics.domain.model.aggregates.DeliveryOrder;
/** Maps API DTOs to commands and domain models to resources.
 * @author Kevin Salazar
 */
public final class DeliveryOrderAssembler {
 private DeliveryOrderAssembler(){}
 public static CreateDeliveryOrderCommand command(CreateDeliveryOrderRequest r) {return new CreateDeliveryOrderCommand(r.deliveryCode(),r.warehouseId(),r.deliveryWindowStartDate(),r.deliveryWindowEndDate(),r.packageLoadQuantity(),r.packageLoadUnit(),r.priorityLevel(),r.status(),r.deliveryNotes());}
 public static DeliveryOrderResource resource(DeliveryOrder a){return new DeliveryOrderResource(a.id(),a.code().code(),a.locationId().value().toString(),a.window().startDate().toString(),a.window().endDate().toString(),a.load().quantity(),a.load().unit(),a.priorityLevel(),a.status().name(),a.notes());}
}
