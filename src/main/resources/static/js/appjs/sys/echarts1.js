
var prefix = "/equipment/count"
$(function() {
    getTreeData();
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/listArea", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                //iconSize : 'outline',
                //toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
              //  pagination : true, // 设置为true会在底部显示分页条
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
                        deptId: $('#deptId').val()
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
                        field : 'categoryName',
                        title : '设备分类'
                    },
                    {
                        field : 'inboundNum',
                        title : '库存数量'
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
                    } ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function getTreeData() {
    $.ajax({
        type : "GET",
        url : "/system/sysDept/treeByAreaLevel",
        success : function(tree) {
            loadTree(tree);
        }
    });
}

function loadTree(tree) {
    $('#jstree_demo_div').jstree({
        'core' : {
            'data' : tree
        },
        "plugins" : [ "search" ]
    });
    $('#jstree_demo_div').jstree().open_all();
}
$('#jstree_demo_div').on("changed.jstree", function(e, data) {
    if (data.selected == -1) {
        var opt = {
            query : {
                deptId : '',
            }
        }
        $('#jstree_demo_div').bootstrapTable('refresh', opt);
    } else {
        deptId =data.selected[0];

        var opt = {

            query : {

                deptId : deptId

            }
        }
        $('#exampleTable').bootstrapTable('refresh',opt);
    }

});

function edit(id) {
    layer.open({
        type : 2,
        title : '编辑',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + id // iframe的url
    });
}


function resetPwd(id) {
    window.location.href="/equipment/stockQuery";

    /*layer.open({
     type : 2,
     title : '详情',
     maxmin : true,
     shadeClose : false, // 点击遮罩关闭层
     area : [ '960px', '520px' ],
     content : prefix + '/info' // iframe的url
     });*/
}
