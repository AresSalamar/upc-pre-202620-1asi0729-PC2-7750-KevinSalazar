package com.andeslogistics.platform.u202417747.logistics.infrastructure.persistence;
import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
/** Persistence-only representation of the aggregate.
 * @author Kevin Salazar
 */
@Entity @Table(name="delivery_orders",uniqueConstraints=@UniqueConstraint(name="uk_delivery_orders_code",columnNames="delivery_code"))
public class DeliveryOrderJpaEntity {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 @Column(name="delivery_code",nullable=false,unique=true) public String code;
 @Column(name="warehouse_id",nullable=false) public UUID locationId;
 @Column(name="window_start_date",nullable=false) public LocalDate startDate;
 @Column(name="window_end_date",nullable=false) public LocalDate endDate;
 @Column(name="package_load_quantity",nullable=false) public Integer quantity;
 @Column(name="package_load_unit",nullable=false) public String unit;
 @Column(name="priority_level",nullable=false) public Integer priorityLevel;
 @Column(name="status",nullable=false) public String status;
 @Column(name="delivery_notes") public String notes;
 @Column(name="created_at",nullable=false) public Instant createdAt;
 @Column(name="updated_at",nullable=false) public Instant updatedAt;
}
