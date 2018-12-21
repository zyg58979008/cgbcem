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
                        contractor: $('#contractor').val(),
                        contractStartDate: $('#contractStartDate').val(),
                        contractEndDate: $('#contractEndDate').val(),
                        floor: $('#floor').val(),
                        brand: $('#brand').val(),
                        buildingId: buildingId
                    };
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
function contractAdd(id) {
    layer.open({
        type: 2,
        title: '合同续签',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/contractadd/' + id// iframe的url
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
                reLoad();
            } else {
                clear();
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
function reLoad() {
    $('#exampleTable').bootstrapTable("refreshOptions",
        {
            url: prefix + "/shopContractTaizhangList", // 服务器数据的加载地址
            columns: [
                [{
                    field: 'contractCode',
                    title: '合同编号',
                    colspan: 1,
                    rowspan: 2,
                    class: 'add-at',
                    align: "center",
                    valign: "middle",
                    width: "200px",
                    formatter: function (value, row, index) {
                        if (value) {
                            return value;
                        } else {
                            return '';
                        }
                    }
                },
                    {
                        field: 'floorString',
                        title: '楼层',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'shopCode',
                        title: '商铺编码',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        width: "180px",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'contractor',
                        title: '签约人',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'contractTrueArea',
                        title: '合同实测面积',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'contractRentArea',
                        title: '合同计租面积（含20%公摊）',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'rentArea',
                        title: '计租面积',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'brand',
                        title: '品牌',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        width: "180px",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'shopType',
                        title: '商铺经营类别',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'contractPayTotal',
                        title: '合同期应收租金',
                        colspan: 1,
                        rowspan: 2,
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
                        field: 'unitPrice',
                        title: '租金单价',
                        colspan: 1,
                        rowspan: 2,
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
                        field: 'payType',
                        title: '收款方式',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'youhui',
                        title: '优惠政策',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        title: '收入分类（元/平）',
                        colspan: 3,
                        rowspan: 1,
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
                        field: 'contractStartDate',
                        title: '合同起',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value.substr(0, 10);
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'contractEndDate',
                        title: '合同止',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value.substr(0, 10);
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'amortizeMonths',
                        title: '摊销月数',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'receivableLvyue',
                        title: '应收履约保证金',
                        colspan: 1,
                        rowspan: 2,
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
                        field: 'receivableZhiliang',
                        title: '应收质量保证金',
                        colspan: 1,
                        rowspan: 2,
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
                        field: 'receivableFuwu',
                        title: '应收服务保证金',
                        colspan: 1,
                        rowspan: 2,
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
                        field: 'receivableZhuangxiu',
                        title: '应收装修押金',
                        colspan: 1,
                        rowspan: 2,
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
                        field: 'phone',
                        title: '电话',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'idCard',
                        title: '身份证号',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'kaidanCode',
                        title: '开单编码',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'kaidanName',
                        title: '开单名称',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'payee',
                        title: '收款人',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    }, {
                    field: 'account',
                    title: '账户',
                    colspan: 1,
                    rowspan: 2,
                    align: "center",
                    valign: "middle",
                    formatter: function (value, row, index) {
                        if (value) {
                            return value;
                        } else {
                            return '';
                        }
                    }
                },
                    {
                        field: 'bank',
                        title: '开户行',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'contractDate',
                        title: '签约日期',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value.substr(0, 10);
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'zulinbiao',
                        title: '租赁申请表',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'copyIdCard',
                        title: '身份证复印件',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'copyBankCard',
                        title: '银行卡复印件',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },
                    {
                        field: 'tuzhi',
                        title: '图纸',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    },

                    {
                        field: 'remarks',
                        title: '备注',
                        colspan: 1,
                        rowspan: 2,
                        align: "center",
                        valign: "middle",
                        formatter: function (value, row, index) {
                            if (value) {
                                return value;
                            } else {
                                return '';
                            }
                        }
                    }
                ], [
                    {
                        field: 'fenleiZujin',
                        title: '应收租金（元）',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'fenleiWuye',
                        title: '应收物业费（元）',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    },
                    {
                        field: 'fenleiGuanli',
                        title: '应收管理费（元）',
                        formatter: function (value) {
                            if (!value || value == '') {
                                return '0.00';
                            } else {
                                return formatData(value.toFixed(2));
                            }
                        }
                    }
                ]
            ],
            onLoadSuccess: function (data) {
                $("td.add-at").attr("data-tableexport-msonumberformat", "\\@");
                var data = $('#exampleTable').bootstrapTable('getData', true);
                // 合并单元格
                var fieldList = ["contractCode", "contractor", "amortizeMonths",
                    "brand", "rentArea", "shopType", "contractPayTotal", "unitPrice",
                    "payType", "youhui", "contractStartDate", "contractEndDate", "receivableLvyue",
                    "receivableZhiliang", "receivableFuwu", "receivableZhuangxiu",
                    "discrepancy", "phone",
                    "idCard", "kaidanCode", "kaidanName",
                    "payee", "account", "bank",
                    "contractDate", "zulinbiao", "copyIdCard",
                    "copyBankCard", "tuzhi", "remarks",
                    "fenleiZujin", "fenleiWuye", "fenleiGuanli"];
                mergeCells(data, "contractCode", 1, $('#exampleTable'), fieldList);
            }
        });
}
function clear() {
    buildingId = '';
    deptId = '';
}
function mergeCells(data, fieldName, colspan, target, fieldList) {
// 声明一个map计算相同属性值在data对象出现的次数和
    var sortMap = {};
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



