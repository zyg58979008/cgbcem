
var prefix = "/equipment/equipment"
$(function() {
	getTreeData();
	loadType();
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
                        pageSize : 15, // 如果设置了分页，每页数据条数
                        pageList: [15, 25, 50, 100],
                        pageNumber: 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								deptId: deptId
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
						columns : [
                            {
                                title: '序号',//标题  可不加
                                formatter: function (value, row, index) {
                                    var pageSize=$('#exampleTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                                    var pageNumber=$('#exampleTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                                    return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                                }
                            },
								{
                                field : 'categoryName',
                                title : '设备分类'
                            },
																{
									field : 'brandName',
									title : '设备品牌'
								},

																{
									field : 'model',
									title : '设备型号'
								},
							{
								field : 'name',
								title : '设备名称'
							},
							{
								field : 'createDate',
								title : '上线日期',
								formatter: function (value, row, index) {
									return value.substr(0, 10)
								}
							},
							{
								field : 'useTime',
								title : '已使用年限',
								formatter: function (value, row, index) {
									var year;
									var month;
									var useT = row.useMonth;
									year = parseInt(useT/12);
									month = useT%12;
									if(year == "0"){
										if(month == "0"){
											month++;
										}
										return month+"月";
									}else if(month != "0"){
										return year+"年"+month+"月";
									}else{
										return year+"年";
									}
								}
							},
                            {
                                field : 'deptName',
                                title : '所在机构'
                            },
																{
									field : 'remarks',
									title : '备注'
								},
																{
									title : '操作',
									field : 'code',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.code
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-danger btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.code
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-info btn-sm " href="#" title="详情"  mce_href="#" onclick="info(\''
												+ row.code
												+ '\')"><i class="fa fa-info" style="width: 10px"></i></a> ';
										return f;
									}
								} ]
					});
}
var deptId;
function reLoad() {
	var name= $('#searchName').val();
	var brand= $('#brandChose').val();
	var category = $('#category').val();
	var options = $('#exampleTable').bootstrapTable('refresh', {
		query:
		{
			name:name,
			brand:brand,
			category:category,
			deptId : deptId
		}
	});
}

function loadType(){
	var html = "";
	$.ajax({
		url : '/equipment/equipment/brand',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
			}
			$("#brandChose").append(html);
			$("#brandChose").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#brandChose').on('change', function(e, params) {
				//console.log(params.selected);
				var opt = {
					query : {
						type : params.selected
					}
				}
			});
		}
	});
}

function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/system/sysDept/treeByAreaLevel",
		success : function(tree) {
			loadTree(tree);
		}
	});
}

function loadTree(tree) {
	$('#jstree_demo_div').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	});
	$('#jstree_demo_div').jstree().open_all();
}
$('#jstree_demo_div').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				deptId : '',
			}
		}
		$('#jstree_demo_div').bootstrapTable('refresh', opt);
	} else {
		deptId =data.selected[0];
		var brand= $('#brandChose').val();
		var category = $('#category').val();
		var opt = {

			query : {
				brand:brand,
				category:category,
				deptId : deptId

			}
		}
		$('#exampleTable').bootstrapTable('refresh',opt);
	}

});

function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '480px' ],
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

var openCategory = function(){
	layer.open({
		type:2,
		title:"选择分类",
		area : [ '300px', '450px' ],
		content: "/equipment/categories" + '/treeView'
	})
}
function loadCategory( Id,name){
	$("#category").val(Id);
	$("#categoryname").val(name);
}

function info(id) {
	layer.open({
		type : 2,
		title : '详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '860px', '480px' ],
		content : "/equipment/stockQuery"+'/info/'+id // iframe的url
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