$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
loadType();
function loadType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/bank',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#bank").append(html);
			$("#bank").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#bank').on('change', function(e, params) {
				//console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				if(params.selected=="04"){
					$("#areaDiv").removeClass('view');
				}
				else {
					$("#areaDiv").addClass('view');
				}
			});
		}
	});
}
loadType1();
function loadType1(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/pay',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#pay").append(html);
			$("#pay").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#pay').on('change', function(e, params) {
				//console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				if(params.selected=="04"){
					$("#areaDiv").removeClass('view');
				}
				else {
					$("#areaDiv").addClass('view');
				}
			});
		}
	});
}
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/stock/product/save",
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