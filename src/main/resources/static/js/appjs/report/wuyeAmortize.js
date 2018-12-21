var prefix = "/report"
$(function() {
	getTreeData();
	height=$(document.body).height();
	height=height-78;
	$("#exampleTable").attr("data-height",height);
	load();
});
var buildingId='-1';//楼宇ID
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
				showExport: true,                     //是否显示导出
				exportDataType: "basic",
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                queryParams : function(params) {
                    return {
						buildingId : buildingId,
						startDate:$('#startDate').val(),
						endDate : $('#endDate').val(),
						roomCode : $('#roomCode').val()
                    };
                }
			});
}
function reLoad() {
	if(($('#startDate').val()==null||$('#startDate').val()=="")){
		layer.msg("请选择查询开始日期");
		return;
	}if(($('#endDate').val()==null||$('#endDate').val()=="")){
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
	var aa=[];
	var a=[];
	var b=[];
	var i;
	a.push({
		'field' : 'name',
		'title' : '购房人',
		'rowspan': 2
	});
	a.push({
		'field' : 'code', // 列字段名
		'title' : '房屋编号',
		'rowspan': 2
	});
	a.push({
		'field': 'unit',
		'title': '单元',
		'rowspan': 2
	});
	a.push({
		'field' : 'floor', // 列字段名
		'title' : '楼层',
		'rowspan': 2
	});
	a.push({
		'field' : 'buildingArea', // 列字段名
		'title' : '建筑面积',
		'rowspan': 2
	});
	i = (yearE*12+monthE)-(yearS*12+monthS)+1;
	for(var x =1;x<=i;x++){

		a.push({
			title: yearS+'年'+monthS+'月份',
			colspan: 2, align: "center"
		});
		b.push({
			field: 'ying'+x,
			title: '应收摊销', align: "center",
			formatter:function (value) {
				if(!value||value==''){
					return '0.00';
				}else{
					var v=value.split("-")[0];
					return formatData(v);
				}
			}}
		);
		b.push({
			field: 'ying'+x,
			title: '已收摊销', align: "center",
			formatter:function (value) {
				if(!value||value==''){
					return '0.00';
				}else{
					var v=value.split("-")[1];
					return formatData(v);
				}
			}
		});
		monthS = monthS + 1;
		if(monthS>12){
			monthS = monthS - 12;
			yearS = yearS + 1;
		}
	}
	aa.push(a);
	aa.push(b);
	$('#exampleTable').bootstrapTable("refreshOptions",
		{
			url : prefix + "/wuyeAmortizeList",
			fixedColumns: true,
			fixedNumber:2,
			columns : aa
		});
}
function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/realty/building/tree/2",
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
			clear();
		}
	}
});
function clear() {
	buildingId='';
}
function c() {
	$("#roomCode").val('');
	$("#startDate").val('');
	$("#endDate").val('');
}
