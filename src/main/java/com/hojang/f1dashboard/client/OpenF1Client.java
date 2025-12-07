package com.hojang.f1dashboard.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class OpenF1Client {

    private final RestClient restClient = RestClient.builder()
            .baseUrl("https://api.openf1.org/v1")
            .build();

    public List<MeetingResponse> getMeetings(int year) {
        return restClient.get()
                .uri("/meetings?year={year}", year)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public record MeetingResponse(
            @JsonProperty("meeting_key") Long meetingKey,
            @JsonProperty("meeting_name") String meetingName,
            @JsonProperty("location") String location,
            @JsonProperty("country_name") String countryName,
            @JsonProperty("date_start") OffsetDateTime dateStart,
            int year
    ) {
    }
}
