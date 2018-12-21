$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	var flag = $("#signupForm").valid();
	if(!flag){
		//没有通过验证
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/inspection/projectClass/update",
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			projectClass : {
				required : true,
				maxlength:100,
				minlength:2,
				remote : {
					url : "/inspection/projectClass/exitUpdate", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						id : function() {
							return $("#id").val();
						},
						projectClass : function() {
							return $("#projectClass").val();
						}
					}
				}
			}
		},
		messages : {
			projectClass : {
				required : icon + "请输入项目名称",
				maxlength:icon + "项目名称字数过多",
				minlength:icon + "项目名称字数太少",
				remote : icon + "项目名称已经存在"
			}
		}
	})
}