package com.hgutierrezg.training.service;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.model.Timesheet;
import com.hgutierrezg.training.repository.TimesheetRepository;
import lombok.AllArgsConstructor;
import com.hgutierrezg.training.mapper.TimesheetObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TimesheetService {
    private final TimesheetRepository timesheetRepository;
    private final TimesheetObjectMapper timesheetObjectMapper;

    public Long createTimesheet(TimesheetDto timesheetDto) {
        return this.upsert(timesheetDto).getId();
    }

    public void updateTimesheet(TimesheetDto timesheetDto) {
        this.upsert(timesheetDto);
    }

    public List<TimesheetDto> getTimesheets() {
        List<Timesheet> timesheets = timesheetRepository.getAll();
        return timesheets.stream()
                .map(timesheetObjectMapper::timesheetToTimesheetDto)
                .collect(Collectors.toList());
    }

    private Timesheet upsert(TimesheetDto timesheetDto) {
        Timesheet timesheet = timesheetObjectMapper.timesheetDtoToTimesheet(timesheetDto);
        Optional<Timesheet> timesheetOptional = timesheetRepository.getById(timesheet.getId());
        if (timesheetOptional.isPresent()) {
            timesheetRepository.update(timesheet);
        } else {
            timesheetRepository.create(timesheet);
        }
        return timesheet;
    }

}
