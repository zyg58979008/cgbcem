$().ready(function() {

	$('.summernote').summernote({
		height : '220px',
		lang : 'zh-CN',
		callbacks: {
            onImageUpload: function(files, editor, $editable) {
                sendFile(files);
            }
        }
	});
	validateRule();
});
$('#jstree_demo_div').jstree({'plugins':["wholerow","checkbox"], 'core' : {
	'data' : [

		{ "id" : "ajson2", "parent" : "#", "text" : "承德市" ,'state' : {  'selected' : false },},
		{ "id" : "ajson3", "parent" : "ajson2", "text" : "丰宁县" },
		{ "id" : "ajson4", "parent" : "ajson2", "text" : "滦平县" },
		{ "id" : "ajson5", "parent" : "ajson2", "text" : "围场县" },
		{ "id" : "ajson6", "parent" : "ajson2", "text" : "平泉县" }

	]
} });

$.validator.setDefaults({
	submitHandler : function() {
		save(1);
	}
});
function save(status) {
	$("#status").val(status);
	var content_sn = $("#content_sn").summernote('code');
	$("#content").val(content_sn);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/blog/bContent/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(r) {
			if (r.code == 0) {
				parent.layer.msg(r.msg);
				parent.reLoad();
				$("#cid").val(r.cid);

			} else {
				parent.layer.alert(r.msg)
			}
		}
	});
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			title : "required",
			author : "required",
			content : "required"
		},
		messages : {
			title : "请填写文章标题",
			author : "请填写文章作者",
			content : "请填写文章内容"
		}
	});
}

function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}