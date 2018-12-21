$().ready(function() {
    loadType();
    $(".buildingDiv").addClass('view');
});

function save() {
	var type=$("#type1").val();
	if(!type||type==''){
        layer.msg('请选择楼宇类型');
		return;
	}
    var name=$("#name").val();
    if(!name||name==''){
        layer.msg('请输入名称');
        return;
    }
    if(type=='2'){
        var unit=$("#unit").val();
        if(!unit||unit==''){
            layer.msg('请输入单元数');
            return;
        }
    }
    var sort=$("#sort").val();
    if(!sort||sort==''){
        layer.msg('请输入排序');
        return;
    }
    $.ajax({
        cache : true,
        type : "POST",
        url : "/realty/building/save",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.msg("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();

                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.msg(data.msg)
            }

        }
    });

}
function loadType(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/building_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#type").append(html);
            $("#type").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#type').on('change', function(e, params) {
                //console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
                if(params.selected=="2"){
                    $(".buildingDiv").removeClass('view');
                }
                if(params.selected=="1"){
                    $(".buildingDiv").addClass('view');
                }
                if(params.selected==""){
                    $(".buildingDiv").addClass('view');
                }
                $("#type1").val(params.selected);
            });
        }
    });
}
