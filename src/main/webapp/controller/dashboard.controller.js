'use strict';
angular.module('sharedTimesheetApp').
 controller('DashboardController', ['timesheetService', function(timesheetService)
{
    const dashboardController = this;
    dashboardController.role = 'employee';
    dashboardController.timesheets = [];
    dashboardController.timesheet = {startDateTime: '', endDateTime: '', client: ''};

    dashboardController.submit = submit;
    dashboardController.reset = reset;
    dashboardController.approve = approve;
    dashboardController.menuSelection = menuSelection;


    getAllTimesheets();
    function getAllTimesheets() {
        timesheetService.getAllTimesheets()
            .then(
                function (response) {
                    dashboardController.timesheets = response;
                },
                function () {
                    console.error('Error while reading timesheets with error ' + errResponse);
                }
            );
    }

    function updateTimesheet(timesheet, id) {
        timesheetService.updateTimesheet(timesheet, id)
            .then(
                getAllTimesheets,
                function (errResponse) {
                    console.error('Error while updating timesheet with error ' + errResponse);
                }
            );
    }

    function approve(timesheet) {
        timesheet.approved = true;
        updateTimesheet(timesheet);
    }

    function menuSelection(selected) {
        dashboardController.role = selected;
    }

    function createTimesheet(timesheet) {
        timesheet.startDateTime = new Date(timesheet.startDateTime).toLocaleString("sv-SE");
        timesheet.endDateTime = new Date(timesheet.endDateTime).toLocaleString("sv-SE");
        timesheetService.createTimesheet(timesheet)
            .then(
                getAllTimesheets,
                function (errResponse) {
                    console.error('Error while creating timesheet with error ' + errResponse);
                }
            );
    }

    function submit() {
        createTimesheet(dashboardController.timesheet);
        reset();
    }

    function reset() {
        dashboardController.timesheet = {
            startDateTime: '',
            endDateTime: '',
            client: ''
        };
    }
}]);