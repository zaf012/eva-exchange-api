package com.eva.evaexchangeapi.controller;

import com.eva.evaexchangeapi.controller.request.ShareCreateRequest;
import com.eva.evaexchangeapi.controller.request.ShareUpdateRequest;
import com.eva.evaexchangeapi.domain.Share;
import com.eva.evaexchangeapi.service.base.ShareService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shares")
public class ShareController {

  private final ShareService shareService;

  public ShareController(ShareService shareService) {
    this.shareService = shareService;
  }

  @PostMapping("/create")
  public ResponseEntity<String> createShare(@Valid @RequestBody ShareCreateRequest request) {
    try {
      shareService.createShare(request);
      return ResponseEntity.ok("Share is created successfully");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateShare(@Valid @RequestBody ShareUpdateRequest request) {
    try {
      shareService.updateShareData(request);
      return ResponseEntity.ok("Share is updated successfully");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/get-all-shares")
  public List<Share> getAllShares() {
    return shareService.getAllShares();
  }

  @GetMapping("/get-share-by-symbol/{symbol}")
  public Share getSharesBySymbol(@PathVariable("symbol") String symbol) {
    return shareService.getShareByShareSymbol(symbol);
  }


}

