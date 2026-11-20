package com.andeslogistics.platform.u202417747.logistics.application;
import com.andeslogistics.platform.u202417747.shared.domain.Result;
import com.andeslogistics.platform.u202417747.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202417747.logistics.domain.model.commands.CreateDeliveryOrderCommand;
import com.andeslogistics.platform.u202417747.logistics.domain.model.repositories.DeliveryOrderRepository;
import com.andeslogistics.platform.u202417747.logistics.domain.model.valueobjects.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/** Create use case; reports business failures via Result.
 * @author Kevin Salazar
 */
@Service
public class CreateDeliveryOrderService {
 private final DeliveryOrderRepository repository;
 public CreateDeliveryOrderService(DeliveryOrderRepository repository){this.repository=repository;}
 @Transactional
 public Result<DeliveryOrder> handle(CreateDeliveryOrderCommand command) {
  if(command==null) return Result.failure("invalid","Command is required");
  try {
   var code=new DeliveryCode(command.deliveryCode());
   if(repository.existsByCode(code.code()))return Result.failure("duplicate","Code already exists");
   var location=new WarehouseId(command.warehouseId());
   var window=new DeliveryWindow(command.deliveryWindowStartDate(),command.deliveryWindowEndDate());
   var load=new PackageLoad(command.packageLoadQuantity(),command.packageLoadUnit());
   DeliveryStatus state=(command.status()==null)?DeliveryStatus.PENDING:DeliveryStatus.valueOf(command.status());
   var aggregate=new DeliveryOrder(null,code,location,window,load,command.priorityLevel(),state,command.deliveryNotes(),null,null);
   return Result.success(repository.save(aggregate));
  }catch(IllegalArgumentException ex){return Result.failure("invalid",ex.getMessage());}
 }
}
