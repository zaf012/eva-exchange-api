package com.eva.evaexchangeapi.controller;

import com.eva.evaexchangeapi.domain.UserShare;
import com.eva.evaexchangeapi.service.base.PortfolioService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

  private final PortfolioService portfolioService;

  public PortfolioController(PortfolioService portfolioService) {
    this.portfolioService = portfolioService;
  }


  @GetMapping("/get-all-user-shares/{userId}")
  public List<UserShare> getAllUserShares(@PathVariable("userId") String userId) {
    return portfolioService.getAllUserSharesByUserId(userId);

  }
}

