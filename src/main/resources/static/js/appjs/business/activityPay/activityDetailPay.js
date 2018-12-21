var prefix = "/business/activity"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/listDetailPay", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                //search : true, // 是否显示搜索框
                fixedColumns: true,
                fixedNumber: 3,
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        orderId: parent.activity.orderId
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
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm ' + s_detailPay_h + '" href="#" mce_href="#" title="缴费" onclick="detailPay(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-danger btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d;
                        }
                    },
                    {
                        field: 'contractor',
                        title: '签约人'
                    },
                    {
                        field: 'brand',
                        title: '品牌'
                    },
                    {
                        field: 'shopType',
                        title: '商铺经营类别'
                    },
                    {
                        field: 'activityName',
                        title: '活动名称'
                    },
                    {
                        field: 'activityMoney',
                        title: '活动费',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'dmDan',
                        title: 'DM单',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'cardMoney',
                        title: '邀请卡款',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'activityMoneyReceived',
                        title: '活动费已收',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'dmDanReceived',
                        title: 'DM单已收',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'cardMoneyReceived',
                        title: '邀请卡款已收',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'activityMoneyUnreceived',
                        title: '活动费未缴',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'dmDanUnreceived',
                        title: 'DM单未缴',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'cardMoneyUnreceived',
                        title: '邀请卡款未缴',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'startDate',
                        title: '活动开始日期',
                        formatter: function (value, row, index) {
                            if (value) {
                                return value.substr(0, 10)
                            }
                            else {
                                return ''
                            }
                        }
                    },
                    {
                        field: 'endDate',
                        title: '活动结束日期',
                        formatter: function (value, row, index) {
                            if (value) {
                                return value.substr(0, 10)
                            }
                            else {
                                return ''
                            }
                        }
                    }
                ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh', {
        query: {
            brand: $("#brand").val()
        }
    });
}
function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}
function detailPay(id) {
    layer.open({
        type: 2,
        title: '缴费',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['950px', '520px'],
        content: prefix + '/detailPay/' + id // iframe的url
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
function download() {
    var url = prefix + "/download";
    //更改form的action
    $("#importForm").attr("action", url);
    //触发submit事件，提交表单
    $("#importForm").submit();
}
function resetPwd(id) {
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
function info() {
    layer.open({
        type: 2,
        title: '输入信息',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/info' // iframe的url
    });
}