var prefix = "/realtyReport"
var isLoad=false;
var height=0;
$(function () {
    getTreeData();
    loadBuildingType();
    loadSellType();
    height=$(document.body).height();
    height=height-120;
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
                pagination: false, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                // search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "client", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        delFlag:'0',
                        roomCode:$('#roomCode').val(),
                        startDate:$('#startDate').val(),
                        endDate : $('#endDate').val(),
                        name:$('#name').val(),
                        code:$('#code').val(),
                        buildingId:buildingId,
                        roomType:$("#roomType").val(),
                        sellType:$("#sellType").val(),
                        state:$("#state").val()
                        // username:$('#searchName').val()
                    };
                }
            });
}
function reLoad() {
    if(($('#startDate').val()==null||$('#startDate').val()=="")){
        layer.msg("请选择查询开始日期");
        return;
    }if(($('#endDate').val()==null||$('#endDate').val()=="")){
        layer.msg("请选择查询结束日期");
        return;
    }
    var yearS;
    var monthS;
    var yearE;
    var monthE;
    yearS = $('#startDate').val().substr(0,4)*1;
    monthS = $('#startDate').val().substr(5,7)*1;
    yearE = $('#endDate').val().substr(0,4)*1;
    monthE = $('#endDate').val().substr(5,7)*1;
    if((yearE*12+monthE)<(yearS*12+monthS)){
        layer.msg("结束日期不能小于开始日期");
        return;
    }
    var aa=[];
    var a=[{
        title: '序号',//标题  可不加
        formatter: function (value, row, index) {
            return index + 1;
        },
        rowspan: 2, align: "center"
    },
        {
            field: 'buildingName',
            title: '楼号',
            rowspan: 2, align: "center"

        },
        {
            field: 'unit',
            title: '单元号',
            rowspan: 2, align: "center"
        },
        {
            field: 'floor',
            title: '楼层',
            rowspan: 2, align: "center"
        },
        {
            field: 'roomCode',
            title: '房屋编号',
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
            field: 'sellTypeName',
            title: '转让类型或未售',
            rowspan: 2
        },
        {
            field: 'name',
            title: '购房者姓名',
            rowspan: 2, align: "center"
        },
        {
            field: 'idCard',
            title: '购房者证件号',
            rowspan: 2, align: "center",class:'add-at'
        },
        {
            field: 'phone',
            title: '购房者电话号',
            rowspan: 2, align: "center"
        },
        {
            field: 'sellDate',
            title: '转让日期',
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
            field: 'code',
            title: '销售合同号',
            rowspan: 2, align: "center",class:'add-at'
        },
        {
            field: 'payTypeName',
            title: '付款方式',
            rowspan: 2, align: "center"
        },
        {
            field: 'price',
            title: '单价',
            rowspan: 2, align: "center",
            formatter:function (value) {
                if(!value||value==''){
                    return '0.00';
                }else{
                    return formatData(value.toFixed(2));
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
                    return formatData(value.toFixed(2));
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
                    return formatData(value.toFixed(2));
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
                    return formatData(value.toFixed(2));
                }
            }
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
            rowspan: 2, align: "center",
            formatter:function (value) {
                if(!value||value==''){
                    return '0.00';
                }else{
                    return formatData(value.toFixed(2));
                }
            }
        },
        {
            field: 'remark',
            title: '备注',
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
                    return formatData(value.toFixed(2));
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
                    return formatData(value.toFixed(2));
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
                    return formatData(value.toFixed(2));
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
                    return formatData(value.toFixed(2));
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
                    return formatData(value.toFixed(2));
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
                    return formatData(value.toFixed(2));
                }
            }
        },
        {
            field: 'sellBy',
            title: '置业顾问',
            rowspan: 2, align: "center"
        },
        {
            title: '渠道',
            colspan: 5, align: "center"
        }
    ];
    var b=[
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
    ];
    var i;
    i = (yearE*12+monthE)-(yearS*12+monthS)+1;
    for(var x =1;x<=i;x++){

        a.push({
            title: yearS+'年'+monthS+'月份',
            colspan: 2, align: "center"
        });
        b.push({
            field: 'date'+x,
            title: '日期', align: "center",
                formatter:function (value) {
                    if(!value||value==''){
                        return '';
                    }else{
                        var v=value.split("-")[0];
                        return v;
                    }
                }
            }
        );
        b.push({
            field: 'date'+x,
            title: '金额', align: "center",
            formatter:function (value) {
                if(!value||value==''){
                    return '0.00';
                }else{
                    var v=value.split("-")[1];
                    return formatData(v);;
                }
            }
        });
        monthS = monthS + 1;
        if(monthS>12){
            monthS = monthS - 12;
            yearS = yearS + 1;
        }
    }
    aa.push(a);
    aa.push(b);
    $('#exampleTable').bootstrapTable("refreshOptions",
        {
            url : prefix + "/contractList",
            columns : aa,
            onLoadSuccess:function(){
                $("td.add-at").attr("data-tableexport-msonumberformat","\\@");
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
    $("#startDate").val('');
    $("#endDate").val('');
}