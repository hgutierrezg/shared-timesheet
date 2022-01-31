'use strict';
 angular.module('sharedTimesheetApp')
    .component('dashboard',
                    {
                    controller: DashboardController,
                    templateUrl: 'components/dashboard/dashboard.html',
                    controllerAs: 'dashboardController'
                    }
                );

function DashboardController () {
    const dashboardController = this;
    dashboardController.role = 'employee';

    dashboardController.menuSelection = menuSelection;

    function menuSelection(selected) {
        dashboardController.role = selected;
    }
}