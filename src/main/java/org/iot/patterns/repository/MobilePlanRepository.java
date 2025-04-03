package org.iot.patterns.repository;

import org.iot.patterns.entity.MobilePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobilePlanRepository extends JpaRepository<MobilePlan, Long> {
}
