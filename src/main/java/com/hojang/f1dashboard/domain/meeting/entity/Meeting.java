package com.hojang.f1dashboard.domain.meeting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "meeting")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    @Id
    private Long meetingKey;

    // [1. 필수 식별 및 필터링 데이터]
    private Integer year;           // 시즌 필터링용
    private Long circuitKey;        // 서킷 정보 확장성 고려

    // [2. 화면 표시 데이터 (UI)]
    private String meetingName;         // 목록용 짧은 이름
    private String meetingOfficialName; // 상세페이지용 긴 이름
    private String circuitShortName;    // 서킷 이름 (예: Silverstone)
    private String location;            // 도시 (예: Melbourne)
    private String countryName;         // 국가 이름 (예: Australia)
    private String countryCode;         // 국기 아이콘 매핑용 (예: AUS)

    // [3. 시간 및 로직 처리 데이터]
    private LocalDateTime dateStart;    // 경기 시작 시간 (정렬 기준)
    private String gmtOffset;           // 현지 시간 계산용 (예: +10:00)
}
