var prefix = "/report"
$(function() {
	getTreeData();
    load('');
});
var buildingId='';//楼宇ID
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				//url : prefix + "/shopAmortizeList",
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
				showExport: true,                     //是否显示导出
				exportDataType: "basic",
				/*showFooter:true,  //开启底部*/
				//pageSize : 30, // 如果设置了分页，每页数据条数
				//pageNumber : 1, // 如果设置了分布，首页页码
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                queryParams : function(params) {
                    return {
						/*limit : params.limit,
						offset : params.offset,*/
						buildingId : buildingId,
						year:$("#year").val(),
						startDate:$('#startDate').val(),
						endDate : $('#endDate').val(),
						brand : $('#brand').val()
                    };
                }
				/*columns : [
					[
                    {
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                        	if(!row.contractCode||row.contractCode==''){
                        		return '';
							}
                            return index + 1;
                        },
						rowspan: 2
                    },
					{
						field : 'contractCode', // 列字段名
						title : '合同编号',
						rowspan: 2,//标题  可不加
						formatter: function (value, row, index) {
							if(!row.contractCode||row.contractCode==''){
								return '';
							}else{
								return value;
							}
						}
					},
						{
							field : 'shopCode', // 列字段名
							title : '商铺编号',
							rowspan: 2,//标题  可不加
							formatter: function (value, row, index) {
								if(!row.shopCode||row.shopCode==''){
									return '';
								}else{
									return value;
								}
							}
						},
					{
						field : 'contractor',
						title : '签约人',
						rowspan: 2,//标题  可不加
						formatter: function (value, row, index) {
							if(!row.contractor||row.contractor==''){
								return '';
							}else{
								return value;
							}
						}
					},
					{
						field : 'brand',
						title : '品牌',
						rowspan: 2
					},
					{
						field : 'rentArea',
						title : '计租面积',
						rowspan: 2
					},
					{
						title: '1月份',
						colspan: 3, align: "center"
					},
					{
						title: '2月份',
						colspan: 3, align: "center"
					},
					{
						title: '3月份',
						colspan: 3, align: "center"
					},
					{
						title: '4月份',
						colspan: 3, align: "center"
					},
					{
						title: '5月份',
						colspan: 3, align: "center"
					},
					{
						title: '6月份',
						colspan: 3, align: "center"
					},
					{
						title: '7月份',
						colspan: 3, align: "center"
					},
					{
						title: '8月份',
						colspan: 3, align: "center"
					},
					{
						title: '9月份',
						colspan: 3, align: "center"
					},
					{
						title: '10月份',
						colspan: 3, align: "center"
					},
					{
						title: '11月份',
						colspan: 3, align: "center"
					},
					{
						title: '12月份',
						colspan: 3, align: "center"
					}/!*,
						{
							title: '未摊销总计',
							colspan: 3, align: "center"
						}*!/
					  ],
					[{
						field: 'totalZujin1',
						title: '租金摊销', align: "center"
					}, {
						field: 'totalWuye1',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli1',
						title: '管理摊销', align: "center"
					},


						{
							field: 'totalZujin2',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye2',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli2',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin3',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye3',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli3',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin4',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye4',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli4',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin5',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye5',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli5',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin6',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye6',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli6',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin7',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye7',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli7',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin8',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye8',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli8',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin9',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye9',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli9',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin10',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye10',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli10',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin11',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye11',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli11',
						title: '管理摊销', align: "center"
					},

						{
							field: 'totalZujin12',
							title: '租金摊销', align: "center"
						}, {
						field: 'totalWuye12',
						title: '物业摊销', align: "center"
					}, {
						field: 'totalGuanli12',
						title: '管理摊销', align: "center"
					}/!*,{
						field: 'totalZujinUn',
						title: '租金未摊销', align: "center"
					}, {
						field: 'totalWuyeUn',
						title: '物业未摊销', align: "center"
					}, {
						field: 'totalGuanliUn',
						title: '管理未摊销', align: "center"
					}*!/
					]
					],*/
				/*onLoadSuccess : function(data) {
					var data = $('#exampleTable').bootstrapTable('getData', true);
					// 合并单元格
					var fieldList=["contractCode","contractor",
						"brand","rentArea","totalWuye1","totalGuanli1","totalZujin1",
						"totalWuye2","totalGuanli2","totalZujin2",
						"totalWuye3","totalGuanli3","totalZujin3",
						"totalWuye4","totalGuanli4","totalZujin4",
						"totalWuye5","totalGuanli5","totalZujin5",
						"totalWuye6","totalGuanli6","totalZujin6",
						"totalWuye7","totalGuanli7","totalZujin7",
						"totalWuye8","totalGuanli8","totalZujin8",
						"totalWuye9","totalGuanli9","totalZujin9",
						"totalWuye10","totalGuanli10","totalZujin10",
						"totalWuye11","totalGuanli11","totalZujin11",
						"totalWuye12","totalGuanli12","totalZujin12"];
					/!*,
						"totalWuyeUn","totalGuanliUn","totalZujinUn"];*!/
					mergeCells(data, "contractCode", 1, $('#exampleTable'),fieldList);
				}*/
			});
}
function reLoad() {
	if(($('#startDate').val()==null&&$('#startDate').val()=="")){
		layer.msg("请选择查询开始日期");
		return;
	}if(($('#endDate').val()==null&&$('#endDate').val()=="")){
		layer.msg("请选择查询结束日期");
		return;
	}
	var yearS;
	var monthS;
	var yearE;
	var monthE;
	yearS = $('#startDate').val().substr(0,4)*1;
	monthS = $('#startDate').val().substr(5,7)*1;
	yearE = $('#endDate').val().substr(0,4)*1;
	monthE = $('#endDate').val().substr(5,7)*1;
	if((yearE*12+monthE)<(yearS*12+monthS)){
		layer.msg("结束日期不能小于开始日期");
		return;
	}
	if(((yearE*12+monthE)-(yearS*12+monthS))>12){
		layer.msg("时间跨度不可大于一年");
		return;
	}
    var aa=[];
     var a=[];
     var b=[];
    var i;
    var fieldListCh=[];
    fieldListCh.push("contractCode");
    fieldListCh.push("contractor");
    fieldListCh.push("brand");
    fieldListCh.push("rentArea");
    /*a.push({
        'field' : 'num',
        'title': '序号',//标题  可不加
        'rowspan': 2
    });*/
    a.push({
        'field' : 'contractCode', // 列字段名
        'title' : '合同编号',
        'rowspan': 2,
		'class' : 'add-at'//标题  可不加
    });

     a.push({
         'field' : 'shopCode', // 列字段名
         'title' : '商铺编号',
         'rowspan': 2//标题  可不加
     });
     a.push({
         'field' : 'contractor',
         'title' : '签约人',
         'rowspan': 2//标题  可不加
     });
     a.push({
     'field' : 'brand', // 列字段名
     'title' : '品牌',
     'rowspan': 2//标题  可不加
     });
     a.push({
     'field' : 'rentArea', // 列字段名
     'title' : '计租面积',
     'rowspan': 2//标题  可不加
     });
i = (yearE*12+monthE)-(yearS*12+monthS)+1;
for(var x =1;x<=i;x++){

    a.push({
        title: yearS+'年'+monthS+'月份',
        colspan: 3, align: "center"
    });
    b.push({
        field: 'totalZujin'+x,
        title: '租金摊销', align: "center"}
    );
    b.push({
            field: 'totalWuye'+x,
            title: '物业摊销', align: "center"
        });
    b.push({
        field: 'totalGuanli'+x,
        title: '管理摊销', align: "center"
    });
    fieldListCh.push("totalZujin"+x);
    fieldListCh.push("totalWuye"+x);
    fieldListCh.push("totalGuanli"+x);
	monthS = monthS + 1;
	if(monthS>12){
		monthS = monthS - 12;
		yearS = yearS + 1;
	}
	if(x==i){
		a.push({
			title: '剩余摊销金额总数',
			colspan: 3, align: "center"
		});
		b.push({
			field: 'totalZujinUn',
			title: '租金摊销', align: "center"}
		);
		b.push({
			field: 'totalWuyeUn',
			title: '物业摊销', align: "center"
		});
		b.push({
			field: 'totalGuanliUn',
			title: '管理摊销', align: "center"
		});
		fieldListCh.push("totalZujinUn");
		fieldListCh.push("totalWuyeUn");
		fieldListCh.push("totalGuanliUn");
	}
}

     aa.push(a);
     aa.push(b);
     $('#exampleTable').bootstrapTable("refreshOptions",
     {
     url : prefix + "/shopAmortizeList",
		 fixedColumns: true,
		 fixedNumber:0,
		 showColumns: false, // 是否显示内容下拉框（选择显示的列）
     columns : aa
         ,onLoadSuccess : function(data) {
		 $("td.add-at").attr("data-tableexport-msonumberformat","\\@");
             var data = $('#exampleTable').bootstrapTable('getData', true);
             mergeCells(data, "contractCode", 1, $('#exampleTable'),fieldListCh);
         }
     });
}
function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/business/building/tree/2",
		success : function(tree) {
			loadTree(tree);
		}
	});
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search"]
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function(e, data) {
	if(data.node){
		var node=data.node;
		if (node.state.type == '2') {
			buildingId=data.selected[0];
			$('#exampleTable').bootstrapTable('refresh');
		}else{
			if(data.selected[0]=='-1'){
				buildingId='';
			}else{
				buildingId=data.selected[0];
			}
			//$('#exampleTable').bootstrapTable('refresh');
			clear();
		}
	}
});
function clear() {
	buildingId='';
}
function mergeCells(data,fieldName,colspan,target,fieldList){
// 声明一个map计算相同属性值在data对象出现的次数和
	var sortMap = {};
	for(var i = 0 ; i < data.length ; i++){
		for(var prop in data[i]){
			//例如people.unit.name
			var fieldArr=fieldName.split(".");
			getCount(data[i],prop,fieldArr,0,sortMap);
		}
	}
	var index = 0;
	for(var prop in sortMap){
		var count = sortMap[prop];
		for(var i = 0 ; i < fieldList.length ; i++){
			$(target).bootstrapTable('mergeCells',{index:index, field:fieldList[i], colspan: colspan, rowspan: count});
		}
		index += count;
	}
}
function getCount(data,prop,fieldArr,index,sortMap){
	if(index == fieldArr.length-1){
		if(prop == fieldArr[index]){
			var key = data[prop];
			if(sortMap.hasOwnProperty(key)){
				sortMap[key] = sortMap[key]+ 1;
			} else {
				sortMap[key] = 1;
			}
		}
		return;
	}
	if(prop == fieldArr[index]){
		var sdata = data[prop];
		index=index+1;
		getCount(sdata,fieldArr[index],fieldArr,index,sortMap);
	}

}