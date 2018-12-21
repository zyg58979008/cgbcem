$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function confirmChechang() {
	var contractChechangDate=$("#contractChechangDate").val();
	if(!contractChechangDate||contractChechangDate==''){
		layer.msg('请选择撤场日期');
		return;
	}
	layer.confirm('是否确认撤场？', {
		btn: ['确定', '取消']
	}, function () {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/business/contract/contractChechang",
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
})
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