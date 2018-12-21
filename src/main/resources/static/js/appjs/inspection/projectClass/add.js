$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var flag = $("#signupForm").valid();
	if(!flag){
		//没有通过验证
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/inspection/projectClass/save",
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
					url : "/inspection/projectClass/exitInsert", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						projectClass : function() {
							return $("#projectClass").val();
						}
					}
				}
			}
		},
		messages : {
			projectClass : {
				required :icon + "请输入分类名称",
				maxlength:icon + "分类名称字数过多",
				minlength:icon + "分类名称字数太少",
				remote : icon + "分类名称已经存在"
			}
		}
	})
}