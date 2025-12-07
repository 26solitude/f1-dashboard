package com.hojang.f1dashboard.domain.driver.controller;

import com.hojang.f1dashboard.domain.driver.service.DriverSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync/drivers")
@RequiredArgsConstructor
public class DriverSyncController {

    private final DriverSyncService driverSyncService;

    // 호출 예시: POST http://localhost:8080/api/sync/drivers?session_key=9158
    @PostMapping
    public String syncDrivers(@RequestParam("session_key") int sessionKey) {
        driverSyncService.syncDrivers(sessionKey);
        return "세션(" + sessionKey + ")의 드라이버 정보 동기화가 완료되었습니다.";
    }
}