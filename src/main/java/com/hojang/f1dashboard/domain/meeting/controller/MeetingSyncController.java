package com.hojang.f1dashboard.domain.meeting.controller;

import com.hojang.f1dashboard.domain.meeting.service.MeetingSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync/meetings")
@RequiredArgsConstructor
public class MeetingSyncController {

    private final MeetingSyncService meetingSyncService;

    // 호출 주소: POST http://localhost:8080/api/sync/meetings?year=2024
    @PostMapping
    public String syncMeetings(@RequestParam(defaultValue = "2024") int year) {
        int count = meetingSyncService.syncMeetings(year);
        return String.format("%d년도 경기 일정 %d개 동기화 완료!", year, count);
    }
}