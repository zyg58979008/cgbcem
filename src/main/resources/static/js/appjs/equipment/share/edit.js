$().ready(function() {
	loadUnit();
	loadBrand();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		exitUpdate();
	}
});


function exitUpdate(){
	$.ajax({
		url : "/equipment/share/exitUpdate", // 后台处理程序
		type : "post", // 数据发送方式
		dataType : "json", // 接受数据格式
		data : { // 要传递的数据
			id : function() {
				return $("#id").val();
			},
			name : function() {
				return $("#name").val();
			},
			model : function() {
				return $("#model").val();
			},
			brand : function() {
				return $("#brand").val();
			},
			category: function () {
				return $("#category").val();
			}
		},
		cache: false,
		success: function(data){
			if(false == data){
				layer.msg("该品牌型号的设备已存在");
				$("#name").val('');
				return;
			}else{
				update();
			}

		}

	});
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/equipment/share/update",
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
				minlength:2,
			}
		},
		messages : {
			name : {
				required : icon + "请输入品牌名称",
				maxlength:icon + "品牌名称字数过多",
				minlength:icon + "品牌名称字数太少"
			}
		}
	})
}
function loadUnit(){
	var unit= document.getElementById("unit1").value;
	var html = "";
	$.ajax({
		url : '/common/dict/list/equipment_unit',
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
			$("#unit").append(html);
			$("#unit").chosen({
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
function loadBrand(){
	var brand= document.getElementById("brand1").value;
	var html = "";
	$.ajax({
		url : '/equipment/brand/brandlist',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				if(data[i].id==brand){
					html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>'
				}
				else {
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
				}
			}
			$("#brand").append(html);
			$("#brand").chosen({
				maxHeight : 200
			});

			//点击事件
			$('#brand').on('change', function(e, params) {
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
var openCategory = function(){
	layer.open({
		type:2,
		title:"选择设备分类",
		area : [ '300px', '450px' ],
		content:"/equipment/categories/treeView"
	})
}
function loadCategory( Id,name){
	$("#category").val(Id);
	$("#categoryname").val(name);
}