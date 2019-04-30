<%@ page import="edu.eventorganizer.auth.model.User" %>
<%--<%@ page language="java"--%>
         <%--contentType="text/html; charset=windows-1256"--%>
         <%--pageEncoding="windows-1256"--%>
         <%--import="edu.eventorganizer.auth.model.User"--%>
<%--%>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style.css">
<html>
<head>
    <%--<script src="../static/js/mainPage.js"></script>--%>
    <script>
        function getData() {
            var request = new XMLHttpRequest();
            request.open('GET', '/main/table', true);
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
            document.getElementById("eventTable").innerHTML = txt;
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
        <%--<link rel="stylesheet" type="text/static" href="/resources/styles.static" &lt;%&ndash;href="<c:url value='/static/style.static'/>"&ndash;%&gt;>--%>
    <title>Main page</title>
</head>
<body onload="getData()">
<%--&lt;%&ndash;<% User currentUser = (User) (session.getAttribute("currentSessionUser"));%>&ndash;%&gt;--%>
<%--<h1>HELLO <%=currentUser.getUsername()%>!</h1>--%>
<div>
    <form action="/profile" method="get">
        <input type="submit" value="Profile">
    </form>
    <form action="/logout" method="get">
        <input type="submit" value="Logout">
    </form>
</div>
<div>
    <form id="eventTable"></form>
</div>

</body>

</html>
