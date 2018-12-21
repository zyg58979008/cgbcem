var prefix = "/equipment/print"
$().ready(function() {
	/*loadName();*/
	/*loadUnit();*/
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		/*save();*/
	}
});
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
function loadName(){
	var html = "";
	$.ajax({
		url : prefix + '/getName',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].name + '">' + data[i].name + '</option>'
			}
			$("#name").append(html);
			$("#name").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#name').on('change', function(e, params) {
				//console.log(params.selected);
				var opt = {
					query : {
						name : params.selected
					}
				}
				getBrand(opt);
			});
		}
	});
}
function getBrand(opt){
	var html = "";
	$.ajax({
		type : "get",
		data : opt,
		url : prefix + '/getBrand',
		success : function(data) {
			html += '<option value="">--选择品牌--</option>'
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
			}
			/*$("#categoryChose").trigger("chosen:updated");*/
			$("#brand").empty().append(html).trigger("chosen:updated");
			$("#brand").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#brand').on('change', function(e, params) {
				//console.log(params.selected);
				var opt = {
					query : {
						brand : params.selected,
						name :$("#name").val()
					}
				}
				getModel(opt);
			});
		}
	});
}
function getModel(opt){
	var html = "";
	$.ajax({
		type : "get",
		data : opt,
		url : prefix + '/getModel',
		success : function(data) {
			html += '<option value="">--选择型号--</option>'
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].model + '">' + data[i].model + '</option>'
			}
			/*$("#categoryChose").trigger("chosen:updated");*/
			$("#model").empty().append(html).trigger("chosen:updated");
			$("#model").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#model').on('change', function(e, params) {
				//console.log(params.selected);
				var opt = {
					query : {
						model : params.selected
					}
				}
			});
		}
	});
}
function loadUnit(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/equipment_unit',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
		},
		messages : {
		}
	})
}

//打印二维码预览窗口
function aa() {
	var id =$("#idd").val();
	var putNum =$("#putNum").val();
	if(id!=null && id!=""&& putNum!=null&& putNum!=""){
		if(putNum == 0){
			$("#putNum").val(1);
		}

		getAllElement();
	}
		else{
			toastr.warning("请将信息填写完整");
	}
}
	function layOpen() {
		layer.open({
			type : 2,
			title : '打印预览',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '600px', '420px' ],
			content : prefix + '/codePrint' // iframe的url
		});
	}
	//查找并生成code和流水号
	function getAllElement()
	{
		/*var codeNew;
		var time;
		time = Date.parse(new Date()).toString();*/
		$.ajax({
			type : "get",
			data :{
				id : $("#idd").val(),
				num : $("#putNum").val()
			},// 你的formid
			/*url : prefix + '/getCode',*/
			/*data :$('#signupForm').serialize(),// 你的formid*/
			url : prefix + '/savePrintCode',

			success : function(data) {
				var printId = data;
				$("#printId").val(printId);
				layOpen();
			/*	$("#unit").val(data[0].unit);
				$("#unitName").val(data[0].unitName);
				$("#category").val(data[0].category);
				$("#durableYears").val(data[0].durableYears);*/
			}
		});
	}

function openEquipment() {
	layer.open({
		type: 2,
		title: '设备详情',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['900px', '480px'],
		content: prefix + '/equipmentInfo/'// iframe的url
	});

}
function loadEquipment(id,name,brandname,brand,model,unitName,remarks) {
	$('#idd').val(id);
	$('#name').val(name);
	$('#brandname').val(brandname);
	$('#brand').val(brand);
	$('#model').val(model);
	$('#unitName').val(unitName);
	$('#remarks').val(remarks);
}

