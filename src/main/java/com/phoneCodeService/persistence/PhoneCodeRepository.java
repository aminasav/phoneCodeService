package com.phoneCodeService.persistence;

import com.phoneCodeService.persistence.model.PhoneCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneCodeRepository extends JpaRepository<PhoneCode, Long> {
}
