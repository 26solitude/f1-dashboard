package com.hojang.f1dashboard.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class OpenF1Client {

    private final RestClient restClient;

    public OpenF1Client(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://api.openf1.org/v1")
                .build();
    }

    // 1. 경기 일정 조회
    public List<MeetingResponse> getMeetings(int year) {
        return restClient.get()
                .uri("/meetings?year={year}", year)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    // 2. 특정 세션의 드라이버 목록 조회
    public List<DriverResponse> getDrivers(int sessionKey) {
        return restClient.get()
                .uri("/drivers?session_key={sessionKey}", sessionKey)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }


    public record MeetingResponse(
            @JsonProperty("meeting_key") Long meetingKey,
            @JsonProperty("year") Integer year,
            @JsonProperty("circuit_key") Long circuitKey,

            @JsonProperty("meeting_name") String meetingName,
            @JsonProperty("meeting_official_name") String meetingOfficialName,
            @JsonProperty("circuit_short_name") String circuitShortName,
            @JsonProperty("location") String location,
            @JsonProperty("country_name") String countryName,
            @JsonProperty("country_code") String countryCode,

            @JsonProperty("date_start") OffsetDateTime dateStart,
            @JsonProperty("gmt_offset") String gmtOffset
    ) {
    }

    public record DriverResponse(
            @JsonProperty("driver_number") Integer driverNumber, // PK
            @JsonProperty("name_acronym") String nameAcronym,    // VER
            @JsonProperty("full_name") String fullName,          // Max VERSTAPPEN
            @JsonProperty("team_name") String teamName,          // Red Bull Racing
            @JsonProperty("team_colour") String teamColour,      // 3671C6
            @JsonProperty("headshot_url") String headshotUrl,    // 이미지 URL
            @JsonProperty("country_code") String countryCode     // NED
    ) {}

}