var deptIds;
$().ready(function() {
	//getMenuTreeData();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		//getAllSelectNodes();
		save();
	}
});
function getAllSelectNodes() {
	var ref = $('#deptTree').jstree(true); // 获得整个树

	deptIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组

	$("#deptTree").find(".jstree-undetermined").each(function(i, element) {
		deptIds.push($(element).closest('.jstree-node').attr("id"));
	});
}
function getMenuTreeData() {
	$.ajax({
		type : "GET",
		url : "/system/sysDept/tree",
		success : function(deptTree) {
			loadDeptTree(deptTree);
		}
	});
}
function loadDeptTree(deptTree) {
	$('#deptTree').jstree({
		'core' : {
			'data' : deptTree
		},
		"checkbox" : {
			"three_state" : true,
		},
		"plugins" : [ "wholerow", "checkbox" ]
	});
	//$('#menuTree').jstree("open_all");

}

function save() {
	$('#deptIds').val(deptIds);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/inspection/planOperation/save",
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
			planTime : {
				required : true
			},
			deptName : {
				required : true
			}
		},
		messages : {
			planTime : {
				required : icon + "请选择巡检计划时间"
			},
			deptName : {
				required : icon + "请选择巡检网点"
			}
		}
	})
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}