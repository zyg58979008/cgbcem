$().ready(function() {
	load();
	loadType();


	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function load() {
	var div = document.getElementById("typeDiv");
    var pId=$("#parentId").val();
	if(pId==0){
		div.style.display = "block";

	} else {
		div.style.display = "none";

	}
}
function save() {
	var pId=$("#parentId").val();
	var type = $("#type").val();
	var flag = $("#signupForm").valid();
	if(!flag){
		//没有通过验证

		return;
	}
	if(pId==0&&type==""){
		layer.msg("请选择设备分类类型");
		return;
	}else {
		$.ajax({
			cache: true,
			type: "POST",
			url: "/equipment/categories/save",
			data: $('#signupForm').serialize(),// 你的formid
			async: false,
			error: function (request) {
				parent.layer.alert("Connection error");
			},
			success: function (data) {
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
function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/stock_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			//点击事件
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
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
				url : "/equipment/categories/exitInsert", // 后台处理程序
				type : "post", // 数据发送方式
				dataType : "json", // 接受数据格式
				data : { // 要传递的数据
					name : function() {
						return $("#name").val();
					}
				}
			}
			}
		},
		messages : {
			name : {
				required : icon + "请输入分类名称",
				maxlength:icon + "分类名称字数过多",
				minlength:icon + "分类名称字数太少",
				remote : icon + "分类名称已经存在"
			}

		}
	})
}