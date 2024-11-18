package com.phoneCodeService.usecasses.dto;

public record PhoneCodeResponseDto(
        Long id,
        String code,
        Long countryId) {
}