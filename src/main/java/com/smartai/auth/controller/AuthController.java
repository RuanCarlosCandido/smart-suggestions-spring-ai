package com.smartai.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartai.auth.dto.AuthResponse;
import com.smartai.auth.dto.LoginRequest;
import com.smartai.auth.security.JwtService;

/**
 * Controller responsible for handling authentication-related endpoints.
 * <p>
 * Provides an endpoint for user login, authenticating credentials and returning
 * a JWT token upon success.
 * </p>
 *
 * <p>
 * Endpoints:
 * <ul>
 * <li><b>POST /auth/login</b>: Authenticates a user and returns a JWT
 * token.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Dependencies:
 * <ul>
 * <li>{@link AuthenticationManager} for authenticating user credentials.</li>
 * <li>{@link JwtService} for generating JWT tokens.</li>
 * </ul>
 * </p>
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest login) {
        var auth = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        var authentication = authManager.authenticate(auth);

        var token = jwtService.gerarToken((UserDetails) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
