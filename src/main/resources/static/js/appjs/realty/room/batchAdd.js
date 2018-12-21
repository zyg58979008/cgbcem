$().ready(function() {
    $("#buildingId").val(parent.buildingId);
    $("#roomType").val(parent.roomType);
    $("#labelUnit").html("开始单元:（"+parent.unit+"）");
    if(parent.unit==0||parent.unit==1){
        $("#startUnit").val('1');
        $("#endUnit").val('1');
    }
});
function save() {
	var code=$("#code").val();
	if(!code||code==''){
        layer.msg('请输入房间编码');
		return;
	}
    var unit=$("#unit").val();
	if(parent.unit==0||parent.unit==1){
        $("#unit").val('1');
    }else{
	    if(parseInt(unit)>parent.unit){
            layer.msg('该楼宇只有'+parent.unit+'个单元');
            return;
        }
    }
    var floor=$("#floor").val();
    if(!floor||floor==''){
        layer.msg('请输入楼层');
        return;
    }
    $.ajax({
        cache : true,
        type : "POST",
        url : "/realty/room/save",
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