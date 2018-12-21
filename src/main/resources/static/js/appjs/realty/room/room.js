var prefix = "/realty/room"
var isLoad = false;
var buildingName = '';
var totalArea = 0;
$(function () {
    getTreeData();
    loadBuildingType();
    var height = $(document.body).height();
    height = height - 68;
    $("#exampleTable").attr("data-height", height);
    load();
});
var buildingId = '';//楼宇ID
var unit = 1;//单元数
var mergeIds = new Array();//合并的房屋
var deptId = '';//项目ID
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                showFooter: true,
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        code: $("#code").val(),
                        roomType: $("#roomType").val(),
                        buildingId: buildingId
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            if(row.roomTypeName=='合计'){
                                return '';
                            }
                            return index + 1;
                        }
                    },
                    {
                        field: 'buildingName', // 列字段名
                        title: '楼宇名称',
                        formatter: function (value, row, index) {
                            if(row.roomTypeName=='合计'){
                                return '';
                            }
                            return buildingName;
                        }
                    },
                    {
                        field: 'code', // 列字段名
                        title: '房间编号' // 列标题
                    },
                    {
                        field: 'unit',
                        title: '单元'
                    },
                    {
                        field: 'floor',
                        title: '楼层'
                    },
                    {
                        field: 'roomTypeName',
                        title: '房屋类型',//标题  可不加
                        footerFormatter: function (value, row, index) {
                            return '合计';
                        }
                    },
                    {
                        field: 'buildingArea',
                        title: '建筑面积',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return value;
                            }
                        },
                        footerFormatter: function (value) {
                            return totalArea;
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit "></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm ' + s_split_h + '" href="#" title="拆分"  mce_href="#" onclick="resetPwd(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d + f;
                        }
                    }], onLoadSuccess: function (item) {

            }
            });
}
function e(){
    var data = {'序号': '','buildingName':'','code':'',
        'unit': '','floor':'','roomTypeName':'合计','buildingArea':totalArea,'id':''};
    $('#exampleTable').bootstrapTable('append', data);
    $('#exampleTable').tableExport({ type: 'excel', escape: 'false' ,fileName:'房屋明细',ignoreColumn:[0,8]});
    $('#exampleTable').bootstrapTable('remove', {field: 'roomTypeName', values: ['合计']});
}
function reLoad() {
    $.ajax({
        type: "POST",
        data: {
            code: $("#code").val(),
            roomType: $("#roomType").val(),
            buildingId: buildingId
        },
        url: prefix + "/getSum",
        success: function (data) {
            if (data) {
                totalArea=data;
            }else {
                totalArea='0.00'
            }
            if (!isLoad) {
                $('#exampleTable').bootstrapTable("refreshOptions",
                    {
                        url: prefix + "/list",
                    });
                load();
                isLoad = true;
            } else {
                $('#exampleTable').bootstrapTable('refresh');
            }
        }
    });

}
function add() {
    if (buildingId == '') {
        layer.msg('请先选择楼宇');
        return;
    }
    // iframe层
    layer.open({
        type: 2,
        title: '增加房间',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add'
    });
}
function remove(id) {
    type : "post",
        layer.confirm('确定要删除选中的记录？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                url: prefix + '/remove',
                type: "post",
                data: {
                    'id': id
                },
                success: function (r) {
                    if (r.code == 0) {
                        layer.msg(r.msg);
                        reLoad();
                    } else {
                        layer.msg(r.msg);
                    }
                }
            });
        })
}
function edit(id) {
    layer.open({
        type: 2,
        title: '修改房间',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}
function batchRemove() {
    if (buildingId == '') {
        layer.msg('请先选择楼宇');
        return;
    }
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {
    });
}
function merge() {
    if (buildingId == '') {
        layer.msg('请先选择楼宇');
        return;
    }
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要合并的房屋");
        return;
    }
    var u = '';
    var isOpne = false;
    $.each(rows, function (i, row) {
        if (u == '') {
            u = row['unit'];
        } else {
            if (u != row['unit']) {
                layer.msg("所选房屋不在同一单元，请重新选择");
                mergeIds[i] = new Array();
                isOpne = true;
                return;
            }
        }
        u = row['unit'];
        mergeIds[i] = row['id'];
    });

    if (isOpne) {
        return;
    }
    unit = rows[0].unit;
    layer.open({
        type: 2,
        title: '合并房间',
        maxmin: true,
        shadeClose: false,
        area: ['800px', '520px'],
        content: prefix + '/merge' // iframe的url
    });
}
function getTreeData() {
    $.ajax({
        type: "GET",
        url: "/realty/building/tree/2",
        success: function (tree) {
            loadTree(tree);
        }
    });
}
function loadTree(tree) {
    $('#jstree').jstree({
        'core': {
            'data': tree
        },
        "plugins": ["search"]
    });
    $('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function (e, data) {
    if (data.node) {
        var node = data.node;
        if (node.state.type == '2') {
            buildingId = data.selected[0];
            deptId = node.state.deptId;
            unit = parseInt(node.state.unit);
            buildingName = node.text.split("(")[0];
            reLoad();
        } else {
            clear();
        }
    }
});
function loadBuildingType() {
    var html = "";
    $.ajax({
        url: '/common/dict/list/room_type',
        success: function (data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#roomType").append(html);
            $("#roomType").chosen({
                maxHeight: 200
            });
            //点击事件
            $('#roomType').on('change', function (e, params, a) {
                //console.log(params.selected);
                $("#roomType1").val(params.selected);
            });
        }
    });
}
function download() {
    var url = prefix + "/exportXls";
    //更改form的action
    $("#importForm").attr("action", url);
    //触发submit事件，提交表单
    $("#importForm").submit();
}

function clear() {
    buildingId = '';
    unit = 0;
    mergeIds = new Array();
    deptId = '';
}
function c() {
    $("#code").val('');
}