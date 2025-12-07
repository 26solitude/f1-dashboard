package com.hojang.f1dashboard.domain.meeting.service;

import com.hojang.f1dashboard.client.OpenF1Client;
import com.hojang.f1dashboard.domain.meeting.entity.Meeting;
import com.hojang.f1dashboard.domain.meeting.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeetingSyncService {

    private final OpenF1Client openF1Client;
    private final MeetingRepository meetingRepository;

    @Transactional
    public int syncMeetings(int year) {
        log.info("Creating schedule sync for year: {}", year);

        // 1. API 호출
        List<OpenF1Client.MeetingResponse> responses = openF1Client.getMeetings(year);

        // 2. DTO -> Entity 변환
        List<Meeting> meetings = responses.stream()
                .map(dto -> Meeting.builder()
                        .meetingKey(dto.meetingKey())
                        .year(dto.year())
                        .circuitKey(dto.circuitKey())

                        .meetingName(dto.meetingName())
                        .meetingOfficialName(dto.meetingOfficialName())
                        .circuitShortName(dto.circuitShortName())
                        .location(dto.location())
                        .countryName(dto.countryName())
                        .countryCode(dto.countryCode())

                        .dateStart(dto.dateStart().toLocalDateTime()) // TimeZone 정보 떼고 로컬 시간으로 저장
                        .gmtOffset(dto.gmtOffset())
                        .build())
                .toList();

        // 3. 저장
        meetingRepository.saveAll(meetings);

        log.info("Saved {} meetings for year {}", meetings.size(), year);
        return meetings.size();
    }
}