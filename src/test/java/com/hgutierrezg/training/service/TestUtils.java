package com.hgutierrezg.training.service;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.model.TimesheetEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static TimesheetDto buildMockedTimesheetDto() {
        return TimesheetDto.builder()
                .approved(Boolean.FALSE)
                .startDate(LocalDateTime.of(2022, 1, 5 + 3, 0, 0))
                .endDate(LocalDateTime.of(2022, 1, 6 + 7, 0, 0))
                .client("Test Client")
                .build();
    }

    public static List<TimesheetEntity> buildMockedTimesheets() {
        List<TimesheetEntity> timesheets = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            TimesheetEntity timesheet = TimesheetEntity.builder()
                    .id((long) i)
                    .approved(false)
                    .startDate(LocalDateTime.of(2022, 1, i + 3, 0, 0))
                    .endDate(LocalDateTime.of(2022, 1, i + 7, 0, 0))
                    .client("Test Client " + i)
                    .build();
            timesheets.add(timesheet);
        }
        return timesheets;
    }
}
