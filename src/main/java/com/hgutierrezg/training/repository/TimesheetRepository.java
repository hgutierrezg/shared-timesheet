package com.hgutierrezg.training.repository;

import com.hgutierrezg.training.model.Timesheet;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class to represent a repository with an in-memory database stored in List<Timesheet> timesheetList
 */
@Repository
public class TimesheetRepository {
    private final AtomicLong counter;
    private final List<Timesheet> timesheetList;

    public TimesheetRepository() {
        this.counter = new AtomicLong();
        this.timesheetList = MockedTimesheetsBuilder();
    }

    /**
     * Retrieves a Timesheet by its id
     * Equivalent to SELECT * FROM timesheet WHERE id=id;
     *
     * @param id The id to retrieve
     * @return An Optional that may or may not contain a Timesheet
     */
    public Optional<Timesheet> getById(long id) {
        return this.timesheetList.stream().filter(timesheet -> timesheet.getId() == id).findFirst();
    }

    /**
     * Retrieves all Timesheet available
     * Equivalent to SELECT * FROM timesheet;
     *
     * @return A collection of Timesheet
     */
    public List<Timesheet> getAll() {
        return this.timesheetList;
    }

    /**
     * Stores a new Timesheet
     * Equivalent to INSERT INTO timesheet VALUES ...;
     *
     * @param timesheet The timesheet to insert
     */
    public void save(Timesheet timesheet) {
        timesheet.setId(counter.incrementAndGet());
        this.timesheetList.add(timesheet);
    }

    /**
     * Updates a timesheet if and only if is found by id
     * @param timesheet The timesheet to update
     */
    public void update(Timesheet timesheet) {
        getById(timesheet.getId()).ifPresent(foundTimesheet -> {
            int index = this.timesheetList.indexOf(timesheet);
            this.timesheetList.set(index, timesheet);
        });
    }

    /**
     * Method to ingest values into the in memory list acting as a repository
     *
     * @return A collection of Timesheet
     */
    private List<Timesheet> MockedTimesheetsBuilder() {
        List<Timesheet> timesheets = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            Timesheet timesheet = Timesheet.builder()
                    .id(counter.incrementAndGet())
                    .approved(false)
                    .startDateTime(LocalDateTime.of(2022, 1, i + 3, 0, 0))
                    .endDateTime(LocalDateTime.of(2022, 1, i + 7, 0, 0))
                    .build();
            timesheets.add(timesheet);
        }
        return timesheets;
    }
}
