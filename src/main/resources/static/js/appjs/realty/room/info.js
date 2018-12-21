var prefix = "/realty/contract";
var isLog=false;
$().ready(function() {
    loadBuildingType();
    loadBank();
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {     
        var activeTab = $(e.target).text();     
        if(activeTab=='合同日志'){
            if(!isLog){
                load();
                isLog=true;
            }else{
                $('#logTable')
                    .bootstrapTable('refresh')
            }

        }
     });
});
function load() {
    $('#logTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/log/"+parent.contract.id,
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : false, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
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
function loadBank(){
    var unit= document.getElementById("openingBank1").value;
    var html = "";
    $.ajax({
        url : '/common/dict/list/bank',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                if(data[i].value==unit){
                    $("#openingBank").val(data[i].name);
                }
            }
        }
    });
}