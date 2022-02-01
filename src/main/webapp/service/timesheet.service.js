'use strict';

angular
    .module('sharedTimesheetApp')
    .service('timesheetService', timesheetService);

timesheetService.$inject = ['$http'];

function timesheetService($http) {

    const HOST_URI = 'http://localhost:8080/';
    const REST_SERVICE_URI = HOST_URI + 'shared-timesheet/times/';

    return {
        getAllTimesheets: getAllTimesheets,
        createTimesheet: createTimesheet,
        updateTimesheet: updateTimesheet
    };

    function getAllTimesheets() {
        return $http.get(REST_SERVICE_URI)
            .then(resolveSuccess)
            .catch(resolveError);
    }

    function createTimesheet(timesheet) {
        return $http.post(REST_SERVICE_URI, timesheet)
            .then(resolveSuccess)
            .catch(resolveError);
    }

    function updateTimesheet(timesheet) {
        return $http.put(REST_SERVICE_URI, timesheet)
            .then(resolveSuccess)
            .catch(resolveError);
    }

    function resolveSuccess(response) {
        return Promise.resolve(response.data);
    }

    function resolveError(error) {
        console.error('Error while calling the end point ' + error);
    }
}