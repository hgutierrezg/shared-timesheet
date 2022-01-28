package com.hgutierrezg.training.service;

import com.hgutierrezg.training.model.Timesheet;
import com.hgutierrezg.training.repository.TimesheetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TimesheetService {
    private final TimesheetRepository timesheetRepository;

    public Long createTimesheet(Timesheet timesheet) {
        return this.upsert(timesheet).getId();
    }

    public void updateTimesheet(Timesheet timesheet) {
        this.upsert(timesheet);
    }

    public List<Timesheet> getTimesheets() {
        return timesheetRepository.getAll();
    }

    private Timesheet upsert(Timesheet timesheet) {
        Optional<Timesheet> timesheetOptional = timesheetRepository.getById(timesheet.getId());
        if (timesheetOptional.isPresent()) {
            timesheetRepository.update(timesheet);
        } else {
            timesheetRepository.save(timesheet);
        }
        return timesheet;
    }

}
