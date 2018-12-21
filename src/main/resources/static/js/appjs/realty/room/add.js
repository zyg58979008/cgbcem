$().ready(function() {
    $("#buildingId").val(parent.buildingId);
    loadBuildingType();
    $("#labelUnit").html("单元（"+parent.unit+"）");
    if(parent.unit==0||parent.unit==1){
        $("#unit").val('1');
    }
});
function save() {
    var code=$("#code").val();
    if(!code||code==''){
        layer.msg('请输入房间编码');
        return;
    }
    var roomType=$("#roomType1").val();
    if(!roomType||roomType==''){
        layer.msg('请选择房屋类型');
        return;
    }
    var unit=$("#unit").val();
    if(parseInt(unit)>parent.unit){
        layer.msg('该楼宇只有'+parent.unit+'个单元');
        return;
    }
    var floor=$("#floor").val();
    if(!floor||floor==''){
        layer.msg('请输入楼层');
        return;
    }
    var buildingArea=$("#buildingArea").val();
    if(!buildingArea||buildingArea==''){
        layer.msg('请输入建筑面积');
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
function loadBuildingType(){
    var html = "";
    $.ajax({
        url : '/common/dict/list/room_type',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#roomType").append(html);
            $("#roomType").chosen({
                maxHeight : 200
            });
            //点击事件
            $('#roomType').on('change', function(e, params,a) {
                //console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
                $("#roomType1").val(params.selected);
            });
        }
    });
}