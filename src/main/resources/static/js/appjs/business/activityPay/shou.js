$().ready(function() {
    $("#activityMoneyDiv").hide();
    $("#dmDanDiv").hide();
    $("#cardMoneyDiv").hide();
    $("#activityMoneyUnreceivedDiv").hide();
    $("#dmDanUnreceivedDiv").hide();
    $("#cardMoneyUnreceivedDiv").hide();
    $("#activityMoneyPriceDiv").hide();
    $("#dmDanPriceDiv").hide();
    $("#cardMoneyPriceDiv").hide();
    loadShouType();
    loadJiaoType();
});
var payType=$("#payType").val();
$("#print").hide();
var obj={};
var isPay=false;

function save() {
    var cardMoneyUnreceived = $('#cardMoneyUnreceived').val()*1;//未收
    var dmDanUnreceived = $('#dmDanUnreceived').val()*1;
    var activityMoneyUnreceived = $('#activityMoneyUnreceived').val()*1;

    var cardMoneyPrice = $('#cardMoneyPrice').val()*1;//收
    var dmDanPrice = $('#dmDanPrice').val()*1;
    var activityMoneyPrice = $('#activityMoneyPrice').val()*1;
    if(isPay){
        layer.msg('不能重复支付');
        return;
    }
    var shouType=$('#shouType1').val();
    if(!shouType||shouType==''){
        layer.msg('请选择收款方式');
        return;
    }
    var shopType=$('#shopType1').val();
    if(!shopType||shopType==''){
        layer.msg('请选择交来项目');
        return;
    }

    if((!activityMoneyPrice||activityMoneyPrice=='')&&(!dmDanPrice||dmDanPrice=='')&&(!cardMoneyPrice||cardMoneyPrice=='')){
        layer.msg('请输入付款金额');
        return;
    }
    if((activityMoneyPrice>activityMoneyUnreceived)||(dmDanPrice>dmDanUnreceived)||(cardMoneyPrice>cardMoneyUnreceived)){//||(receivedLvyueIn>receivableLvyue)||(receivedZhiliangIn>receivedZhiliang)||(receivedFuwuIn>receivedFuwu)||(receivedZhuangxiuIn>receivedZhuangxiu)
        layer.msg('付款金额大于未付金额');
        return;
    }
    var receiptBy=$('#receiptBy').val();
    if(!receiptBy||receiptBy==''){
        layer.msg('请输入收款人');
        return;
    }
    var pay={};
    pay.payDate = $('#payDate').val();
    pay.remarks = $('#remarks').val();
    pay.activityMoneyPrice=activityMoneyPrice;
    pay.dmDanPrice=dmDanPrice;
    pay.cardMoneyPrice=cardMoneyPrice;

    pay.yewuId=$("#id").val();
    pay.receiptBy=receiptBy;
    pay.type=shouType;
    pay.typeName=$("#shouTypeName").val();
    pay.sType=$("#shopType").val();
    pay.sTypeName=$("#shopTypeName").val();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/business/activity/savepay",
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
        title : '活动费打印',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '950px', '520px' ],
        content : '/business/activity/print/' + obj.id
    });
}
function loadShouType(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/shop_shou_type',
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
        url : '/common/dict/list/activity_pay_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#shopType").append(html);
            $("#shopType").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#shopType').on('change', function(e, params) {
                //console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
                if(params.selected=="1"){
                    $("#activityMoneyPrice").val("");
                    $("#dmDanPrice").val("");
                    $("#cardMoneyPrice").val("");

                    $("#activityMoneyPriceDiv").show();
                    $("#dmDanPriceDiv").hide();
                    $("#cardMoneyPriceDiv").hide();
                    $("#activityMoneyUnreceivedDiv").show();
                    $("#dmDanUnreceivedDiv").hide();
                    $("#cardMoneyUnreceivedDiv").hide();
                    $("#activityMoneyDiv").show();
                    $("#dmDanDiv").hide();
                    $("#cardMoneyDiv").hide();


                }
                else if(params.selected=="2"){
                    $("#activityMoneyPrice").val("");
                    $("#dmDanPrice").val("");
                    $("#cardMoneyPrice").val("");

                    $("#activityMoneyPriceDiv").hide();
                    $("#dmDanPriceDiv").show();
                    $("#cardMoneyPriceDiv").hide();
                    $("#activityMoneyUnreceivedDiv").hide();
                    $("#dmDanUnreceivedDiv").show();
                    $("#cardMoneyUnreceivedDiv").hide();
                    $("#activityMoneyDiv").hide();
                    $("#dmDanDiv").show();
                    $("#cardMoneyDiv").hide();
                }
                else if(params.selected=="3"){
                    $("#activityMoneyPrice").val("");
                    $("#dmDanPrice").val("");
                    $("#cardMoneyPrice").val("");

                    $("#activityMoneyPriceDiv").hide();
                    $("#dmDanPriceDiv").hide();
                    $("#cardMoneyPriceDiv").show();
                    $("#activityMoneyUnreceivedDiv").hide();
                    $("#dmDanUnreceivedDiv").hide();
                    $("#cardMoneyUnreceivedDiv").show();
                    $("#activityMoneyDiv").hide();
                    $("#dmDanDiv").hide();
                    $("#cardMoneyDiv").show();
                }
                $("#shopType1").val(params.selected);
                $("#shopTypeName").val(e.target[params.selected].label);
            });
        }
    });
}