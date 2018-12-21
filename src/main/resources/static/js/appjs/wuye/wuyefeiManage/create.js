
var prefix = "/wuye/wuyefeiManage"
$(function() {
	load();
});
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : "/realty/building/list",
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
				pageSize : 200, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						delFlag:'0',
						type:'2',
						pagination:false
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
						},
						width:'10%'
					},
					{
						field : 'name',
						title : '名称',
						valign : 'center',
						width:'70%'
					},
					{
						field : 'type',
						title : '类型',
						valign : 'center',
						formatter: function (item, row, index) {
							if(item=='1'){
								return '分组';
							}
							if(item=='2'){
								return '楼宇';
							}
						},
						width:'20%'
					},]
			});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function  save() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要生成物业费的楼宇");
		return;
	}
	layer.confirm('生成物业费之前请先配置物业费，确定要生成物业费？', {
		btn : [ '确定', '取消' ]
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		var manage={};
		manage.ids=ids;
		manage.id=$("#id").val();
		$.ajax({
			url : prefix + "/createWuyefei",
			type : "post",
			data : {mydata:JSON.stringify(manage)},// 你的formid
			success : function(r) {
				if (r.code == 0) {
					parent.layer.msg("操作成功");
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
				} else {
					parent.layer.msg(data.msg)
				}
			}
		});
	})
}