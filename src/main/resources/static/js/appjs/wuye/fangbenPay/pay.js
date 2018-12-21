var prefix = "/wuye/fangbenManage"
var isLoad=false;
$(function () {
    getTreeData();
    var height=$(document.body).height();
    height=height-58;
    $("#exampleTable").attr("data-height",height);
    load();
});
var buildingId = '';//楼宇ID
var payType = '';//楼宇ID
var roomType = '';//楼宇ID
var contract={};
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
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                fixedColumns: true,
                fixedNumber:5,
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams: function (params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        buildingId: buildingId,
                        payType: $("#payType").val(),
                        code: $("#code").val(),
                        name: $("#name").val()
                    };
                },
                columns: [
                    [{
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            return index + 1;
                        }, rowspan: 2
                    },
                        {
                            title: '操作',
                            field: 'id',
                            align: 'center',
                            rowspan: 2,
                            formatter: function (value, row, index) {
                                var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="缴费" onclick="pay(\''+ row.id+ '\')"><i class="fa fa-edit "></i></a> ';
                                return e;
                            }
                        },
                        {
                            field: 'name', // 列字段名
                            title: '姓名',
                            rowspan: 2

                        },
                        {
                            field: 'buildingName', // 列字段名
                            title: '楼宇名称',
                            rowspan: 2
                        },
                        {
                            field: 'code', // 列字段名
                            title: '房间编号',
                            rowspan: 2
                        },
                        {
                            field: 'unit',
                            title: '单元',
                            rowspan: 2
                        },
                        {
                            field: 'floor',
                            title: '楼层',
                            rowspan: 2
                        },
                        {
                            field: 'roomTypeName',
                            title: '房屋类型',
                            rowspan: 2
                        },
                        {
                            field: 'buildingArea',
                            title: '建筑面积',
                            rowspan: 2,
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(3);
                                }                            }
                        },
                        {
                            field: 'payTypeName',
                            title: '付款方式',
                            rowspan: 2
                        },
                        {
                            title: '应交费用',
                            colspan: 5, align: "center"
                        },
                        {
                            title: '已交费用',
                            colspan: 5, align: "center"
                        },
                        {
                            title: '未交费用',
                            colspan: 5, align: "center"
                        }],
                    [
                        {
                            field: 'weixiuYing',
                            title: '维修基金',
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return formatData(value.toFixed(2));
                                }                            }
                        }, {
                            field: 'jiaoyiYing',
                            title: '交易费',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }, {
                            field: 'fangbenYing',
                            title: '房产证',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }, {
                            field: 'qishuiYing',
                            title: '契税',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }, {
                            field: 'daibanYing',
                            title: '代办费',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        },
                        {
                            field: 'weixiuPayer',
                            title: '维修基金',
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return formatData(value.toFixed(2));
                                }                            }
                        }, {
                            field: 'jiaoyiPayer',
                            title: '交易费',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }, {
                            field: 'fangbenPayer',
                            title: '房产证',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }, {
                            field: 'qishuiPayer',
                            title: '契税',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }, {
                            field: 'daibanPayer',
                            title: '代办费',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        },
                        {
                            field: 'weixiuUnpay',
                            title: '维修基金',
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return formatData(value.toFixed(2));
                                }                            }
                        }, {
                            field: 'jiaoyiUnpay',
                            title: '交易费',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }, {
                            field: 'fangbenUnpay',
                            title: '房产证',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }, {
                            field: 'qishuiUnpay',
                            title: '契税',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }, {
                            field: 'daibanUnpay',
                            title: '代办费',
                        formatter:function (value) {
                            if(!value||value==''){
                                return '0.00';
                            }else{
                                return formatData(value.toFixed(2));
                            }                        }
                        }
                    ]
                ],
                onDblClickRow: function (item, $element) {
                    contract=item;
                    contract.types='fangben';
                    var perContent = layer.open({
                        type : 2,
                        title : '详情',
                        shadeClose : false, // 点击遮罩关闭层
                        area : [ '820px', '100%' ],
                        content : '/wuye/fangbenManage/info?buildingId='+item.buildingId+'&code='+item.code+'&idCard='+item.idCard+'&types=fangben' // iframe的url
                    });
                }
            });
}
function reLoad() {
    if(!isLoad){
        $('#exampleTable').bootstrapTable("refreshOptions",
            {
                url : prefix + "/listPay",
            });
        load();
        isLoad=true;
    }else {
        $('#exampleTable').bootstrapTable('refresh');
    }
}
function pay(id) {
    layer.open({
        type: 2,
        title: '办证费缴费',
        maxmin: true,
        shadeClose: false,
        area: ['950px', '520px'],
        content: prefix + '/pay/' + id, // iframe的url
        end:function () {
            reLoad();
        }
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
    if (data.selected == -1) {
        buildingId = '';
    } else {
        if (data.node) {
            var node = data.node;
            if (node.state.type == '2') {
                buildingId = data.selected[0];
                reLoad()
            } else {
                buildingId = '';
            }
        }
    }
});
function c() {
    roomType = '';
    payType = '';
    $("#payType").val('');
    $("#code").val('');
    $("#name").val('');
}