package com.hojang.f1dashboard.domain.driver.service;

import com.hojang.f1dashboard.client.OpenF1Client;
import com.hojang.f1dashboard.domain.driver.entity.Driver;
import com.hojang.f1dashboard.domain.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverSyncService {

    private final OpenF1Client openF1Client;
    private final DriverRepository driverRepository;

    /**
     * 특정 세션의 드라이버 정보를 동기화합니다.
     * (이미 있는 드라이버면 정보 업데이트, 없으면 신규 생성)
     */
    @Transactional
    public void syncDrivers(int sessionKey) {
        // 1. API 호출
        List<OpenF1Client.DriverResponse> responses = openF1Client.getDrivers(sessionKey);

        if (responses == null || responses.isEmpty()) {
            return;
        }

        // 2. DTO -> Entity 변환
        List<Driver> drivers = responses.stream()
                .map(dto -> Driver.builder()
                        .driverNumber(dto.driverNumber())
                        .nameAcronym(dto.nameAcronym())
                        .fullName(dto.fullName())
                        .teamName(dto.teamName())
                        .teamColour(dto.teamColour())
                        .headshotUrl(dto.headshotUrl())
                        .countryCode(dto.countryCode())
                        .build())
                .toList();

        // 3. DB 저장
        driverRepository.saveAll(drivers);

        log.info("Synced {} drivers for session {}", drivers.size(), sessionKey);
    }
}