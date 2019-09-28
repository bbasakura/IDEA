(function($){
	var ms = {
		init:function(obj,args){
			return (function(){
				ms.fillHtml(obj,args);
				ms.bindEvent(obj,args);
			})();
		},
		//填充html
		fillHtml:function(obj,args){
			return (function(){
				obj.empty();
				//上一页
				if(args.current > 1){
					obj.append('<li><a href="javascript:;" class="prevPage">&laquo;</a></li>');
				}else{
					obj.remove('.prevPage');
					obj.append('<li><a class="disabled">&laquo;</a></li>');
				}
				//中间页码
				if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
					obj.append('<li><a href="javascript:;" class="tcdNumber">'+1+'</a></li>');
				}
				if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
					obj.append('<li><a>...</a></li>');
				}
				var start = args.current -2,end = args.current+2;
				if((start > 1 && args.current < 4)||args.current == 1){
					end++;
				}
				if(args.current > args.pageCount-4 && args.current >= args.pageCount){
					start--;
				}
				for (;start <= end; start++) {
					if(start <= args.pageCount && start >= 1){
						if(start != args.current){
							obj.append('<li><a href="javascript:;" class="tcdNumber">'+ start +'</a></li>');
						}else{
							obj.append('<li class="active"><a class="current">'+ start +'</a></li>');
						}
					}
				}
				if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
					obj.append('<li><a>...</a></li>');
				}
				if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
					obj.append('<li><a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a></li>');
				}
				//下一页
				if(args.current < args.pageCount){
					obj.append('<li><a href="javascript:;" class="nextPage">&raquo;</a></li>');
				}else{
					obj.remove('.nextPage');
					obj.append('<li><a class="disabled">&raquo;</a></li>');
				}
				obj.append('<li><input class="page-go" placeholder="跳转至" /></li><li><a class="page-btn" href="#">GO</a></li>');
			})();
		},
		//绑定事件
		bindEvent:function(obj,args){
			return (function(){
				obj.off("click");
				obj.on("click","a.tcdNumber",function(){
					var current = parseInt($(this).text());
					//ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					}
				});
				//上一页
				obj.on("click","a.prevPage",function(){
					var current = parseInt(obj.find("a.current").text());
					//ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current-1);
					}
				});
				//下一页
				obj.on("click","a.nextPage",function(){
					var current = parseInt(obj.find("a.current").text());
					//ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current+1);
					}
				});
				//go
				obj.on("click","a.page-btn",function(){
					var current = parseInt($(".page-go").val());
					if(current > 0 && current<=args.pageCount){
						//ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
						if(typeof(args.backFn)=="function"){
							args.backFn(current);
						}
					}
				});
			})();
		}
	}
	$.fn.createPage = function(options){
		var args = $.extend({
			pageCount : 15,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);