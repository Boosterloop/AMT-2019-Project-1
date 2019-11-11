<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--

=========================================================
* Now UI Kit - v1.3.0
=========================================================

* Product Page: https://www.creative-tim.com/product/now-ui-kit
* Copyright 2019 Creative Tim (http://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/now-ui-kit/blob/master/LICENSE.md)

* Designed by www.invisionapp.com Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

-->
<html lang="en">

<head>
  <meta charset="utf-8" />
  <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png">
  <link rel="icon" type="image/png" href="./assets/img/favicon.png">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title>
    Top Cities
  </title>
  <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
  <!--     Fonts and icons     -->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
  <!-- CSS Files -->
  <link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
  <link href="./assets/css/now-ui-kit.css?v=1.3.0" rel="stylesheet" />
  <!-- Data tables for pagination -->
  <link rel="stylesheet" type="text/css" href="./assets/css/datatables.min.css" />
</head>

<body style="background-image:url('./assets/img/new-york.jpg')">
<!-- Navbar -->
<nav class="bg-primary fixed-top navbar navbar-expand-md">
  <div class="container">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a href="/citylog">Home</a>
      </li>
      <li class="nav-item">
        <a href="visits">Visits</a>
      </li>
      <li class="nav-item">
        <a href="cities">Top Cities</a>
      </li>
      <li class="nav-item">
        <a href="logout">Logout</a>
      </li>
    </ul>
  </div>
</nav>
<!-- End Navbar -->
<div style="margin-bottom:100px; margin-top: 60px;">
  <div class="content">
    <div class="container">
      <div class="col-md-10 ml-auto mr-auto">
        <h1 class="text-white">Top Cities</h1>
        <table id="cities-table" class="table bg-white">
          <thead>
          <tr>
            <th>City</th>
            <th>Country</th>
            <th>Number of visits</th>
          </tr>
          </thead>
        </table>
      </div>
    </div>
  </div>
</div>

<footer class="footer mt-auto py-3 fixed-bottom bg-primary">
  <div class=" container text-white">
    <div class="copyright" id="copyright">
      &copy;
      <script>
          document.getElementById('copyright').appendChild(document.createTextNode(new Date().getFullYear()))
      </script>, Designed by
      <a href="https://www.invisionapp.com" target="_blank" class="text-white">Invision</a>. Coded by
      <a href="https://www.creative-tim.com" target="_blank" class="text-white">Creative Tim</a>.
    </div>
  </div>
</footer>

<!--   Core JS Files   -->
<script src="./assets/js/core/jquery.min.js" type="text/javascript"></script>
<script src="./assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="./assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="./assets/js/plugins/bootstrap-switch.js"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="./assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
<!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
<script src="./assets/js/plugins/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
<script src="./assets/js/now-ui-kit.js?v=1.3.0" type="text/javascript"></script>
<!-- Data tables script -->
<script type="text/javascript" src="./assets/js/plugins/datatables.min.js"></script>

<script>
    $(document).ready(function() {
        $('#cities-table').DataTable({
            processing: false,
            serverSide: true,
            lengthMenu: [10],
            ajax: {
                url: '<%=request.getContextPath() + "/cities"%>',
                type: 'POST'
            },
            columns: [{
                data: "name",
                mData: "name"
            }, {
                searchable: false,
                data: "country",
                mData: "country"
            }, {
                searchable: false,
                data: "count",
                mData: "count"
            }]
        });
    });
</script>
</body>
</html>