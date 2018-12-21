
var prefix = "/realtyReport";
var xianjin=0;
var pos=0;
var zhuanzhang=0;
var total=0;
$(function() {
	load();
});
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/paymentList",
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : false, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
                exportDataType: "basic",
                showRefresh: false,
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
				queryParams : function(params) {
					return {
                        startDate:$("#startDate").val(),
                        endDate:$("#endDate").val()
					};
				},
				columns : [
					{
						title: '序号',//标题  可不加
						formatter: function (value, row, index) {
                            if(row.date=='合计'){
                                return '';
                            }
                            return index + 1;
						}
					},
					{
						field : 'date', // 列字段名
						title : '回款日期'
					},
                    {
                        field : 'xianjin', // 列字段名
                        title : '现金',//标题  可不加
                        formatter: function (value, row, index) {
                            xianjin+=value;
                            return formatData(value.toFixed(2));
                        }
                    },
                    {
                        field : 'pos', // 列字段名
                        title : 'POS',//标题  可不加
                        formatter: function (value, row, index) {
                            pos+=value;
                            return formatData(value.toFixed(2));
                        }
                    },
                    {
                        field : 'zhuanzhang', // 列字段名
                        title : '转账',//标题  可不加
                        formatter: function (value, row, index) {
                            zhuanzhang+=value;
                            return formatData(value.toFixed(2));
                        }
                    },
                    {
                        field : 'h', // 列字段名
                        title : '合计金额',//标题  可不加
                        formatter: function (value, row, index) {
                            total+=row.xianjin+row.pos+row.zhuanzhang;
                            return formatData((row.xianjin+row.pos+row.zhuanzhang).toFixed(2));
                        }
                    }
				],
                onLoadSuccess : function(data) {
                    var data = {'序号': '','date':'合计',
                        'xianjin': xianjin,'pos':pos,'zhuanzhang':zhuanzhang
                        ,'h':total};
                    $('#exampleTable').bootstrapTable('append', data);
                    xianjin=0;
                    pos=0;
                    zhuanzhang=0;
                    total=0;
                }
			});
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function c() {
    $("#startDate").val("");
    $("#endDate").val("");
}