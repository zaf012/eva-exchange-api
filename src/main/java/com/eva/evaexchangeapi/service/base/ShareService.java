package com.eva.evaexchangeapi.service.base;

import com.eva.evaexchangeapi.controller.request.ShareCreateRequest;
import com.eva.evaexchangeapi.controller.request.ShareUpdateRequest;
import com.eva.evaexchangeapi.domain.Share;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface ShareService {

  Share getShareByShareSymbol(String shareSymbol);

  List<Share> getAllShares();

  @Transactional
  void createShare(ShareCreateRequest request);

  @Transactional
  void updateShareData(ShareUpdateRequest request);
}
