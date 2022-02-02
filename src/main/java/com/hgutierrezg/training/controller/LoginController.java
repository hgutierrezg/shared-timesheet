package com.hgutierrezg.training.controller;

import com.hgutierrezg.training.dto.CredentialsDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> login(@RequestBody CredentialsDto credentialsDto) {

        // Always return success if the CredentialsDto validations pass
        return ResponseEntity.ok().build();
    }
}