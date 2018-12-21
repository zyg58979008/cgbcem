var prefix = "/equipment/stockQuery"
$(function () {
    loadParent();
    loadChild();
    loadType();
    loadBrand();
    getTreeData();
});

function loadParent() {
    $('#parentTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 15, // 如果设置了分页，每页数据条数
                pageList: [15, 25, 50, 100],
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset
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
                columns: [
                    {
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            var pageSize=$('#parentTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                            var pageNumber=$('#parentTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                        }
                    },

                    {
                        field: 'categoryName',
                        title: '分类名称',
                        formatter: function (value, row, index) {
                            return value.substr(0, 10)
                        }
                    },
                    {
                        field: 'num',
                        title: '库存数量'
                    }]
            });
}
function loadChild() {
    $('#childTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/listChild", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: false, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                detailView: true,//父子表
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset
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
                columns: [{
                    title: '序号',//标题  可不加
                    formatter: function (value, row, index) {
                        return index + 1;
                    }
                },
                    {
                        field: 'name',
                        title: '设备名称'
                    },
                    {
                        field: 'categoryName',
                        title: '设备分类'
                    },
                    {
                        field: 'brandName',
                        title: '设备品牌'
                    },
                    {
                        field: 'model',
                        title: '设备型号'
                    },
                    {
                        field: 'unit',
                        title: '设备单位'
                    },
                    {
                        field: 'num',
                        title: '设备库存量'
                    }],
                //注册加载子表的事件。注意下这里的三个参数！
                onExpandRow: function (index, row, $detail) {
                    InitSubTable(index, row, $detail);
                }
            });
}
function InitSubTable(index, row, $detail) {
    var cur_table = $detail.html('<table></table>').find('table');
    var a = prefix + '/listChildGroup/?type='+type+'&deptId='+deptId+'&brand=' + row.brand+'&model=' + row.model+'&name=' + row.name+'&category=' + category+'&categoryType=' + categoryType;
    $(cur_table).bootstrapTable({
        method: 'get',
        url:a,
        clickToSelect: true,
        sidePagination: "server",
        detailView: false,//父子表
        uniqueId: "id",
        columns: [
            {
                title: '序号',//标题  可不加
                formatter: function (value, row, index) {
                    return index + 1;
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
                field: 'unit',
                title: '设备单位'
            },
            {
                field: 'deptName',
                title: '所在机构'
            },
            {
                field: 'address',
                title: '所在位置',
                formatter : function(value, r, i) {
                    if(r.type=='1'||r.type=='2'||r.type=='3'){
                        return r.storeroomName;
                    }
                    if(r.type=='5'){
                        return r.userName;
                    }
                }
            },
            {
                field: 'num',
                title: '设备库存量'
            },
            {
                title : '操作',
                field : 'id',
                align : 'center',
                formatter : function(value, row, i) {
                    var f = '<a class="btn btn-success btn-sm " href="#" title="详情"  mce_href="#" ' +
                        'onclick="detail(\''
                        + row.type
                        + '\',\''
                        + row.deptId
                        + '\',\''
                        + row.storeroomId
                        + '\',\''
                        + row.userId
                        + '\',\''
                        + row.name
                        + '\',\''
                        + row.brand
                        + '\',\''
                        + row.model
                        + '\')"><i class="fa fa-info" style="width: 10px"></i></a> ';

                    return f;
                }
            }]
    });
};
var name='';
var brand='';
var deptId='';
var type='0';
function reLoad() {
    name='';
    if($('#name').val()&&$('#name').val()!=''){
        name=$('#name').val();
    }
    brand='';
    if($('#brand').val()&&$('#brand').val()!=''){
        brand=$('#brand').val();
    }
    deptId='';
    if($('#deptId').val()&&$('#deptId').val()!=''){
        deptId=$('#deptId').val();
    }
    type='';
    if($('#type').val()&&$('#type').val()!=''){
        type=$('#type').val();
    }
    if(categoryType=='parent'){
        $("#child").addClass("hidden");
        $("#parent").removeClass("hidden");
    }
    if(categoryType=='child'){
        $("#parent").addClass("hidden");
        $("#child").removeClass("hidden");
    }
    $('#'+categoryType+'Table').bootstrapTable('refresh', {
        query:
        {
            type:type,
            deptId:deptId,
            brand:brand,
            name:name,
            category:category,
            categoryType:categoryType
        }
    });
}
//t-类型，d-机构，s-库房，u-人员,n-设备名称,b-品牌,m-型号
function detail(t,d,s,u,n,b,m) {

    var index=layer.open({
        type: 2,
        title: '详情',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['860px', '480px'],
        content: prefix + '/detail?type='+t+'&deptId='+d+'&storeroomId=' + s+'&name='
        + n+'&brand=' + b+'&model=' + m+'&userId=' + u
    });
    layer.full(index);
}
function loadType(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/storeroom_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#type").append(html);
            $("#type").chosen({
                maxHeight : 200
            });
        }
    });
}
function loadBrand(){
    var html = "";
    $.ajax({
        url : '/equipment/equipment/brand',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
            }
            $("#brand").append(html);
            $("#brand").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#brand').on('change', function(e, params) {
                //console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected
                    }
                }
            });
        }
    });
}
var openDept = function () {
    layer.open({
        type: 2,
        title: "选择部门",
        area: ['300px', '450px'],
        content: "/system/sysDept/treeViewById"
    })
}
function loadDept(deptId, deptName) {
    $("#deptId").val(deptId);
    $("#deptName").val(deptName);
}
function getTreeData() {
    $.ajax({
        type : "GET",
        url : "/equipment/categories/tree",
        success : function(tree) {
            loadTree(tree);
        }
    });
}
function loadTree(tree) {
    $('#jstree').jstree({
        'core' : {
            'data' : tree
        },
        "plugins" : [ "search" ]
    });
    $('#jstree').jstree().open_all();
}
var category='';
var categoryType='parent';
$('#jstree').on("changed.jstree", function(e, data) {
    if (data.node.id == -1||data.node.children.length>0) {
        if(data.node.id == -1){
            category='0';
        }else {
            category=data.node.id;
        }
        categoryType='parent';
    } else {
        category=data.node.id;
        categoryType='child';
    }
    reLoad();
});