package com.chrisgya.springjpagraddle.controller;

import com.chrisgya.springjpagraddle.UserApi;
import com.chrisgya.springjpagraddle.entity.UserEntity;
import com.chrisgya.springjpagraddle.exception.InvalidRefreshTokenException;
import com.chrisgya.springjpagraddle.model.RefreshToken;
import com.chrisgya.springjpagraddle.model.SignInReq;
import com.chrisgya.springjpagraddle.model.SignedInUser;
import com.chrisgya.springjpagraddle.model.User;
import com.chrisgya.springjpagraddle.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.*;


@RestController
public class AuthController implements UserApi {

  private final UserService service;
  private final PasswordEncoder passwordEncoder;

  public AuthController(UserService service, PasswordEncoder passwordEncoder) {
    this.service = service;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public ResponseEntity<SignedInUser> getAccessToken(@Valid RefreshToken refreshToken) {
    return ok(service.getAccessToken(refreshToken).orElseThrow(InvalidRefreshTokenException::new));
  }

  @Override
  public ResponseEntity<SignedInUser> signIn(@Valid SignInReq signInReq) {
    UserEntity userEntity = service.findUserByUsername(signInReq.getUsername());
    if (passwordEncoder.matches(signInReq.getPassword(), userEntity.getPassword())) {
      return ok(service.getSignedInUser(userEntity));
    }
    throw new InsufficientAuthenticationException("Unauthorized.");
  }

  @Override
  public ResponseEntity<Void> signOut(@Valid RefreshToken refreshToken) {
    // We are using removeToken API for signout.
    // Ideally you would like to get tgit she user ID from Logged in user's request
    // and remove the refresh token based on retrieved user id from request.
    service.removeRefreshToken(refreshToken);
    return accepted().build();
  }

  @Override
  public ResponseEntity<SignedInUser> signUp(@Valid User user) {
    // Have a validation for all required fields.
    return status(HttpStatus.CREATED).body(service.createUser(user).get());
  }
}
