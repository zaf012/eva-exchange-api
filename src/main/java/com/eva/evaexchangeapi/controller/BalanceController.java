package com.eva.evaexchangeapi.controller;

import com.eva.evaexchangeapi.controller.request.BalanceRequest;
import com.eva.evaexchangeapi.service.base.BalanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

  private final BalanceService balanceService;

  public BalanceController(BalanceService balanceService) {
    this.balanceService = balanceService;
  }

  @PostMapping("/add-deposit")
  public ResponseEntity<String> addDeposit(@Valid @RequestBody BalanceRequest request) {
    try {
      balanceService.addDeposit(request);
      return ResponseEntity.ok("Balance is updated successfully");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/draw-out")
  public ResponseEntity<String> drawOut(@Valid @RequestBody BalanceRequest request) {
    try {
      balanceService.drawOut(request);
      return ResponseEntity.ok("Balance is updated successfully");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


}

