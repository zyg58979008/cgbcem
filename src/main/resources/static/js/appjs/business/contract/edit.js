$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/business/contract/update",
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
				required : icon + "请输入商品名称"
			}
		}
	})
}
loadBank();
function loadBank(){
	var unit= document.getElementById("bank1").value;
	var html = "";
	$.ajax({
		url : '/common/dict/list/bank',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				if(data[i].value==unit){
					html += '<option value="' + data[i].value + '" selected>' + data[i].name + '</option>'
				}
				else {
					html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
				}
			}
			$("#bank").append(html);
			$("#bank").chosen({
				maxHeight : 200
			});

			//点击事件
			$('#bank').on('change', function(e, params,a) {
				//console.log(params.selected);
				$("#bank1").val(params.selected);
			});
		}
	});
}