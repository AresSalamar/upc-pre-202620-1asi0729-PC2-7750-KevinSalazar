package com.andeslogistics.platform.u202417747.logistics.domain.model.repositories;
import com.andeslogistics.platform.u202417747.logistics.domain.model.aggregates.DeliveryOrder;
/**
 * @author Kevin Salazar
 */
public interface DeliveryOrderRepository {boolean existsByCode(String code);DeliveryOrder save(DeliveryOrder aggregate);}
