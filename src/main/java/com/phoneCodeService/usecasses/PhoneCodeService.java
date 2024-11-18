package com.phoneCodeService.usecasses;

import com.phoneCodeService.usecasses.dto.PhoneCodeResponseDto;

public interface PhoneCodeService {
    PhoneCodeResponseDto getPhoneCodeById(Long id);
}
