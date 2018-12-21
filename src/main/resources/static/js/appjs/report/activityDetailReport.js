var prefix = "/report"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/activityDetailReportList", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                showExport: true,
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                exportDataType: "basic",
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                //pageSize : 10, // 如果设置了分页，每页数据条数
                //pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                fixedColumns: true,
                fixedNumber: 2,
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        /*limit: params.limit,
                         offset:params.offset,*/
                        orderId: parent.activity.orderId
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
                        field: 'unreceived',
                        title: '活动未缴合计',
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
                            return value.substr(0, 10)
                        }
                    },
                    {
                        field: 'endDate',
                        title: '活动结束日期',
                        formatter: function (value, row, index) {
                            return value.substr(0, 10)
                        }
                    }
                ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh', {
        query: {
            brand: $("#brand").val(),
            contractor: $("#contractor").val()
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
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
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