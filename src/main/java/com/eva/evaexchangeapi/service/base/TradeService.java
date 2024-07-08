package com.eva.evaexchangeapi.service.base;

import com.eva.evaexchangeapi.controller.request.TradeRequest;

public interface TradeService {

  void executeTrade(TradeRequest tradeRequest);
}
