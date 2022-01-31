/**
 * Creating controller only inside sharedTimesheetApp module,
 * so the controller does not become a global function
 */
'use strict';
angular.module('sharedTimesheetApp')
    .component(
        'timesheetlist',
        {
            controller: TimesheetListController,
            templateUrl: 'components/timesheet-list/timesheet-list.html',
            controllerAs: 'timesheetListController',
            bindings: {
                role: '<',
            }
        });

function TimesheetListController (timesheetService, $scope) {

        var timesheetController = this;
        timesheetController.timesheets = [];
        timesheetController.approve = approve;

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

    /**
     * scope listening to event emitted with the name of 'timesheetCreated'
      */
    $scope.$on("timesheetCreated", function (evt) {
        getAllTimesheets();
    });
}