var prefix = "/wuye/wuyefeiConfig"
$(function() {
	getTreeData();
	loadBuildingType();
    load('');
});
var buildingId='0';//楼宇ID
var deptId='';//项目ID
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/listNotHas",
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 30, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
						roomType : $("#roomType1").val(),
						code : $("#code").val(),
						id : $("#id").val(),
                        buildingId : buildingId
                    };
                },
				columns : [
					{
						checkbox : true
					},
                    {
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },
					{
						field : 'code', // 列字段名
						title : '房间编号' // 列标题
					},
					{
						field : 'unit',
						title : '单元'
					},
					{
						field : 'floor',
						title : '楼层'
					},
					{
						field : 'roomTypeName',
						title : '房屋类型'
					},
					{
						field : 'buildingArea',
						title : '建筑面积',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return value.toFixed(2);
							}						}
					},
					{
						field : 'wuyefei',
						title : '物业费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return value.toFixed(2);
							}						}
					}]
			});
}
function add() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要生成物业费的房屋");
		return;
	}
	layer.confirm("确认要生成选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
		// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		var w={};
		w.id=$("#id").val();
		w.ids=ids;
		$.ajax({
			type : 'POST',
			data : {mydata:JSON.stringify(w)},
			url : '/wuye/wuyefeiManage/batchCreate',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}
function reLoad() {

	$('#exampleTable').bootstrapTable('refresh');
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
			deptId=node.state.deptId;
			$('#exampleTable').bootstrapTable('refresh');
		}else{
			buildingId=0;
			clear();
		}
	}
});
function loadBuildingType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/room_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#roomType").append(html);
			$("#roomType").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#roomType').on('change', function(e, params,a) {
				//console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$("#roomType1").val(params.selected);
			});
		}
	});
}
function clear() {
	buildingId='';
	deptId='';
}
function c() {
	$("#code").val('');
	$("#roomType").val('');
	$("#roomType").trigger("chosen:updated");
	$("#roomType1").val('');
}