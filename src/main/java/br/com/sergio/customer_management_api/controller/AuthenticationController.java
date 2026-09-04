package br.com.sergio.customer_management_api.controller;

import br.com.sergio.customer_management_api.dto.LoginRequestDTO;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO dto) {

        var token = new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password());

        var authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();

    }
}
