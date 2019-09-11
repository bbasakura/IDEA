var aid = window.cpro_id;

var callbackHandler = function (json) {
    var html ="";
    for (var i = 0; i < json.length; i++) {
        html += '<li class="J_sk_item sk_item"> <div class="sk_item_pic">';
        html += '<a href="'+json[i].url+'"><img style="height: 150px;width: 150px;" src="'+json[i].img+'"></img></a>'
        html += '<a href="\'+json[i].url+\'"><p class="sk_item_name">'+json[i].name+'</p> </a>';
        html += '<p class="sk_item_price"><span class="mod_price sk_item_price_new"><i>¥</i><span>'+json[i].price+'</span></span></p></li>';
    }
    document.getElementById("ad").innerHTML = html;
};

//纯js跨域请求的处理代码
// 远端服务器的url地址（不管是什么类型的地址，比如：asp、php、asp.net、jsp等，最终生成的返回值都是一段javascript代码）
var url = "http://ad.itcast.cn/ad.html?aid=" + aid + "&ran=" + Math.random() + "&callback=callbackHandler"; //这里的localhost:15493根据实际情况进行修改
// 创建script标签，设置src属性
var script = document.createElement('script');
script.setAttribute('src', url);
// 把script标签加入head
document.getElementsByTagName('head')[0].appendChild(script);
//本例中 服务器返回的结果为 callbackHandler({"name": "sd"});实际上就是调用本js里的callbackHandler函数
//本方法每执行一次，head就会新添加一次 script,使用$.ajax的方法则不会
// http://blog.csdn.net/lhtzbj12/article/details/53928394

