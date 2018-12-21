
var prefix = "/equipment/move"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list?delFlag=0", // 服务器数据的加载地址
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
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageList: [10, 25, 50, 100],
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
								field : 'orderId',
								title : '单据编号'
							},
							{
								field : 'fromName',
								title : '设备来源',
								formatter : function(v, row, index) {
									var value=row.type;
									if(value=='0'||value=='2'||value=='3'||value=='4'||value=='1'){
										return row.fromStoreName;
									}
									if(value=='5'||value=='6'){
										return row.fromDeptName;
									}
								}
							},

							{
								field : 'intoName',
								title : '设备去处',
								formatter : function(v, row, index) {
									var value=row.type;
									if(value=='0'||value=='5'||value=='3'||value=='4'||value=='6'){
										return row.intoStoreName;
									}
									if(value=='1'||value=='2'){
										return row.intoDeptName;
									}
								}
							},								{
									field : 'createDate',
									title : '接收日期',
									formatter : function(value, row, index) {
										return value.substr(0,10)
									}

								},

								{
									field : 'createByName',
									title : '接收人员'
								},
																{
									field : 'remarks',
									title : '备注'
								},
							{
								title: '操作',
								field: 'orderId',
								align: 'center',
								formatter: function (value, row, index) {
									var e = '<a class="btn btn-info btn-sm ' + s_info_h + '" href="#" mce_href="#" title="详情" onclick="info(\''
										+ row.orderId
										+ '\')"><i class="fa fa-info" style="width: 10px"></i></a> ';
									var f = '<a class="btn btn-success btn-sm" href="#" title="打印条码"  mce_href="#" onclick="resetPwd(\''
										+ row.orderId
										+ '\')"><i class="fa fa-print"></i></a> ';
									return e;
								}
							} ]
					});
}
function reLoad() {
	var startDate='';
	if($('#startDate').val()){
		startDate= $('#startDate').val()+' 00:00:00';
	}
	var endDate= '';
	if($('#endDate').val()){
		endDate= $('#endDate').val()+' 23:59:59';
	}
	var options = $('#exampleTable').bootstrapTable('refresh', {
		query:
		{
			startDate:startDate,
			endDate:endDate
		}
	});
}
function add() {
	layer.open({
		type : 2,
		title : '详情',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '900px', '480px' ],
		content : prefix + '/add3' ,
	});
}
function info(id) {
	layer.open({
		type: 2,
		title: '详情',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['900px', '480px'],
		content: prefix + '/info/' + id// iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		btn:['保存'],
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '900px', '620px' ],
		content : prefix + '/edit/' + id ,// iframe的url
		yes:function(index, layero){
			var body = layer.getChildFrame('body', index);
			var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
			iframeWin.update();
		}

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