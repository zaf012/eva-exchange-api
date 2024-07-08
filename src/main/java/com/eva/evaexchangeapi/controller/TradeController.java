package com.eva.evaexchangeapi.controller;

import com.eva.evaexchangeapi.controller.request.TradeRequest;
import com.eva.evaexchangeapi.service.base.TradeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

  private final TradeService tradeService;

  public TradeController(TradeService tradeService) {
    this.tradeService = tradeService;
  }

  @PostMapping("/execute-trade")
  public ResponseEntity<String> executeTrade(@Valid @RequestBody TradeRequest request) {
    try {
      tradeService.executeTrade(request);
      return ResponseEntity.ok("Trade executed successfully");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
