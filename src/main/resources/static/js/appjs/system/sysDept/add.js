$().ready(function() {
	validateRule();
	$("#signupForm").validate({
		submitHandler:function(form){
			save()
		}
	})
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
		url : "/system/sysDept/save",
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入机构名称"
			}
		}
	})
}
var openArea = function(){
	layer.open({
		type:2,
		title:"选择区域",
		area : [ '300px', '450px' ],
		content:"/system/sysArea/treeView"
	})
}
function loadArea( area,areaName){
	$("#area").val(area);
	$("#areaName").val(areaName);
}