package com.hgutierrezg.training.service;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.mapper.TimesheetObjectMapper;
import com.hgutierrezg.training.model.TimesheetEntity;
import com.hgutierrezg.training.repository.TimesheetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TimesheetService {
    private final TimesheetObjectMapper timesheetObjectMapper;
    private final TimesheetRepository timesheetRepository;

    public Long createTimesheet(TimesheetDto timesheetDto) {
        return this.upsert(timesheetDto).getId();
    }

    public void updateTimesheet(TimesheetDto timesheetDto) {
        this.upsert(timesheetDto);
    }

    public List<TimesheetDto> getTimesheets() {
        List<TimesheetEntity> timesheets = timesheetRepository.getAll();
        return timesheets.stream()
                .map(timesheetObjectMapper::timesheetEntityToTimesheetDto)
                .collect(Collectors.toList());
    }

    public void deleteTimesheet(Long id) {
        timesheetRepository.deleteEntity(id);
    }

    private TimesheetEntity upsert(TimesheetDto timesheetDto) {
        TimesheetEntity timesheet = timesheetObjectMapper.timesheetDtoToTimesheetEntity(timesheetDto);
        timesheetRepository.saveEntity(timesheet);
        return timesheet;
    }
}
