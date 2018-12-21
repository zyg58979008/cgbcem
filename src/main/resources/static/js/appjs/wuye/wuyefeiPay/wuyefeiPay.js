
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
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者

				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						name : $("#name").val(),
						year : $("#year").val(),
						state : '0',
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
						width:'20%'
					},
					{
						field : 'state',
						title : '状态',//标题  可不加
						formatter: function (value, row, index) {
							if(value=='0'){
								return '未付清';
							}
							if(value=='1'){
								return '已付清';
							}
						},
						width:'10%'
					},
					{
						field : 'j',
						title : '缴费情况',//标题  可不加
						formatter: function (value, row, index) {
							return row.payed+'/'+(row.payed+row.unpay);
						},
						width:'10%'
					},
					{
						field : 'moneyYing',
						title : '应收金额',
						width:'10%',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'moneyPayed',
						title : '已收金额',
						width:'10%',
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
						width:'10%',
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
						width:'20%'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var f = '<a class="btn btn-success btn-sm ' + s_info_h + '" href="#" title="详情"  mce_href="#" onclick="info(\''
								+ row.id
								+ '\')"><i class="fa fa-key"></i></a> ';
							return f;
						}
					} ]
			});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function info(id) {
	var info=layer.open({
		type : 2,
		title : '物业费详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/payInfo/' + id,
		end: function () {
			reLoad()
		}
	});
	layer.full(info);
}
function c() {
	$("#name").val('');
	$("#year").val('');
}
