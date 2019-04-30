function getData() {
    var request = new XMLHttpRequest();
    console.log("Opening request...");
    request.open('GET', '/main/table', true);
    console.log("Request is opened...")
    request.onreadystatechange = function () {
        if(request.readyState == 4) {
            if(request.status == 200) {
                console.log("Parsing JSON...")
                var obj = JSON.parse(request.responseText);
                return obj.events;
            } else {
                console.log("Something going wrong!")
                console.log(request.status)
            }
        }
    }
}
function constructTable() {
    console.log("Getting data from server...");
    var events = getData();
    var txt = "";
    txt += "<table border = '1'>";
    for(e in events){
        txt += "<tr><td>" + e.eventName + "</td>";
        txt += "<td>" + e.startDate + "</td>";
        txt += "<td>" + e.endDate + "</td>";
        txt += "<td>" + e.isPrivate + "</td></tr>";
    }
    txt += "</table>";
    document.getElementById("mytable").innerHTML = txt;
}