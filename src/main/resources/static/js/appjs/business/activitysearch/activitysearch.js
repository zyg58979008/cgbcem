var prefix = "/business/activitysearch";
$(function() {
	load();
});

function load() {
	$('#dataTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url :"/js/appjs/business/activitysearch/data.json", // 服务器数据的加载地址
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
				//search : true, // 是否显示搜索框
				//showColumns : true, // 是否显示内容下拉框（选择显示的列）
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
					{ // 列配置项
						checkbox : true
						// 列表中显示复选框
					},
					{
						title: '序号',//标题  可不加
						formatter: function (value, row, index) {
							return index + 1;
						}
					},
					{
						field : 'b1',
						title : '项目',
						align:"center",
						valign:"middle"
					},
					{
						field : 'b2',
						title : '楼层',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a3',
						title : '商铺编码',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a4',
						title : '签约人',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a5',
						title : '合同实测面积',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a6',
						title : '合同计租面积（含20%公摊）',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a7',
						title : '计租面积',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a8',
						title : '品牌',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a9',
						title : '商铺经营类别',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a10',
						title : '活动名称',
						align:"center",
						valign:"middle"
					},
					{
						field : 'b1',
						title : '应收金额',
						align:"center",
						valign:"middle"
					},
					{
						field : 'b2',
						title : '已收金额',
						align:"center",
						valign:"middle"
					},
					{
						field : 'b3',
						title : '未收金额',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a12',
						title : '活动类型',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a13',
						title : '邀请卡款',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a30',
						title : '电话',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a31',
						title : '身份证号',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a32',
						title : '开单编码',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a33',
						title : '开单名称',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a34',
						title : '付款人',
						align:"center",
						valign:"middle"
					},{
						field : 'a35',
						title : '账户',
						align:"center",
						valign:"middle"
					},
					{
						field : 'a36',
						title : '开户行',
						align:"center",
						valign:"middle"
					},
					{
						title: '操作',
						field: 'id',
						align: "center",
						valign: "middle",
						switch: true,
						formatter: function (value, row, index) {
							var e = '<a class="btn btn-primary " href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-warning " href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
								+ row.id
								+ '\')"><i class="fa fa-key"></i></a> ';
							return e + d;
						}
					}]
			});
}
function reLoad() {
	$('#dataTable').bootstrapTable('refresh');
}
function add() {
	// iframe层
	layer.open({
		type : 2,
		title : '添加楼宇信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '560px' ],
		content : prefix + '/add' // iframe的url
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
		title : '楼宇信息修改',
		maxmin : true,
		shadeClose : true, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function batchRemove() {

	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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