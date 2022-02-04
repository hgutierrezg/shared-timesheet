'use strict';

angular
    .module('sharedTimesheetApp')
    .service('loginService', loginService);

loginService.$inject = ['$http'];

function loginService($http) {

    const HOST_URI = 'http://localhost:8080/';
    const REST_SERVICE_URI = HOST_URI + 'shared-timesheet/login/';

    return {
        login: login
    };

    /**
     * Sync method that waits for the response to come back
     * @param credentials
     * @returns {*}
     */
    function login(credentials) {
        return $http.post(REST_SERVICE_URI, credentials);
    }
}