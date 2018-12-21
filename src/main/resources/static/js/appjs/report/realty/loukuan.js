
var prefix = "/realtyReport";
var totalPrice=0;
var yishou=0;
var weishou=0;
$(function() {
	load();
});
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/loukuanList",
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : false, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
                exportDataType: "basic",
                showRefresh: true,
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
					};
				},
				columns : [
					{
						title: '序号',//标题  可不加
						formatter: function (value, row, index) {
						    if(row.buildingName=='合计'){
						        return '';
                            }
							return index + 1;
						}
					},
					{
						field : 'parentBuildingName', // 列字段名
						title : '区段'
					},
                    {
                        field : 'buildingName', // 列字段名
                        title : '楼号'
                    },
                    {
                        field : 'totalPrice', // 列字段名
                        title : '房款总额',//标题  可不加
                        formatter: function (value, row, index) {
                            totalPrice+=value;
                            return formatData(value.toFixed(2));
                        }
                    },
                    {
                        field : 'yishou', // 列字段名
                        title : '已收金额',//标题  可不加
                        formatter: function (value, row, index) {
                            yishou+=value;
                            return formatData(value.toFixed(2));
                        }
                    },
                    {
                        field : 'weishou', // 列字段名
                        title : '未收金额',//标题  可不加
                        formatter: function (value, row, index) {
                            weishou+=value;
                            return formatData(value.toFixed(2));
                        }
                    }
				],
                onLoadSuccess : function(data) {
                    var data = $('#exampleTable').bootstrapTable('getData', true);
                    //合并单元格
                    mergeCells(data, "parentBuildingName", 1, $('#exampleTable'));
                    var data = {'序号': '合计','parentBuildingName':'','buildingName':'合计',
                        'totalPrice': totalPrice,'yishou':yishou,'weishou':weishou};
                    $('#exampleTable').bootstrapTable('append', data);
                    totalPrice=0;
                    yishou=0;
                    weishou=0;
                }
			});
}
/**
 * 合并单元格
 * @param data  原始数据（在服务端完成排序）
 * @param fieldName 合并属性名称
 * @param colspan   合并列
 * @param target    目标表格对象
 */
function mergeCells(data,fieldName,colspan,target){
    //声明一个map计算相同属性值在data对象出现的次数和
    var sortMap = {};
    for(var i = 0 ; i < data.length ; i++){
        for(var prop in data[i]){
            if(prop == fieldName){
                var key = data[i][prop]
                if(sortMap.hasOwnProperty(key)){
                    sortMap[key] = sortMap[key] * 1 + 1;
                } else {
                    sortMap[key] = 1;
                }
                break;
            }
        }
    }
    for(var prop in sortMap){
        console.log(prop,sortMap[prop])
    }
    var index = 0;
    for(var prop in sortMap){
        var count = sortMap[prop] * 1;
        $(target).bootstrapTable('mergeCells',{index:index, field:fieldName, colspan: colspan, rowspan: count});
        index += count;
    }
}
