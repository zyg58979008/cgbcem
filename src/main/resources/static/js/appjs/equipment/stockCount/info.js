$().ready(function() {
	load();
});
var prefix = "/equipment/stockCount"
var oldList=[];
var newList=[];
var id;
var nameOld='';
var brandOld='';
var categoryOld='';
var modelOld='';
var nameNew='';
var brandNew='';
var categoryNew='';
var modelNew='';
var index=-1;
var typeNew='';
var typeOld='';
var childNum=0;
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
				pageSize : 100, // 如果设置了分页，每页数据条数
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
				},
				onLoadSuccess:function (data) {
					var rows=data.rows;
					if(typeOld=='add'){
						for(var i=0;i<rows.length;i++){
							if(rows[i].name==nameOld&&rows[i].brand==brandOld&&rows[i].category==categoryOld&&rows[i].model==modelOld){
								$("#old").bootstrapTable('expandRow', i);
								nameOld='';
								brandOld='';
								categoryOld='';
								modelOld='';
								typeOld='';
							}
						}
					}
					if(typeOld=='del'){
						if(index!=-1){
							$("#old").bootstrapTable('expandRow', index);
							index=-1;
							typeOld='';
						}
					}
					if(typeOld=='back'){
						for(var i=0;i<rows.length;i++){
							if(rows[i].name==nameOld&&rows[i].brand==brandOld&&rows[i].category==categoryOld&&rows[i].model==modelOld){
								$("#old").bootstrapTable('expandRow', i);
								nameOld='';
								brandOld='';
								categoryOld='';
								modelOld='';
								typeOld='';
							}
						}
					}
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
				},
				onLoadSuccess:function (data) {
					var rows=data.rows;
					if(typeNew=='add'){
						if(index!=-1){
							$("#new").bootstrapTable('expandRow', index);
							index=-1;
							typeNew='';
						}
					}
					if(typeNew=='back'){
						for(var i=0;i<rows.length;i++){
							if(rows[i].name==nameNew&&rows[i].brand==brandNew&&rows[i].category==categoryNew&&rows[i].model==modelNew){
								$("#new").bootstrapTable('expandRow', i);
								nameNew='';
								brandNew='';
								categoryNew='';
								modelNew='';
								typeNew='';
							}
						}
					}
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
			},
			{
				title : '操作',
				field : 'id',
				align : 'center',
				formatter : function(value, r, i) {
					var d = '<a class="btn btn-danger btn-sm " href="#" title="删除"  mce_href="#" onclick="del(\''
						+ id
						+ '\',\''
						+ r.id
						+ '\',\''
						+ index
						+ '\',\''
						+ r.state
						+ '\',\''
						+ r.name
						+ '\',\''
						+ r.brand
						+ '\',\''
						+ r.category
						+ '\',\''
						+ r.model
						+ '\')"><i class="fa fa-remove" style="width: 10px"></i></a> ';
					if(r.state=='0'&&r.num>0){}
					else{
						return d;
					}
				}
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
			},
			{
				title : '操作',
				field : 'id',
				align : 'center',
				formatter : function(value, r, i) {
					var f = '<a class="btn btn-success btn-sm " href="#" title="添加"  mce_href="#" onclick="add(\''
						+ id
						+ '\',\''
						+ r.id
						+ '\',\''
						+ index
						+ '\',\''
						+ r.name
						+ '\',\''
						+ r.brand
						+ '\',\''
						+ r.category
						+ '\',\''
						+ r.model
						+ '\')"><i class="fa fa-plus" style="width: 10px"></i></a> ';
					if(r.state=='0'&&r.num==0 ){
						return f;
					}
					return '';
				}
			}]
	});
};

function add(orderId,equipmentId,i,n,b,c,m) {
	$.ajax({
		cache : true,
		type : "GET",
		url : "/equipment/stockCount/add?id="+orderId+"&equipmentIds="+equipmentId,
		error : function(request) {

		},
		success : function(data) {
			$("#old").bootstrapTable('refresh', {});
			$("#new").bootstrapTable('refresh', {});
			nameNew=n;
			brandNew=b;
			categoryNew=c;
			modelNew=m;
			nameOld=n;
			brandOld=b;
			categoryOld=c;
			modelOld=m;
			index=i;
			typeNew='add';
			typeOld='add';
		}
	});

}
function del(orderId,equipmentId,i,state,n,b,c,m) {
	$.ajax({
		cache : true,
		type : "GET",
		url : "/equipment/stockCount/del?id="+orderId+"&equipmentIds="+equipmentId+"&state="+state,
		error : function(request) {

		},
		success : function(data) {
			if(state=='0'){
				$("#old").bootstrapTable('refresh', {});
				typeOld='del';
				index=i;
			}
			if(state=='2'){
				$("#old").bootstrapTable('refresh', {});
				typeOld='del';
				index=i;
			}
			if(state=='1'){
				$("#old").bootstrapTable('refresh', {});
				$("#new").bootstrapTable('refresh', {});
				typeOld='back';
				typeNew='back';
				nameOld=n;
				brandOld=b;
				categoryOld=c;
				modelOld=m;
				nameNew=n;
				brandNew=b;
				categoryNew=c;
				modelNew=m;
			}
		}
	});
};
function save() {
	$.ajax({
		cache : true,
		type : "GET",
		url : "/equipment/stockCount/update/"+id,
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
