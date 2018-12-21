
var prefix = "/business/contract"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/listDetail", // 服务器数据的加载地址
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
				pageSize : 30, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
				// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
				// queryParamsType = 'limit' ,返回参数必须包含
				// limit, offset, search, sort, order 否则, 需要包含:
				// pageSize, pageNumber, searchText, sortName,
				// sortOrder.
				// 返回false将会终止请求
				queryParams: function (params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit: params.limit,
						offset: params.offset,
						contractCode:parent.contract.contractCode
						// username:$('#searchName').val()
					};
				},
				columns : [
					{
						field : 'buildingName',
						title : '楼宇',
						align:"center",
						valign:"middle",
						width:"200px"
					},
					{
						field : 'contractCode',
						title : '合同编号',
						align:"center",
						valign:"middle",
						width:"200px"
					},

						{
							field : 'floor',
							title : '楼层',
							align:"center",
							valign:"middle"
						},
						{
							field : 'shopCode',
							title : '商铺编码',
							align:"center",
							valign:"middle",
							width:"180px"
						},
						{
							field : 'contractor',
							title : '签约人',
							align:"center",
							valign:"middle"
						},

						{
							field : 'rentArea',
							title : '计租面积',
							align:"center",
							valign:"middle"
						},
						{
							field : 'brand',
							title : '品牌',
							align:"center",
							valign:"middle",
							width:"180px"
						},

						{
							field : 'idCard',
							title : '身份证号',
							align:"center",
							valign:"middle"
						},

						{
							title : '操作',
							field : 'id',
							align:"center",
							valign:"middle",
							switch:true,
							formatter : function(value, row, index) {
								var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
									+ row.id
									+ '\')"><i class="fa fa-edit"></i></a> ';
								var d = '<a class="btn btn-danger btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
									+ row.id
									+ '\')"><i class="fa fa-remove"></i></a> ';
								/*var f = '<a class="btn btn-success btn-sm ' + s_jiaofang_h + '" href="#" title="交房"  mce_href="#" onclick="jiaofang(\''
									+ row.id
									+ '\')"><i class="fa fa-key"></i></a> ';*/
								return;
							}
						} ],
				onDblClickRow: function (item, $element) {
					contract=item;
					var perContent = layer.open({
						type : 2,
						title : '合同详情',
						shadeClose : false, // 点击遮罩关闭层
						area : [ '820px', '500px' ],
						content : prefix + '/info?idCard='+item.idCard+'&shopId='+item.shopId+'&brand='+item.brand // iframe的url
					});
				}
			});
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
		content : prefix + '/edit/' + id// iframe的url
	});
}
function renew(id) {
	layer.open({
		type : 2,
		title : '合同续签',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/renew'// iframe的url
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
function download() {
	var url = prefix + "/download";
	//更改form的action
	$("#importForm").attr("action", url);
	//触发submit事件，提交表单
	$("#importForm").submit();
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
function reLoad() {
	var opt = {
		query : {
			contractor : $('#contractor').val(),
			contractStartDate : $('#contractStartDate').val(),
			contractEndDate : $('#contractEndDate').val()
		}
	}
	$('#exampleTable').bootstrapTable('refresh',opt);
}
function clear() {
	buildingId = '';
	deptId = '';
}