<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8"/>
    <title>Shared Timesheet</title>
    <link rel="shortcut icon" href="#">
    <link rel="stylesheet" href="static/css/style.css">

    <script>
        document.write('<base href="' + document.location + '" />');
    </script>

    <!-- Angular JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- Project modules -->
    <script src="static/app.module.js"></script>
    <script src="static/service/timesheet.service.js"></script>
    <script src="static/controller/dashboard.controller.js"></script>
</head>

<body ng-app="sharedTimesheetApp">

<main ng-cloak>

    <div class="container-fluid" ng-controller="DashboardController as dashboardController">
        <div class="row content">
            <nav class="col-sm-3 sidenav">
                <h4>Shared Timesheet</h4>
                <h5>Username: ${credentialsDto.username}</h5>
                <ul class="nav nav-pills nav-stacked">
                    <li ng-click="dashboardController.menuSelection('employee') " id="employeeMenuId"
                        ng-class="{ 'active' : dashboardController.userRole == 'employee' }"/>
                    <a href="#employee">Employee</a>

                    <li ng-click="dashboardController.menuSelection('internalManager') " id="internalManagerId"
                        ng-class="{ 'active' : dashboardController.userRole == 'internalManager' }"/>
                    <a href="#internalManager">Internal Manager</a>

                    <li ng-click="dashboardController.menuSelection('externalManager') " id="externalManagerId"
                        ng-class="{ 'active' : dashboardController.userRole == 'externalManager' }"/>
                    <a href="#externalManager">External Manager</a>
                </ul>
                <br>
            </nav>

            <div class="col-sm-9">
                <div ng-if="dashboardController.userRole == 'employee'">
                    <section>
                        <h3><small>Create new timesheet</small></h3>
                        <hr>
                        <form class="form-inline my-2 my-lg-0" ng-submit="dashboardController.submit()"
                              name="createForm">
                            <div class="form-group">
                                <label class="text" for="startDate">From:</label>
                                <input type="datetime-local" id="startDate" name="startDate"
                                       ng-model="dashboardController.timesheet.startDate"
                                       class="form-control" placeholder="yyyy-MM-dd HH:mm:ss"
                                       min="2001-01-01T00:00:00" required/>
                            </div>

                            <div class="form-group">
                                <label class="text" for="endDate">To:</label>
                                <input type="datetime-local" id="endDate" name="endDate"
                                       ng-model="dashboardController.timesheet.endDate"
                                       class="form-control" placeholder="yyyy-MM-dd HH:mm:ss"
                                       min="2001-01-01T00:00:00" required/>
                            </div>

                            <div class="form-group">
                                <label class="text" for="client">Client:</label>
                                <input type="text" id="client" name="client"
                                       ng-model="dashboardController.timesheet.client"
                                       class="form-control" placeholder="Client Name"/>
                            </div>

                            <button type="submit" class="btn btn-success" ng-disabled="createForm.$invalid">Add</button>
                            <button type="button" ng-click="dashboardController.reset()"
                                    class="btn btn-default">Reset
                            </button>
                        </form>
                        <br><br>
                        <hr>
                    </section>
                </div>

                <div>
                    <section>
                        <h3><small>Timesheet List</small></h3>
                        <hr>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>ID.</th>
                                    <th>From</th>
                                    <th>To</th>
                                    <th>Client</th>
                                    <th>Total Hours</th>
                                    <th>Approved</th>
                                </tr>
                                </thead>

                                <tbody>
                                <tr ng-repeat="time in dashboardController.timesheets">
                                    <td><span ng-bind="time.id"></span></td>
                                    <td>
                                        <span ng-bind="time.startDate | date:'yyyy-MM-dd HH:mm:ss'"></span>
                                    </td>
                                    <td>
                                        <span ng-bind="time.endDate | date:'yyyy-MM-dd HH:mm:ss'"></span>
                                    </td>
                                    <td><span ng-bind="time.client"></span></td>
                                    <td><span ng-bind="time.totalHours"></span></td>
                                    <td>
                                        <span ng-class="time.approved == true ? 'bi bi-check-lg': 'bi bi-x-lg' "></span>
                                    </td>
                                    <td class="approveBtn">
                                        <button type="button"
                                                ng-if="time.approved == false && dashboardController.userRole == 'internalManager'"
                                                ng-click="dashboardController.approve(time)" id="approveBtnId"
                                                class="btn btn-success custom-width">Approve
                                        </button>
                                    </td>

                                    <td class="deleteBtn">
                                        <button type="button"
                                                ng-if="dashboardController.userRole != 'externalManager'"
                                                ng-click="dashboardController.deleteTimesheet(time)" id="deleteBtnId"
                                                class="btn btn-secondary custom-width">Delete
                                        </button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </section>
                </div>

            </div>
        </div>
    </div>

</main>
</body>
</html>