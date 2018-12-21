$().ready(function() {
    $("#fenleiZujinDiv").hide();
    $("#fenleiWuyeDiv").hide();
    $("#fenleiGuanliDiv").hide();
    $("#fenleiZujinUnreceivedDiv").hide();
    $("#fenleiWuyeUnreceivedDiv").hide();
    $("#fenleiGuanliUnreceivedDiv").hide();
    $("#receivableLvyueDiv").hide();
    $("#receivableZhiliangDiv").hide();
    $("#receivableFuwuDiv").hide();
    $("#receivableZhuangxiuDiv").hide();
    $("#receivedLvyueDiv").hide();
    $("#receivedZhiliangDiv").hide();
    $("#receivedFuwuDiv").hide();
    $("#receivedZhuangxiuDiv").hide();
    $("#fenleiZujinReceivedDiv").hide();
    $("#fenleiWuyeReceivedDiv").hide();
    $("#fenleiGuanliReceivedDiv").hide();
    $("#receivedLvyueInDiv").hide();
    $("#receivedZhiliangInDiv").hide();
    $("#receivedFuwuInDiv").hide();
    $("#receivedZhuangxiuInDiv").hide();
    loadShouType();
    loadJiaoType();
});
var payType=$("#payType").val();
$("#print").hide();
var obj={};
var isPay=false;

