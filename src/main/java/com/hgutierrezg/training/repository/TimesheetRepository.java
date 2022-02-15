package com.hgutierrezg.training.repository;

import com.hgutierrezg.training.model.TimesheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends
        JpaRepository <TimesheetEntity, Long> {

}
