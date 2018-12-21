var prefix = "/wuye/wuye";
$(function() {
	load();
});

function load() {
	$('#dataTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url :"/js/appjs/wuye/wuye/data.json", // 服务器数据的加载地址
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						iconSize : 'outline',
						toolbar : '#dataToolbar',
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						search : true, // 是否显示搜索框
						showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
						// "server"
						// queryParams : queryParams,
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									field : 'shopId', // 列字段名
									title : '编号' ,// 列标题
									align : 'center'
								},
							    {
									field : 'year',
									title : '年份',
									align : 'center'
								},
								{
									field : 'name',
									title : '名称',
									align : 'center'
								},
								{
									field : 's',
									title : '开始日期',
									align : 'center'
								},
								{
									field : 'e',
									title : '结束日期',
									align : 'center'
								},
								{
									title : '操作',
									field : 'roleId',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary " href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.roleId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var f = '<a class="btn btn-info " href="#" mce_href="#" title="详情" onclick="info(\''
											+ row.roleId
											+ '\')"><i class="fa fa-info"></i></a> ';
										var d = '<a class="btn btn-danger " href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.roleId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										return e + d;
									}
								} ]
					});
}
function reLoad() {
	$('#dataTable').bootstrapTable('refresh');
}
function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '生成物业费',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '560px' ],
		content : prefix + '/add' // iframe的url
	});
}
function info() {
	// iframe层
	layer.open({
		type : 2,
		title : '物业费详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '560px' ],
		content : prefix + '/info',
		maxmin: true
	});
	layer.full(index);
}
function remove(id) {
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
				if (r.code === 0) {
					layer.msg("删除成功");
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})

}
function edit(id) {
	layer.open({
		type : 2,
		title : '商铺信息修改',
		maxmin : true,
		shadeClose : true, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function batchRemove() {
	
	var rows = $('#dataTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	}, function() {
		var ids = new Array();
		$.each(rows, function(i, row) {
			ids[i] = row['roleId'];
		});
		console.log(ids);
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
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