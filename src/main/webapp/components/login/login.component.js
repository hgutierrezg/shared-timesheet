'use strict';
 angular.module('sharedTimesheetApp')
    .component('login',
                    {
                    controller: LoginController,
                    templateUrl: 'components/login/login.html',
                    controllerAs: 'loginController'
                    }
                );

function LoginController (loginService, $rootScope) {
    const loginController = this;

    loginController.credentials = {username: '', password: ''};
    loginController.submit = submit;

    loginController.userLoggedIn = false;

    function submit() {
        loginService.login(loginController.credentials)
            .then(
                userLoggedIn,
                function (errResponse) {
                    console.error('Error while creating timesheet with error ' + errResponse);
                }
            );
    }

    /**
     * Function to emitted to the root scope that a user was logged in
     * Any controller listening from each scope will be able to capture it
     */
    function userLoggedIn() {
        loginController.userLoggedIn = true;
        $rootScope.$broadcast("userLoggedIn", loginController.userLoggedIn);
    }
}