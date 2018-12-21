var prefix = "/base/owner";
$().ready(function() {
});

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix+"/update",
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
loadBank();
function loadBank(){
	var unit= document.getElementById("openingBank1").value;
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
			$("#openingBank").append(html);
			$("#openingBank").chosen({
				maxHeight : 200
			});

			//点击事件
			$('#unit').on('change', function(e, params) {
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