%{--
<!DOCTYPE html>
<!--
	Transit by TEMPLATED
	templated.co @templatedco
	Released for free under the Creative Commons Attribution 3.0 license (templated.co/license)
-->
<html lang="en">
<head>

    <style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 4px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
    </style>
    <meta charset="UTF-8">
    <title>Generic - Transit by TEMPLATED</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <!--[if lte IE 8]><script src="js/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    --}%
%{--<asset:javascript src="jquery.min.js"/>
     <asset:javascript src="skel.min.js"/>
     <asset:javascript src="skel-layers.min.js"/>
     <asset:javascript src="init.js"/>--}%%{--


    <script src="grails-app/assets/javascripts/jquery.min.js" type="text/javascript"></script>
    <script src="grails-app/assets/javascripts/skel.min.js" type="text/javascript"></script>
    <script src="grails-app/assets/javascripts/skel-layers.min.js" type="text/javascript"></script>
    <script src="grails-app/assets/javascripts/init.js" type="text/javascript"></script>


    <script src="js/jquery.min.js"></script>
    <script src="js/skel.min.js"></script>
    <script src="js/skel-layers.min.js"></script>
    <script src="js/init.js"></script>
    --}%
%{--<noscript>
--}%%{--

        <asset:stylesheet src="drop.css"/>
        <asset:stylesheet src="style.css"/>
        <asset:stylesheet src="style-xlarge.css"/>

       --}%
%{-- <link rel="stylesheet" href="drop.css" />
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="style-xlarge.css" />--}%%{--

    --}%
%{--</noscript>--}%%{--

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<!-- Header -->
<header id="header">
    <h1>
        <a href="index.html">Attendance Track</a>
    </h1>
    <nav id="nav">
        <ul class="g">
            <li class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button"
                        data-toggle="dropdown">
                    Adminstration <span class="caret"></span>
                </button>
                <ul class="dropdown-content">
                    <li><a href="Employee" action="emp">Employee</a></li>
                    <li><g:link controller="Company" action="camp">Company</g:link></li>

                    <li><g:link controller="Geozone" action="Geozone">geozone</g:link></li>
                    <li><g:link controller="eventData" action="eventList">event</g:link></li>
                </ul>

            </li>


           --}%
%{-- <li class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button"
                        data-toggle="dropdown">
                    Geozone <span class="caret"></span>
                </button>
                <ul class="dropdown-content">
                    <li><a href="#">Polygon</a></li>
                    <li><a href="#">Readius</a></li>

                </ul>
            </li>--}%%{--




            <li><g:link controller="logout" class="button special">Log Out</g:link></li>
        </ul>
    </nav>
</header>

<!-- Main -->
<section id="main" class="wrapper">
    <div class="container">

        <header class="major">
            <h2>Attendence-Detail</h2>

            <table>
                <tr>
                    <th><h4><input type="date" name="date"></h4></th>
                    <th>Employee-Id</th>
                    <th>Employee-Name</th>
                    <th>Company-Name</th>
                    <th>In-Time</th>
                    <th>Out-Time</th>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>







        </header>
        <!--

				<div id="googleMap" style="width:100%;height:400px;"></div>

				<script>
					function myMap() {
					var mapProp= {
   					 center:new google.maps.LatLng(51.508742,-0.120850),
   					 zoom:5,
					};
				var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
					}
				</script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAShrIwR7ZBeCkAEKhncD42oEHMtnLCOf4&callback=initMap"></script> -->

    </div>
</section>

<!-- Footer -->
<footer id="footer">


    <div class="row">
        <div class="8u 12u$(medium)">
            <ul class="copyright">
                <li>&copy; Untitled. All rights reserved.</li>
                <li>Design: <a href="http://templated.co">TEMPLATED</a></li>
                <li>Images: <a href="http://unsplash.com">Unsplash</a></li>
            </ul>
        </div>
    </div>

</footer>
<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA3vSAsZcWtZoXKU9hDt1nKfMrxOqeBo94=myMap"></script> -->


</body>
</html>--}%

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <asset:stylesheet src="home_view_style.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="Stylesheet.css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>HRM | HOME</title>
</head>
<body>
<div class="col-md-12">
<div class="col-md-12" style="background-color: white; padding:4px; padding-left: 0px; padding-right: 0px">
    <div class="col-md-10">
        <h2 style="text-align:left;color:darkorange;font-family: Verdana" >Attendance Track</h2>
        Admin ${username}<button class="btn btn-primary dropdown-toggle" style="border: 0px;color: black; background-color: white; padding-bottom: 3px; padding-top: 3px; padding-left: 6px;padding-right: 6px" type="button" data-toggle="dropdown">
        <span class="caret"></span></button>
        <ul class="dropdown-menu">
           %{-- <li><a href="Employee" action="emp">Employee</a></li>--}%
           %{-- <li><g:link controller="Company" action="camp">Company</g:link></li>--}%

            <li><g:link controller="Geozone" action="Geozone">geozone</g:link></li>
            %{--<li><g:link controller="eventData" action="eventList">event</g:link></li>--}%
        </ul>

    </div>
    <div class="col-md-2" style="padding:20px;padding-bottom: 0px; height:100%; color: #666666" >
        Welcome ${username}<button class="btn btn-primary dropdown-toggle" style="border: 0px;color: black; background-color: white; padding-bottom: 3px; padding-top: 3px; padding-left: 6px;padding-right: 6px" type="button" data-toggle="dropdown">
        <span class="caret"></span></button>
        <ul class="dropdown-menu">
            <li><g:link controller="logout" style="text-decoration: none">Log Out</g:link></li>
        </ul>


    </div>

</div>


<div class="col-sm-12" style="padding: 0 0 0 0; background-color: #f28c38">
    <div class="col-sm-2" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=companyName&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Employee-Id</a></div>
    <div class="col-sm-2" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=taxId&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Employee-Name</a></div>
    <div class="col-sm-2" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Date</a></div>
    <div class="col-sm-2" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=registrationNo&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Login_Time</a></div>
    <div class="col-sm-2" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=totalEmployee&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Logout_Time</a></div>
    <div class="col-sm-2" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=email&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Total-Time</a></div>

</div>



<g:form name="showForm" controller="modify" action="company">

    %{--<g:each in="${attendanceList}" var="atnd">
        <g:set var="employee" value="${Entity.Employee.findByAttendance(atnd.id).get()}" />
        <p>${atnd.attendanceDate},${atnd.logIntime}, ${atnd.logOuttime}, ${employee.id},${employee.employeeName} </p>
    </g:each>--}%





    <g:each in="${attendanceList}" var="atnd">
        <g:set var="employee" value="${Entity.Employee.findByAttendance(atnd.id).get()}" />
        <div class="col-sm-12" style="background-color: #f2f2f2; padding: 5px 0 5px 0; border:1px solid white">
            %{--<div class="col-sm-2" style="padding-left:0px; padding-right:0px">
                <div class="col-sm-1" style=" padding: 0 0 0 10px"align="center"><input type="radio" id="ohrmList_chkSelectRecord_1" name="radio" value="${company.id}"></div>
                <div class="col-sm-9" style=" padding: 0 0 0 15px">${company.companyName}</div>
            </div>--}%
            <div class="col-sm-2" style="padding: 0 0 0 10px;">${employee.id}</div>
            <div class="col-sm-2" style="padding: 0 0 0 10px;">${employee.employeeName}</div>
            <div class="col-sm-2" style="padding: 0 0 0 10px;">${atnd.attendanceDate}</div>
            <div class="col-sm-2" style="padding: 0 0 0 10px;">${atnd.logIntime}</div>
            <div class="col-sm-2" style="padding: 0 0 0 10px;">${atnd.logOuttime}</div>
        </div>
    </g:each>

    <div class="top, col-sm-12" style="padding: 20px; background-color: white" align="center">
        <input type="submit" class="btn btn-success" name="modify" value="Show">
        <input type="submit" class="btn btn-warning" name="modify" value="Edit">
        <input type="submit" class="btn btn-danger"  name="modify" value="Delete">

    </div>
    %{--<div class="pagination" role="status" aria-live="polite" style="float: left">Showing ${from} to ${to} of ${companyCount} entries</div>
    <div class="pagination" style="float: right">
        <li><g:paginate class="row" style="text-align: center; display: inline;" next="Next" prev="Previous" maxsteps="0" controller="Secure" action="list" total="${companyCount}"/>
    </div>--}%
    </div>

</g:form>














%{--<g:form name="attendance" controller="secure" action="index">

<g:each in="${attendanceList}" var="atnd">
    <g:set var="employee" value="${Entity.Employee.findByAttendance(atnd.id).get()}" />
    <p>${atnd.attendanceDate},${atnd.logIntime}, ${atnd.logOuttime}, ${employee.id},${employee.employeeName} </p>
</g:each>

<div class="col-sm-12" style="padding: 0 0 0 0; background-color: #f28c38">
    --}%%{--<div class="col-sm-3" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=companyName&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Employee-Id</a></div>--}%%{--
    <div class="col-sm-1">
        <div class="row">
            <div style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Employee-Id</a></div>
        </div>
        <g:each in="${empList}" var="empInstance" status="i">
            <div class="row"><div class="" style="border:1px solid white;padding: 10px;"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">${empInstance.id}</a></div></div>
        </g:each>
    </div>
    --}%%{--<div class="col-sm-3" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=taxId&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Employee-Name</a></div>--}%%{--
    <div class="col-sm-3">
        <div class="row">
            <div style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Employee-name</a></div>
        </div>
        <g:each in="${empList}" var="empInstance" status="i">
            <div class="row"><div class="" style="border:1px solid white;padding: 10px;"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">${empInstance.employeeName}</a></div></div>
        </g:each>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <div style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Date</a></div>
        </div>
        <g:each in="${attendanceList}" var="attendanceInstance" status="i">
            <div class="row"><div class="" style="border:1px solid white;padding: 10px;"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">${attendanceInstance.attendanceDate}</a></div></div>
        </g:each>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <div style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">LogIn-Time</a></div>
        </div>
        <g:each in="${attendanceList}" var="attendanceInstance" status="i">
            <div class="row"><div class="" style="border:1px solid white;padding: 10px;"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">${attendanceInstance.logIntime}</a></div></div>
        </g:each>
    </div>
    <div class="col-sm-2">
        <div class="row">
        <div style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=registrationNo&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">LogOut-Time</a></div>
        </div>
        <g:each in="${attendanceList}" var="attendanceInstance" status="i">
            <div class="row"><div class="" style="border:1px solid white;padding: 10px;"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">${attendanceInstance.logOuttime}</a></div></div>
        </g:each>
    </div>
    <div class="col-sm-2">
        <div class="row">
            <div style="border:1px solid white; padding: 10px"><a href="sort=registrationNo&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Total-Time</a></div>
        </div>
        <g:each in="${attendanceList}" var="attendanceInstance" status="i">
            <div class="row"><div class="" style="border:1px solid white;padding: 10px;"><a href="/secure/superAdmin?sort=companyStatus&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">${attendanceInstance.logOuttime}</a></div></div>
        </g:each>
    </div>
    --}%%{--    <div class="col-sm-2" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=totalEmployee&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Total Employee</a></div>
        <div class="col-sm-2" style="border:1px solid white; padding: 10px"><a href="/secure/superAdmin?sort=email&max=4&order=asc" style="color: #f2f2f2; text-decoration: none">Email</a></div>--}%%{--

</div>


    <div class="top, col-sm-12" style="padding: 20px; background-color: white" align="center">
        <input type="submit" class="btn btn-success" name="modify" value="Show">
        <input type="submit" class="btn btn-warning" name="modify" value="Edit">
        <input type="submit" class="btn btn-danger"  name="modify" value="Delete">

    </div>
--}%%{--<div class="pagination" role="status" aria-live="polite" style="float: left">Showing ${from} to ${to} of ${companyCount} entries</div>--}%%{--
--}%%{-- <div class="pagination" style="float: right">--}
     --}%%{----}%%{--<li><g:paginate class="row" style="text-align: center; display: inline;" next="Next" prev="Previous" maxsteps="0" controller="Secure" action="list" total="${companyCount}"/>--}%%{----}%%{--
 </div>--}%%{--
    </div>

</g:form>--}%
</div>
</body>
</html>

