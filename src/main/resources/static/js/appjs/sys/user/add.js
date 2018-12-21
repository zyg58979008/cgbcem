var deptIds;
$().ready(function() {
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
    var ref = $('#menuTree').jstree(true); // 获得整个树

    deptIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组

    $("#menuTree").find(".jstree-undetermined").each(function(i, element) {
        deptIds.push($(element).closest('.jstree-node').attr("id"));
    });
}
function getMenuTreeData() {
    $.ajax({
        type : "GET",
        url : "/system/sysDept/tree",
        success : function(menuTree) {
            loadMenuTree(menuTree);
        }
    });
}
function loadMenuTree(menuTree) {
    $('#menuTree').jstree({
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
function getCheckedRoles() {
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function save() {
	var deptId=$("#deptId").val();
	if(!deptId||deptId==''){
		layer.msg('请选择所属项目');
		return;
	}
    $('#deptIds').val(deptIds);
	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/user/save",
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
jQuery.validator.addMethod("isMobile", function(value, element) {
	return true;
	var length = value.length;
	var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
	return this.optional(element) //|| (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			mobile : {
				required : true,
				minlength : 1,
				isMobile : true,
				remote : {
					url : "/sys/user/exit", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						mobile : function() {
							return $("#mobile").val();
						}
					}
				}
			},
			password : {
				required : true,
				minlength : 6
			},
			confirm_password : {
				required : true,
				minlength : 6,
				equalTo : "#password"
			},
			topic : {
				required : "#newsletter:checked",
				minlength : 2
			},
			agree : "required"
		},
		messages : {

			name : {
				required : icon + "请输入姓名"
			},
			/*mobile : {
				required : "请输入手机号",
				minlength : "确认手机不能小于11个字符",
				isMobile : "请正确填写您的手机号码",
				remote: "填写的手机号码已存在"
			},*/
			password : {
				required : icon + "请输入密码",
				minlength : icon + "密码必须6个字符以上"
			},
			confirm_password : {
				required : icon + "请再次输入密码",
				minlength : icon + "密码必须6个字符以上",
				equalTo : icon + "两次输入的密码不一致"
			}
		}
	})
}

var openDept = function(){
	layer.open({
		type:2,
		title:"选择项目",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}