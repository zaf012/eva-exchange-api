package com.eva.evaexchangeapi.service;

import com.eva.evaexchangeapi.controller.request.ShareCreateRequest;
import com.eva.evaexchangeapi.controller.request.ShareUpdateRequest;
import com.eva.evaexchangeapi.domain.Share;
import com.eva.evaexchangeapi.domain.Users;
import com.eva.evaexchangeapi.enums.UserType;
import com.eva.evaexchangeapi.repository.ShareRepository;
import com.eva.evaexchangeapi.service.base.ShareService;
import com.eva.evaexchangeapi.service.base.UserService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShareServiceImpl implements ShareService {

  private final ShareRepository shareRepository;
  private final UserService userService;

  public ShareServiceImpl(ShareRepository shareRepository, UserService userService) {
    this.shareRepository = shareRepository;
    this.userService = userService;
  }

  @Override
  public Share getShareByShareSymbol(String shareSymbol) {

    return shareRepository.findBySymbol(shareSymbol)
        .orElseThrow(() -> new IllegalArgumentException("Share not found !"));

  }

  @Override
  public List<Share> getAllShares() {
    return shareRepository.findAll();
  }

  @Transactional
  @Override
  public void createShare(ShareCreateRequest request) {
    Users userById = userService.findUserById(request.getUserId());

    if (UserType.TRADER.getValue().equals(userById.getUserType())) {
      throw new RuntimeException("Trader is not allowed to create share");
    }

    Share share = shareRepository.findBySymbol(request.getShareSymbol()).orElse(new Share());
    share.setSymbol(request.getShareSymbol());
    share.setPrice(request.getRate());
    share.setUpdateAt(LocalDateTime.now());
    share.setTotalShareQuantity(request.getTotalShareQuantity());
    share.setExchangeCurrency(request.getExchangeCurrency());
    shareRepository.save(share);

  }

  @Transactional
  @Override
  public void updateShareData(ShareUpdateRequest request) {
    Users userById = userService.findUserById(request.getUserId());

    if (UserType.TRADER.getValue().equals(userById.getUserType())) {
      throw new RuntimeException("Trader is not allowed to update share data");
    }

    Share shareByShareSymbol = this.getShareByShareSymbol(request.getShareSymbol());

    if (LocalDateTime.now().minusHours(1).isBefore(shareByShareSymbol.getUpdateAt())) {
      throw new RuntimeException("Share rates allow to update an hourly basis !");
    }

    shareByShareSymbol.setPrice(request.getRate());
    shareByShareSymbol.setUpdateAt(LocalDateTime.now());

    shareRepository.save(shareByShareSymbol);

  }
}
