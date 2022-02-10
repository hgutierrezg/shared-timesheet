package com.hgutierrezg.training.repository;

import com.hgutierrezg.training.model.TimesheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TimesheetRepository extends
        JpaRepository <TimesheetEntity, Long>, JpaSpecificationExecutor <TimesheetEntity> {

}
