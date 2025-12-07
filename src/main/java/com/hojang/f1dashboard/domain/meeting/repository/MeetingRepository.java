package com.hojang.f1dashboard.domain.meeting.repository;

import com.hojang.f1dashboard.domain.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findAllByYear(Integer year);
}
