var prefix = "/business/contract";
var isLog=false;
var isPay=false;
var isShop=false;
$().ready(function() {
    //loadBuildingType();
    loadBank();
    loadShop();
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
        if(activeTab=='房屋信息'){

                $('#exampleTable')
                    .bootstrapTable('refresh')

        }
    });
});
function loadShop() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/listDetail", // 服务器数据的加载地址
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                toolbar : '#dataToolbar',
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 30, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        contractCode:parent.contract.contractCode
                        // username:$('#searchName').val()
                    };
                },
                columns : [
                    {
                        field : 'buildingName',
                        title : '楼宇',
                        align:"center",
                        valign:"middle",
                        width:"200px"
                    },
                    {
                        field : 'contractCode',
                        title : '合同编号',
                        align:"center",
                        valign:"middle",
                        width:"200px"
                    },

                    {
                        field : 'floor',
                        title : '楼层',
                        align:"center",
                        valign:"middle"
                    },
                    {
                        field : 'shopCode',
                        title : '商铺编码',
                        align:"center",
                        valign:"middle",
                        width:"180px"
                    },
                    {
                        field : 'contractor',
                        title : '签约人',
                        align:"center",
                        valign:"middle"
                    },

                    {
                        field : 'rentArea',
                        title : '计租面积',
                        align:"center",
                        valign:"middle"
                    },
                    {
                        field : 'brand',
                        title : '品牌',
                        align:"center",
                        valign:"middle",
                        width:"180px"
                    },

                    {
                        field : 'idCard',
                        title : '身份证号',
                        align:"center",
                        valign:"middle"
                    }/*,

                    {
                        title : '操作',
                        field : 'id',
                        align:"center",
                        valign:"middle",
                        switch:true,
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-danger btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            /!*var f = '<a class="btn btn-success btn-sm ' + s_jiaofang_h + '" href="#" title="交房"  mce_href="#" onclick="jiaofang(\''
                             + row.id
                             + '\')"><i class="fa fa-key"></i></a> ';*!/
                            return;
                        }
                    } */]
            });
}
function loadPay() {
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
                        oid:parent.contract.oid
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
                        field : 'sTypeName', // 列字段名
                        title : '交来类型', // 列标题,
                    },
                    {
                        field : 'typeName',
                        title : '收款方式'
                    },
                    {
                        field : 'price',
                        title : '收款金额'
                    },
                    {
                        field : 'receiptBy',
                        title : '收款人'
                    },
                    {
                        field : 'createDate',
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
                        contractId:parent.contract.contractCode
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