package com.hojang.f1dashboard.domain.driver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "driver")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id // 드라이버 넘버를 PK로 사용 (1, 44, 16...)
    private Integer driverNumber;

    private String nameAcronym; // VER
    private String fullName;    // Max Verstappen
    private String teamName;    // Red Bull Racing
    private String teamColour;  // #3671C6 (HEX)
    private String headshotUrl; // 프로필 사진 URL
    private String countryCode; // NED
}