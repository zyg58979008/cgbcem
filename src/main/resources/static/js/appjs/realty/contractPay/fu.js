$().ready(function() {
    loadShouType();
    loadJiaoType();
});
var payType=$("#payType").val();
var yishou=parseFloat($("#yishou").val());
var depositPayed=parseFloat($("#depositPayed").val());
var renchouPayed=parseFloat($("#renchouPayed").val());
var firstReceive=parseFloat($("#firstReceive").val());
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
        layer.msg('请选择付款方式');
        return;
    }
    var jiaoType=$("#jiaoType1").val();
    if(!jiaoType||jiaoType==''){
        layer.msg('请选择退还方式');
        return;
    }
    var price=$("#price").val();
    if(!price||price==''){
        layer.msg('请输入付款金额');
        return;
    }
    price=parseFloat($("#price").val()).toFixed(2);
    if(price<=0){
        layer.msg('请输入正确金额');
        return;
    }
    if(jiaoType=='1'){
        if(price>depositPayed){
            layer.msg('付款金额大于已交定金');
            return;
        }
    }
    if(jiaoType=='2'){
        if(price>renchouPayed){
            layer.msg('付款金额大于已交认筹款');
            return;
        }
    }
    if(jiaoType=='3'){
        if(price>firstReceive){
            layer.msg('付款金额大于已交首付款');
            return;
        }
    }
    if(jiaoType=='4'){
        if(price>yishou-depositPayed-renchouPayed-firstReceive){
            layer.msg('付款金额大于已交房款');
            return;
        }
    }
    var receiptBy=$("#receiptBy").val();
    if(!receiptBy||receiptBy==''){
        layer.msg('请输入付款人');
        return;
    }
    var payDate=$("#payDate").val();
    if(!payDate||payDate==''){
        layer.msg('请选择付款日期');
        return;
    }
    var pay={};
    pay.yewuId=$("#id").val();
    pay.receiptBy=receiptBy;
    pay.price=price;
    pay.state='3';
    pay.type=shouType;
    pay.typeName=$("#shouTypeName").val();
    pay.roomId=$("#roomId").val();
    pay.sType=jiaoType;
    pay.sTypeName=$("#jiaoTypeName").val();
    pay.payDate=payDate;
    pay.remark=$("#remark").val();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/realty/contract/pay",
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
        title : '房款打印',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '950px', '460px' ],
        content : '/realty/contract/printBack/' + obj.id,
        end: function () {
            reLoad()
        }
    });
}
function checkPrice() {
    var jiaoType=$("#jiaoType1").val();
    if(!jiaoType||jiaoType==''){
        layer.msg('请选择退还方式');
        return;
    }
}
function loadShouType(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/dichan_shou_type',
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
        url : '/common/dict/list/dichan_jiao_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                if((payType==''||payType=='1')&&data[i].value=='3'){
                    continue;
                }
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#jiaoType").append(html);
            $("#jiaoType").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#jiaoType').on('change', function(e, params) {
                //console.log(params.selected);
                if(params.selected=='1'){
                    $("#payed").val(depositPayed);
                }
                if(params.selected=='2'){
                    $("#payed").val(renchouPayed);
                }
                if(params.selected=='3'){
                    $("#payed").val(firstReceive);
                }
                if(params.selected=='4'){
                    $("#payed").val(yishou);
                }
                $("#jiaoType1").val(params.selected);
                $("#jiaoTypeName").val(e.target[params.selected].label);
            });
        }
    });
}
function pay() {
    var payed=$("#payed").val();
    $("#price").val(payed);
}