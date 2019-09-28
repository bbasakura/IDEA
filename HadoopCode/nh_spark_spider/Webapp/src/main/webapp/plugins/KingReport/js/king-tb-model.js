var kingTb = {
	creatNew : function() {
		var tb = {
			title : {
				text : '员工信息表', // 标题内容,
				link : 'http://www.baidu.com', // 标题链接
				position : 'center', // 标题位置center,left,right
				color : '#000', // 标题颜色
				appearance : {
					background : '#FFF', // 背景颜色
					border : {
						color : '#FFF', // 边框颜色
						type : 'solid', // solid dashed dotted（实线，虚线，点线）
						size : '2px', // 边框大小
						setColor : function(kColor) {
							this.color = kColor;
						},
						setType : function(kType) {
							this.type = kType;
						},
						setSize : function(kSize) {
							this.size = kSize;
						}
					},
					setBackground : function(kBackground) {
						console.log("setBackground");
						// console.log(this.background);
						console.log("color:" + kBackground);
						this.background = kBackground;
						// renderTitle(kingTb.title);
					}
				},
				/*
				 * font-weight参数：
				 * 
				 * normal : 正常的字体。相当于number为400。声明此值将取消之前任何设置 bold :
				 * 粗体。相当于number为700。也相当于b对象的作用 bolder : IE5+ 特粗体 lighter : IE5+
				 * 细体 number : IE5+ 100 | 200 | 300 | 400 | 500 | 600 | 700 |
				 * 800 | 900
				 */

				/*
				 * vertical-align属性的具体定义列表如下： 语法： vertical-align : baseline |
				 * sub | super | top | text- top | middle | bottom | text-bottom |
				 * <百分比> | <长度> | inherit
				 */
				font : {
					color : '#000',
					size : '18px',
					style : 'normal', // normal,oblique,italic,blod
					thickness : 'bolder', // 字体的粗细1-4 1-4渐粗 是否不需要？
					textAlignH : 'center', // 字体水平对齐 left,center,right
					textAlignV : 'middle', // 字体垂直居中 left,center,right
					setColor : function(kColor) {
						this.color = kColor;
					},
					setSize : function(kSize) {
						this.size = kSize;
					},
					setStyle : function(kStyle) {
						this.style = kStyle;
					},
					setThickness : function(kThickness) {
						this.thickness = kThickness;
					},
					setTextAlignH : function(kTextAlignH) {
						this.textAlignH = kTextAlignH;
					},
					setTextAlignV : function(kTextAlignV) {
						this.textAlignV = kTextAlignV;
					}
				},
				setText : function(kText) {
					this.text = kText;
				},
				setLink : function(kLink) {
					this.link = kLink;
				},
				setPosition : function(kPosition) {
					this.position = kPosition;
				},
				setColor : function(kColor) {
					this.color = kColor;
				}
			},
			header : {
				appearance : {
					background : '#ddf5ff', // 背景颜色
					border : {
						color : '#ddf5ff', // 边框颜色
						type : 'solid', // solid dashed dotted（实线，虚线，点线）
						size : '2px', // 边框大小
						setColor : function(kColor) {
							this.color = kColor;
						},
						setType : function(kType) {
							this.type = kType;
						},
						setSize : function(kSize) {
							this.size = kSize;
						}
					},
					setBackground : function(kBackground) {
						this.background = kBackground;
					}
				},
				font : {
					color : '#000',
					size : '15px',
					style : 'normal', // normal,oblique,italic,blod
					thickness : '1', // 字体的粗细1-4 1-4渐粗 是否不需要？
					textAlignH : 'center', // 字体水平对齐 left,center,right
					textAlignV : 'top', // 字体垂直居中 left,center,right
					setColor : function(kColor) {
						this.color = kColor;
					},
					setSize : function(kSize) {
						this.size = kSize;
					},
					setStyle : function(kStyle) {
						this.style = kStyle;
					},
					setThickness : function(kThickness) {
						this.thickness = kThickness;
					},
					setTextAlignH : function(kTextAlignH) {
						this.textAlignH = kTextAlignH;
					},
					setTextAlignV : function(kTextAlignV) {
						this.textAlignV = kTextAlignV;
					}
				}
			},
			body : {
				appearance : {
					background : '#f3f3f3', // 背景颜色
					border : {
						color : '#d9d9d9', // 边框颜色
						type : 'solid', // solid dashed dotted（实线，虚线，点线）
						size : '1px', // 边框大小
						setColor : function(kColor) {
							this.color = kColor;
						},
						setType : function(kType) {
							this.type = kType;
						},
						setSize : function(kSize) {
							this.size = kSize;
						}
					},
					setBackground : function(kBackground) {
						this.background = kBackground;
					}
				},
				font : {
					color : '#000',
					size : '14px',
					style : 'normal', // normal,oblique,italic,blod
					thickness : '1', // 字体的粗细1-4 1-4渐粗 是否不需要？
					textAlignH : 'center', // 字体水平对齐 left,center,right
					textAlignV : 'middle', // 字体垂直居中 left,center,right
					setColor : function(kColor) {
						this.color = kColor;
					},
					setSize : function(kSize) {
						this.size = kSize;
					},
					setStyle : function(kStyle) {
						this.style = kStyle;
					},
					setThickness : function(kThickness) {
						this.thickness = kThickness;
					},
					setTextAlignH : function(kTextAlignH) {
						this.textAlignH = kTextAlignH;
					},
					setTextAlignV : function(kTextAlignV) {
						this.textAlignV = kTextAlignV;
					}
				}
			},
			column : {
				displayColumn : [ {
					name : 'name',
					alias : 'userName',
					index : 0
				}, {
					name : 'position',
					alias : 'position',
					index : 1
				}, {
					name : 'salary',
					alias : 'salary',
					index : 2
				}, {
					name : 'start_date',
					alias : 'start_date',
					index : 4
				}, {
					name : 'office',
					alias : 'office',
					index : 3
				}, {
					name : 'extn',
					alias : 'extn',
					index : 5
				} ],
				ordering : [ {
					columnIndex : 1,
					type : 'desc'
				}, {
					columnIndex : 2,
					type : 'asc'
				} ],
				setDisplayColumn : function(kDisplayColumn) {
					this.displayColumn = kDisplayColumn;
				},
				deleteDisplayColumn : function(name) {
					if (typeof (this.displayColumn) != "undefined"
							&& this.displayColumn != "") {
						for (var i = 0; i < this.displayColumn.length; i++) {
							if (this.displayColumn.name == name) {
								this.displayColumn.slice(i, 1);
							}
						}
					}
				},
				insertDisplayColumn : function(column) {
					if (typeof (column) != "undefined" && column != "") {
						this.displayColumn.push(column);
					}
				},
				insertDisplayColumns : function(columns) {
					if (typeof (columns) != undefined && columns != "") {
						for (var i = 0; i < columns.length; i++) {
							this.displayColumn.push(columns[i]);
						}
					}
				},
				removeAllDisplayColumn : function() {
					this.displayColumn = [];
				},
				setOrdering : function(kOrdering) {
					this.ordering = kOrdering;
				},
				deleteOrderingColumn : function(name) {
					if (typeof (this.ordering) != "undefined"
							&& this.ordering != "") {
						for (var i = 0; i < this.ordering.length; i++) {
							if (this.ordering.name == name) {
								this.ordering.slice(i, 1);
							}
						}
					}
				},
				insertOrderingColumn : function(column) {
					if (typeof (column) != "undefined" && column != "") {
						this.ordering.push(column);
					}
				},
				insertOrderingColumns : function(columns) {
					if (typeof (columns) != undefined && columns != "") {
						for (var i = 0; i < columns.length; i++) {
							this.ordering.push(columns[i]);
						}
					}
				},
				removeAllOrderingColumn : function() {
					this.ordering = [];
				}
			},
			base : {
				thmem : 'blue', // 主题风格
				data : 'data', // 用意暂不明确
				serialNumber : true, // 是否显示序号
				paging : {
					show : true, // 是否显示分页
					count : 1000, // 每页展示的行数
					setShow : function(kShow) {
						this.show = kShow;
					},
					setCount : function(kCount) {
						this.count = kCount;
					}
				},
				isOrdering : true, // 是否排序
				fullingColor : ["#faf7fa","#fff"], // 表格填充颜色
				setTheme : function(kTheme) {
					this.thmem = kTheme;
				},
				setData : function(kData) {
					this.data = kData;
				},
				setSerialNumber : function(kserialNumber) {
					this.serialNumber = kserialNumber;
				},
				setIsOrdering : function(kIsOrdering) {
					this.isOrdering = kIsOrdering;
				},
				setFullingColor : function(kFullingColor) {
					this.fullingColor = kFullingColor;
				},
				insertFullingColor : function(color) {
					this.fullingColor.push(color);
				},
				deleteFullingColor : function(color) {
					if (typeof (this.ffullingColor) != "undefined"
							&& this.fullingColor != "") {
						for (var i = 0; i < this.fullingColor.length; i++) {
							if (this.fullingColor[i] == color) {
								this.fullingColor.slice(1, i);
								break;
							}
						}
					}
				},
				deleteFullingColorByIndex : function(index) {
					if (typeof (this.fullingColor) != "undefined"
							&& this.fullingColor != "") {
						if (index < this.fullingColor.length) {
							this.fullingColor.slice(1, index);
						}
					}
				},
				removeAllFuliingColor : function() {
					this.fullingColor = [];
				}
			},
			highLight : {
				isHighLight : true, // 是否高亮显示
				rangeText : [], // 数值范围类型和时间范围类型
				equalText : [], // 等值高亮类型
				dateText:[],
				setIsHighLight : function(kIsHighLight) {
					this.isHighLight = kIsHighLight;
				},
				setRangeText : function(kRangeText) {
					this.rangeText = kRangeText;
				},
				setEqualText : function(kEqualText) {
					this.equalText = kEqualText;
				},
				setDateText:function(kDateText){
					this.dateText=kDateText;
				},
				column:[],
				setColumn:function(kColumn){
					this.column=kColumn;
				},
				getNumberColumn:function(){
					if(this.column!=[]){
						var numberColumn=[];
						$.each(this.column,function(index,item){
							if(item.type=="number"){
								numberColumn.push(item);
							}
						});
						return numberColumn;
					}else{
						return null;
					}
				},
				getTextColumn:function(){
					if(this.column!=[]){
						var textColumn=[];
						$.each(this.column,function(index,item){
							if(item.type=="text"){
								textColumn.push(item);
							}
						});
						return textColumn;
					}else{
						return null;
					}
				},
				getDateColumn:function(){
					if(this.column!=[]){
						var dateColumn=[];
						$.each(this.column,function(index,item){
							if(item.type=="date"){
								dateColumn.push(item);
							}
						});
						return dateColumn;
					}else{
						return null;
					}
				}
			},
			data:[],
			setData:function(kData){
				this.data=kData;
			},
			total:100
		}
		return tb;
	}
}

