function update() {
	$("#weixiuUnpay").val($("#weixiuYing").val());
	$("#jiaoyiUnpay").val($("#jiaoyiYing").val());
	$("#fangbenUnpay").val($("#fangbenYing").val());
	$("#qishuiUnpay").val($("#qishuiYing").val());
	$("#daibanUnpay").val($("#daibanYing").val());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/wuye/fangbenManage/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.msg("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg)
			}

		}
	});

}