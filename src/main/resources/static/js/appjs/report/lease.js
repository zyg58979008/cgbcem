var prefix = "/report"
var isLoad=false;
$(function() {
	getTreeData();
	var height=$(document.body).height();
	height=height-58;
	$("#exampleTable").attr("data-height",height);
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
         'field' : 'roomCode', // 列字段名
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
     /*a.push({
	 'field' : 'roomTypeName', // 列字段名
	 'title' : '房屋类型',
	 });*/
     a.push({
     'field' : 'buildingArea', // 列字段名
     'title' : '建筑面积',
		 'rowspan': 2
     });
     a.push({
     'field' : 'price', // 列字段名
     'title' : '单价',
		 'rowspan': 2
     });
     a.push({
     'field' : 'totalPrice', // 列字段名
     'title' : '价税合计',
	 'rowspan': 2
     });
	i = (yearE*12+monthE)-(yearS*12+monthS)+1;
	for(var x =1;x<=i;x++){

		a.push({
			title: yearS+'年'+monthS+'月份',
			colspan: 3, align: "center"
		});
		b.push({
			field: 'ying'+x,
			title: '应返金额', align: "center",
			formatter:function (value, row, index) {
				if(!value.split('-')[0]||value.split('-')[0]==''){
					return '0.00';
				}else{
					return formatData(value.split('-')[0]);
				}
			}}
		);
		b.push({
			field: 'ying'+x,
			title: '已返金额', align: "center",
			formatter:function (value, row, index) {
				if (!value.split('-')[1] || value.split('-')[1] == '') {
					return '0.00';
				} else {
					return formatData(value.split('-')[1]);
				}
			}}
		);
		b.push({
			field: 'ying'+x,
			title: '未返金额', align: "center",
			formatter:function (value, row, index) {
				if(!value.split('-')[2]||value.split('-')[2]==''){
					return '0.00';
				}else{
					return formatData(value.split('-')[2]);
				}
			}}
		);
		monthS = monthS + 1;
		if(monthS>12){
			monthS = monthS - 12;
			yearS = yearS + 1;
		}
	/*	if(x==i){
			a.push({
				title: '剩余返租金额',
				colspan: 2, align: "center"
			});
		}*/
	}
	aa.push(a);
	aa.push(b);
     $('#exampleTable').bootstrapTable("refreshOptions",
     {
     url : prefix + "/leaseList",
	 fixedColumns: true,
	 fixedNumber:3,
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
function c() {
	$("#roomCode").val('');
	$("#startDate").val('');
	$("#endDate").val('');
}
