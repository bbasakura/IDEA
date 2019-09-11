var aid = window.cpro_id;
alert(aid)
var xmlHttp;

function createXMLHttpRequest() {
    if(window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function routeList(){
    createXMLHttpRequest();
    url = "http://ad.itcast.cn/ad.html?aid="+aid+"&ran="+Math.random();
    method = "GET";
    xmlHttp.open(method,url,true);
    xmlHttp.onreadystatechange = showList;
    xmlHttp.send(null);
}

function showList(){
    if (xmlHttp.readyState == 4){
        // if (xmlHttp.status == 200){
            var text = xmlHttp.responseText;
            alert(text);
            document.getElementById("ad").innerHTML = text;
        // }else {
        //     var text = "<a href=\"/jquery/jquery_ref_ajax.asp\" >jQuery Ajax 参考手册</a>"
        //     document.getElementById("ad").innerHTML = text;
        // }
    }
}
routeList();

