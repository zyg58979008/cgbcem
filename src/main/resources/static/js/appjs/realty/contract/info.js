var prefix = "/realty/contract";
var isLog=false;
var isPay=false;
$().ready(function() {
    loadBuildingType();
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {     
        var activeTab = $(e.target).text();     
        if(activeTab=='合同日志'){
            if(!isLog){
                loadLog();
                isLog=true;
            }else{
                $('#logTable')
                    .bootstrapTable('refresh')
            }
        }
        if(activeTab=='缴费记录'){
            if(!isPay){
                loadPay();
                isPay=true;
            }else{
                $('#payTable')
                    .bootstrapTable('refresh')
            }
        }
     });
});
function loadPay() {
    var yewuId=parent.contract.id;
    if(!yewuId||yewuId==''){
        yewuId='0';
    }
    $('#payTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/payList",
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        yewuId:yewuId
                        // username:$('#searchName').val()
                    };
                },
                columns : [
                    {
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },
                    {
                        field : 'state',
                        title : '状态',//标题  可不加
                        formatter: function (value, row, index) {
                            if(value=='0'){
                                return '正常';
                            }
                            if(value=='1'){
                                return '冲销';
                            }
                            if(value=='2'){
                                return '已冲销';
                            }
                        }
                    },
                    {
                        field : 'sTypeName', // 列字段名
                        title : '交来类型', // 列标题,
                    },
                    {
                        field : 'typeName',
                        title : '收款方式'
                    },
                    {
                        field : 'price',
                        title : '收款金额',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return value.toFixed(2);
                            }
                        }
                    },
                    {
                        field : 'receiptBy',
                        title : '收款人'
                    },
                    {
                        field : 'payDate',
                        title : '收款日期'
                    } ]
            });
}
function loadLog() {
    $('#logTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/log",
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                iconSize : 'outline',
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        contractId:parent.contract.id
                        // username:$('#searchName').val()
                    };
                },
                columns : [
                    {
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },
                    {
                        field : 'name', // 列字段名
                        title : '内容', // 列标题,
                        width:'50%'
                    },
                    {
                        field : 'createByName',
                        title : '操作人'
                    },
                    {
                        field : 'createDate',
                        title : '操作日期'
                    } ]
            });
}
function loadBuildingType(){
    var roomType= document.getElementById("roomType1").value;
    $.ajax({
        url : '/common/dict/list/room_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                if(data[i].value==roomType){
                    $("#roomType").val(data[i].name);
                }
            }
        }
    });
}