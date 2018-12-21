$().ready(function() {
	loadEquip1();
	loadEquip2();
	load1();
	load2();
	load3();
	//loadPic();
    //showImg();
	validateRule();
});
var prefix = "/inspection/template";
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function load1() {
	var id=$("#id").val();
	$('#exampleTable1')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/resultList1?id="+id, // 服务器数据的加载地址
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
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageList: [10, 25, 50, 100],
				pageNumber: 1, // 如果设置了分布，首页页码
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
						width: '10%',
						formatter: function (value, row, index) {
							var pageSize=$('#exampleTable1').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
							var pageNumber=$('#exampleTable1').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
							return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
						}
					},

					{
						field : 'name',
						title : '检查项目',
						width: '30%'
					},
					{
						field : 'type',
						title : '状态',
						width: '40%'
					},
					{
						field : 'result',
						title : '处理方法',
						width: '20%'
					}
				]
			});
}

function load2() {
	var id=$("#id").val();
	$('#exampleTable2')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/resultList2?id="+id, // 服务器数据的加载地址
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
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageList: [10, 25, 50, 100],
				pageNumber: 1, // 如果设置了分布，首页页码
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
						width: '10%',
						formatter: function (value, row, index) {
							var pageSize=$('#exampleTable2').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
							var pageNumber=$('#exampleTable2').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
							return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
						}
					},

					{
						field : 'name',
						title : '检查项目',
						width: '30%'
					},
					{
						field : 'type',
						title : '检查结果',
						width: '40%'
					},
					{
						field : 'result',
						title : '备注',
						width: '20%'
					}
				]
			});
}

function load3() {
	var id=$("#id").val();
	$('#exampleTable3')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/resultList3?id="+id, // 服务器数据的加载地址
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
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageList: [10, 25, 50, 100],
				pageNumber: 1, // 如果设置了分布，首页页码
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
						width: '10%',
						formatter: function (value, row, index) {
							var pageSize=$('#exampleTable3').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
							var pageNumber=$('#exampleTable3').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
							return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
						}
					},

					{
						field : 'name',
						title : '检查项目',
						width: '30%'
					},
					{
						field : 'type',
						title : '状态',
						width: '40%'
					},
					{
						field : 'result',
						title : '整改意见',
						width: '20%'
					}
				]
			});
}

function loadEquip1() {
	var id=$("#id").val();
	$('#equipment1')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/equipInfoList?id="+id, // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				//toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageList: [10, 25, 50, 100],
				pageNumber: 1, // 如果设置了分布，首页页码
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
						width: '10%',
						formatter: function (value, row, index) {
							var pageSize=$('#equipment1').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
							var pageNumber=$('#equipment1').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
							return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
						}
					},

					{
						field : 'name',
						title : '设备名称',
						width: '30%'
					},
					{
						field : 'brandname',
						title : '品牌',
						width: '20%'
					},
					{
						field : 'model',
						title : '型号',
						width: '20%'
					},
					{
						field : 'state',
						title : '设备状态',
						width: '20%',
						formatter : function(value, row, index) {
							if (value == '0') {
								return '<label>正常</label>';
							} else if (value == '1') {
								return '<label>维修</label>';
							}else if (value == '2') {
								return '<label>更换</label>';
							}else{
								return '<label></label>';
							}
						}
					}
				]
			});
}
function loadEquip2() {
	var id=$("#id").val();
	$('#equipment2')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/equipRepairInfoList?id="+id, // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				//toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageList: [10, 25, 50, 100],
				pageNumber: 1, // 如果设置了分布，首页页码
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
						width: '10%',
						formatter: function (value, row, index) {
							var pageSize=$('#equipment2').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
							var pageNumber=$('#equipment2').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
							return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
						}
					},

					{
						field : 'name',
						title : '设备名称',
						width: '30%'
					},
					{
						field : 'equipmentId',
						title : '设备编号',
						width: '20%'
					},
					{
						field : 'useTime',
						title : '已使用时间',
						width: '20%'
					},
					{
						field : 'mode',
						title : '处理方式',
						width: '20%',
						formatter : function(value, row, index) {
							if (value == '0') {
								return '<label>现场维修</label>';
							} else if (value == '1') {
								return '<label>统一维修</label>';
							}else{
								return '<label></label>';
							}
						}
					}
				]
			});
}
function loadPic() {
	var id=$("#id").val();
	$('#picture')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/picList?id="+id, // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				//toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageList: [10, 25, 50, 100],
				pageNumber: 1, // 如果设置了分布，首页页码
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
						width: '10%',
						formatter: function (value, row, index) {
							var pageSize=$('#exampleTable3').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
							var pageNumber=$('#exampleTable3').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
							return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
						}
					},

					{
						field : 'createDate',
						title : '上传时间',
						width: '30%'
					},
					{
						field : 'pic',
						title : '图片',
						width: '60%',
						formatter : function(value, row, index) {
							return '<label><img src="'+value+'" width="520px" height="280px"/></label>';
						}
					}
				]
			});
}



function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/inspection/plan/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}