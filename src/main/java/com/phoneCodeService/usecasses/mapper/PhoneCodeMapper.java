package com.phoneCodeService.usecasses.mapper;

import com.phoneCodeService.persistence.model.PhoneCode;
import com.phoneCodeService.usecasses.dto.PhoneCodeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneCodeMapper {
    PhoneCodeResponseDto toResponseDto(PhoneCode phoneCode);
}
