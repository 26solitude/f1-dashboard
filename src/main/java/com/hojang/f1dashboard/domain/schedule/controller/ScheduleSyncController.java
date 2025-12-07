package com.hojang.f1dashboard.domain.schedule.controller;

import com.hojang.f1dashboard.domain.schedule.service.ScheduleSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class ScheduleSyncController {

    private final ScheduleSyncService scheduleSyncService;

    // 호출 주소: POST http://localhost:8080/api/sync/meetings?year=2024
    @PostMapping("/meetings")
    public String syncMeetings(@RequestParam(defaultValue = "2024") int year) {
        scheduleSyncService.syncMeetings(year);
        return year + "년 경기 일정 수집이 완료되었습니다.";
    }
}