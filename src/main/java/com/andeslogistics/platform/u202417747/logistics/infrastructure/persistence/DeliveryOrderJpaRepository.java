package com.andeslogistics.platform.u202417747.logistics.infrastructure.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @author Kevin Salazar
 */
public interface DeliveryOrderJpaRepository extends JpaRepository<DeliveryOrderJpaEntity,Long> {boolean existsByCode(String code);}
