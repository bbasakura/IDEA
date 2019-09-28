var kingpoint = {
	/**
	 * post提交json格式数据，与后台交互
	 */
	postObject : function(url, data, handle) {
		$.ajax({
			url : url,
			dataType : "json",
			contentType : "application/json",
			type : 'post',
			data : JSON.stringify(data),
			success : function(data) {
				handle(data);
			},error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log("XMLHttpRequest",XMLHttpRequest);
				console.log("textStatus",textStatus);
				console.log("errorThrown",errorThrown);
			}
		});
	},
	/**
	 * post提交，与后台交互
	 */
	postData : function(url, data, handle) {
		$.ajax({
			url : url,
			dataType : "json",
			type : 'post',
			data : data,
			success : function(data) {
				handle(data);
			},error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log("XMLHttpRequest",XMLHttpRequest);
				console.log("textStatus",textStatus);
				console.log("errorThrown",errorThrown);
			}
		});
	},

	// get获取后台数据
	getData : function(url, handle) {
		$.ajax({
			url : url,
			type : 'get',
			async : false,
			dataType : 'json',
			success : function(data) {
				handle(data);
			},
			error : function(error) {
				console.log(error)
				return;
			}
		});
	}
}