function save() {
    var fenleiZujin = $('#fenleiZujin').val()*1;//应收
    var fenleiWuye = $('#fenleiWuye').val()*1;
    var fenleiGuanli = $('#fenleiGuanli').val()*1;

    var fenleiZujinUnreceived = $('#fenleiZujinUnreceived').val()*1;//未收
    var fenleiWuyeUnreceived = $('#fenleiWuyeUnreceived').val()*1;
    var fenleiGuanliUnreceived = $('#fenleiGuanliUnreceived').val()*1;

    var receivableLvyue = $('#receivableLvyue').val()*1;//应收
    var receivableZhiliang = $('#receivableZhiliang').val()*1;
    var receivableFuwu = $('#receivableFuwu').val()*1;
    var receivableZhuangxiu = $('#receivableZhuangxiu').val()*1;

    var receivedLvyue = $('#receivedLvyue').val()*1;//已收
    var receivedZhiliang = $('#receivedZhiliang').val()*1;
    var receivedFuwu = $('#receivedFuwu').val()*1;
    var receivedZhuangxiu = $('#receivedZhuangxiu').val()*1;

    var unreceivedLvyue = receivableLvyue - receivedLvyue;//未收
    var unreceivedZhiliang = receivableZhiliang - receivedZhiliang;
    var unreceivedFuwu = receivableFuwu - receivedFuwu;
    var unreceivedZhuangxiu = receivableZhuangxiu - receivedZhuangxiu;

    var fenleiZujinReceived = $('#fenleiZujinReceived').val()*1;//已收-收
    var fenleiWuyeReceived = $('#fenleiWuyeReceived').val()*1;
    var fenleiGuanliReceived = $('#fenleiGuanliReceived').val()*1;

    var receivedLvyueIn = $('#receivedLvyueIn').val()*1;//收
    var receivedZhiliangIn = $('#receivedZhiliangIn').val()*1;
    var receivedFuwuIn = $('#receivedFuwuIn').val()*1;
    var receivedZhuangxiuIn = $('#receivedZhuangxiuIn').val()*1;
   /* if(isPay){
        layer.msg('不能重复支付');
        return;
    }*/
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
    if((!receivedZhuangxiuIn||receivedZhuangxiuIn=='')&&(!receivedFuwuIn||receivedFuwuIn=='')&&(!receivedZhiliangIn||receivedZhiliangIn=='')&&(!receivedLvyueIn||receivedLvyueIn=='')&&(!fenleiZujinReceived||fenleiZujinReceived=='')&&(!fenleiWuyeReceived||fenleiWuyeReceived=='')&&(!fenleiGuanliReceived||fenleiGuanliReceived=='')){
        layer.msg('请输入付款金额');
        return;
    }
    if((fenleiZujinReceived>fenleiZujinUnreceived)||(fenleiWuyeReceived>fenleiWuyeUnreceived)||(fenleiGuanliReceived>fenleiGuanliUnreceived)||(receivedLvyueIn>unreceivedLvyue)||(receivedZhiliangIn>unreceivedZhiliang)||(receivedFuwuIn>unreceivedFuwu)||(receivedZhuangxiuIn>unreceivedZhuangxiu)){//
        layer.msg('付款金额大于应付金额');
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

    pay.fenleiZujinReceived=fenleiZujinReceived;
    pay.fenleiWuyeReceived=fenleiWuyeReceived;
    pay.fenleiGuanliReceived=fenleiGuanliReceived;

    pay.receivedLvyueIn=receivedLvyueIn;
    pay.receivedZhiliangIn=receivedZhiliangIn;
    pay.receivedFuwuIn=receivedFuwuIn;
    pay.receivedZhuangxiuIn=receivedZhuangxiuIn;
    pay.yewuId=$("#id").val();
    pay.receiptBy=receiptBy;
    pay.type=shouType;
    pay.typeName=$("#shouTypeName").val();
    pay.sType=$("#shopType").val();
    pay.sTypeName=$("#shopTypeName").val();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/business/contract/pay",
        data : {mydata:JSON.stringify(pay)},// 你的formid
        async : false,
        error : function(request) {
            parent.layer.msg("Connection error");
        },
        success : function(data) {
            if (data) {
                if(data.request){
                    if(data.request == "1"){
                        parent.layer.msg("请先将之前周期欠款缴清");
                        return;
                    }
                }
                parent.layer.msg("操作成功");
                isPay=true;
                obj=data;
                $("#print").show();
                var t = obj.sType;
                if(t=='1'){
                    $('#fenleiZujinUnreceived').val(obj.fenleiZujinUnreceived);
                }else if(t=='2'){
                    $('#fenleiWuyeUnreceived').val(obj.fenleiWuyeUnreceived);
                }else if(t=='3'){
                    $('#fenleiGuanliUnreceived').val(obj.fenleiGuanliUnreceived);
                }
                else if(t=='4'){
                    $('#receivedZhuangxiu').val(obj.receivedZhuangxiu);
                }
                else if(t=='5'){
                    $('#receivedLvyue').val(obj.receivedLvyue);
                }
                else if(t=='6'){
                    $('#receivedZhiliang').val(obj.receivedZhiliang);
                }else if(t=='7'){
                    $('#receivedFuwu').val(obj.receivedFuwu);
                }

                /*parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引/shou/' + id
                parent.layer.close(index);*/
            }
        }
    });

}
function p() {
    layer.open({
        type :2,
        title : '合同收款打印',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '950px', '460px' ],
        content : '/business/contract/print/' + obj.id,
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
        url : '/common/dict/list/shop_pay_type',
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
                    $("#print").hide();
                    $("#fenleiZujinReceived").val("");
                    $("#fenleiWuyeReceived").val("");
                    $("#fenleiGuanliReceived").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");


                    $("#fenleiZujinDiv").show();
                    $("#fenleiWuyeDiv").hide();
                    $("#fenleiGuanliDiv").hide();
                    $("#fenleiZujinUnreceivedDiv").show();
                    $("#fenleiWuyeUnreceivedDiv").hide();
                    $("#fenleiGuanliUnreceivedDiv").hide();
                    $("#receivableLvyueDiv").hide();
                    $("#receivableZhiliangDiv").hide();
                    $("#receivableFuwuDiv").hide();
                    $("#receivableZhuangxiuDiv").hide();
                    $("#receivedLvyueDiv").hide();
                    $("#receivedZhiliangDiv").hide();
                    $("#receivedFuwuDiv").hide();
                    $("#receivedZhuangxiuDiv").hide();
                    $("#fenleiZujinReceivedDiv").show();
                    $("#fenleiWuyeReceivedDiv").hide();
                    $("#fenleiGuanliReceivedDiv").hide();
                    $("#receivedLvyueInDiv").hide();
                    $("#receivedZhiliangInDiv").hide();
                    $("#receivedFuwuInDiv").hide();
                    $("#receivedZhuangxiuInDiv").hide();
                }
                else if(params.selected=="2"){
                    $("#print").hide();
                    $("#fenleiZujinReceived").val("");
                    $("#fenleiWuyeReceived").val("");
                    $("#fenleiGuanliReceived").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinDiv").hide();
                    $("#fenleiWuyeDiv").show();
                    $("#fenleiGuanliDiv").hide();
                    $("#fenleiZujinUnreceivedDiv").hide();
                    $("#fenleiWuyeUnreceivedDiv").show();
                    $("#fenleiGuanliUnreceivedDiv").hide();
                    $("#receivableLvyueDiv").hide();
                    $("#receivableZhiliangDiv").hide();
                    $("#receivableFuwuDiv").hide();
                    $("#receivableZhuangxiuDiv").hide();
                    $("#receivedLvyueDiv").hide();
                    $("#receivedZhiliangDiv").hide();
                    $("#receivedFuwuDiv").hide();
                    $("#receivedZhuangxiuDiv").hide();
                    $("#fenleiZujinReceivedDiv").hide();
                    $("#fenleiWuyeReceivedDiv").show();
                    $("#fenleiGuanliReceivedDiv").hide();
                    $("#receivedLvyueInDiv").hide();
                    $("#receivedZhiliangInDiv").hide();
                    $("#receivedFuwuInDiv").hide();
                    $("#receivedZhuangxiuInDiv").hide();
                }
                else if(params.selected=="3"){
                    $("#print").hide();
                    $("#fenleiZujinReceived").val("");
                    $("#fenleiWuyeReceived").val("");
                    $("#fenleiGuanliReceived").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinDiv").hide();
                    $("#fenleiWuyeDiv").hide();
                    $("#fenleiGuanliDiv").show();
                    $("#fenleiZujinUnreceivedDiv").hide();
                    $("#fenleiWuyeUnreceivedDiv").hide();
                    $("#fenleiGuanliUnreceivedDiv").show();
                    $("#receivableLvyueDiv").hide();
                    $("#receivableZhiliangDiv").hide();
                    $("#receivableFuwuDiv").hide();
                    $("#receivableZhuangxiuDiv").hide();
                    $("#receivedLvyueDiv").hide();
                    $("#receivedZhiliangDiv").hide();
                    $("#receivedFuwuDiv").hide();
                    $("#receivedZhuangxiuDiv").hide();
                    $("#fenleiZujinReceivedDiv").hide();
                    $("#fenleiWuyeReceivedDiv").hide();
                    $("#fenleiGuanliReceivedDiv").show();
                    $("#receivedLvyueInDiv").hide();
                    $("#receivedZhiliangInDiv").hide();
                    $("#receivedFuwuInDiv").hide();
                    $("#receivedZhuangxiuInDiv").hide();
                }
                else if(params.selected=="4"){
                    $("#print").hide();
                    $("#fenleiZujinReceived").val("");
                    $("#fenleiWuyeReceived").val("");
                    $("#fenleiGuanliReceived").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinDiv").hide();
                    $("#fenleiWuyeDiv").hide();
                    $("#fenleiGuanliDiv").hide();
                    $("#fenleiZujinUnreceivedDiv").hide();
                    $("#fenleiWuyeUnreceivedDiv").hide();
                    $("#fenleiGuanliUnreceivedDiv").hide();
                    $("#receivableLvyueDiv").hide();
                    $("#receivableZhiliangDiv").hide();
                    $("#receivableFuwuDiv").hide();
                    $("#receivableZhuangxiuDiv").show();
                    $("#receivedLvyueDiv").hide();
                    $("#receivedZhiliangDiv").hide();
                    $("#receivedFuwuDiv").hide();
                    $("#receivedZhuangxiuDiv").show();
                    $("#fenleiZujinReceivedDiv").hide();
                    $("#fenleiWuyeReceivedDiv").hide();
                    $("#fenleiGuanliReceivedDiv").hide();
                    $("#receivedLvyueInDiv").hide();
                    $("#receivedZhiliangInDiv").hide();
                    $("#receivedFuwuInDiv").hide();
                    $("#receivedZhuangxiuInDiv").show();
                }
                else if(params.selected=="5"){
                    $("#print").hide();
                    $("#fenleiZujinReceived").val("");
                    $("#fenleiWuyeReceived").val("");
                    $("#fenleiGuanliReceived").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinDiv").hide();
                    $("#fenleiWuyeDiv").hide();
                    $("#fenleiGuanliDiv").hide();
                    $("#fenleiZujinUnreceivedDiv").hide();
                    $("#fenleiWuyeUnreceivedDiv").hide();
                    $("#fenleiGuanliUnreceivedDiv").hide();
                    $("#receivableLvyueDiv").show();
                    $("#receivableZhiliangDiv").hide();
                    $("#receivableFuwuDiv").hide();
                    $("#receivableZhuangxiuDiv").hide();
                    $("#receivedLvyueDiv").show();
                    $("#receivedZhiliangDiv").hide();
                    $("#receivedFuwuDiv").hide();
                    $("#receivedZhuangxiuDiv").hide();
                    $("#fenleiZujinReceivedDiv").hide();
                    $("#fenleiWuyeReceivedDiv").hide();
                    $("#fenleiGuanliReceivedDiv").hide();
                    $("#receivedLvyueInDiv").show();
                    $("#receivedZhiliangInDiv").hide();
                    $("#receivedFuwuInDiv").hide();
                    $("#receivedZhuangxiuInDiv").hide();
                }
                else if(params.selected=="6"){
                    $("#print").hide();
                    $("#fenleiZujinReceived").val("");
                    $("#fenleiWuyeReceived").val("");
                    $("#fenleiGuanliReceived").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinDiv").hide();
                    $("#fenleiWuyeDiv").hide();
                    $("#fenleiGuanliDiv").hide();
                    $("#fenleiZujinUnreceivedDiv").hide();
                    $("#fenleiWuyeUnreceivedDiv").hide();
                    $("#fenleiGuanliUnreceivedDiv").hide();
                    $("#receivableLvyueDiv").hide();
                    $("#receivableZhiliangDiv").show();
                    $("#receivableFuwuDiv").hide();
                    $("#receivableZhuangxiuDiv").hide();
                    $("#receivedLvyueDiv").hide();
                    $("#receivedZhiliangDiv").show();
                    $("#receivedFuwuDiv").hide();
                    $("#receivedZhuangxiuDiv").hide();
                    $("#fenleiZujinReceivedDiv").hide();
                    $("#fenleiWuyeReceivedDiv").hide();
                    $("#fenleiGuanliReceivedDiv").hide();
                    $("#receivedLvyueInDiv").hide();
                    $("#receivedZhiliangInDiv").show();
                    $("#receivedFuwuInDiv").hide();
                    $("#receivedZhuangxiuInDiv").hide();
                }
                else if(params.selected=="7"){
                    $("#print").hide();
                    $("#fenleiZujinReceived").val("");
                    $("#fenleiWuyeReceived").val("");
                    $("#fenleiGuanliReceived").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinDiv").hide();
                    $("#fenleiWuyeDiv").hide();
                    $("#fenleiGuanliDiv").hide();
                    $("#fenleiZujinUnreceivedDiv").hide();
                    $("#fenleiWuyeUnreceivedDiv").hide();
                    $("#fenleiGuanliUnreceivedDiv").hide();
                    $("#receivableLvyueDiv").hide();
                    $("#receivableZhiliangDiv").hide();
                    $("#receivableFuwuDiv").show();
                    $("#receivableZhuangxiuDiv").hide();
                    $("#receivedLvyueDiv").hide();
                    $("#receivedZhiliangDiv").hide();
                    $("#receivedFuwuDiv").show();
                    $("#receivedZhuangxiuDiv").hide();
                    $("#fenleiZujinReceivedDiv").hide();
                    $("#fenleiWuyeReceivedDiv").hide();
                    $("#fenleiGuanliReceivedDiv").hide();
                    $("#receivedLvyueInDiv").hide();
                    $("#receivedZhiliangInDiv").hide();
                    $("#receivedFuwuInDiv").show();
                    $("#receivedZhuangxiuInDiv").hide();
                }
                $("#shopType1").val(params.selected);
                $("#shopTypeName").val(e.target[params.selected].label);
            });
        }
    });
}