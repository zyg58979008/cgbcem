$().ready(function() {
    loadShouType();
    loadJiaoType();
});
$("#print").hide();
var obj={};
var isPay=false;
function save() {
    if(isPay){
        layer.msg('不能重复支付');
        return;
    }
    var shouType=$("#shouType1").val();
    if(!shouType||shouType==''){
        layer.msg('请选择收款方式');
        return;
    }
    var jiaoType=$("#jiaoType1").val();
    if(!jiaoType||jiaoType==''){
        layer.msg('请选择交来方式');
        return;
    }
    var price=$("#price").val();
    if(!price||price==''){
        layer.msg('请输入付款金额');
        return;
    }
    price=parseFloat($("#price").val()).toFixed(2);
    /*if(price<=0){
        layer.msg('请输入正确金额');
        return;
    }*/
    var unpay=parseFloat($("#unpay").val());
    if(price>unpay){
        layer.msg('付款金额大于未付金额');
        return;
    }
    var receiptBy=$("#receiptBy").val();
    if(!receiptBy||receiptBy==''){
        layer.msg('请输入收款人');
        return;
    }
    var payDate=$("#payDate").val();
    if(!payDate||payDate==''){
        layer.msg('请选择收款日期');
        return;
    }
    debugger
    var pay={};
    pay.yewuId=$("#id").val();
    pay.receiptBy=receiptBy;
    pay.price=price;
    pay.type=shouType;
    pay.typeName=$("#shouTypeName").val();
    pay.sType=jiaoType;
    pay.sTypeName=$("#jiaoTypeName").val();
    pay.remark=$("#remark").val();
    pay.payDate=payDate;
    $.ajax({
        cache : true,
        type : "POST",
        url : "/wuye/qunuanManage/pay",
        data : {mydata:JSON.stringify(pay)},// 你的formid
        async : false,
        error : function(request) {
            parent.layer.msg("Connection error");
        },
        success : function(data) {
            if (data) {
                parent.layer.msg("操作成功");
                isPay=true;
                obj=data;
                $("#print").show();
                /*parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);*/
            }
        }
    });

}
function p() {
    layer.open({
        type :2,
        title : '取暖费打印',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '950px', '480px' ],
        content : '/wuye/qunuanManage/print/' + obj.id,
        end: function () {
            reLoad()
        }
    });
}
function checkPrice() {
    var jiaoType=$("#jiaoType1").val();
    if(!jiaoType||jiaoType==''){
        layer.msg('请选择交来方式');
        return;
    }
}
function pay() {
    var unpay=$("#unpay").val();
    $("#price").val(unpay);
}
function loadShouType(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/qunuan_shou_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#shouType").append(html);
            $("#shouType").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#shouType').on('change', function(e, params) {
                //console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
                $("#shouType1").val(params.selected);
                $("#shouTypeName").val(e.target[params.selected].label);
            });
        }
    });
}
function loadJiaoType(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/qunuan_jiao_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#jiaoType").append(html);
            $("#jiaoType").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#jiaoType').on('change', function(e, params) {
                //console.log(params.selected);
                var jiaoType=params.selected;
                if(jiaoType=='2'){
                    $("#unpay").val($("#qunuanUnpay").val());
                }
                if(jiaoType=='3'){
                    $("#unpay").val($("#tingnuanUnpay").val());
                }
                $("#jiaoType1").val(params.selected);
                $("#jiaoTypeName").val(e.target[params.selected-1].label);
            });
        }
    });
}