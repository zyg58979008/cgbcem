$().ready(function() {
    $("#fenleiZujinShouldDiv").hide();
    $("#fenleiWuyeShouldDiv").hide();
    $("#fenleiGuanliShouldDiv").hide();
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

    var fenleiZujinReceived = $('#fenleiZujinReceived').val()*1;//
    var fenleiWuyeReceived = $('#fenleiWuyeReceived').val()*1;
    var fenleiGuanliReceived = $('#fenleiGuanliReceived').val()*1;

    var fenleiZujinShould = $('#fenleiZujinShould').val()*1;//
    var fenleiWuyeShould = $('#fenleiWuyeShould').val()*1;
    var fenleiGuanliShould = $('#fenleiGuanliShould').val()*1;

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
    if(($('#receivedZhuangxiuIn').val()=='')&&($('#receivedFuwuIn').val()=='')&&($('#receivedZhiliangIn').val()=='')&&($('#receivedLvyueIn').val()=='')&&($('#fenleiZujinShould').val()=='')&&($('#fenleiWuyeShould').val()=='')&&($('#fenleiGuanliShould').val()=='')){
        layer.msg('请输入补退金额');
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

    pay.fenleiZujinShould=fenleiZujinShould;
    pay.fenleiWuyeShould=fenleiWuyeShould;
    pay.fenleiGuanliShould=fenleiGuanliShould;

    pay.receivedLvyueIn=receivedLvyueIn;
    pay.receivedZhiliangIn=receivedZhiliangIn;
    pay.receivedFuwuIn=receivedFuwuIn;
    pay.receivedZhuangxiuIn=receivedZhuangxiuIn;
    pay.id=$("#id").val();
    pay.receiptBy=receiptBy;
    pay.type=shouType;
    pay.typeName=$("#shouTypeName").val();
    pay.sType=$("#shopType").val();
    pay.sTypeName=$("#shopTypeName").val();
    $.ajax({
        cache : true,
        type : "POST",
        url : "/business/contract/payForChechang",
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
                var t = obj.sType;
                if(t=='1'){
                    $('#fenleiZujinUnreceived').val(obj.fenleiZujinUnreceived);
                    $('#fenleiZujinReceived').val(obj.fenleiZujinReceived);
                    $('#fenleiZujin').val(obj.fenleiZujin);
                }else if(t=='2'){
                    $('#fenleiWuyeUnreceived').val(obj.fenleiWuyeUnreceived);
                    $('#fenleiWuyeReceived').val(obj.fenleiWuyeReceived);
                    $('#fenleiWuye').val(obj.fenleiWuye);
                }else if(t=='3'){
                    $('#fenleiGuanliUnreceived').val(obj.fenleiGuanliUnreceived);
                    $('#fenleiGuanliReceived').val(obj.fenleiGuanliReceived);
                    $('#fenleiGuanli').val(obj.fenleiGuanli);
                }
                else if(t=='4'){
                    $('#receivedZhuangxiu').val(obj.receivedZhuangxiu);
                    $('#receivableZhuangxiu').val(obj.receivableZhuangxiu);
                }
                else if(t=='5'){
                    $('#receivedLvyue').val(obj.receivedLvyue);
                    $('#receivableLvyue').val(obj.receivableLvyue);
                }
                else if(t=='6'){
                    $('#receivedZhiliang').val(obj.receivedZhiliang);
                    $('#receivableZhiliang').val(obj.receivableZhiliang);
                }else if(t=='7'){
                    $('#receivedFuwu').val(obj.receivedFuwu);
                    $('#receivableFuwu').val(obj.receivableFuwu);
                }

                /*parent.reLoad();
                 var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引/shou/' + id
                 parent.layer.close(index);*/
            }
        }
    });

}
function p() {
    var url;
    if($("#fenleiZujinShould").val()<0||$("#fenleiWuyeShould").val()<0||$("#fenleiGuanliShould").val()<0||$("#receivedLvyueIn").val()<0||$("#receivedZhiliangIn").val()<0||$("#receivedFuwuIn").val()<0||$("#receivedZhuangxiuIn").val()
    ){
        url =  '/business/contract/printForChechangBack/' + obj.yewuId
    }else{
        url =  '/business/contract/printForChechang/' + obj.yewuId
    }
    layer.open({
        type :2,
        title : '合同收款打印',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '950px', '460px' ],
        content : url
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
                    $("#fenleiZujinShould").val("");
                    $("#fenleiWuyeShould").val("");
                    $("#fenleiGuanliShould").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinShouldDiv").show();
                    $("#fenleiWuyeShouldDiv").hide();
                    $("#fenleiGuanliShouldDiv").hide();
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
                    $("#fenleiZujinShould").val("");
                    $("#fenleiWuyeShould").val("");
                    $("#fenleiGuanliShould").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinShouldDiv").hide();
                    $("#fenleiWuyeShouldDiv").show();
                    $("#fenleiGuanliShouldDiv").hide();
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
                    $("#fenleiZujinShould").val("");
                    $("#fenleiWuyeShould").val("");
                    $("#fenleiGuanliShould").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinShouldDiv").hide();
                    $("#fenleiWuyeShouldDiv").hide();
                    $("#fenleiGuanliShouldDiv").show();
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
                    $("#fenleiZujinShould").val("");
                    $("#fenleiWuyeShould").val("");
                    $("#fenleiGuanliShould").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinShouldDiv").hide();
                    $("#fenleiWuyeShouldDiv").hide();
                    $("#fenleiGuanliShouldDiv").hide();
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
                    $("#fenleiZujinShould").val("");
                    $("#fenleiWuyeShould").val("");
                    $("#fenleiGuanliShould").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinShouldDiv").hide();
                    $("#fenleiWuyeShouldDiv").hide();
                    $("#fenleiGuanliShouldDiv").hide();
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
                    $("#fenleiZujinShould").val("");
                    $("#fenleiWuyeShould").val("");
                    $("#fenleiGuanliShould").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinShouldDiv").hide();
                    $("#fenleiWuyeShouldDiv").hide();
                    $("#fenleiGuanliShouldDiv").hide();
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
                    $("#fenleiZujinShould").val("");
                    $("#fenleiWuyeShould").val("");
                    $("#fenleiGuanliShould").val("");
                    $("#receivedLvyueIn").val("");
                    $("#receivedZhiliangIn").val("");
                    $("#receivedFuwuIn").val("");
                    $("#receivedZhuangxiuIn").val("");

                    $("#fenleiZujinShouldDiv").hide();
                    $("#fenleiWuyeShouldDiv").hide();
                    $("#fenleiGuanliShouldDiv").hide();
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