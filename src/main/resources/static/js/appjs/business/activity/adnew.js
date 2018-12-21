$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function savenew() {
	switch (null||'')
	{
		case $('#brand').val():
			layer.msg("请输入品牌名");
			break;
		case $('#contractor').val():
			layer.msg("请输入签约人姓名");
			break;
		case $('#activityMoney').val():
			layer.msg("请输入活动费");
			break;
		case $('#dmDan').val():
			layer.msg("请输入DM单");
			break;
		case $('#cardMoney').val():
			layer.msg("请输入邀请卡款");
			break;
		default:
			$.ajax({
				cache : true,
				type : "POST",
				url : "/business/activity/savenew",
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
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}