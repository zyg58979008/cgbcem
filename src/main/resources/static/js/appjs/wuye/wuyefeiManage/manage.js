
var prefix = "/wuye/wuyefeiManage"
$(function() {
	load();
});
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/list",
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
						name : $("#name").val(),
						year : $("#year").val(),
						state : $("#state").val(),
						delFlag:'0'
					};
				},
				columns : [
					{
						title: '序号',//标题  可不加
						formatter: function (value, row, index) {
							return index + 1;
						},
						width:'5%'
					},
					{
						field : 'name', // 列字段名
						title : '物业费名称', // 列标题,
						width:'15%'
					},
					{
						field : 'status',
						title : '状态',//标题  可不加
						formatter: function (value, row, index) {
							if(value=='0'){
								return '未生成';
							}
							if(value=='1'){
								return '已生成';
							}
						},
						width:'7%'
					},
					{
						field : 'state',
						title : '缴费情况',//标题  可不加
						formatter: function (value, row, index) {
							if(value=='0'){
								return '未付清';
							}
							if(value=='1'){
								return '已付清';
							}
						},
						width:'7%'
					},
					{
						field : 'j',
						title : '缴费情况',//标题  可不加
						formatter: function (value, row, index) {
							return row.payed+'/'+(row.payed+row.unpay);
						},
						width:'8%'
					},
					{
						field : 'l',
						title : '收缴率',//标题  可不加
						formatter: function (value, row, index) {
							if(!row.moneyYing||row.moneyYing==''){
								return '0%';
							}else{
								var ying=parseFloat(row.moneyYing);
								if(!row.moneyPayed||row.moneyPayed==''){
									return '0%';
								}else{
									var yi=parseFloat(row.moneyPayed);
									var l=(yi/ying).toFixed(4);
									return l*100+"%";
								}
							}
						},
						width:'8%'
					},
					{
						field : 'moneyYing',
						title : '应收金额',
						width:'6%',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}
						}
					},
					{
						field : 'moneyPayed',
						title : '已收金额',
						width:'6%',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'moneyUnpay',
						title : '未收金额',
						width:'6%',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'd',
						title : '缴费周期',//标题  可不加
						formatter: function (value, row, index) {
							return row.startDate.substr(0,10)+'-'+row.endDate.substr(0,10);
						},
						width:'16%'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit "></i></a> ';
							var g = '<a  class="btn btn-primary btn-sm ' + s_create_h + '" ' +
								'href="#" mce_href="#" title="按楼生成物业费" onclick="create(\''+ row.id+ '\',\''+ row.status+ '\')">' +
								'<i class="fa fa-plus "></i></a> ';
							var z = '<a  class="btn btn-primary btn-sm ' + s_create_h + '" ' +
								'href="#" mce_href="#" title="按房屋生成物业费" onclick="createSec(\''+ row.id+ '\',\''+ row.status+ '\')">' +
								'<i class="fa fa-plus "></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="removeone(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm ' + s_info_h + '" href="#" title="详情"  mce_href="#" onclick="info(\''
								+ row.id
								+ '\')"><i class="fa fa-key"></i></a> ';
							var a = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="导出凭证" onclick="e(\''
								+ row.id
								+ '\')"><i class="fa fa-download "></i></a> ';
							return f+e +g+z+ a+d;
						}
					} ]
			});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '生成物业费',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/add'
	});
}
function e(id) {
	layer.open({
		type : 2,
		title : '导出凭证',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '200px' ],
		content : prefix + '/export/' + id
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '修改物业费',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/edit/' + id
	});
}
function create(id,status) {
	if(status=='1'){
		layer.open({
			type : 2,
			title : '生成物业费',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '860px', '100%' ],
			content : prefix + '/createSec/' + id,
			end:function () {
				reLoad();
			}
		});
	}else{
		layer.open({
			type : 2,
			title : '生成物业费',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '860px', '100%' ],
			content : prefix + '/create/' + id
		});
	}
}
function createSec(id,status) {
	layer.open({
		type : 2,
		title : '生成物业费',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '860px', '100%' ],
		content : prefix + '/createSec/' + id,
		end:function () {
			reLoad();
		}
	});
}
function info(id) {
	var info=layer.open({
		type : 2,
		title : '修改物业费',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/info/' + id,
		end: function () {
			reLoad()
		}
	});
	layer.full(info);
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
function c() {
	$("#name").val('');
	$("#year").val('');
	$("#state").val('');
}
