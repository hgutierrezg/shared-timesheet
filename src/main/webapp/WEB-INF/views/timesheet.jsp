<!DOCTYPE html>
<html ng-app="sharedTimesheetApp" lang="en">
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
    <script src="static/js/app.module.js"></script>
    <script src="static/js/controller/timesheet.controller.js"></script>
    <script src="static/js/service/timesheet.service.js"></script>
</head>

<body class="ng-cloak">

<div class="container-fluid" ng-controller="TimesheetController as timesheetController">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <h4>Shared Timesheet</h4>
            <ul class="nav nav-pills nav-stacked">
                <li ng-click="timesheetController.menuSelection('employee')"
                    ng-class="{'active' : timesheetController.role == 'employee'}"><a href="#employee">Employee</a></li>
                <li ng-click="timesheetController.menuSelection('internalManager')"
                    ng-class="{'active' : timesheetController.role == 'internalManager'}"><a href="#internalManager">Internal
                    Manager</a></li>
                <li ng-click="timesheetController.menuSelection('externalManager')"
                    ng-class="{'active' : timesheetController.role == 'externalManager'}"><a href="#externalManager">External
                    Manager</a></li>
            </ul>
            <br>
        </div>

        <div class="col-sm-9">
            <div ng-if="timesheetController.role == 'employee'">


                <h3><small>Create new timesheet</small></h3>
                <hr>
                <form class="form-inline" ng-submit="timesheetController.submit()" name="myForm">
                    <div class="form-group">
                        <label class="sr-only" for="startDateTime">Start Date and Time</label>
                        <input type="datetime-local" id="startDateTime" name="startDateTime"
                               ng-model="timesheetController.timesheet.startDateTime"
                               class="form-control" placeholder="yyyy-MM-dd HH:mm:ss"
                               min="2001-01-01T00:00:00" required/>

                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="endDateTime">End Date and Time</label>
                        <input type="datetime-local" id="endDateTime" name="endDateTime"
                               ng-model="timesheetController.timesheet.endDateTime"
                               class="form-control" placeholder="yyyy-MM-dd HH:mm:ss"
                               min="2001-01-01T00:00:00" required/>
                    </div>
                    <button type="submit" class="btn btn-success" ng-disabled="myForm.$invalid">Add</button>
                    <button type="button" ng-click="timesheetController.reset()"
                            class="btn btn-default">Reset
                    </button>
                </form>
                <br><br>
                <hr>
            </div>

            <div>
                <h3><small>Timesheet List</small></h3>
                <hr>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID.</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Total Hours</th>
                            <th>Approved</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr ng-repeat="time in timesheetController.timesheets">
                            <td><span ng-bind="time.id"></span></td>
                            <td>
                                <span ng-bind="time.startDateTime | date:'yyyy-MM-dd HH:mm:ss'"></span>
                            </td>
                            <td>
                                <span ng-bind="time.endDateTime | date:'yyyy-MM-dd HH:mm:ss'"></span>
                            </td>
                            <td><span ng-bind="time.totalHours"></span></td>
                            <td>
                                <span ng-class="time.approved == true ? 'bi bi-check-lg': 'bi bi-x-lg' "></span>
                            </td>
                            <td class="approveBtn">
                                <button type="button"
                                        ng-if="time.approved == false && timesheetController.role == 'internalManager'"
                                        ng-click="timesheetController.approve(time)"
                                        class="btn btn-success custom-width">Approve
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

</body>

</html>