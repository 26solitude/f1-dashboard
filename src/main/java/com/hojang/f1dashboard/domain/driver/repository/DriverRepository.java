package com.hojang.f1dashboard.domain.driver.repository;

import com.hojang.f1dashboard.domain.driver.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}