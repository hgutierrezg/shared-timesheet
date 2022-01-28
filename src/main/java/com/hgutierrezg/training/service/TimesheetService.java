package com.hgutierrezg.training.service;

import java.util.List;

import com.hgutierrezg.training.model.Timesheet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class TimesheetService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Timesheet> timesheets;

    static{
        timesheets = createMockedTimesheets();
    }

    public void saveTimesheet(Timesheet timesheet) {
        timesheet.setId(counter.incrementAndGet());
        timesheets.add(timesheet);
    }

    public void updateTimesheet(Timesheet timesheet) {
        Timesheet currentTimesheet = findById(timesheet.getId());

        if (currentTimesheet != null){
            int index = timesheets.indexOf(timesheet);
            if (index != -1){
                timesheets.set(index, timesheet);
            }
        }
    }

    private Timesheet findById(long id) {
        for(Timesheet user : timesheets){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public List<Timesheet> getTimesheets() {

        return timesheets;
    }

    private static List<Timesheet> createMockedTimesheets(){
        List<Timesheet> timesheets = new ArrayList<>();

        for (int i = 0; i<= 3; i++){
            timesheets.add(
                    Timesheet.builder()
                            .id(counter.incrementAndGet())
                            .approved(false)
                            .startDateTime(LocalDateTime.of(2022, 1, i + 3, 0, 0))
                            .endDateTime(LocalDateTime.of(2022, 1, i + 7, 0, 0)).build()
            );
        }
        return timesheets;
    }
}
