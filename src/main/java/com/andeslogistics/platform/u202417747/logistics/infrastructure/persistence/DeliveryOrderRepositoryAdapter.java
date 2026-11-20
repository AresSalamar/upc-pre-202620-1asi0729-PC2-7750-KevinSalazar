package com.andeslogistics.platform.u202417747.logistics.infrastructure.persistence;
import com.andeslogistics.platform.u202417747.logistics.domain.model.aggregates.DeliveryOrder;
import com.andeslogistics.platform.u202417747.logistics.domain.model.repositories.DeliveryOrderRepository;
import com.andeslogistics.platform.u202417747.logistics.domain.model.valueobjects.*;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataIntegrityViolationException;
/** Converts between aggregate and persistence entity.
 * @author Kevin Salazar
 */
@Repository
public class DeliveryOrderRepositoryAdapter implements DeliveryOrderRepository {
 private final DeliveryOrderJpaRepository jpa;
 public DeliveryOrderRepositoryAdapter(DeliveryOrderJpaRepository jpa){this.jpa=jpa;}
 @Override public boolean existsByCode(String code){return jpa.existsByCode(code);}
 @Override public DeliveryOrder save(DeliveryOrder a){
  var e=new DeliveryOrderJpaEntity(); e.id=a.id();e.code=a.code().code();e.locationId=a.locationId().value();e.startDate=a.window().startDate();e.endDate=a.window().endDate();e.quantity=a.load().quantity();e.unit=a.load().unit();e.priorityLevel=a.priorityLevel();e.status=a.status().name();e.notes=a.notes();e.createdAt=a.createdAt();e.updatedAt=a.updatedAt();
  var saved=jpa.saveAndFlush(e);
  return new DeliveryOrder(saved.id,new DeliveryCode(saved.code),new WarehouseId(saved.locationId),new DeliveryWindow(saved.startDate,saved.endDate),new PackageLoad(saved.quantity,saved.unit),saved.priorityLevel,DeliveryStatus.valueOf(saved.status),saved.notes,saved.createdAt,saved.updatedAt);
 }
}
