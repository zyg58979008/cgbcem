

var prefix = "/equipment/count"
$(function() {
    load1();
    load2();
});
function load1() {
    $('#exampleTable1')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list1", // 服务器数据的加载地址
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                queryParamsType : "limit",
               /* queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        deptId: $('#deptId').val()
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },*/
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [


                    {
                        field : 'categoryName',
                        title : '设备分类名称'
                    },


                    {
                        field : 'repairNum',
                        title : '维修数量'
                    },
                    {
                        field : 'rejectNum',
                        title : '报废数量'
                    },
                    {
                        field : 'onlineNum',
                        title : '在线数量'
                    }

                ]
            });
}
function load2() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list2", // 服务器数据的加载地址
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型

                 queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
              //  singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                //search : true, // 是否显示搜索框

             /*  queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                       limit: params.limit,
                        offset:params.offset,
                        deptId: $('#deptId').val()
                        // username:$('#searchName').val()
                    };
                },*/
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [


                    {
                        field : 'name',
                        title : '设备名称'
                    },
                    {
                        field : 'brandName',
                        title : '品牌'
                    },

                    {
                        field : 'model',
                        title : '型号'
                    },
                   {
                        field : 'num',
                        title : '数量'
                    }

                     ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}



