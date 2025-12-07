package com.hojang.f1dashboard.domain.schedule.entity;

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
    private Long meetingKey; // OpenF1의 meeting_key를 PK로 사용

    private String meetingName;
    private String location;
    private String countryName;
    private LocalDateTime dateStart;
    private int year;
}
