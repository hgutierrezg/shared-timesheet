package com.hgutierrezg.training.service;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.exception.InvalidRequestException;
import com.hgutierrezg.training.mapper.TimesheetObjectMapper;
import com.hgutierrezg.training.model.TimesheetEntity;
import com.hgutierrezg.training.repository.TimesheetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TimesheetService {
    private final TimesheetObjectMapper timesheetObjectMapper;
    private final TimesheetRepository timesheetRepository;

    public Long createTimesheet(TimesheetDto timesheetDto) {
        return this.save(timesheetDto).getId();
    }

    public void updateTimesheet(TimesheetDto timesheetDto) {
       Optional<Long> id = Optional.ofNullable(timesheetDto.getId());
       if (id.isPresent()) {
           this.save(timesheetDto);
       } else {
           throw new InvalidRequestException("Id is required for update");
       }
    }

    public List<TimesheetDto> getTimesheets() {
        List<TimesheetEntity> timesheets = timesheetRepository.findAll();
        return timesheets.stream()
                .map(timesheetObjectMapper::timesheetEntityToTimesheetDto)
                .collect(Collectors.toList());
    }

    public void deleteTimesheet(Long id) {
        if (Optional.ofNullable(id).isPresent()) {
            timesheetRepository.deleteById(id);
        } else {
            throw new InvalidRequestException("Id is required for delete");
        }
    }

    private TimesheetEntity save(TimesheetDto timesheetDto) {
        TimesheetEntity timesheet = timesheetObjectMapper.timesheetDtoToTimesheetEntity(timesheetDto);
        timesheetRepository.save(timesheet);
        return timesheet;
    }
}
