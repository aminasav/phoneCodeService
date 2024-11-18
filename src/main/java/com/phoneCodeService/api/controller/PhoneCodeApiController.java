package com.phoneCodeService.api.controller;

import com.phoneCodeService.usecasses.PhoneCodeService;
import com.phoneCodeService.usecasses.dto.PhoneCodeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/phone-codes")
public class PhoneCodeApiController {
    private final PhoneCodeService phoneCodeService;

    @GetMapping("/{id}")
    public ResponseEntity<PhoneCodeResponseDto> getPhoneCodeById(@PathVariable Long id) {
        return new ResponseEntity<>(phoneCodeService.getPhoneCodeById(id), HttpStatus.OK);
    }
}
