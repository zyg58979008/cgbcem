
var prefix = "/equipment/count"
$(function() {

    load();
});



function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/listAll", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                //iconSize : 'outline',
                //toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
               // pagination : true, // 设置为true会在底部显示分页条
                queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                // singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                //pageSize : 10, // 如果设置了分页，每页数据条数
                //pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                //showColumns : false, // 是否显示内容下拉框（选择显示的列）
                //sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        //deptId: $('#deptId').val()
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
                    [
                        {
                                field: '',
                                title: "",
                                valign:"middle",
                                align:"center",
                                colspan: 1,
                                rowspan: 1
                        },
                         {
                                title: "承德郊区",
                                     valign:"middle",
                                  align:"center",
                                   colspan: 3,
                                     rowspan: 1
                            },
                        {
                            title: "丰宁县",
                            valign:"middle",
                                    align:"center",
                                   colspan: 3,
                                    rowspan: 1
                        },
                        {
                            title: "承德县",
                            valign:"middle",
                            align:"center",
                            colspan: 3,
                            rowspan: 1
                        },
                        {
                            title: "围场县",
                            valign:"middle",
                            align:"center",
                            colspan: 3,
                            rowspan: 1
                        },
                        {
                            title: "平泉县",
                            valign:"middle",
                            align:"center",
                            colspan: 3,
                            rowspan: 1
                        },
                        {
                            title: "滦平县",
                            valign:"middle",
                            align:"center",
                            colspan: 3,
                            rowspan: 1
                        },
                        {
                            title: "隆化县",
                            valign:"middle",
                            align:"center",
                            colspan: 3,
                            rowspan: 1
                        },
                        {
                            title: "宽城县",
                            valign:"middle",
                            align:"center",
                            colspan: 3,
                            rowspan: 1
                        },
                        {
                            title: "兴隆县",
                            valign:"middle",
                            align:"center",
                            colspan: 3,
                            rowspan: 1
                        }
                    ],

                   [ {
                        field : 'categoryName',
                        title : '设备分类'
                    },
                    {
                        field : 'inboundNum130801',
                        title : '库存'
                    },

                    {
                        field : 'repairNum130801',
                        title : '维修'
                    },
                    {
                        field : 'rejectNum130801',
                        title : '报废'
                    },
                       {
                           field : 'inboundNum130826',
                           title : '库存'
                       },

                       {
                           field : 'repairNum130826',
                           title : '维修'
                       },
                       {
                           field : 'rejectNum130826',
                           title : '报废'
                       },
                       {
                           field : 'inboundNum130821',
                           title : '库存'
                       },

                       {
                           field : 'repairNum130821',
                           title : '维修'
                       },
                       {
                           field : 'rejectNum130821',
                           title : '报废'
                       },
                       {
                           field : 'inboundNum130828',
                           title : '库存'
                       },

                       {
                           field : 'repairNum130828',
                           title : '维修'
                       },
                       {
                           field : 'rejectNum130828',
                           title : '报废'
                       },
                       {
                           field : 'inboundNum130823',
                           title : '库存'
                       },

                       {
                           field : 'repairNum130823',
                           title : '维修'
                       },
                       {
                           field : 'rejectNum130823',
                           title : '报废'
                       },
                       {
                           field : 'inboundNum130824',
                           title : '库存'
                       },

                       {
                           field : 'repairNum130824',
                           title : '维修'
                       },
                       {
                           field : 'rejectNum130824',
                           title : '报废'
                       },
                       {
                           field : 'inboundNum130825',
                           title : '库存'
                       },

                       {
                           field : 'repairNum130825',
                           title : '维修'
                       },
                       {
                           field : 'rejectNum130825',
                           title : '报废'
                       },
                       {
                           field : 'inboundNum130827',
                           title : '库存'
                       },

                       {
                           field : 'repairNum130827',
                           title : '维修'
                       },
                       {
                           field : 'rejectNum130827',
                           title : '报废'
                       },
                       {
                           field : 'inboundNum130822',
                           title : '库存'
                       },

                       {
                           field : 'repairNum130822',
                           title : '维修'
                       },
                       {
                           field : 'rejectNum130822',
                           title : '报废'
                       }
                   ]]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}





function resetPwd(id) {
    window.location.href="/stock/product1";

    /*layer.open({
     type : 2,
     title : '详情',
     maxmin : true,
     shadeClose : false, // 点击遮罩关闭层
     area : [ '960px', '520px' ],
     content : prefix + '/info' // iframe的url
     });*/
}
