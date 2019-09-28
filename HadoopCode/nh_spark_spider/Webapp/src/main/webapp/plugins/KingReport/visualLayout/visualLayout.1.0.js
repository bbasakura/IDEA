/**
 * 动态拖动自适应布局<br>
 * 基本逻辑如下：布局layout组成，使用DIV，设定DIV的宽高百分比。一个布局内可以有0个布局或者有2个布局<br>
 * 布局内0个布局，则该布局内直接放置的是具体内容，两个布局则为包含布局，不可能有其他情况<br>
 * 设定拖入时根据指定sourceHandler来设定源对象<br>
 * 
 * Copyright (c) 2015-2016 www.kingpintcn.com. All rights reserved.
 * liyj@kingpintcn.com
 *
 */
(function($) {
	/**
	 * 随机数
	 */
	$.uuid = function() {
		var s = [];
		var hexDigits = "0123456789abcdef";
		for (var i = 0; i < 32; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[14] = "4";
		s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
		s[8] = s[13] = s[18] = s[23];

		var uuid = s.join("");
		return uuid;
	}
	/**
	 * 定义主体样式
	 */
	var classDefault = "visualLayout";
	var classLayoutTemp = "VL_LayoutTemp";
	var classLayoutHover = "VL_Hover"
	var classLayoutContext = "VL_Context";
	var classLayoutDraggableProxy = "VLdragProxy";

	/**
	 * 检查对象是否包含某个样式
	 */
	function checkClass(object, clssName) {
		var clzz = $(object).attr("class");
		if (clzz == null) {
			return;
		}
		clzz = clzz.split(" ");
		var flag = false;
		$.each(clzz, function(i, v) {
					if (v === clssName) {

						flag = true;
						return false;
					}
				})

		return flag;
	}
	/**
	 * 触发事件<br>
	 * options：配置信息<br>
	 * eStr:事件<br>
	 * args:事件参数
	 */
	function triggerEvent(mainLayout, eStr, args) {
		mainLayout = $(mainLayout);
		var options = mainLayout[0].options;
		if (options==null || options.event == null) {
			return;
		}
		if (options.event[eStr] == null) {
			return;
		}
		options.event[eStr].call(mainLayout[0],args);
	}
	/**
	 * 拖拽处理
	 */
	var droppableAction = {

		/**
		 * 被拖拽元素到放置区内的时候触发<br>
		 * 创建遮罩效果<br>
		 * 创建临时布局将原有元素放入
		 */
		onDragEnter : function(e, source) {
			var target = $(e.target);
			/*将布局内所有的内容添加到一个临时布局中*/
			if (target.children().length > 0
					&& $("." + classLayoutTemp, target).length <= 0) {
				var div1 = $("<DIV>");
				div1[0].mainLayout = e.target.mainLayout;
				div1.addClass(classLayoutTemp);
				div1.append(target.children());
				div1.css("width", "100%");
				div1.css("height", "100%");
				div1.css("float", "left");
				target.append(div1);
			}
			/*创建拖入效果*/
			if ($("." + classLayoutHover, target).length <= 0) {
				var div2 = $("<DIV>");
				div2[0].mainLayout = e.target.mainLayout;
				div2.css("float", "left");
				div2.addClass(classLayoutHover);
				div2.hide();
				target.append(div2);
			}
			/*即时触发一个拖拽效果*/
			droppableAction.onDragOver(e, source);
			triggerEvent(target[0].mainLayout, "dragEnter", {
						target : target,
						source : source
					});
		},
		/**
		 * 被拖拽元素经过放置区的时候触发<br>
		 * 实时改变遮罩效果与临时布局的位置
		 */
		onDragOver : function(e, source) {
			var target = $(e.target);
			var temp1 = $("." + classLayoutTemp, target);
			var temp2 = $("." + classLayoutHover, target);
			temp2.show();
			if (temp1.length <= 0 || temp1.children().length <= 0) {
				temp2.css("width", "100%");
				temp2.css("height", "100%");
				return;
			}
			var ppc = droppableAction.getPlaceLocation(e, target);
			/*不允许拖放中间，即拖入时始终处于上下左右*/
			// if (ppc == 'center') {
			// return;
			// }
			if (ppc == 'left' || ppc == 'right') {
				temp1.css("width", "50%");
				temp1.css("height", "100%");
				temp2.css("width", "50%");
				temp2.css("height", "100%");

			} else {
				temp1.css("width", "100%");
				temp1.css("height", "50%");
				temp2.css("width", "100%");
				temp2.css("height", "50%");
			}
			if (ppc == 'left' || ppc == 'top') {
				temp1.before(temp2);
			} else {
				temp1.after(temp2);
			}
			/*处理拖动位置改变时触发事件*/
			if (target[0].addTo != ppc) {
				triggerEvent(target[0].mainLayout, "dragChangeLocal", {
							layout : target,
							source : source,
							location : ppc
						});
			}
			target[0].addTo = ppc;
			triggerEvent(target[0].mainLayout, "drag", {
						layout : target,
						source : source,
						location : ppc
					});
		},
		/**
		 * 被拖拽元素离开放置区的时候触发<br>
		 * 删除遮罩效果以及临时布局
		 */
		onDragLeave : function(e, source) {
			/*删除拖入效果*/
			var target = $(e.target);
			target[0].addTo = null;
			var temp = $("." + classLayoutTemp, target);
			var temp2 = $("." + classLayoutHover, target);
			temp2.detach();
			/*删除临时布局*/
			var childre = temp.children();
			temp.before(childre);
			temp.detach();
			/*重新初始化目标大小改变*/
			resizable.initResizable(target);

			triggerEvent(target[0].mainLayout, "dragOut", {
						layout : target,
						source : source
					});
		},
		/**
		 * 被拖拽元素放入到放置区的时候触发<br>
		 * 放置元素,替换遮罩效果变为实际效果<br>
		 * 如果拖动的元素不是布局，则创建布局，如果时则不创建布局
		 */
		onDrop : function(e, source) {

			source = $(source);
			source.removeClass(classLayoutDraggableProxy);
			var target = $(e.target);
			// 将遮罩替换成实际元素
			var tempLayout = $("." + classLayoutTemp, target);
			var tempHover = $("." + classLayoutHover, target);
			var retV = layoutOps.createLayout({
						mainLayout : target[0].mainLayout,
						tempHover : tempHover,
						source : source
					});
			// 处理临时布局，如果临时布局内只有一个元素则删除临时布局，否则将临时布局变为具体布局
			var children = tempLayout.children();
			if (children.length == 1 && checkClass(children, classDefault)) {
				children.css("width", tempLayout[0].style.width);
				children.css("height", tempLayout[0].style.height);
				tempLayout.before(children);
				tempLayout.detach();
			} else if (tempLayout.length > 0) {
				/*
				 * flag的作用：当拖入到某个布局内部则为false,否则为true； flag处理是否初始化templayout拖拽功能
				 */
				var flag = checkClass(tempLayout.children(), classDefault);
				tempLayout.removeClass(classLayoutTemp);
				tempLayout.addClass(classDefault);
				tempLayout[0].mainLayout = target[0].mainLayout;
				//上级是mainLayout特殊处理
				if(tempLayout.parent().attr('id')==tempLayout[0].mainLayout.id){
					tempLayout.attr('id',"VL"+$.uuid());
				}else{
					tempLayout.attr('id',tempLayout.parent().attr('id'));
					tempLayout.parent().attr('id',"VL"+$.uuid());
					
				}
				layoutOps.initLayout(tempLayout, !flag);
			}

			// 处理拖动大小组件
			resizable.initResizable(retV.layout.parent());
			triggerEvent(e.target.mainLayout, "afterDrag", {
						target : $(e.target),
						layout : retV.layout,
						source : retV.source,
						isNewLayout : retV.source != null
					});

		},
		/**计算拖拽到的位置，上下左右中*/
		getPlaceLocation : function(e, target) {
			var left = target.offset().left;
			var top = target.offset().top;
			var right = left + target.width();
			var bottom = top + target.height();
			/*设定位置与边距的差距内则为此位置*/
			var paddingWidth = target.width() * 0.20;
			var paddingHeight = target.height() * 0.20;
			var x = event.pageX;
			var y = event.pageY;
			var flag = "center";
			// 判断逻辑，左、右、上、下，分别为边线的30%的范围内
			if (x >= left && x <= (left + paddingWidth)) {
				flag = "left";
			}
			if (x >= (right - paddingWidth) && x <= right) {
				flag = "right";
			}
			if (y >= top && y <= (top + paddingHeight)) {
				flag = "top";
			}
			if (y >= (bottom - paddingHeight) && y <= bottom) {
				flag = "bottom";
			}
			return flag;
		}

	}
	/**
	 * 布局相关处理
	 */
	var layoutOps = {
		/**
		 * 初始化布局<br>
		 * isDrag；表示是否初始化布局的拖拽
		 */
		initLayout : function(layout, isDrag) {
			isDrag = isDrag == false ? false : true;
			if (layout.attr('id') == null) {
				layout.attr('id', "VL" + $.uuid());
			}
			if (isDrag) {
				/*只有当标题或标题子对象包含title-moveAction样式才进行拖拽*/
				var title = $(".VL_title", layout);
				var move = $(".title-moveAction", title);
				var flag = false;
				if (move.length <= 0) {
					flag = checkClass(title, "title-moveAction");
				} else {
					flag = true;
				}
				if (flag) {
					var conf = {
						handle : ".title-moveAction",
						/**
						 * 业务关键点：当开始拖动某个布局时<br>
						 * 将被拖动的布局从父级删除添加到body中<br>
						 * 重新设置父级的内容及宽高
						 */
						onStartDrag : function(e) {
							var layoutDiv = $(this);
							/** **将被拖动的布局添加到body中**** */
							var playout = layoutDiv.parent();
							layoutDiv.appendTo("body");
							if (this.mainLayout.options.dragProxy != null
									&& (typeof this.mainLayout.options.dragProxy != 'function')) {
								/*处理代理时大小，此设定为非代理对象，而是修改源对象大小*/
								layoutDiv.css("width",
										this.mainLayout.options.dragProxy.width
												+ "PX");
								layoutDiv
										.css(
												"height",
												this.mainLayout.options.dragProxy.height
														+ "PX");
								layoutDiv.addClass(classLayoutDraggableProxy);
							}
							/*如果父级只剩下一个，并且父级不是mainlayout时删除父级对象，子对象设定为100%
							 * */
							if (playout.children().length >= 1) {
								if (playout[0] != playout[0].mainLayout) {

									playout.children().css("width",
											playout[0].style.width);
									playout.children().css("height",
											playout[0].style.height);
									var children = playout.children();
									playout.before(children);
									playout.detach();
									resizable.initResizable(children.parent());
								} else {
									playout.children().css("width", "100%");
									playout.children().css("height", "100%");
								}
							} else if (playout[0] != this.mainLayout) {
								playout.detach();
							}
							triggerEvent(this.mainLayout, "startDrag",
									{
										layout : layoutDiv
									});
						},
						/**
						 * 停止拖拽，用于监听是否将布局拖出mainLayout，如果是则删除此布局
						 * */
						onStopDrag : function(e) {
							
							var layoutDiv = $(this);
							var flag = this.mainLayout.isLeave;
							if (flag == true) {
								var layoutID = layoutDiv.attr("id");
								layoutDiv.detach();
								triggerEvent(layoutDiv[0].mainLayout,
										"afterClose", {
											layoutID : layoutID
										});
							}
						}
					};
					if (layout[0].mainLayout.options.dragProxy != null
							&& (typeof layout[0].mainLayout.options.dragProxy == 'function')) {
						conf.proxy = layout[0].mainLayout.options.dragProxy;
					}
					layout.VLdraggable(conf);
				}
			}
			layout.VLdroppable(droppableOpts);
		},
		createLayout : function(args) {
			if (checkClass(args.source, classDefault)) {
				return layoutOps.createLayoutForLayout(args);
			} else {
				return layoutOps.createLayoutForNew(args);
			}
		},
		/**
		 * 用于将旧的布局拖拽添加
		 */
		createLayoutForLayout : function(args) {
			args.source.removeAttr("style");
			args.source.css("width", args.tempHover[0].style.width);
			args.source.css("height", args.tempHover[0].style.height);
			var div = $("<DIV>");
			div.append(args.source.children());
			args.tempHover.before(div);
			args.tempHover.detach();

			div[0].mainLayout = args.mainLayout;
			div.attr("class", args.source.attr("class"));
			div.attr("id", args.source.attr("id"));
			div.attr("style", args.source.attr("style"));
			layoutOps.initLayout(div);
			args.source.detach();

			return {
				layout : div
			};
		},
		/**
		 * 用于添加新的布局
		 */
		createLayoutForNew : function(args) {
			var source = args.source.clone();

			var layoutDiv = args.tempHover;
			if (layoutDiv == null) {
				layoutDiv = $("<DIV>");

				layoutDiv.css("width", "100%");
				layoutDiv.css("height", "100%");
			} else {
				layoutDiv.removeClass(classLayoutHover);
			}
			layoutDiv.addClass(classDefault);

			var mainDiv = $("<DIV>");
			mainDiv.css("width", "100%");
			mainDiv.css("height", "100%");
			mainDiv.addClass(classLayoutContext);
			mainDiv.append(source);

			var titleDiv = layoutOps.createTitle(args, mainDiv, source);
			mainDiv.append(titleDiv);

			// 临时处理
			source.css("width", "100%");
			source.css("height", "100%");
			if(args.mainLayout.options.sourceHandler!=null)
				args.mainLayout.options.sourceHandler(source);
			layoutDiv.append(mainDiv);
			layoutDiv[0].mainLayout = args.mainLayout;
			layoutOps.initLayout(layoutDiv);
			return {
				layout : layoutDiv,
				source : source
			};
		},
		/**
		 * 创建标题栏，返回标题DIV
		 */
		createTitle : function(args, mainDiv, source) {
			// 处理配置参数
			var titleOps = layoutOps.parseTitleOpts(args.mainLayout.options,
					source);
			if (titleOps == false) {
				return null;
			}
			if (titleOps.handler != null) {
				try {
					var temp = titleOps.handler({
								source : source,
								opts : titleOps
							});
					temp = $(temp);
					if (temp[0].tagName != 'DIV' && temp[0].tagName != 'div') {
						var titleDiv = $("<DIV>");
						titleDiv.append(temp);
						temp = titleDiv;
					}
					temp.addClass("VL_title");
					if (titleOps.move != false) {
						temp.addClass("title-moveAction");
					}
					return temp;
				} catch (e) {
					titleOps = layoutOps.defaultTitleOps;
				}
			}
			var titleDiv = $("<DIV>");
			titleDiv.addClass("VL_title");
			if (titleOps.overShow == true) {
				titleDiv.hide();
				mainDiv.mouseout(function() {
							var layout2 = $(this).parent();
							var vt = $(".VL_title", layout2);
							if (vt.attr("overShow") == 'false') {
								return;
							}
							vt.hide();

						});
				mainDiv.mouseover(function() {
							var layout2 = $(this).parent();
							$(".VL_title", layout2).show();
						});
			} else {
				titleDiv.attr("overShow", "false");
			}
			if (titleOps.move != false) {
				var moveIcon = $("<DIV>");
				moveIcon.addClass("title-move");
				moveIcon.addClass("title-moveAction");
				titleDiv.append(moveIcon);
			}
			if (titleOps.buttons != null && titleOps.buttons instanceof Array) {
				$.each(titleOps.buttons, function(i, button) {
					if (!(button.hasOwnProperty("icon")
							&& button.hasOwnProperty("handler") && typeof(button.handler) == "function")) {
						return;
					}
					var but = $("<DIV>");
					but.addClass(button.icon);
					titleDiv.append(but);
					but.click(function() {
								button.handler($(this).parent().parent()
										.parent());
							});
					if (button.hasOwnProperty("css")) {
						try {
							$.each(button.css, function(k, v) {
										but.css(k, v);
									});
						} catch (e) {
						}
					}
				});
			}
			if (titleOps.close != false) {
				var closeIcon = $("<DIV>");
				closeIcon.addClass("title-close");
				titleDiv.append(closeIcon);
				closeIcon.click(function() {
							// 删除当前
							var layout2 = $(this).parent().parent().parent();
							if (triggerEvent(layout2[0].mainLayout, "beforeClose", {
										layout : layout2
									}) == false) {
								return;
							}
							layoutOps.removeLayout(layout2);
							triggerEvent(layout2[0].mainLayout, "afterClose", {
										layoutID : layout2.attr('id')
									});
						});
			}
			if (args.mainLayout.options.maxLayout == true) {
				titleDiv.dblclick(function() {
							var layout = $(this).parent().parent();
							var mainLayout = layout[0].mainLayout;
							if (layout.parent()[0] == mainLayout
									&& $(mainLayout).children().length <= 1) {
								return;
							}
							var layouts = $("." + classDefault, mainLayout);
							if (layout.attr('maxWinStyle') != null
									&& layout.attr('maxWinStyle') != '') {
								layouts.show();
								layoutOps.recoverLayout(mainLayout,layout);
								triggerEvent(mainLayout,
										"recoverLayout", {
											layout : layout
										});
							} else {
								layouts.hide();
								layoutOps.maxLayout(mainLayout,layout);
								triggerEvent(mainLayout, "maxLayout", {
											layout : layout
										});
							}
						});
			}
			return titleDiv;
		},
		defaultTitleOps : {
			overShow : true,
			move : true,
			close : true
		},
		parseTitleOpts : function(options, source) {
			if (options == null || options.toolbox == null) {
				// 返回默认
				return layoutOps.defaultTitleOps;
			}
			if (options.toolbox == false) {
				return false;
			}
			try {
				var newOpts = {};
				$.each(options.toolbox, function(k, v) {
							if (k == 'handler') {
								newOpts[k] = v;
								return;
							}
							if (typeof(v) == "function") {
								try {
									newOpts[k] = v({
												source : source
											});
								} catch (e) {
								}
							} else {
								newOpts[k] = v;
							}
						});
				// 处理默认值
				newOpts.overShow = newOpts.overShow == false ? false : true;
				newOpts.move = newOpts.move == false ? false : true;
				newOpts.close = newOpts.close == false ? false : true;
				return newOpts;
			} catch (e) {
				return layoutOps.defaultTitleOps;
			}
		},
		removeLayout : function(layout) {
			var playout = layout.parent();
			if (layout != null)
				layout.detach();

			var children = playout.children();
			if (children.length <= 0) {
				return;
			}
			if (checkClass(children, classDefault)) {
				children.css("width", "100%");
				children.css("height", "100%");

				// 删除父级
				if (playout[0] != playout[0].mainLayout) {
					children.attr("style", playout.attr("style"));

					playout.before(children);
					children.attr("class", playout.attr("class"));
					playout.detach();
					resizable.initResizable(children.parent());

				} else {
					children.css("width", "100%");
					children.css("height", "100%");
				}
			}
		},
		maxLayout : function(mainLayout, layout) {
			layout.show();
			layout.attr("maxWinStyle", layout.attr('style'));
			layout.attr("maxWinClass", layout.attr('class'));
			resizable.disable(layout);
			if (layout.parent()[0] != mainLayout) {
				layoutOps.maxLayout(mainLayout,layout.parent());
			}
			layout.css("width", layout.parent().width);
			layout.css("height",layout.parent().width);
		},
		recoverLayout : function(mainLayout,layout) {
			layout.attr('style', layout.attr('maxWinStyle'));
			layout.attr("class", layout.attr('maxWinClass'));
			layout.removeAttr('maxWinStyle');
			layout.removeAttr('maxWinClass');
			resizable.enable(layout);
			if (layout.parent()[0] != mainLayout) {
				layoutOps.recoverLayout(mainLayout,layout.parent());
			}
		}
	}
	var droppableOpts = {
		/**
		 * 被拖拽元素到放置区内的时候触发<br>
		 * 创建遮罩效果<br>
		 * 创建临时布局将原有元素放入
		 */
		onDragEnter : function(e, source) {
			//拖入始终标记被拖入到mainLayout
			e.target.mainLayout.isLeave = false;
			var target = $(e.target);
			var currentOverTarget = e.target.mainLayout.currentOverTarget;
			if (currentOverTarget != null && currentOverTarget.length > 0) {
				if (currentOverTarget.attr("id") != target.attr("id")) {
					droppableAction.onDragLeave({
								target : currentOverTarget
							});
				}
			}
			e.target.mainLayout.currentOverTarget = target;
			droppableAction.onDragEnter(e, source);
		},
		/**
		 * 被拖拽元素经过放置区的时候触发<br>
		 * 实时改变遮罩效果与临时布局的位置
		 */
		onDragOver : function(e, source) {
			droppableAction.onDragOver({
						target : e.target.mainLayout.currentOverTarget
					}, source);
		},
		/**
		 * 被拖拽元素离开放置区的时候触发<br>
		 * 删除遮罩效果以及临时布局
		 */
		onDragLeave : function(e, source) {
			//标记是否拖动移出mainLayout外
			if(e.target==e.target.mainLayout){
				this.mainLayout.isLeave = true;
			}
			droppableAction.onDragLeave(e, source);
			if (this == this.mainLayout && this.mainLayout.isLeave) {
				triggerEvent(this.mainLayout, "dragOutMain", {
							layout : this.mainLayout.currentOverTarget,
							source : source
						});
			}
		},
		/**
		 * 被拖拽元素放入到放置区的时候触发<br>
		 * 放置元素,替换遮罩效果变为实际效果<br>
		 * 如果拖动的元素不是布局，则创建布局，如果时则不创建布局
		 */
		onDrop : function(e, source) {
			droppableAction.onDrop(e, source);
		}
	}
	var resizable = {
		cleanBorder : function(object) {
			object = $(object);
			object.css({
						"border-bottom" : "0px",
						"border-left" : "0px",
						"border-right" : "0px",
						"border-top" : "0px"
					});
			object.removeClass("resizableBorderbottom");
			object.removeClass("resizableBorderright");
		},
		getLayoutModel : function(layout) {
			if (layout == null || layout.length <= 0) {
				return null;
			}
			if (layout.children().length <= 0) {
				return null;
			}
			var child = layout.children()[0]
			if (child.style.width == '100%') {
				return "row";
			} else {
				return "column";
			}
		},
		/**
		 * 用来初始化主layout的大小调整
		 */
		initMainResizable : function(mainLayout, resizeType) {
			mainLayout.VLresizable({
						handles : resizeType,
						onApplySize : function(resizeData) {

							var width = resizeData.width;
							var height = resizeData.height;
							mainLayout.css("height", height + "px");
							mainLayout.css("width", width + "px");
						},
						onStopResize : function() {
							triggerEvent(this.mainLayout,
									"afterResize", {
										layout : $(this)
									});
						}
					});
		},
		enable : function(layout) {
			layout.VLresizable("enable");
		},
		disable : function(layout) {
			layout.VLresizable("disable");
			layout.removeClass("resizableBorderright");
			layout.removeClass("resizableBorderbottom");
		},
		initResizable : function(layout) {
			if(layout[0].mainLayout.options.resize.doSize==false){return;}
			if (layout.children().length <= 1) {
				var temp = $(layout.children()[0]).VLresizable("disable");
				resizable.cleanBorder(temp);
				return;
			}
			var rh = null;
			var cssName;
			if (resizable.getLayoutModel(layout) == 'row') {
				rh = 's';
				cssName = "resizableBorderbottom";
			} else {
				cssName = "resizableBorderright";
				rh = 'e';
			}
			var temp = $(layout.children()[1]).VLresizable("disable");
			resizable.cleanBorder(temp);
			temp = $(layout.children()[0]).VLresizable("enable");
			resizable.cleanBorder(temp);
			temp.VLresizable({
						handles : rh,
						onApplySize : function(resizeData) {
							var model = resizable.getLayoutModel(temp.parent());
							var width = resizeData.width;
							var height = resizeData.height;
							var toHeight, toWidth;
							if (model == 'row') {
								width = 100;
								toWidth = 100;
								height = height / temp.parent().height() * 100;
								height = height.toFixed(2);
								toHeight = (100 - height)
										/ (temp.parent().children().length - 1);
							} else if (model == 'column') {
								height = 100;
								toHeight = 100;
								width = width / temp.parent().width() * 100;
								width = width.toFixed(2);
								toWidth = (100 - width)
										/ (temp.parent().children().length - 1);
							}
							$.each(temp.parent().children(), function(i, oc) {
										if (temp[0] != oc) {
											$(oc).css("width", toWidth + "%");
											$(oc).css("height", toHeight + "%");
										}

									});
							temp.css("width", width + "%");
							temp.css("height", height + "%");
							// 触发调整布局中事件
							// 无需增加传参，使用布局所引用的MAINLAYOUT即可拿到options
						},
						onStopResize : function() {
							triggerEvent(this.mainLayout,
									"afterResize", {
										layout : $(this)
									});
						}
					});
			if (layout.children().length > 1) {
				temp.removeClass(cssName == "resizableBorderbottom"
						? "resizableBorderright"
						: "resizableBorderbottom");
				if(layout[0].mainLayout.options.resize.borderMode=='display'){
					temp.addClass(cssName);
					temp.attr("VLresize",'true');
				}else if(layout[0].mainLayout.options.resize.borderMode=='over'){
					temp.parent().mouseenter(function(){
						temp.addClass(cssName);
						temp.attr("VLresize",'true');
					});
					temp.parent().mouseleave(function(){
						temp.removeClass(cssName);
					});
				}
			}
		}
	}
	/**
	 * 全局参数<br>
	 * *********************************************************************<br>
	 * 参数名|描述|默认<br>
	 * toolbox|是否显示工具条，可设置为以下配置，也可设置为false，表示不显示工具条<br>
	 * toolbox都可以传入方法进行实时处理,参数传入{source:source}<br>
	 * toolbox.overShow|为true鼠标移入显示，移出影藏，否则一直显示<br>
	 * toolbox.handler|返回工具条对象，此属性将覆盖工具条具体配置，{source:source,opts:{}}<br>
	 * |默认为null<br>
	 * toolbox.move|是否可以移动,默认为true<br>
	 * toolbox.close|是否可以关闭，默认为true<br>
	 * toolbox.buttons|对象，设置工具条按钮，将在布局右上角显示，[{icon:'',handler:'',title:''}]|默认为null<br>
	 * resize.doSize|是否可调整大小，默认为true<br>
	 * resize.borderMode|边框模式取值over(鼠标移入移出改变),display(一直显示)默认为display；如果doSize为false时不显示边框
	 * dragProxy|拖拽时的代理,可设置{width:,height:,}或者function代理类，否则将默认拖拽对象大小
	 * sourceHandler |source处理对象，传入source源对象， 返回新的source对象 (DOM对象)| null <br>
	 * resizeMain|是否对主窗口进行大小调整，设置为false不调整,否则设置为s为可调整高度，默认为false<br>
	 * maxLayout|是否可以最大化某个布局，默认为false
	 * ***************************************************************************************************<br>
	 * 方法
	 * ***************************************************************************************************<br>
	 * enableResize|设置全局，可调整大小
	 * disableResize|设置全局，不可调整大小
	 * enableDrag|设置全局，可进行拖动
	 * disableDrag|设置全局，不可进行拖动
	 * remove|删除某个layout，参数：layoutID
	 * removeAll|删除所有layout
	 * getData|获取JSON格式数据
	 * setData|设置JSON格式数据，参数:从getData获取的JSON格式数据
	 * getLayoutBySource|根据source获取到layout，参数:source对象
	 * getLayoutById|根据ID获取到layout，参数：layoutID
	 * getSourceByLayoutID|根据layoutID获取到source对象，参数：layoutID
	 * ***************************************************************************************************<br>
	 * 事件如下,事件全部定义在参数中的event中 <br>
	 * ***************************************************************************************************<br>
	 * afterClose |点击关闭按钮之后触发，{layoutID:layoutID} | <br>
	 * beforeClose |点击关闭按钮之前触发，{layout:layout}|如果返回false则不关闭<br>
	 * afterResize |调整布局大小之后,{layout:layout}<br>
	 * afterDrag|在布局内放置之后{target:目标layout,layout:layout,source:source,isNewLayout:是否为新的布局}<br>
	 * afterDraw|在setData时完成对布局的渲染触发{target:目标layout,layout:layout,source:source}<br>
	 * dragEnter|拖入到某个布局时触发{target:目标layout,source:source}<br>
	 * dragOut|拖出某个布局时触发{layout:源layout,source:source}<br>
	 * dragOutMain|拖拽出主布局时触发{layout:源layout,source:source}<br>
	 * drag|拖动时触发{layout:源layout,source:source,location:'center|left|right|top|bottom'}
	 * dragChangeLocal|拖动并改变放置位置时触发{layout:源layout,source:source,location:'center|left|right|top|bottom'}
	 * startDrag|开始拖拽前{layout:被拖拽的layout} maxLayout|最大化layout时触发{layout:layout}
	 * recoverLayout|还原layout时触发{layout:layout}
	 * 
	 */
	$.fn.visualLayout = function(options, param) {
		if (typeof options == 'string') {
			if (options == 'options') {
				return this[0].mainLayout.options;
			}
			var fuc = $.fn.visualLayout.methods[options];
			if(fuc!=null&& typeof fuc === 'function' ){
			return fuc(this, param);
			}
			return null;
		}
		this.each(function() {
					if (!/div/gi.test(this.tagName)) {
						throw new Error("visualLayout1.0 onlay select div");
					}
				});
		// 处理参数
		if (options.dragProxy != null
				&& (typeof options.dragProxy != 'function')) {
			if (!(options.dragProxy.hasOwnProperty("width") && options.dragProxy
					.hasOwnProperty("height"))) {
				options.dragProxy = null;
			}
		}
		options.resizeMain = options.resizeMain == 's'
				|| options.resizeMain == 'S' ? 's' : false;
		options.maxLayout = options.maxLayout == true ? true : false;
		//最大化功能还有问题，先设置为默认false
		options.maxLayout=false;
		options.resize = options.resize==null?{}:options.resize;
		options.resize.doSize = options.resize.doSize==false?false:true;
		options.resize.borderMode = options.resize.borderMode=='over'|| options.resize.borderMode=='display'? options.resize.borderMode:'display';
		return this.each(function() {
					$(this).addClass(classDefault).VLdroppable(droppableOpts);
					this.mainLayout = this;
					this.mainLayout.options = options;
					if (options.resizeMain != false)
						resizable
								.initMainResizable($(this), options.resizeMain);
				});
	};

	$.fn.visualLayout.methods = {
		/**
		 * 根据source获取到layout
		 */
		getLayoutBySource:function(jq,which){
			var layout = $(which).parent().parent();
			if(checkClass(layout,classDefault)){
				return layout;
			}
			return null;
		},
		/**
		 * 根据ID获取到layout
		 */
		getLayoutById:function(jq,which){
			var layout = $("#" + which, jq);
			return layout;
		},
		/**
		 * 根据layoutID获取到source对象
		 */
		getSourceByLayoutID:function(jq,which){
			var layout = $("#" + which, jq);
			if(layout==null||layout.length<=0){return null;}
			return $(":not(.VL_title)",layout);
		},
		/**
		 * 删除一个布局对象
		 */
		remove : function(jq, which) {
			if (which == null) {
				return;
			}
			$.each(jq, function(i, obj) {
						var layout = $("#" + which, obj);
						if (layout.length <= 0) {
							return;
						}
						layoutOps.removeLayout(layout);
					})

		},
		/**
		 * 删除所有布局对象
		 */
		removeAll : function(jq, which) {
			$.each(jq, function(i, obj) {
						$(obj).children().detach();
					})
		},
		/**
		 * 获取布局的JSON格式
		 */
		getData : function(jq, which) {
			jq = $(jq);
			if (jq.attr("class").indexOf(classDefault) == -1) {
				return null;
			}
			function getJson(object) {
				var json = {
					id : object.attr("id"),
					width : object[0].style.width,
					height : object[0].style.height,
					mode : null
				}
				var temp = object.children();
				if (temp.length > 0) {
					json.children = [];
					$.each(temp, function(i, obj) {
								obj = $(obj);
								if (!(obj.attr("class") != null && obj
										.attr("class").indexOf(classDefault) != -1)) {
									return;
								}
								var pc = getJson($(obj));
								if (pc != null) {
									if (pc.width == '100%'
											&& pc.height != '100%') {
										json.mode = 'row';
									} else if (pc.height == '100%'
											&& pc.width != '100%') {
										json.mode = 'column';
									} else {
										delete json.mode;
									}
									json.children.push(pc);
								}
							});
				}
				return json;
			}

			return getJson(jq);
		},
		/**
		 * 设置布局的JSON格式
		 */
		setData : function(jq, which) {
			var options = jq.visualLayout("options");
			function execute(div, conf) {
				if (conf.children == null || conf.children.length <= 0) {
					return;
				}
				$.each(conf.children, function(i, lv) {
							if (lv.children != null && lv.children.length > 0) {
								var div2 = $("<DIV>");
								div2[0].mainLayout = jq[0].mainLayout;
								div2.addClass(classDefault);
								div2.attr("id", lv.id);
								div2.css("width", lv.width);
								div2.css("height", lv.height);
								div.append(div2);
								execute(div2, lv);
								return;
							}
							var source = $("<DIV>");
							var retV = layoutOps.createLayout({
										mainLayout : jq[0].mainLayout,
										source : source
									});
							retV.layout.attr("id", lv.id);
							retV.layout.css("width", lv.width);
							retV.layout.css("height", lv.height);
							div.append(retV.layout);
							triggerEvent(jq[0].mainLayout, "afterDraw",
									{
										target : div,
										layout : retV.layout,
										source : retV.source,
										isNewLayout : true
									});
						});
				resizable.initResizable(div);
			}
			jq.visualLayout("removeAll");
			execute(jq, which);
			
		},
		/**
		 * 允许全局改变大小
		 */
		enableResize:function(jq,which){
			var temps = $("."+classDefault+"[VLresize='true']",jq);
			$.each(temps,function(i,obj){
				resizable.initResizable($(obj).parent());
			})
			return jq;
		},
		/**
		 * 不允许全局改变大小
		 */
		disableResize:function(jq,which){
			$("."+classDefault+"[VLresize='true']",jq).VLresizable("disable");
			return jq;
		},
		/**
		 * 允许全局拖拽
		 */
		enableDrag:function(jq,which){
			$("."+classDefault,jq).VLdraggable("enable");
			return jq;
		},
		/**
		 * 不允许全局拖拽
		 */
		disableDrag:function(jq,which){
			$("."+classDefault,jq).VLdraggable("disable");
			return jq;
		}
	}
})(jQuery);
// 以下基于easyui1.4.5源码进行修改
(function($) {
	function drag(e) {
		var state = $.data(e.data.target, 'VLdraggable');
		var opts = state.options;
		var proxy = state.proxy;

		var dragData = e.data;
		var left = e.pageX;
		var top = e.pageY;
		if (opts.axis == 'h') {
			dragData.left = left;
		} else if (opts.axis == 'v') {
			dragData.top = top;
		} else {
			dragData.left = left;
			dragData.top = top;
		}
	}

	function applyDrag(e) {
		var state = $.data(e.data.target, 'VLdraggable');
		var opts = state.options;
		var proxy = state.proxy;
		if (!proxy) {
			proxy = $(e.data.target);
		}
		proxy.css({
					left : e.data.left,
					top : e.data.top
				});
		$('body').css('cursor', opts.cursor);
	}

	function doDown(e) {
		if (!$.fn.VLdraggable.isDragging) {
			return false;
		}

		var state = $.data(e.data.target, 'VLdraggable');
		var opts = state.options;

		var droppables = $('.VLdroppable').filter(function() {
					return e.data.target != this;
				}).filter(function() {
					var accept = $.data(this, 'VLdroppable').options.accept;
					if (accept) {
						return $(accept).filter(function() {
									return this == e.data.target;
								}).length > 0;
					} else {
						return true;
					}
				});
		state.droppables = droppables;

		var proxy = state.proxy;
		if (!proxy) {
			if (opts.proxy) {
				if (opts.proxy == 'clone') {
					proxy = $(e.data.target).clone().insertAfter(e.data.target);
				} else {
					proxy = opts.proxy.call(e.data.target, e.data.target);
				}
				state.proxy = proxy;
			} else {
				proxy = $(e.data.target);
			}
		}

		proxy.css('position', 'absolute');
		drag(e);
		applyDrag(e);

		opts.onStartDrag.call(e.data.target, e);
		return false;
	}

	function doMove(e) {
		if (!$.fn.VLdraggable.isDragging) {
			return false;
		}

		var state = $.data(e.data.target, 'VLdraggable');
		drag(e);
		if (state.options.onDrag.call(e.data.target, e) != false) {
			applyDrag(e);
		}

		var source = e.data.target;
		state.droppables.each(function() {
					var dropObj = $(this);
					if (dropObj.VLdroppable('options').disabled) {
						return;
					}

					var p2 = dropObj.offset();
					if (e.pageX > p2.left
							&& e.pageX < p2.left + dropObj.outerWidth()
							&& e.pageY > p2.top
							&& e.pageY < p2.top + dropObj.outerHeight()) {
						if (!this.entered) {
							$(this).trigger('_dragenter', [source]);
							this.entered = true;
						}
						$(this).trigger('_dragover', [source]);
					} else {
						if (this.entered) {
							$(this).trigger('_dragleave', [source]);
							this.entered = false;
						}
					}
				});

		return false;
	}

	function doUp(e) {
		if (!$.fn.VLdraggable.isDragging) {
			clearDragging();
			return false;
		}

		doMove(e);

		var state = $.data(e.data.target, 'VLdraggable');
		var proxy = state.proxy;
		var opts = state.options;
		if (opts.revert) {
			if (checkDrop() == true) {
				$(e.data.target).css({
							position : e.data.startPosition,
							left : e.data.startLeft,
							top : e.data.startTop
						});
			} else {
				if (proxy) {
					var left, top;
					if (proxy.parent()[0] == document.body) {
						left = e.data.startX - e.data.offsetWidth;
						top = e.data.startY - e.data.offsetHeight;
					} else {
						left = e.data.startLeft;
						top = e.data.startTop;
					}
					proxy.animate({
								left : left,
								top : top
							}, function() {
								removeProxy();
							});
				} else {
					$(e.data.target).animate({
								left : e.data.startLeft,
								top : e.data.startTop
							}, function() {
								$(e.data.target).css('position',
										e.data.startPosition);
							});
				}
			}
		} else {
			$(e.data.target).css({
						position : 'absolute',
						left : e.data.left,
						top : e.data.top
					});
			checkDrop();
		}

		opts.onStopDrag.call(e.data.target, e);

		clearDragging();

		function removeProxy() {
			if (proxy) {
				proxy.remove();
			}
			state.proxy = null;
		}

		function checkDrop() {
			var dropped = false;
			state.droppables.each(function() {
						var dropObj = $(this);
						if (dropObj.VLdroppable('options').disabled) {
							return;
						}

						var p2 = dropObj.offset();
						if (e.pageX > p2.left
								&& e.pageX < p2.left + dropObj.outerWidth()
								&& e.pageY > p2.top
								&& e.pageY < p2.top + dropObj.outerHeight()) {
							if (opts.revert) {
								$(e.data.target).css({
											position : e.data.startPosition,
											left : e.data.startLeft,
											top : e.data.startTop
										});
							}
							$(this).trigger('_drop', [e.data.target]);
							removeProxy();
							dropped = true;
							this.entered = false;
							return false;
						}
					});
			if (!dropped && !opts.revert) {
				removeProxy();
			}
			return dropped;
		}

		return false;
	}

	function clearDragging() {
		if ($.fn.VLdraggable.timer) {
			clearTimeout($.fn.VLdraggable.timer);
			$.fn.VLdraggable.timer = undefined;
		}
		$(document).unbind('.VLdraggable');
		$.fn.VLdraggable.isDragging = false;
		setTimeout(function() {
					$('body').css('cursor', '');
				}, 100);
	}

	$.fn.VLdraggable = function(options, param) {
		if (typeof options == 'string') {
			return $.fn.VLdraggable.methods[options](this, param);
		}

		return this.each(function() {
			var opts;
			var state = $.data(this, 'VLdraggable');
			if (state) {
				state.handle.unbind('.VLdraggable');
				opts = $.extend(state.options, options);
			} else {
				opts = $.extend({}, $.fn.VLdraggable.defaults, $.fn.VLdraggable
								.parseOptions(this), options || {});
			}
			var handle = opts.handle ? (typeof opts.handle == 'string' ? $(
					opts.handle, this) : opts.handle) : $(this);

			$.data(this, 'VLdraggable', {
						options : opts,
						handle : handle
					});

			if (opts.disabled) {
				$(this).css('cursor', '');
				return;
			}

			handle.unbind('.VLdraggable').bind('mousemove.VLdraggable', {
						target : this
					}, function(e) {
						if ($.fn.VLdraggable.isDragging) {
							return
						}
						var opts = $.data(e.data.target, 'VLdraggable').options;
						if (checkArea(e)) {
							$(this).css('cursor', opts.cursor);
						} else {
							$(this).css('cursor', '');
						}
					}).bind('mouseleave.VLdraggable', {
						target : this
					}, function(e) {
						$(this).css('cursor', '');
					}).bind('mousedown.VLdraggable', {
						target : this
					}, function(e) {
						if (checkArea(e) == false)
							return;
						$(this).css('cursor', '');

						var position = $(e.data.target).position();
						var offset = $(e.data.target).offset();
						var data = {
							startPosition : $(e.data.target).css('position'),
							startLeft : position.left,
							startTop : position.top,
							left : position.left,
							top : position.top,
							startX : e.pageX,
							startY : e.pageY,
							offsetWidth : (e.pageX - offset.left),
							offsetHeight : (e.pageY - offset.top),
							target : e.data.target,
							parent : $(e.data.target).parent()[0]
						};

						$.extend(e.data, data);
						var opts = $.data(e.data.target, 'VLdraggable').options;
						if (opts.onBeforeDrag.call(e.data.target, e) == false)
							return;

						$(document).bind('mousedown.VLdraggable', e.data,
								doDown);
						$(document).bind('mousemove.VLdraggable', e.data,
								doMove);
						$(document).bind('mouseup.VLdraggable', e.data, doUp);

						$.fn.VLdraggable.timer = setTimeout(function() {
									$.fn.VLdraggable.isDragging = true;
									doDown(e);
								}, opts.delay);
						return false;
					});

			// check if the handle can be dragged
			function checkArea(e) {
				var state = $.data(e.data.target, 'VLdraggable');
				var handle = state.handle;
				var offset = $(handle).offset();
				var width = $(handle).outerWidth();
				var height = $(handle).outerHeight();
				var t = e.pageY - offset.top;
				var r = offset.left + width - e.pageX;
				var b = offset.top + height - e.pageY;
				var l = e.pageX - offset.left;

				return Math.min(t, r, b, l) > state.options.edge;
			}

		});
	};

	$.fn.VLdraggable.methods = {
		options : function(jq) {
			return $.data(jq[0], 'VLdraggable').options;
		},
		proxy : function(jq) {
			return $.data(jq[0], 'VLdraggable').proxy;
		},
		enable : function(jq) {
			return jq.each(function() {
						$(this).VLdraggable({
									disabled : false
								});
					});
		},
		disable : function(jq) {
			return jq.each(function() {
						$(this).VLdraggable({
									disabled : true
								});
					});
		}
	};

	$.fn.VLdraggable.parseOptions = function(target) {
		return {};
	};

	$.fn.VLdraggable.defaults = {
		proxy : null,
		revert : false,
		cursor : 'move',
		deltaX : null,
		deltaY : null,
		handle : null,
		disabled : false,
		edge : 0,
		axis : null, // v or h
		delay : 100,

		onBeforeDrag : function(e) {
		},
		onStartDrag : function(e) {
		},
		onDrag : function(e) {
		},
		onStopDrag : function(e) {
		}
	};

	$.fn.VLdraggable.isDragging = false;

})(jQuery);
(function($) {
	function init(target) {
		$(target).addClass('VLdroppable');
		$(target).bind('_dragenter', function(e, source) {
			$.data(target, 'VLdroppable').options.onDragEnter.apply(target, [e,
							source]);
		});
		$(target).bind('_dragleave', function(e, source) {
			$.data(target, 'VLdroppable').options.onDragLeave.apply(target, [e,
							source]);
		});
		$(target).bind('_dragover', function(e, source) {
			$.data(target, 'VLdroppable').options.onDragOver.apply(target, [e,
							source]);
		});
		$(target).bind('_drop', function(e, source) {
			$.data(target, 'VLdroppable').options.onDrop.apply(target, [e,
							source]);
		});
	}

	$.fn.VLdroppable = function(options, param) {
		if (typeof options == 'string') {
			return $.fn.VLdroppable.methods[options](this, param);
		}

		options = options || {};
		return this.each(function() {
					var state = $.data(this, 'VLdroppable');
					if (state) {
						$.extend(state.options, options);
					} else {
						init(this);
						$.data(this, 'VLdroppable', {
									options : $
											.extend(
													{},
													$.fn.VLdroppable.defaults,
													$.fn.VLdroppable
															.parseOptions(this),
													options)
								});
					}
				});
	};

	$.fn.VLdroppable.methods = {
		options : function(jq) {
			return $.data(jq[0], 'VLdroppable').options;
		},
		enable : function(jq) {
			return jq.each(function() {
						$(this).VLdroppable({
									disabled : false
								});
					});
		},
		disable : function(jq) {
			return jq.each(function() {
						$(this).VLdroppable({
									disabled : true
								});
					});
		}
	};

	$.fn.VLdroppable.parseOptions = function(target) {
		return {};
	};

	$.fn.VLdroppable.defaults = {
		accept : null,
		disabled : false,
		onDragEnter : function(e, source) {
		},
		onDragOver : function(e, source) {
		},
		onDragLeave : function(e, source) {
		},
		onDrop : function(e, source) {
		}
	};
})(jQuery);

/**
 * 拖动调整大小组件<br>
 * 基于Easyui1.3.6源码进行修改，增加了onApplySize事件，用于调整大小<br>
 * 设置了此事件，将在事件内修改对象大小，事件传入resizeData，包含width,height,top.left
 */
(function($) {
	$.fn.VLresizable = function(options, param) {
		if (typeof options == "string") {
			return $.fn.VLresizable.methods[options](this, param)
		}
		function resize(e) {
			var src = $(e.data.target);
			var resizeData = e.data;
			var options = $.data(resizeData.target, "resizable").options;
			if (resizeData.dir.indexOf("e") != -1) {
				var width = resizeData.startWidth + e.pageX - resizeData.startX;
				width = Math.min(
							Math.max(width, options.minWidth),
							options.maxWidth
						);
				resizeData.width = width
			}
			if (resizeData.dir.indexOf("s") != -1) {
				var height = resizeData.startHeight + e.pageY
						- resizeData.startY;
				height = Math.min(
						Math.max(height, options.minHeight),
						options.maxHeight
				);
				resizeData.height = height
			}
			if (resizeData.dir.indexOf("w") != -1) {
				var width = resizeData.startWidth - e.pageX + resizeData.startX;
				width = Math.min(
							Math.max(width, options.minWidth),
							options.maxWidth
						);
				resizeData.width = width;
				resizeData.left = resizeData.startLeft + resizeData.startWidth
						- resizeData.width
			}
			if (resizeData.dir.indexOf("n") != -1) {
				var height = resizeData.startHeight - e.pageY
						+ resizeData.startY;
				height = Math.min(
							Math.max(height, options.minHeight),
							options.maxHeight
						);
				resizeData.height = height;
				resizeData.top = resizeData.startTop + resizeData.startHeight
						- resizeData.height
			}
		}
		function applySize(e) {
			var resizeData = e.data;
			if (options.onApplySize != null) {
				options.onApplySize(resizeData);
				return
			}
			var t = $(resizeData.target);
			t.css({
						left : resizeData.left,
						top : resizeData.top
					});
			if (t.outerWidth() != resizeData.width) {
				t._outerWidth(resizeData.width)
			}
			if (t.outerHeight() != resizeData.height) {
				t._outerHeight(resizeData.height)
			}
		}
		function doDown(e) {
			$.fn.VLresizable.isResizing = true;
			$.data(e.data.target, "resizable").options.onStartResize.call(
					e.data.target, e);
			return false
		}
		function doMove(e) {
			resize(e);
			if ($.data(e.data.target, "resizable").options.onResize.call(
					e.data.target, e) != false) {
				applySize(e)
			}
			return false
		}
		function doUp(e) {
			$.fn.VLresizable.isResizing = false;
			resize(e, true);
			applySize(e);
			$.data(e.data.target, "resizable").options.onStopResize.call(
					e.data.target, e);
			$(document).unbind(".VLresizable");
			$("body").css("cursor", "");
			return false
		}
		return this.each(function() {
			var opts = null;
			var state = $.data(this, "resizable");
			if (state) {
				$(this).unbind(".VLresizable");
				opts = $.extend(state.options, options || {})
			} else {
				opts = $.extend({}, $.fn.VLresizable.defaults, $.fn.VLresizable
								.parseOptions(this), options || {});
				$.data(this, "resizable", {
							options : opts
						})
			}
			if (opts.disabled == true) {
				return
			}
			$(this).bind("mousemove.VLresizable", {
						target : this
					}, function(e) {
						if ($.fn.VLresizable.isResizing) {
							return
						}
						var dir = getDirection(e);
						if (dir == "") {
							$(e.data.target).css("cursor", "")
						} else {
							$(e.data.target).css("cursor", dir + "-resize")
						}
					}).bind("mouseleave.VLresizable", {
						target : this
					}, function(e) {
						$(e.data.target).css("cursor", "")
					}).bind("mousedown.VLresizable", {
						target : this
					}, function(e) {
						var dir = getDirection(e);
						if (dir == "") {
							return
						}
						function getCssValue(css) {
							var val = parseInt($(e.data.target).css(css));
							if (isNaN(val)) {
								return 0
							} else {
								return val
							}
						}
						var data = {
							target : e.data.target,
							dir : dir,
							startLeft : getCssValue("left"),
							startTop : getCssValue("top"),
							left : getCssValue("left"),
							top : getCssValue("top"),
							startX : e.pageX,
							startY : e.pageY,
							startWidth : $(e.data.target).outerWidth(),
							startHeight : $(e.data.target).outerHeight(),
							width : $(e.data.target).outerWidth(),
							height : $(e.data.target).outerHeight(),
							deltaWidth : $(e.data.target).outerWidth()
									- $(e.data.target).width(),
							deltaHeight : $(e.data.target).outerHeight()
									- $(e.data.target).height()
						};
						$(document).bind("mousedown.VLresizable", data, doDown);
						$(document).bind("mousemove.VLresizable", data, doMove);
						$(document).bind("mouseup.VLresizable", data, doUp);
						$("body").css("cursor", dir + "-resize")
					});
			function getDirection(e) {
				var tt = $(e.data.target);
				var dir = "";
				var offset = tt.offset();
				var width = tt.outerWidth();
				var height = tt.outerHeight();
				var edge = opts.edge;
				if (e.pageY > offset.top && e.pageY < offset.top + edge) {
					dir += "n"
				} else {
					if (e.pageY < offset.top + height
							&& e.pageY > offset.top + height - edge) {
						dir += "s"
					}
				}
				if (e.pageX > offset.left && e.pageX < offset.left + edge) {
					dir += "w"
				} else {
					if (e.pageX < offset.left + width
							&& e.pageX > offset.left + width - edge) {
						dir += "e"
					}
				}
				var handles = opts.handles.split(",");
				for (var i = 0; i < handles.length; i++) {
					var handle = handles[i].replace(/(^\s*)|(\s*$)/g, "");
					if (handle == "all" || handle == dir) {
						return dir
					}
				}
				return ""
			}
		})
	};
	$.fn.VLresizable.methods = {
		options : function(jq) {
			return $.data(jq[0], "resizable").options
		},
		enable : function(jq) {
			return jq.each(function() {
						$(this).VLresizable({
									disabled : false
								})
					})
		},
		disable : function(jq) {
			return jq.each(function() {
						$(this).VLresizable({
									disabled : true
								})
					})
		}
	};
	$.fn.VLresizable.parseOptions = function(target) {
		return {}
		/*
		 * var t = $(target); return $.extend({},
		 * $.VLparser.parseOptions(target, ["handles", { minWidth : "number",
		 * minHeight : "number", maxWidth : "number", maxHeight : "number", edge :
		 * "number" }]), { disabled : (t.attr("disabled") ? true : undefined) })
		 */
	};
	$.fn.VLresizable.defaults = {
		disabled : false,
		handles : "n, e, s, w, ne, se, sw, nw, all",
		minWidth : 30,
		minHeight : 30,
		maxWidth : 10000,
		maxHeight : 10000,
		edge : 5,
		onStartResize : function(e) {
		},
		onResize : function(e) {
		},
		onStopResize : function(e) {
		}
	};
	$.fn.VLresizable.isResizing = false
})(jQuery);
