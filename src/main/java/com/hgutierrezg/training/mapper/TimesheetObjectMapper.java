package com.hgutierrezg.training.mapper;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.model.TimesheetEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimesheetObjectMapper {
    TimesheetEntity timesheetDtoToTimesheetEntity(TimesheetDto timesheetDto);

    TimesheetDto timesheetEntityToTimesheetDto(TimesheetEntity timesheet);
}
