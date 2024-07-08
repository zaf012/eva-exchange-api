package com.eva.evaexchangeapi.controller;

import com.eva.evaexchangeapi.controller.request.UsersCreateRequest;
import com.eva.evaexchangeapi.service.base.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

  private final UserService userService;

  public UsersController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/create-user")
  public ResponseEntity<String> createUser(@Valid @RequestBody UsersCreateRequest request) {
    try {
      userService.createUser(request);
      return ResponseEntity.ok("User is created successfully");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/get-user-id-by-params/{username}/{password}")
  public String getUserIdByUsernameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password) {
    return userService.getUserIdByUsernameAndPassword(username, password);
  }
}
