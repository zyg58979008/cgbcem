var prefix = "/business/contract";
$(function () {
    load();
});
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "/js/appjs/business/contract/pay1.json", // 服务器数据的加载地址
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
                // queryParams : queryParams,
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求

                columns : [
                    {
                        field: 'buildingId', // 列字段名
                        title: '序号',// 列标题
                        align: 'center'
                    },
                    {
                        field: 'date',
                        title: '缴费日期',
                        align: 'center'
                    },
                    {
                        field: 'ju',
                        title: '收据单号',
                        align: 'center'
                    },
                    {
                        field: 'ming',
                        title: '费项名称',
                        align: 'center'
                    },
                    {
                        field: 'name',
                        title: '缴费人',
                        align: 'center'
                    },
                    {
                        field: 'state',
                        title: '状态',
                        align: 'center'
                    },
                    {
                        field: 'ying',
                        title: '收费金额',
                        align: 'center'
                    },
                    {
                        field: 'shou',
                        title: '收款方式',
                        align: 'center'
                    },
                    {
                        field: 'bank',
                        title: '开户行',
                        align: 'center'
                    },
                    {
                        field: 'hao',
                        title: '卡号',
                        align: 'center'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: "center",
                        valign: "middle",
                        switch: true,
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary " href="#" mce_href="#" title="打印" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-print"></i></a> ';
                            var d = '<a class="btn btn-danger" href="#" title="作废"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e+d;
                        }
                    } ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    // iframe层
    layer.open({
        type: 2,
        title: '缴费',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '560px'],
        content: prefix + '/add1' // iframe的url
    });
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code === 0) {
                    layer.msg("删除成功");
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
        title: '楼宇信息修改',
        maxmin: true,
        shadeClose: true, // 点击遮罩关闭层
        area: ['800px', '480px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}
function batchRemove() {

    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
    }, function () {
        var ids = new Array();
        $.each(rows, function (i, row) {
            ids[i] = row['roleId'];
        });
        console.log(ids);
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