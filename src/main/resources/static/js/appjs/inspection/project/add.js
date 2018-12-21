$().ready(function() {
	loadClass();
	loadType();
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
	exitInsert();
}

function exitInsert() {
	var projectClassId = $("#projectClassId").val();
	var type = $("#type").val();
	if (projectClassId == ""||type == "") {
		layer.msg("请填写项目类别和项目类型");
		return;
	} else {
		$.ajax({
			url: "/inspection/project/exitInsert", // 后台处理程序
			type: "post", // 数据发送方式
			dataType: "json", // 接受数据格式
			data: { // 要传递的数据
				name: function () {
					return $("#name").val();
				},
				projectClassId: function () {
					return $("#projectClassId").val();
				}
			},
			cache: false,
			success: function (data) {
				if (false == data) {
					layer.msg("该项目分类中的项目名称已存在");
					$("#name").val('');
					return;
				} else {
					Insert();
				}
			}
		});
	}
}
function Insert() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/inspection/project/save",
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
				required : true,
				maxlength:100,
				minlength:2
			}
		},
		messages : {
			name : {
				required :icon + "请输入项目名称",
				maxlength:icon + "项目名称字数过多",
				minlength:icon + "项目名称字数太少"

			}
		}
	})
}
function loadClass(){
	var html = "";
	$.ajax({
		url : '/inspection/projectClass/classlist',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].projectClass + '</option>'
			}
			$("#projectClassId").append(html);
			$("#projectClassId").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#projectClassId').on('change', function(e, params) {
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
function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/xm_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
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

function changeType() {
	if($("#type").val()=='2'){
		$("#unitDiv").show();
	}else {
		$("#unitDiv").hide();
		$("#unit").val("");
	}
}