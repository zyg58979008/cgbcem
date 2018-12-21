
var prefix = "/equipment/categories"
$(document).ready(function () {
	load();
});
var load = function () {
	$('#exampleTable')
		.bootstrapTreeTable(
			{
				id: 'id',
				code: 'id',
				parentCode: 'parentId',
				type: "GET", // 请求数据的ajax类型
				url: prefix + '/list', // 请求数据的ajax的url
				ajaxParams: {}, // 请求数据的ajax的data属性
				expandColumn: '0',// 在哪一列上面显示展开按钮
				striped: true, // 是否各行渐变色
				bordered: true, // 是否显示边框
				expandAll: false, // 是否全部展开
				// toolbar : '#exampleToolbar',
				columns: [
					{
						title: '名称',
						valign: 'center',
						field: 'name',
						width: '20%'
					},

					{
						title: '编码',
						valign: 'center',
						field: 'code',
						width: '20%'
					},
					{
						title: '类型',
						valign: 'center',
						field: 'type',
						width: '20%'
					},
					{
						title: '操作',
						field: 'id',
						align: 'center',
						valign: 'center',
						formatter: function (item, index) {
							var e = '<a class="btn btn-primary btn-sm '
								+ s_edit_h
								+ '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ item.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var p = '<a class="btn btn-primary btn-sm '
								+ s_add_h
								+ '" href="#" mce_href="#" title="添加下级" onclick="add(\''
								+ item.id
								+ '\')"><i class="fa fa-plus"></i></a> ';
							var d = '<a class="btn btn-danger btn-sm '
								+ s_remove_h
								+ '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ item.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							return e + d + p;
						}
					}]
			});
}

function reLoad() {
	load();
}

function add(pId) {
	layer.open({
		type: 2,
		title: '增加',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['800px', '420px'],
		content: prefix + '/add/' + pId // iframe的url
	});
}
function edit(id) {
	layer.open({
		type: 2,
		title: '编辑',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['800px', '420px'],
		content: prefix + '/edit/' + id // iframe的url
	});
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

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
		// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['classId'];
		});
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


