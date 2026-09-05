package br.com.sergio.customer_management_api.controller;

import br.com.sergio.customer_management_api.config.security.TokenService;
import br.com.sergio.customer_management_api.database.entity.User;
import br.com.sergio.customer_management_api.dto.LoginRequestDTO;
import br.com.sergio.customer_management_api.dto.TokenResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO dto) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password());

        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken(
                (User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(tokenJWT));
    }
}