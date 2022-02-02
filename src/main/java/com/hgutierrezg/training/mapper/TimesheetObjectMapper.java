package com.hgutierrezg.training.mapper;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.model.Timesheet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimesheetObjectMapper {
    Timesheet timesheetDtoToTimesheet(TimesheetDto timesheetDto);
    TimesheetDto timesheetToTimesheetDto(Timesheet timesheet);
}
