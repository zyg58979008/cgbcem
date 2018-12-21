var prefix = "/realty/contract"
var isLoad=false;
$(function () {
    getTreeData();
    loadBuildingType();
    loadSellType();
    var height=$(document.body).height();
    height=height-110;
    $("#exampleTable").attr("data-height",height);
    load();
});
var buildingId = '';//楼宇ID
var deptId = '';//项目ID
var contract={};
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
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
                // search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                pageNumber: 1,
                pageSize: 30,
                pageList: [30, 50, 100],
                fixedColumns: true,
                fixedNumber:5,
                // "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        delFlag:'0',
                        roomCode:$('#roomCode').val(),
                        name:$('#name').val(),
                        code:$('#code').val(),
                        buildingId:buildingId,
                        roomType:$("#roomType").val(),
                        sellType:$("#sellType").val(),
                        state:$("#state").val()
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


                    [{
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            return index + 1;
                        },
                        rowspan: 2, align: "center"
                    },
                        {
                            title: '操作',
                            field: 'id',
                            align: 'center',
                            rowspan: 2,
                            formatter: function (value, row, index) {
                                var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''+ row.roomId+ '\',\''+ row.yishou+ '\')"><i class="fa fa-edit"></i></a> ';
                                var d = '<a class="btn btn-danger btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                    + row.id
                                    + '\')"><i class="fa fa-remove"></i></a> ';
                                var f = '<a class="btn btn-success btn-sm ' + s_jiaofang_h + '" href="#" title="交房"  mce_href="#" onclick="jiaofang(\''
                                    + row.id
                                    + '\')"><i class="fa fa-key"></i></a> ';
                                var btn;
                                if(!row.id){
                                    btn=e;
                                }else {
                                    btn=e + d +f;
                                }
                                return btn;
                            }
                        },
                        {
                            field: 'code',
                            title: '合同编号',
                            rowspan: 2, align: "center"
                        },
                        {
                            field: 'buildingName',
                            title: '楼宇名称',
                            rowspan: 2, align: "center"

                        },
                        {
                            field: 'roomCode',
                            title: '房屋编号',
                            rowspan: 2, align: "center"
                        },
                        {
                            field: 'unit',
                            title: '单元',
                            rowspan: 2, align: "center"
                        },
                        {
                            field: 'floor',
                            title: '楼层',
                            rowspan: 2, align: "center"
                        },
                        {
                            field: 'roomTypeName',
                            title: '房屋类型',
                            rowspan: 2, align: "center"
                        },
                        {
                            field: 'buildingArea',
                            title: '建筑面积',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'state',
                            title: '合同状态',
                            rowspan: 2, align: "center",
                            formatter: function (value, row, index) {
                                if(value=='0'){
                                    return '未交房';
                                }
                                if(value=='1'){
                                    return '已交房';
                                }
                                if(value=='2'){
                                    return '撤销';
                                }
                            },
                        },
                        {
                            field: 'sellTypeName',
                            title: '转让类型或未售',
                            rowspan: 2
                        },
                        {
                            field: 'price',
                            title: '单价',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'totalPrice',
                            title: '价税合计',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'payTypeName',
                            title: '付款方式',
                            rowspan: 2, align: "center"
                        },
                        {
                            field: 'firstPay',
                            title: '首付款',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'firstReceive',
                            title: '已收首付款',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'firstOwn',
                            title: '欠(首付）款',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'loan',
                            title: '贷款金额',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'totalPrice',
                            title: '应收金额',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'yishou',
                            title: '已收金额',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'weishou',
                            title: '未收金额',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'sellBy',
                            title: '销售顾问',
                            rowspan: 2, align: "center"
                        },
                        {
                            field: 'sellDate',
                            title: '销售日期',
                            rowspan: 2, align: "center",
                            formatter: function (value, row, index) {
                                if(!value||value==''){
                                    return '';
                                }else{
                                    return value.substr(0,10);
                                }
                            }
                        },
                        {
                            field: 'remark',
                            title: '备注',
                            rowspan: 2, align: "center"
                        },
                        {
                            title: '渠道',
                            colspan: 5, align: "center"
                        },
                        {
                            field: 'balanceArea',
                            title: '退补面积',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'balancePrice',
                            title: '退补金额',
                            rowspan: 2,
                            align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'tax',
                            title: '税款',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'totalPriceNoTax',
                            title: '房屋结算价款(不含税)',
                            rowspan: 2, align: "center",
                            formatter:function (value) {
                                if(!value||value==''){
                                    return '0.00';
                                }else{
                                    return value.toFixed(2);
                                }
                            }
                        },
                        {
                            field: 'name',
                            title: '购房者姓名',
                            rowspan: 2, align: "center"
                        },
                        {
                            field: 'idCard',
                            title: '购房者证件号',
                            rowspan: 2, align: "center"
                        },
                        {
                            field: 'phone',
                            title: '购房者电话号',
                            rowspan: 2, align: "center"
                        }
                        ],
                    [
                        {
                            field: 'bumen',
                            title: '部门', align: "center"
                        }, {
                        field: 'renyuan',
                        title: '人员', align: "center"
                    }, {
                        field: 'leixing',
                        title: '类型', align: "center"
                    }, {
                        field: 'guishu',
                        title: '归属', align: "center"
                    }, {
                        field: 'beizhu',
                        title: '备注', align: "center"
                    }
                    ]
                ],
                onDblClickRow: function (item, $element) {
                    contract=item;
                    var perContent = layer.open({
                        type : 2,
                        title : '合同详情',
                        shadeClose : false, // 点击遮罩关闭层
                        area : [ '820px', '500px' ],
                        content : prefix + '/info?buildingId='+item.buildingId+'&code='+item.roomCode+'&idCard='+item.idCard // iframe的url
                    });
                }
            });
}
function reLoad() {
    if(buildingId!=''){
        if(!isLoad){
            $('#exampleTable').bootstrapTable("refreshOptions",
                {
                    url : prefix + "/list",
                });
            load();
            isLoad=true;
        }else {

            $('#exampleTable').bootstrapTable('refresh');
        }
    }
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
function edit(id,yishou) {
    /*if(yishou>0){
        layer.msg("已缴费不能编辑");
        return;
    }*/
    var edit=layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '100%'],
        content: prefix + '/edit/' + id // iframe的url
    });
    layer.full(edit);
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
function jiaofang(id) {
    var edit=layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '100%'],
        content: prefix + '/jiaofangIndex/' + id // iframe的url
    });


    /*layer.confirm('确定要交房吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/jiaofang",
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
    })*/
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
function download() {
    var url = prefix + "/download";
    //更改form的action
    $("#importForm").attr("action", url);
    //触发submit事件，提交表单
    $("#importForm").submit();
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
        clear();
    } else {
        if (data.node) {
            var node = data.node;
            if (node.state.type == '2') {
                buildingId = data.selected[0];
                deptId = node.state.deptId;
                reLoad()
            } else {
                // buildingId = data.selected[0];
                // deptId = node.state.deptId;
                // $('#exampleTable').bootstrapTable('refresh');
                clear();
            }
        }
    }
});
function loadBuildingType(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/room_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#roomType").append(html);
            $("#roomType").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#roomType').on('change', function(e, params,a) {
                //console.log(params.selected);
                $("#roomType1").val(params.selected);
            });
        }
    });
}
function loadSellType(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/sell_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#sellType").append(html);
            $("#sellType").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#sellType').on('change', function(e, params,a) {
                //console.log(params.selected);
                $("#sellType1").val(params.selected);
            });
        }
    });
}
function clear() {
    buildingId = '';
    deptId = '';
}
function c() {
    $("#roomType").val('');
    $("#roomType").trigger("chosen:updated");
    $("#roomType1").val('');
    $("#sellType").val('');
    $("#sellType").trigger("chosen:updated");
    $("#sellType1").val('');
    $("#roomCode").val('');
    $("#name").val('');
    $("#state").val('');
}