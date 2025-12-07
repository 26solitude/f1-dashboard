package com.hojang.f1dashboard.domain.schedule.repository;

import com.hojang.f1dashboard.domain.schedule.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
