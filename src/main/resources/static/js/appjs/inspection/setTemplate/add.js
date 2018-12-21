//var menuTree;

var menuIds;
$(function() {
	loadTemplate();
	getMenuTreeData();
	validateRule();
});
$.validator.setDefaults({
	submitHandler : function() {
		getAllSelectNodes();
		save();
	}
});

function getAllSelectNodes() {
	var ref = $('#deptTree').jstree(true); // 获得整个树

	menuIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组

	/*$("#deptTree").find(".jstree-undetermined").each(function(i, element) {
		menuIds.push($(element).closest('.jstree-node').attr("id"));
	});*/
}
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
			'data' : menuTree
		},
		"checkbox" : {
			"three_state" : true,
		},
		"plugins" : [ "wholerow", "checkbox" ]
	});
	//$('#menuTree').jstree("open_all");

}

function save() {

	$('#menuIds').val(menuIds);
	var templateCode = $("#templateCode").val();
	if (menuIds.length == 0 || templateCode == "") {
		layer.msg("请填写完整信息");
		return;
	} else {
		var role = $('#signupForm').serialize();
		$.ajax({
			cache: true,
			type: "POST",
			url: "/inspection/setTemplate/save",
			data: role,
			async: false,
			error: function (request) {
				alert("Connection error");
			},
			success: function (data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引

					parent.layer.close(index);

				} else {
					parent.layer.msg(data.msg);
				}
			}
		});
	}
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			templateCode : {
				required : true
			}
		},
		messages : {
			templateCode : {
				required : icon + "请模板名"
			}
		}
	});
}

function loadTemplate(){
	var html = "";
	$.ajax({
		url : '/inspection/template/templatelist',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
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