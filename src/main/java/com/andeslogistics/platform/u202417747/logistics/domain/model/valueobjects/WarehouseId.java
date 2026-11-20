package com.andeslogistics.platform.u202417747.logistics.domain.model.valueobjects;
import java.util.UUID;
/** External bounded-context identifier.
 * @author Kevin Salazar
 */
public record WarehouseId(UUID value) {public WarehouseId {if(value==null) throw new IllegalArgumentException("Identifier is required");}}
