<%--
  Created by IntelliJ IDEA.
  User: aleks
  Date: 4/24/2019
  Time: 3:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <script>
        function getData() {
            var request = new XMLHttpRequest();
            request.open('GET', '/profile/getUserData', true);
            request.send();
            request.onreadystatechange = function () {
                if(request.readyState == 4) {
                    if(request.status == 200) {
                        var obj = request.responseText;
                        constructTable(obj);
                    }
                } else {
                    console.log(request.readyState);
                }
            };
        }
        function constructTable(events) {
            var txt = "";
            txt += "<table border = '1'><tr>" +
                "<td>Event</td>" +
                "<td>Starting date</td>" +
                "<td>Ending date</td>" +
                "</tr>";
            var json = JSON.parse(events);
            var arrayLength = arraySize(json);
            if(!isEmpty(json)) {
                for (var i = 0; i < arrayLength; i++) {
                    txt += "<tr><td>" + json[i].eventName + "</td>";
                    txt += "<td>" + json[i].startDate + "</td>";
                    txt += "<td>" + json[i].endDate + "</td>";
                }
            }
            txt += "</table>";
            document.getElementById("userData").innerHTML = txt;
        }

        function isEmpty(obj) {
            for(var key in obj) {
                if(obj.hasOwnProperty(key)) {
                    return false;
                }
            }
            return true;
        }

        function arraySize(obj) {
            return Object.keys(obj).length;
        }
    </script>

    <title>Profile page</title>
</head>
<body onload="getData()">
<div>
    <form action="/main" method="get">
        <input type="submit" value="Back to main page">
    </form>
    <form action="/" method="get">
        <input type="submit" value="Logout">
    </form>
</div>
<div>
    <form action="userData"></form>
</div>
</body>
</html>
