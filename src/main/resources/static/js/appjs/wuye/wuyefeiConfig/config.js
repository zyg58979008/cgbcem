var prefix = "/wuye/wuyefeiConfig";
var isLoad=false;
$(function() {
	getTreeData();
	loadBuildingType();

	var height=$(document.body).height();
	height=height-58;
	$("#exampleTable").attr("data-height",height);
    load();
});
var buildingId='';//楼宇ID
var deptId='';//项目ID
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
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
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
						roomType : $("#roomType1").val(),
						code : $("#code").val(),
                        buildingId : buildingId
                    };
                },
				columns : [
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
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit "></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm ' + s_split_h + '" href="#" title="拆分"  mce_href="#" onclick="resetPwd(\''
								+ row.id
								+ '\')"><i class="fa fa-key"></i></a> ';
							return e ;
						}
					} ]
			});
}
function add() {
	if(buildingId==''){
		layer.msg('请先选择楼宇');
		return;
	}
	// iframe层
	layer.open({
		type : 2,
		title : '物业费配置',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '100%' ],
		content : prefix + '/add'
	});
}
function reLoad() {
	if(!isLoad){
		$('#exampleTable').bootstrapTable("refreshOptions",
			{
				url : prefix + "/list",
			});
		load();
		isLoad=true;
	}else {

		$('#exampleTable').bootstrapTable('refresh');
	}
}
function edit(id) {
	layer.open({
		type : 2,
		title : '修改物业费',
		maxmin : true,
		shadeClose : false,
		area : [ '300px', '270px' ],
		content : prefix + '/edit/' + id // iframe的url
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
			deptId=node.state.deptId;
			reLoad();
		}else{
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