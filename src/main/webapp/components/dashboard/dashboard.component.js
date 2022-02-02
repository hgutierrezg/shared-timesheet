'use strict';
 angular.module('sharedTimesheetApp')
    .component('dashboard',
                    {
                    controller: DashboardController,
                    templateUrl: 'components/dashboard/dashboard.html',
                    controllerAs: 'dashboardController'
                    }
                );

function DashboardController ($scope) {
    const dashboardController = this;
    dashboardController.role = 'employee';

    dashboardController.userLoggedIn = false;

    dashboardController.menuSelection = menuSelection;

    function menuSelection(selected) {
        dashboardController.role = selected;
    }

    /**
     * scope listening to event emitted with the name of 'userLoggedIn'
     */
    $scope.$on("userLoggedIn", function (evt) {
        dashboardController.userLoggedIn = true;
    });
}