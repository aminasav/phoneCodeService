package com.phoneCodeService.usecasses.impl;

import com.phoneCodeService.persistence.PhoneCodeRepository;
import com.phoneCodeService.persistence.model.PhoneCode;
import com.phoneCodeService.usecasses.PhoneCodeService;
import com.phoneCodeService.usecasses.dto.PhoneCodeResponseDto;
import com.phoneCodeService.usecasses.mapper.PhoneCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneCodeServiceImpl implements PhoneCodeService {

    private final PhoneCodeRepository phoneCodeRepository;
    private final PhoneCodeMapper phoneCodeMapper;

    @Override
    public PhoneCodeResponseDto getPhoneCodeById(Long id) {
        PhoneCode phoneCode = phoneCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone code not found with id: " + id));
        return phoneCodeMapper.toResponseDto(phoneCode);
    }
}
