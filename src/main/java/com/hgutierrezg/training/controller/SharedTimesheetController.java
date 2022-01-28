package com.hgutierrezg.training.controller;

import java.util.List;

import com.hgutierrezg.training.model.Timesheet;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hgutierrezg.training.service.TimesheetService;

@RestController
@RequestMapping("/times")
@AllArgsConstructor
public class SharedTimesheetController {

    private final TimesheetService timesheetService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Timesheet>> getAllTimesheets() {
        return ResponseEntity.ok(timesheetService.getTimesheets());
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTimesheet(@RequestBody Timesheet timesheet) {
        timesheetService.saveTimesheet(timesheet);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTimesheet(@RequestBody Timesheet timesheet) {
       timesheetService.updateTimesheet(timesheet);
    }
}