$().ready(function() {
	loadType();
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
		url : "/equipment/storeroom/update",
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
function loadType(){
	var type= document.getElementById("type1").value;
	var html = "";
	$.ajax({
		url : '/common/dict/list/storeroom_type',
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
			$("#type").append(html);
			$("#type").chosen({
				maxHeight : 200
			});

			//点击事件
			$('#type').on('change', function(e, params) {
				//console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}

			});
		}
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true,
				maxlength:100,
				minlength:2,
				remote : {
					url : "/equipment/storeroom/exitUpdate", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						id : function() {
							return $("#id").val();
						},
						name : function() {
							return $("#name").val();
						}
					}
				}
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字",
				maxlength:icon + "库房名称字数过多",
				minlength:icon + "库房名称字数太少",
				remote : icon + "库房名已经存在"
			}
		}
	})
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '380px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}