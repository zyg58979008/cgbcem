var prefix = "/report"
$(function () {
    load();
    getTreeData();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/shopShoujiaolvList", // 服务器数据的加载地址
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                iconSize: 'outline',
                toolbar: '#dataToolbar',
                fixedColumns: true,
                fixedNumber: 0,
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        findDate: $('#findDate').val(),
                        contractor: $('#contractor').val(),
                        brand: $('#brand').val()
                    };
                },
                columns: [
                    {
                        field: 'contractCode',
                        title: '合同编号',
                        colspan: 1,
                        align: "center",
                        valign: "middle",
                        width: "200px"
                    },
                    {
                        field: 'floors',
                        title: '楼层',
                        colspan: 1,
                        align: "center",
                        valign: "middle"
                    },
                    {
                        field: 'shopCode',
                        title: '商铺编码',
                        colspan: 1,
                        align: "center",
                        valign: "middle",
                        width: "180px"
                    },
                    {
                        field: 'contractor',
                        title: '签约人',
                        colspan: 1,
                        align: "center",
                        valign: "middle"
                    },
                    {
                        field: 'brand',
                        title: '品牌',
                        colspan: 1,
                        align: "center",
                        valign: "middle",
                        width: "180px"
                    },
                    {
                        field: 'total',
                        title: '应收',
                        colspan: 1,
                        align: "center",
                        valign: "middle",
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'payed',
                        title: '已收',
                        colspan: 1,
                        align: "center",
                        valign: "middle",
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'unpay',
                        title: '未收',
                        colspan: 1,
                        align: "center",
                        valign: "middle",
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'percentString',
                        title: '收缴率',
                        colspan: 1,
                        align: "center",
                        valign: "middle"
                    }
                ],
                onLoadSuccess: function (data) {
                    var data = $('#exampleTable').bootstrapTable('getData', true);
                    // 合并单元格
                    var fieldList = ["contractCode", "contractor",
                        "brand", "total", "payed", "unpay",
                        "percentString"];
                    mergeCells(data, "contractCode", 1, $('#exampleTable'), fieldList);
                }
            });
}
function getTreeData() {
    $.ajax({
        type: "GET",
        url: "/business/building/tree/2",
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
    if (data.selected == -1) {
        buildingId = '0';
        $('#exampleTable').bootstrapTable('refresh');
        clear();
    } else {
        if (data.node) {
            var node = data.node;
            if (node.state.type == '2') {
                buildingId = data.selected[0];
                deptId = node.state.deptId;
                var opt = {
                    query: {
                        buildingId: data.selected[0],
                    }
                }
                $('#exampleTable').bootstrapTable('refresh', opt);
            } else {
                clear();
                $('#exampleTable').bootstrapTable('refresh');
            }
        }
    }
});
function resetPwd(id) {
}
function download() {
    var url = prefix + "/download";
    //更改form的action
    $("#importForm").attr("action", url);
    //触发submit事件，提交表单
    $("#importForm").submit();
}
function batchRemove() {
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
function reLoad() {
    /*var opt = {
     query : {
     contractor : $('#contractor').val(),
     contractStartDate : $('#contractStartDate').val(),
     contractEndDate : $('#contractEndDate').val(),
     floor:$('#floor').val(),
     brand:$('#brand').val()
     }
     }*/
    $('#exampleTable').bootstrapTable('refresh');
}
function clear() {
    buildingId = '';
    deptId = '';
}
function mergeCells(data, fieldName, colspan, target, fieldList) {
// 声明一个map计算相同属性值在data对象出现的次数和
    var sortMap = {};
    /*for(var i = 0 ; i < data.length ; i++){
     for(var prop in data[i]){
     //例如people.unit.name
     var fieldArr=fieldName.split(".");
     getCount(data[i],prop,fieldArr,0,sortMap);
     }
     }*/
    var id = '';
    for (var i = 0; i < data.length; i++) {
        var f = data[i].contractCode;
        if (!f)
            f = '';
        if (id == '' || id == f) {
            id = f;
            var key = f;
            if (!key)
                key = '';
            if (sortMap.hasOwnProperty(key)) {
                sortMap[key] = sortMap[key] + 1;
            } else {
                sortMap[key] = 1;
            }
        }
        else {
            id = f;
            var key = f;
            if (!key)
                key = '';
            if (sortMap.hasOwnProperty(key)) {
                sortMap[key] = sortMap[key] + 1;
            } else {
                sortMap[key] = 1;
            }
        }
    }
    var index = 0;
    for (var prop in sortMap) {
        var count = sortMap[prop];
        for (var i = 0; i < fieldList.length; i++) {
            $(target).bootstrapTable('mergeCells', {
                index: index,
                field: fieldList[i],
                colspan: colspan,
                rowspan: count
            });
        }
        index += count;
    }
}
function getCount(data, prop, fieldArr, index, sortMap) {
    if (index == fieldArr.length - 1) {
        if (prop == fieldArr[index]) {
            var key = data[prop];
            if (sortMap.hasOwnProperty(key)) {
                sortMap[key] = sortMap[key] + 1;
            } else {
                sortMap[key] = 1;
            }
        }
        return;
    }
    if (prop == fieldArr[index]) {
        var sdata = data[prop];
        index = index + 1;
        getCount(sdata, fieldArr[index], fieldArr, index, sortMap);
    }

}