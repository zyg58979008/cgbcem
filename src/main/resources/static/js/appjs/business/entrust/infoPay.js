
var prefix = "/business/entrust";
$(function() {
	var height=$(document.body).height();
	height=height-48;
	$("#exampleTable").attr("data-height",height);
	load();
});
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				striped : true, // 设置为true会有隔行变色效果
				url : prefix + "/detailList",
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						id : $("#id").val(),
						pay : 'unpay',
						leasebackStartDate : $("#leasebackStartDate").val()
					};
				},
				columns : [
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm" href="#" mce_href="#" title="返租" onclick="fanzu(\''
								+ row.id
								+ '\')"><i class="fa fa-edit "></i></a> ';
							return e;
						}
					},
					{
						title: '序号',//标题  可不加
						formatter: function (value, row, index) {
							return index + 1;
						},
					},
					{
						field: 'buildingName',
						title: '楼宇名称',
						align: "center"

					},
					{
						field: 'roomCode',
						title: '房屋编号',
						align: "center"
					},
					{
						field: 'unit',
						title: '单元',
						align: "center"
					},
					{
						field: 'floor',
						title: '楼层',
						align: "center"
					},
					{
						field: 'month',
						title: '返租月份',
						align: "center",//标题  可不加
						formatter: function (value, row, index) {
							if(!value||value==''){
								return '';
							}else{
								return value.substr(0,7);
							}
						}
					},
					{
						field : 'ying',
						title : '应返金额',//标题  可不加
						formatter: function (value, row, index) {
							return formatData(value);
						}
					},
					{
						field : 'payed',
						title : '已返金额',//标题  可不加
						formatter: function (value, row, index) {
							return formatData(value);
						}
					},
					{
						field : 'unpay',
						title : '未返金额',//标题  可不加
						formatter: function (value, row, index) {
							return formatData(value);
						}
					} ]
			});
}
function fanzu(id) {
    layer.open({
        type: 2,
        title: '返租',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['950px', '520px'],
        content: prefix + '/fanzu/' + id, // iframe的url
        end:function () {
            reLoad();
        }
    });
}