$().ready(function() {
    loadPayType();
    loadSellType();
    loadJiaofang();
    var payType=$("#payType1").val();
    if(payType=='2'){
        $("#firstPay").attr("disabled",false);
    }else{
        $("#firstPay").attr("disabled",true);
    }
    /*if(payType=='1'){
        $("#payType").attr("disabled",true);
    }*/
    var firstReceive=parseFloat($("#firstReceive").val());
   /* var yishou=parseFloat($("#yishou1").val());
    if(yishou>0){
        $("#price").attr("disabled",true);
        $("#totalPrice").attr("disabled",true);
        $("#firstPay").attr("disabled",true);
        $("#code").attr("disabled",true);
    }*/
});
var buildingArea=parseFloat($("#buildingArea").val());
function update() {
    /*var payType=$("#payType1").val();
    if(!payType||payType==''){
        layer.msg('请选择付款方式');
        return;
    }*/
   /* var payType=$("#payType1").val();
    if(payType&&payType=='2') {
         var firstPay=parseFloat($("#firstPay").val());
         if(!firstPay||firstPay==0) {
             layer.msg('请录入首付款金额');
             return;
         }
    }
    if(payType&&payType!=''){
        var price=parseFloat($("#price").val());
        if(!price||price==0) {
            layer.msg('请录入房屋单价');
            return;
        }
    }*/
    var sellDate=$("#sellDate").val();
    if(!sellDate||sellDate=='') {
        $("#sellDate").removeAttr("name");
    }
	$.ajax({
		cache : true,
		type : "POST",
		url : "/realty/contract/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
}
function loadPayType(){
    var type= $("#payType1").val();
    var html = "";
    $.ajax({
        url : '/common/dict/list/pay_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                if(data[i].value==type){
                    html += '<option value="' + data[i].value + '" selected>' + data[i].name + '</option>'
                }
                else {
                    html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
                }
            }
            $("#payType").append(html);
            $("#payType").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#payType').on('change', function(e, params) {
                var value=params.selected;
                $("#payType1").val(value);
                if(value=='2'){
                    $("#firstPay").attr("disabled",false);
                }else{
                    $("#firstPay").attr("disabled",true);
                }
            });
        }
    });
}
function loadSellType(){
    var type= $("#sellType1").val();
    var html = "";
    $.ajax({
        url : '/common/dict/list/sell_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                if(data[i].value==type){
                    html += '<option value="' + data[i].value + '" selected>' + data[i].name + '</option>'
                }
                else {
                    html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
                }
            }
            $("#sellType").append(html);
            $("#sellType").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#sellType').on('change', function(e, params) {
                $("#sellType1").val(params.selected);
            });
        }
    });
}
function loadJiaofang(){
    var type= $("#state1").val();
    var html = "";
    $.ajax({
        url : '/common/dict/list/jiaofang_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                if(data[i].value==type){
                    html += '<option value="' + data[i].value + '" selected>' + data[i].name + '</option>'
                }
                else {
                    html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
                }
            }
            $("#state").append(html);
            $("#state").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#state').on('change', function(e, params) {
                $("#state1").val(params.selected);
            });
        }
    });
}