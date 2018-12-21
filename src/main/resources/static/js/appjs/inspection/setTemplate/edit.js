
var prefix = "/inspection/setTemplate"
$().ready(function() {
	loadTemplate();
	getMenuTreeData();
	validateRule();
});


$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function getMenuTreeData() {
	$.ajax({
		type : "GET",
		url : "/system/sysDept/tree1",
		success : function(menuTree) {
			loadMenuTree(menuTree);
		}
	});
}
function loadMenuTree(menuTree) {
	$('#deptTree').jstree({
		'core' : {
			'data' : menuTree,
			"multiple": false //单选
		},
		"checkbox" : {
			"three_state" : true,
		},
		"plugins" : [ "wholerow", "checkbox" ]
	}).on('select_node.jstree', function (e, data) {
	}).on("loaded.jstree", function (event, data) {
		//这两句化是在loaded所有的树节点后，然后做的选中操作，这点是需要注意的，loaded.jstree 这个函数
		//取消选中，然后选中某一个节点
		$("#deptTree").jstree("deselect_all",true);
		//$("#keyKamokuCd").val()是选中的节点id，然后后面的一个参数 true表示的是不触发默认select_node.change的事件
		$('#deptTree').jstree('select_node',$("#areaCode").val(),true);
	});
	//$('#menuTree').jstree("open_all");

}
	//节点勾选事件
$('#deptTree').bind("activate_node.jstree", function (obj, e) {

	$("#areaCode").val(e.node.id);
});
/*
$('#dailogTvmTree').jstree({
	'core': {'check_callback':true,
		'multiple':false,
		"expand_selected_onload":true,
		'data': treeData },
}).on('select_node.jstree', function (e, data) {
}).on("loaded.jstree", function (event, data) {
	//这两句化是在loaded所有的树节点后，然后做的选中操作，这点是需要注意的，loaded.jstree 这个函数
	//取消选中，然后选中某一个节点
	$("#dailogTvmTree").jstree("deselect_all",true);
	//$("#keyKamokuCd").val()是选中的节点id，然后后面的一个参数 true表示的是不触发默认select_node.change的事件
	$('#dailogTvmTree').jstree('select_node',$("#keyKamokuCd").val(),true);
});*/

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/inspection/setTemplate/update",
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

function loadTemplate(){
	var templateCode= document.getElementById("templateCode1").value;
	var html = "";
	$.ajax({
		url : '/inspection/template/templatelist',
		success : function(data) {
			//加载数据

			for (var i = 0; i < data.length; i++) {
				if(data[i].id==templateCode){
					html += '<option value="' + data[i].id + '" selected>' + data[i].name + '</option>'
				}
				else {
					html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
				}
			}

			$("#templateCode").append(html);
			$("#templateCode").chosen({
				maxHeight : 200
			});

			//点击事件
			$('#templateCode').on('change', function(e, params) {
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
			}
		},
		messages : {
			name : {
				required : icon + "请输入项目名称",
				maxlength:icon + "项目名称字数过多",
				minlength:icon + "项目名称字数太少"
			}
		}
	})
}
