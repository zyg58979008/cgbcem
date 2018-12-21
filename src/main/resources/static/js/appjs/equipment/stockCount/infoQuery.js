$().ready(function() {
	load();
});
var prefix = "/equipment/stockCount"
var oldList=[];
var newList=[];
var id;
function load() {
	id=$("#id").val();
	$('#old')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/equipmentList?id="+id+"&type=old", // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : false, // 设置为true会在底部显示分页条
				detailView: true,//父子表
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 50, // 如果设置了分页，每页数据条数
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
				rowStyle: function (row, index) {
					//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
					var strclass = "";
					if (row.durableYears >0) {
						strclass = 'warning';//还有一个active
					}
					return { classes: strclass }
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
							return index + 1;
						}
					},
					{
						field : 'name',
						title : '设备名称',
					},
					{
						field : 'categoryName',
						title : '设备分类'
					},
					{
						field : 'brandName',
						title : '设备品牌',
					},
					{
						field : 'model',
						title : '设备型号'
					},
					{
						field : 'num',
						title : '设备数量'
					}
				],
				//注册加载子表的事件。注意下这里的三个参数！
				onExpandRow: function (index, row, $detail) {
					InitSubOldTable(index, row, $detail);
				}
			});
	$('#new')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/equipmentList?id="+id+"&type=new", // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : false, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				detailView: true,//父子表
				pageSize : 50, // 如果设置了分页，每页数据条数
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
				rowStyle: function (row, index) {
					//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
					var strclass = "";
					if (row.durableYears >0) {
						strclass = 'warning';//还有一个active
					}
					return { classes: strclass }
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
							return index + 1;
						}
					},
					{
						field : 'name',
						title : '设备名称',
					},
					{
						field : 'categoryName',
						title : '设备分类'
					},
					{
						field : 'brandName',
						title : '设备品牌',
					},
					{
						field : 'model',
						title : '设备型号'
					},
					{
						field : 'num',
						title : '设备数量'
					}
				],
				//注册加载子表的事件。注意下这里的三个参数！
				onExpandRow: function (index, row, $detail) {
					InitSubNewTable(index, row, $detail);
				}
			});
}
function InitSubOldTable(index, row, $detail) {
	var cur_table = $detail.html('<table></table>').find('table');
	var a = prefix + '/listChild/?type=old&id=' + id+'&name=' + row.name+'&brand=' + row.brand+'&category=' + row.category+'&model=' + row.model;
	$(cur_table).bootstrapTable({
		url: a,
		method: 'get',
		clickToSelect: true,
		sidePagination: "server",
		detailView: false,//父子表
		uniqueId: "id",
		rowStyle: function (row, index) {
			//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
			var strclass = "";
			if (row.state == "2") {
				strclass = 'danger';//还有一个active
			}
			if (row.state == "1") {
				strclass = 'success';//还有一个active
			}
			return { classes: strclass }
		},
		columns: [
			{
				title: '序号',//标题  可不加
				formatter: function (value, row, index) {
					return index + 1;
				}
			},
			{
				field : 'name',
				title : '设备名称',
			},
			{
				field : 'categoryName',
				title : '设备分类'
			},
			{
				field : 'brandName',
				title : '设备品牌',
			},
			{
				field : 'model',
				title : '设备型号'
			},
			{
				field : 'id',
				title : '设备编码'
			}]
	});
};
function InitSubNewTable(index, row, $detail) {
	var cur_table = $detail.html('<table></table>').find('table');
	var a = prefix + '/listChild/?type=new&id=' + id+'&name=' + row.name+'&brand=' + row.brand+'&category=' + row.category+'&model=' + row.model;
	$(cur_table).bootstrapTable({
		url: a,
		method: 'get',
		clickToSelect: true,
		sidePagination: "server",
		detailView: false,//父子表
		uniqueId: "id",
		rowStyle: function (row, index) {
			//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
			var strclass = "";
			if (row.state == "1") {
				strclass = 'success';//还有一个active
			}
			return { classes: strclass }
		},
		columns: [
			{
				title: '序号',//标题  可不加
				formatter: function (value, row, index) {
					return index + 1;
				}
			},
			{
				field : 'name',
				title : '设备名称',
			},
			{
				field : 'categoryName',
				title : '设备分类'
			},
			{
				field : 'brandName',
				title : '设备品牌',
			},
			{
				field : 'model',
				title : '设备型号'
			},
			{
				field : 'id',
				title : '设备编码'
			}
			]
	});
};
