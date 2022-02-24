package com.hgutierrezg.training.controller;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.service.TimesheetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/timesheet")
@AllArgsConstructor
public class SharedTimesheetController {

    private final TimesheetService timesheetService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TimesheetDto>> getAllTimesheets() {
        return ResponseEntity.ok(timesheetService.getTimesheets());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createTimesheet(@Valid @RequestBody TimesheetDto timesheetDto) {
        Long id = timesheetService.createTimesheet(timesheetDto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateTimesheet(@RequestBody TimesheetDto timesheetDto) {
        timesheetService.updateTimesheet(timesheetDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Long id) {
        timesheetService.deleteTimesheet(id);
        return ResponseEntity.ok().build();
    }
}