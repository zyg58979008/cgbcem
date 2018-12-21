
var prefix = "/business/entrust";
var buildingId = '';//楼宇ID
var isLoad=false;
$(function() {
	getTreeData();
	var height=$(document.body).height();
	height=height-58;
	$("#exampleTable").attr("data-height",height);
	load();
});
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
				fixedColumns: true,
				fixedNumber:3,
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						code : $("#code").val(),
						year : $("#year").val(),
						buildingId:buildingId,
						delFlag:'0'
					};
				},
				columns : [
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm" href="#" mce_href="#" title="返租" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit "></i></a> ';
							return e;
						}
					},
					{
						title: '序号',//标题  可不加
						formatter: function (value, row, index) {
							return index + 1;
						},
					},
					{
						field: 'buildingName',
						title: '楼宇名称',
						align: "center"

					},
					{
						field: 'roomCode',
						title: '房屋编号',
						align: "center"
					},
					{
						field: 'unit',
						title: '单元',
						align: "center"
					},
					{
						field: 'floor',
						title: '楼层',
						align: "center"
					},
					{
						field: 'roomTypeName',
						title: '房屋类型',
						align: "center"
					},
					{
						field: 'buildingArea',
						title: '建筑面积',
						align: "center",
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return value.toFixed(2);
							}
						}
					},
					{
						field: 'price',
						title: '单价',
						align: "center",
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return value.toFixed(2);
							}
						}
					},
					{
						field: 'totalPrice',
						title: '价税合计',
						align: "center",
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return value.toFixed(2);
							}
						}
					},
					{
						field : 'jiaofangDate',
						title : '交房日期',
						formatter: function (value, row, index) {
							if(value){
								return value.substr(0,10);
							}else {
								return '';
							}

						}
					},
					{
						field : 'd',
						title : '委托管理起止日期',//标题  可不加
						formatter: function (value, row, index) {
							return row.entrustStartDate.substr(0,10)+'-'+row.entrustEndDate.substr(0,10);
						}
					},
					{
						field : 'e',
						title : '返租起止日期',//标题  可不加
						formatter: function (value, row, index) {
							return row.leasebackStartDate.substr(0,10)+'-'+row.leasebackEndDate.substr(0,10);
						}
					},
					{
						field : 'type',
						title : '委托约定',//标题  可不加
						formatter: function (value, row, index) {
							if(value=='0'){
								return '按市场价格';
							}else if(value=='2'){
								return '第六年开始按市场价格';
							}else {
								return '';
							}
						}
					},
					{
						field : 'ying',
						title : '应返金额',//标题  可不加
						formatter: function (value, row, index) {
							if (!value.split('-')[0] || value.split('-')[0] == '') {
								return '0.00';
							} else {
								return formatData(value.split('-')[0]);
							}
						}
					},
					{
						field : 'ying',
						title : '已返金额',//标题  可不加
						formatter: function (value, row, index) {
							if (!value.split('-')[1] || value.split('-')[1] == '') {
								return '0.00';
							} else {
								return formatData(value.split('-')[1]);
							}
						}
					},
					{
						field : 'ying',
						title : '未返金额',//标题  可不加
						formatter: function (value, row, index) {
							if (!value.split('-')[2] || value.split('-')[2] == '') {
								return '0.00';
							} else {
								return formatData(value.split('-')[2]);
							}
						}
					} ],onDblClickRow: function (item, $element) {
						edit(item.id)
					}
					});
}
function reLoad() {
	if(!isLoad){
		$('#exampleTable').bootstrapTable("refreshOptions",
			{
				url : prefix + "/list"
			});
		load();
		isLoad=true;
	}else {
		$('#exampleTable').bootstrapTable('refresh');
	}
}
function edit(id) {
	var a=layer.open({
		type : 2,
		title : '返租明细',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/info/' + id
	});
	layer.full(a);
}
function removeone(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}
function getTreeData() {
	$.ajax({
		type: "GET",
		url: "/realty/building/tree/2",
		success: function (tree) {
			loadTree(tree);
		}
	});
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core': {
			'data': tree
		},
		"plugins": ["search"]
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function (e, data) {
	if (data.node) {
		var node = data.node;
		if (node.state.type == '2') {
			buildingId = data.selected[0];
			deptId = node.state.deptId;
			reLoad();
		} else {
			clear();
		}
	}
});
function download() {
	var url = prefix + "/download";
	//更改form的action
	$("#importForm").attr("action", url);
	//触发submit事件，提交表单
	$("#importForm").submit();
}
function c() {
	buildingId = '';
	$("#code").val('');
	$("#year").val('');
}
