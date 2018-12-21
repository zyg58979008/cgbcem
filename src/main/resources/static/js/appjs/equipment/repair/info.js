$().ready(function() {
	load();
});
var prefix = "/equipment/repair"

function load() {
	var id=$("#id").val();
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/equipmentList?id="+id, // 服务器数据的加载地址
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
                        field : 'unit',
                        title : '设备单位'
                    },
					{
						field : 'id',
						title : '设备编码'
					},
					{
						field : 'userName',
						title : '设备接收人'
					},
					{
						field : 'createDate',
						title : '接收时间',
						formatter: function (value, row, index) {
							var createDate=row.createDate;
							if(createDate==null){
								return '';
							}else {
								return value.substr(0, 10);
							}

						}
					},
					{
						field : 'storeroomName',
						title : '接收地点'
					}
					 ]
			});
}
