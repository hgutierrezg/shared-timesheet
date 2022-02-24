package com.hgutierrezg.training.service;

import com.hgutierrezg.training.dto.TimesheetDto;
import com.hgutierrezg.training.exception.InvalidRequestException;
import com.hgutierrezg.training.mapper.TimesheetObjectMapper;
import com.hgutierrezg.training.mapper.TimesheetObjectMapperImpl;
import com.hgutierrezg.training.model.TimesheetEntity;
import com.hgutierrezg.training.repository.TimesheetRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class TimesheetServiceTest {

    private TimesheetService timesheetService;

    @Mock
    private TimesheetRepository timesheetRepository;

    private TimesheetDto timesheetDto;
    private List<TimesheetEntity> timesheetList;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Mockito.when(timesheetRepository.save(Mockito.any(TimesheetEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        TimesheetObjectMapper timesheetObjectMapper = new TimesheetObjectMapperImpl();
        timesheetService = new TimesheetService(timesheetObjectMapper, timesheetRepository);

        timesheetDto = TestUtils.buildMockedTimesheetDto();
        timesheetList = TestUtils.buildMockedTimesheets();
    }

    @Test
    public void givenAllParameters_whenCallCreate_thenRepoSaveInvoked() {
        timesheetService.createTimesheet(timesheetDto);
        Mockito.verify(timesheetRepository).save(Mockito.any(TimesheetEntity.class));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNoStartDateProvided_whenCallCreate_thenRepoSaveNotInvoked() {
        timesheetDto.setStartDate(null);
        timesheetService.createTimesheet(timesheetDto);
        Mockito.verify(timesheetRepository, Mockito.times(0)).save(Mockito.any(TimesheetEntity.class));
    }

    @Test
    public void givenAllParameters_whenCallUpdate_thenRepoSaveInvoked() {
        timesheetDto.setId(1L);
        timesheetService.updateTimesheet(timesheetDto);
        Mockito.verify(timesheetRepository, Mockito.atMost (2)).save(Mockito.any(TimesheetEntity.class));
    }

    @Test(expectedExceptions = InvalidRequestException.class)
    public void givenNoIdProvided_whenCallUpdate_thenRepoSaveNotInvoked() {
        timesheetDto.setId(null);
        timesheetService.updateTimesheet(timesheetDto);
        Mockito.verify(timesheetRepository, Mockito.times(0)).save(Mockito.any(TimesheetEntity.class));
    }

    @Test
    public void given4TimesheetsExists_whenGetAll_then4TimesheetsReturned() {
        Mockito.when(timesheetRepository.findAll()).thenReturn(timesheetList);
        List<TimesheetDto> timesheetEntityList = timesheetService.getTimesheets();
        Assert.assertEquals(4, timesheetEntityList.size());
    }

    @Test
    public void givenNoTimesheetsExists_whenGetAll_thenTimesheetListIsEmpty() {
        Mockito.when(timesheetRepository.findAll()).thenReturn(Collections.emptyList());
        List<TimesheetDto> timesheetEntityList = timesheetService.getTimesheets();
        Assert.assertTrue(timesheetEntityList.isEmpty());
    }

    @Test
    public void givenIdNotProvided_whenCallDelete_thenRepoDeleteInvoked() {
        timesheetService.deleteTimesheet(1L);
        Mockito.verify(timesheetRepository, Mockito.atMost (1)).deleteById(Mockito.any(Long.class));
    }

    @Test(expectedExceptions = InvalidRequestException.class)
    public void givenIdNotProvided_whenCallDelete_thenRepoDeleteNotInvoked() {
        timesheetService.deleteTimesheet(null);
        Mockito.verify(timesheetRepository, Mockito.times(0)).deleteById(Mockito.any(Long.class));
    }
}