
var prefix = "/stock/query"
$(function() {
	load();
});
//一般data从后台返回，调用这个方法显示视图
$('#jstree_demo_div').jstree({'plugins':["wholerow"], 'core' : {
	'data' : [
		{ "id" : "设备类别", "parent" : "#", "text" : "设备类别" ,'state' : {  'selected' : false },},
		{ "id" : "打印机", "parent" : "设备类别", "text" : "打印机" },
		{ "id" : "路由器", "parent" : "设备类别", "text" : "路由器" },
		{ "id" : "硒鼓", "parent" : "设备类别", "text" : "硒鼓" },
		{ "id" : "服务器", "parent" : "设备类别", "text" : "服务器" },
	]
} });
$('#jstree_demo_div').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				category : '',
			}
		}
		$('#exampleTable').bootstrapTable('refresh', opt);
	} else {
		var opt = {
			query : {
				category : data.selected[0],
			}
		}
		$('#exampleTable').bootstrapTable('refresh',opt);
	}

});
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list?type=1", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						detailView: true,//父子表
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 15, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [ {
							title: '序号',//标题  可不加
							formatter: function (value, row, index) {
								return index + 1;
							}
						},
																{
									field : 'category', 
									title : '设备分类' 
								},
							{
								field : 'brand',
								title : '设备品牌'
							},
																{
									field : 'name', 
									title : '设备名称' 
								},

																{
									field : 'model', 
									title : '设备型号' 
								},

																{
									field : 'num', 
									title : '设备库存量' 
								},
							{
								field : 'unit',
								title : '设备单位'
							}],
						//注册加载子表的事件。注意下这里的三个参数！
						onExpandRow: function (index, row, $detail) {
							InitSubTable(index, row, $detail);
						},onLoadSuccess: function () {
						$('#total').remove();
						var rows=$('#exampleTable').bootstrapTable("getData");
						var total=0;
						for(var i=0;i<rows.length;i++){
							total+=rows[i].num;
						}
						var html="<table id='total' class='table table-hover table-striped'><td style='text-align: right'>合计："+total+"</td></table>";
						$('#exampleTable').after(html)
					}
					});
}
function InitSubTable(index, row, $detail) {
	var cur_table = $detail.html('<table></table>').find('table');
	var a =prefix+'/list/?type=1&&parentId='+row.id;
	$(cur_table).bootstrapTable({
		url: a,
		method: 'get',
		clickToSelect: true,
		sidePagination: "server",
		detailView: false,//父子表
		uniqueId: "id",
		columns: [ {
			title: '序号',//标题  可不加
			formatter: function (value, row, index) {
				return index + 1;
			}
		},{
			field : 'category',
			title : '设备分类'
		},
			{
				field : 'brand',
				title : '设备品牌'
			},
			{
				field : 'name',
				title : '设备名称'
			},

			{
				field : 'model',
				title : '设备型号'
			},

			{
				field : 'num',
				title : '数量'
			},
			{
				field : 'storeroomId',
				title : '所在位置'
			},{
				field : 'unit',
				title : '设备单位'
			},						{
				title : '操作',
				field : 'id',
				align : 'center',
				formatter : function(value, row, index) {
					var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
						+ row.id
						+ '\')"><i class="fa fa-edit"></i></a> ';
					var d = '<a class="btn btn-danger btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
						+ row.id
						+ '\')"><i class="fa fa-remove"></i></a> ';
					var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
						+ row.id
						+ '\')"><i class="fa fa-key"></i></a> ';
					return  e;
				}
			} ]
	});
};
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
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
			ids[i] = row['id'];
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
	}, function() {

	});
}