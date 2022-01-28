package com.hgutierrezg.training.controller;

import com.hgutierrezg.training.model.Timesheet;
import com.hgutierrezg.training.service.TimesheetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
@AllArgsConstructor
public class SharedTimesheetController {

    private final TimesheetService timesheetService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Timesheet>> getAllTimesheets() {
        return ResponseEntity.ok(timesheetService.getTimesheets());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createTimesheet(@RequestBody Timesheet timesheet) {
        Long id = timesheetService.createTimesheet(timesheet);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTimesheet(@RequestBody Timesheet timesheet) {
        timesheetService.updateTimesheet(timesheet);
        return ResponseEntity.ok().build();
    }
}