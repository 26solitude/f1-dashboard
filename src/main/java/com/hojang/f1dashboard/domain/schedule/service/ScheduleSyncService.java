package com.hojang.f1dashboard.domain.schedule.service;

import com.hojang.f1dashboard.client.OpenF1Client;
import com.hojang.f1dashboard.domain.schedule.entity.Meeting;
import com.hojang.f1dashboard.domain.schedule.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleSyncService {

    private final OpenF1Client openF1Client;
    private final MeetingRepository meetingRepository;

    @Transactional
    public void syncMeetings(int year) {

        long startTime = System.currentTimeMillis();

        // 1. API 호출
        List<OpenF1Client.MeetingResponse> responses = openF1Client.getMeetings(year);

        // 2. DTO -> Entity 변환
        List<Meeting> meetings = responses.stream()
                .map(dto -> Meeting.builder()
                        .meetingKey(dto.meetingKey())
                        .meetingName(dto.meetingName())
                        .location(dto.location())
                        .countryName(dto.countryName())
                        .dateStart(dto.dateStart().toLocalDateTime())
                        .year(dto.year())
                        .build())
                .toList();

        // 3. DB 저장 (이미 있는 키면 Update, 없으면 Insert)
        meetingRepository.saveAll(meetings);
        meetingRepository.flush();

        long endTime = System.currentTimeMillis();
        System.out.println("총 소요 시간: " + (endTime - startTime) + " ms");
    }
}