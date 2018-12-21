
var prefix = "/business/building"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
		.bootstrapTreeTable(
			{
				id : 'id',
				code : 'id',
                parentCode : 'parentId',
				type : "GET", // 请求数据的ajax类型
				url : prefix + '/list?delFlag=0', // 请求数据的ajax的url
				ajaxParams : {}, // 请求数据的ajax的data属性
				expandColumn : '1', // 在哪一列上面显示展开按钮
				striped : true, // 是否各行渐变色
				bordered : true, // 是否显示边框
				expandAll : false, // 是否全部展开
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						name : $('#searchName').val(),
					};
				},
				// toolbar : '#exampleToolbar',
				columns : [
					{
                        field : 'id',
                        title : '编号',
                        valign : 'center',
                        width :20
					},
					{
						field : 'name',
						title : '名称',
                        valign : 'center',
						width :20
					},
					{
						field : 'type',
						title : '类型',
						valign : 'center',
						width :20,
						formatter: function (item, row, index) {
							if(item.type=='1'){
								return '分组';
							}
							if(item.type=='2'){
								return '楼宇';
							}
						}
					},
					/*{
						field : 'unit',
						title : '单元数',
						align : 'center',
						valign : 'center',
					},*/
					{
						field : 'sort',
						title : '排序',
                        align : 'center',
                        valign : 'center',
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
                        valign : 'center',
						formatter : function(item, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ item.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var a = '<a class="btn btn-primary btn-sm ' + s_add_h + '" href="#" title="增加下級"  mce_href="#" onclick="add(\''
								+ item.id
								+ '\')"><i class="fa fa-plus"></i></a> ';
							var d = '<a class="btn btn-danger btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="removeone(\''
								+ item.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm＂ href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
								+ item.id
								+ '\')"><i class="fa fa-key"></i></a> ';
							if(item.type=='1'){
                                return e + a + d;
							}
                            if(item.type=='2'){
                                return e +  d;
                            }
						}
					} ]
			});
}
function reLoad() {
    load();
}
function add(pId) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '420px' ],
		content : prefix + '/add/' + pId
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '420px' ],
		content : prefix + '/edit/' + id
	});
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

