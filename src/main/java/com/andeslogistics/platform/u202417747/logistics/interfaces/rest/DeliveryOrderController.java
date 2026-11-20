package com.andeslogistics.platform.u202417747.logistics.interfaces.rest;
import com.andeslogistics.platform.u202417747.shared.domain.Result;
import com.andeslogistics.platform.u202417747.shared.infrastructure.ApiExceptionHandler;
import com.andeslogistics.platform.u202417747.logistics.application.CreateDeliveryOrderService;
import com.andeslogistics.platform.u202417747.logistics.domain.model.aggregates.DeliveryOrder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Locale;
import java.util.Map;
/** Only the create endpoint required by the sample assignment.
 * @author Kevin Salazar
 */
@RestController @RequestMapping("/api/v1/delivery-orders")
public class DeliveryOrderController {
 private final CreateDeliveryOrderService service;
 private final ApiExceptionHandler errors;
 public DeliveryOrderController(CreateDeliveryOrderService service,ApiExceptionHandler errors) {this.service=service;this.errors=errors;}
 @PostMapping
 public ResponseEntity<?> create(@Valid @RequestBody CreateDeliveryOrderRequest request,Locale locale){
   try{
    Result<DeliveryOrder> result=service.handle(DeliveryOrderAssembler.command(request));
    if(result instanceof Result.Success<DeliveryOrder> ok) {
      DeliveryOrderResource resource=DeliveryOrderAssembler.resource(ok.value());
      return ResponseEntity.created(URI.create("/api/v1/delivery-orders/"+resource.id())).body(resource);
    }
    var failure=(Result.Failure<DeliveryOrder>)result;
    int status=failure.code().equals("duplicate")?409:400;
    return ResponseEntity.status(status).body(Map.of("message",errors.text(status==409?"error.duplicate":"error.invalid",locale)));
   }catch(DataIntegrityViolationException ex){
    return ResponseEntity.status(409).body(Map.of("message",errors.text("error.duplicate",locale)));
   }
 }
}
