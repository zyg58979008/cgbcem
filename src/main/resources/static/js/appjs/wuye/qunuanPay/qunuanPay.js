
var prefix = "/wuye/qunuanManage"
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
						title : '取暖费名称', // 列标题,
						width:'15%'
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
						width:'5%'
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
						field : 'qunuanYing',
						title : '应收取暖费',
						width:'6%',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'qunuanPayed',
						title : '已收取暖费',
						width:'6%',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'qunuanUnpay',
						title : '未收取暖费',
						width:'6%',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'tingnuanYing',
						title : '应收停暖费',
						width:'6%',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'tingnuanPayed',
						title : '已收停暖费',
						width:'6%',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'tingnuanUnpay',
						title : '未收停暖费',
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
						width:'15%'
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
		title : '取暖费详情',
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
