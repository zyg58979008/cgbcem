function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/wuye/wuyefeiConfig/update",
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
function num1(obj){
	if(isNaN(obj.value) && !/^-$/.test(obj.value)){
		obj.value="";
	}
	if(!/^[+-]?\d*\.{0,1}\d{0,1}$/.test(obj.value)){
		obj.value=obj.value.replace(/\.\d{2,}$/,obj.value.substr(obj.value.indexOf('.'),5));
	}
}