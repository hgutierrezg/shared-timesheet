/**
 * Creating controller only inside sharedTimesheetApp module,
 * so the controller does not become a global function
 */
'use strict';
angular.module('sharedTimesheetApp')
    .component(
        'timesheetform',
        {
            controller: TimesheetFormController,
            templateUrl: 'components/timesheet-form/timesheet-form.html',
            controllerAs: 'timesheetFormController'
        });

function TimesheetFormController (timesheetService, $rootScope) {

    const timesheetFormController = this;
    timesheetFormController.timesheet = {startDateTime: '', endDateTime: '', client: ''};

    timesheetFormController.submit = submit;
    timesheetFormController.reset = reset;

    function createTimesheet(timesheet) {
        timesheet.startDateTime = new Date(timesheet.startDateTime).toLocaleString("sv-SE");
        timesheet.endDateTime = new Date(timesheet.endDateTime).toLocaleString("sv-SE");
        timesheetService.createTimesheet(timesheet)
            .then(
                timesheetCreated,
                function (errResponse) {
                    console.error('Error while creating timesheet with error ' + errResponse);
                }
            );
    }

    function submit() {
        createTimesheet(timesheetFormController.timesheet);
        reset();
    }

    function reset() {
        timesheetFormController.timesheet = {
            startDateTime: '',
            endDateTime: '',
            client: ''
        };
    }

    /**
     * Function to emitted to the root scope that a new time sheet was created.
     * Any controller listening from each scope will be able to capture it
     */
    function timesheetCreated() {
        $rootScope.$broadcast("timesheetCreated", "timesheetCreated");
    }
}
