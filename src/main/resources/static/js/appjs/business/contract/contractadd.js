
$(function() {
});
loadBank();
function loadBank(){
    var unit= document.getElementById("bank1").value;
    var html = "";
    $.ajax({
        url : '/common/dict/list/bank',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                if(data[i].value==unit){
                    html += '<option value="' + data[i].value + '" selected>' + data[i].name + '</option>'
                }
                else {
                    html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
                }
            }
            $("#bank").append(html);
            $("#bank").chosen({
                maxHeight : 200
            });

            //点击事件
            $('#bank').on('change', function(e, params,a) {
                //console.log(params.selected);
                $("#bank1").val(params.selected);
            });
        }
    });
}

loadPayType();
function loadPayType(){
    var unit= document.getElementById("payType1").value;
    var html = "";
    $.ajax({
        url : '/common/dict/list/shop_pay_cycle',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                if(data[i].value==unit){
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
            $('#payType').on('change', function(e, params,a) {
                //console.log(params.selected);
                $("#payType1").val(params.selected);
            });
        }
    });
}

function save() {

    var role = $('#signupForm').serialize();
    switch (null||'')
    {
        case $('#brand').val():
            layer.msg("请输入品牌名");
            break;
        case $('#bank').val():
            layer.msg("请选择开户行");
            break;
        case $('#fenleiGuanli').val():
            layer.msg("请输入管理费");
            break;
        case $('#fenleiZujin').val():
            layer.msg("请输入租金");
            break;
        case $('#contractor').val():
            layer.msg("请输入签约人姓名");
            break;
        case $('#idCard').val():
            layer.msg("请输入身份证号");
            break;
        case $('#contractCode').val():
            layer.msg("请输合同编号");
            break;
        case $('#unitPrice').val():
            layer.msg("请输入单价");
            break;
        case $('#rentArea').val():
            layer.msg("请输入计租面积");
            break;
        case $('#payType').val():
            layer.msg("请选择收款方式");
            break;
        case $('#contractStartDate').val():
            layer.msg("请选择合同开始日期");
            break;
        case $('#contractEndDate').val():
            layer.msg("请选择合同结束日期");
            break;
        default:
            var regBox = {
                regDiscount:/^(\d(\.\d{1})?|10)$/,
                regYouhui:/^(?!0)\d+(,\d+)+$/,
                regIdCard:/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/,//身份证号
                regCardNo:/^(998801|998802|622525|622526|435744|435745|483536|528020|526855|622156|622155|356869|531659|622157|627066|627067|627068|627069)\d{10}$/,//银行卡号
                regMobile:/^0?1[0-9][0-9]\d{8}$///手机
            }
            var idCard = $('#idCard').val();
            var mobile = $('#phone').val();
            var cardNo = $('#cardNo').val();
            var Youhui = $('#youhui').val();
            var Dis = $('#discount').val();
            var Disflag = regBox.regDiscount.test(Dis);
            var mflag = regBox.regMobile.test(mobile);
            var idflag = regBox.regIdCard.test(idCard);
            var cardflag = regBox.regCardNo.test(cardNo);
            var yoflag = regBox.regYouhui.test(Youhui);
            if(Dis){
                if(!Disflag){
                    layer.msg("折扣格式输入有误！需十以内小数：如七五折：7.5；不打折留空即可");
                    return;
                }
            }
            if(Youhui){
                if(!yoflag){
                    layer.msg("优惠格式输入有误！需数字加英文逗号加数字：如交六免三：6,3");
                    return;
                }
            }
            if (!mflag) {
                layer.msg("手机号输入有误！");
                return;
            }
            else if(!idflag){
                layer.msg("身份证号输入有误！");
                return;
            }
            var aa;
            var bb;
            aa = $('#contractStartDate').val();
            bb = $('#contractEndDate').val();
            if(bb<aa){
                layer.msg("合同结束日期不能小于合同开始日期");
                return;
            }
           /* else if(!cardflag){
                layer.msg("银行卡号输入有误！");
            }*/
            else{
                $.ajax({
                    cache : true,
                    type : "POST",
                    url : "/business/contract/contractaddSave",
                    data : role, // 你的formid
                    async : false,
                    error : function(request) {
                        alert("Connection error");
                    },
                    success : function(data) {
                        if (data.code == 0) {
                            parent.layer.msg("操作成功");
                            parent.reLoad();
                            var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                            parent.layer.close(index);

                        } else {
                            parent.layer.msg(data.msg);
                        }
                    }
                });
            };



    }
}