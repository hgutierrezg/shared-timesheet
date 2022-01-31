'use strict';

angular
    .module('sharedTimesheetApp')
    .service('timesheetService', timesheetService);

timesheetService.$inject = ['$http', '$q'];

function timesheetService($http, $q) {

    const HOST_URI = 'http://localhost:8080/';
    const REST_SERVICE_URI = HOST_URI + 'shared-timesheet/times/';

    return {
        getAllTimesheets: getAllTimesheets,
        createTimesheet: createTimesheet,
        updateTimesheet: updateTimesheet
    };

    function getAllTimesheets() {
        return $http.get(REST_SERVICE_URI)
            .then(getAllTimesheetsComplete)
            .catch(getAllTimesheetsFailed);

        function getAllTimesheetsComplete(response) {
            return response.data;
        }

        function getAllTimesheetsFailed(error) {
            console.error('Error while creating Timesheet ' + error);
        }
    }

    function createTimesheet(timesheet) {
        const deferred = $q.defer();
        $http.post(REST_SERVICE_URI, timesheet)
            .then(function (response) {
                deferred.resolve(response.data);
            }, function (errResponse) {
                console.error('Error while creating Timesheet');
                deferred.reject(errResponse);
            });
        return deferred.promise;
    }

    function updateTimesheet(timesheet) {
        const deferred = $q.defer();
        $http.put(REST_SERVICE_URI, timesheet)
            .then(function (response) {
                deferred.resolve(response.data);
            }, function (errResponse) {
                console.error('Error while updating Timesheet');
                deferred.reject(errResponse);
            });
        return deferred.promise;
    }
}