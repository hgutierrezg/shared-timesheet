'use strict';

/**
 * Creating controller only inside sharedTimesheetApp module, so the controller does not become a global function
 */
angular.module('sharedTimesheetApp').controller(
    'TimesheetController', ['$scope', 'timesheetService', function($scope, timesheetService) {

        var timesheetController = this;
        timesheetController.timesheet = {startDateTime: '', endDateTime: ''};

        timesheetController.timesheets = [];
        timesheetController.role = 'employee';

        timesheetController.submit = submit;
        timesheetController.approve = approve;
        timesheetController.reset = reset;
        timesheetController.menuSelection = menuSelection;
        timesheetController.displayApproveButton = timesheetController;

        getAllTimesheets();

        function getAllTimesheets() {
            timesheetService.getAllTimesheets()
                .then(
                    function (response) {
                        timesheetController.timesheets = response;
                    },
                    function () {
                        console.error('Error while reading timesheets with error ' + errResponse);
                    }
                );
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

        function updateTimesheet(timesheet, id) {
            timesheetService.updateTimesheet(timesheet, id)
                .then(
                    getAllTimesheets,
                    function (errResponse) {
                        console.error('Error while updating timesheet with error ' + errResponse);
                    }
                );
        }

        function submit() {
            createTimesheet(timesheetController.timesheet);
            reset();
        }

        function approve(timesheet) {
            timesheet.approved = true;
            updateTimesheet(timesheet);
        }

        function reset() {
            timesheetController.timesheet = {
                startDateTime: '',
                endDateTime: ''
            };
            // $scope.myForm.$setPristine();
        }

        function menuSelection(selected) {
            console.log('value for selected ' + selected);
            timesheetController.role = selected;
        }
    }
]);