
$(function() {
});
loadType();
function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/bank',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#openingBank").append(html);
			$("#openingBank").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#openingBank').on('change', function(e, params) {
				//console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				if(params.selected=="04"){
					$("#areaDiv").removeClass('view');
				}
				else {
					$("#areaDiv").addClass('view');
				}
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
		case $('#juridicalPerson').val():
			layer.msg("请输入法人姓名");
			break;
		case $('#idCard').val():
			layer.msg("请输入身份证号");
			break;
		case $('#phone').val():
			layer.msg("请输入联系电话");
			break;
		case $('#address').val():
			layer.msg("请输入住址");
			break;
		case $('#openingBank').val():
			layer.msg("请选择开户行");
			break;
		case $('#cardNo').val():
			layer.msg("请输入卡号");
			break;
		default:
			var regBox = {
				regIdCard:/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/,//身份证号
				regCardNo:/^(998801|998802|622525|622526|435744|435745|483536|528020|526855|622156|622155|356869|531659|622157|627066|627067|627068|627069)\d{10}$/,//银行卡号
				regMobile:/^0?1[0-9][0-9]\d{8}$///手机
			}
			var idCard = $('#idCard').val();
			var mobile = $('#phone').val();
			var tel = $('#phone').val();
			var cardNo = $('#cardNo').val();
			var mflag = regBox.regMobile.test(mobile);
			var idflag = regBox.regIdCard.test(idCard);
			var cardflag = regBox.regCardNo.test(cardNo);
			if (!mflag) {
				layer.msg("手机号输入有误！");
			}
			else if(!idflag){
				layer.msg("身份证号输入有误！");
			}
			else if(!cardflag){
				layer.msg("银行卡号输入有误！");
			}
			else{
				$.ajax({
					cache : true,
					type : "POST",
					url : "/base/merchant/save",
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