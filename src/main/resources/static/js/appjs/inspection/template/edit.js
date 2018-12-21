$().ready(function() {
	load1();
	load2();
	load3();
	validateRule();
});
var prefix = "/inspection/template"
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
				url : prefix + "/projectList1?id="+id, // 服务器数据的加载地址
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
						width: '40%',
						formatter : function(value, row, index) {
							if (value == '0') {
								return '<label class="radio-inline" > <input type="radio" name="status" value="1" disabled/><div style="width:90px; height:91px; margin-top:7px; margin-left:15px; display:inline"  >正常</div> </label> <label class="radio-inline"> <input type="radio"name="status" value="0" disabled/><div style="width:90px; height:91px; margin-top:7px; margin-left:15px; display:inline"  >不正常 </div></label>';
							} else if (value == '1') {
								return '<label><input type="text" name="status" disabled/></label>';
							}else{
								return '<label><input type="number" name="status" disabled/> 单位：'+value.substring(1,value.length)+'</label>';
							}
						}
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
				url : prefix + "/projectList2?id="+id, // 服务器数据的加载地址
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
						width: '40%',
						formatter : function(value, row, index) {
							if (value == '0') {
								return '<label class="radio-inline" > <input type="radio" name="status" value="1" disabled/><div style="width:90px; height:91px; margin-top:7px; margin-left:15px; display:inline"  >正常</div> </label> <label class="radio-inline"> <input type="radio"name="status" value="0" disabled/><div style="width:90px; height:91px; margin-top:7px; margin-left:15px; display:inline"  >不正常 </div></label>';
							} else if (value == '1') {
								return '<label><input type="text" name="status" disabled/></label>';
							}else{
								return '<label><input type="number" name="status" disabled/> 单位：'+value.substring(1,value.length)+'</label>';
							}
						}
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
				url : prefix + "/projectList3?id="+id, // 服务器数据的加载地址
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
						width: '40%',
						formatter : function(value, row, index) {
							if (value == '0') {
								return '<label class="radio-inline" > <input type="radio" name="status" value="1" disabled/><div style="width:90px; height:91px; margin-top:7px; margin-left:15px; display:inline"  >正常</div> </label> <label class="radio-inline"> <input type="radio"name="status" value="0" disabled/><div style="width:90px; height:91px; margin-top:7px; margin-left:15px; display:inline"  >不正常 </div></label>';
							} else if (value == '1') {
								return '<label><input type="text" name="status" disabled/></label>';
							}else{
								return '<label><input type="number" name="status" disabled/> 单位：'+value.substring(1,value.length)+'</label>';
							}
						}
					},
					{
						field : 'result',
						title : '整改意见',
						width: '20%'
					}
				]
			});
}





function update() {
	var flag = $("#signupForm").valid();
	if(!flag){
		//没有通过验证
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/inspection/template/update",
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
				required : true,
				maxlength:100,
				minlength:2,
				remote : {
					url : "/inspection/template/exitUpdate", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						id : function() {
							return $("#id").val();
						},
						name : function() {
							return $("#name").val();
						}
					}
				}
			}
		},
		messages : {
			name : {
				required : icon + "请输入模板名称",
				maxlength:icon + "模板名称字数过多",
				minlength:icon + "模板名称字数太少",
				remote : icon + "模板名称已经存在"
			}
		}
	})
}