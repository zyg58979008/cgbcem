$().ready(function() {
    $("#buildingId").val(parent.buildingId);
    $("#roomType").val(parent.roomType);
});
var num=1;
function save() {
    var room={};
    room.mergeIds=parent.mergeIds;
    room.mergedIds=new Array();
    room.buildingId=parseInt(parent.buildingId);
    room.roomType=parent.roomType;
    room.roomDOList=new Array();
    room.deptId=parent.deptId;
    for(var i=0;i<num;i++){
        var r={};
        var index=i+1
        var code=$(".code:eq("+i+")").val();
        if(!code||code==''){
            layer.msg('请输入第'+index+'个房间的编码');
            return;
        }
        r.code=code;
        room.mergedIds.push(code);
        var floor=$(".floor:eq("+i+")").val();
        if(!floor||floor==''){
            layer.msg('请输入第'+index+'个房间的楼层');
            return;
        }
        r.floor=parseInt(floor);
        var buildingArea=$(".buildingArea:eq("+i+")").val();
        if(!buildingArea||buildingArea==''){
            layer.msg('请输入第'+index+'个房间的建筑面积');
            return;
        }
        r.buildingArea=parseFloat(buildingArea);
        var floorArea=$(".floorArea:eq("+i+")").val();
        if(!floorArea||floorArea==''){
            layer.msg('请输入第'+index+'个房间的使用面积');
            return;
        }
        r.floorArea=parseFloat(floorArea);
        r.buildingId=parseInt(parent.buildingId);
        r.roomType=parent.roomType;
        r.unit=parent.unit;
        r.deptId=parent.deptId;
        room.roomDOList.push(r);
    }
    jQuery.ajax({
        cache : false,
        type : "POST",
        url : "/realty/room/batchSave",
        data : {mydata:JSON.stringify(room)},// 你的formid
        async : false,
        dataType:"json",
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
function add() {
    num++;
    var timestamp = Date.parse( new Date());
    var html='<div class="card" id="card_'+timestamp+'">'+
        '							<label class="cardLabel">'+num+'</label>' +
        '<button class="btn btn-danger" style="margin-left: 40px;" onclick="del('+timestamp+')">删除</button>'+
        '							<div class="form-group">'+
        '								<label class="col-sm-3 control-label">房间编码：</label>'+
        '								<div class="col-sm-3">'+
        '									<input class="form-control code" type="text" onkeyup="this.value=this.value.replace(/^ +| +$/g,\'\')">'+
        '								</div>'+
        '								<label class="col-sm-3 control-label">房间楼层：</label>'+
        '								<div class="col-sm-3">'+
        '									<input class="form-control floor"'+
        '										   type="number">'+
        '								</div>'+
        '							</div>'+
        '							<div class="form-group buildingDiv">'+
        '								<label class="col-sm-3 control-label">建筑面积：</label>'+
        '								<div class="col-sm-3">'+
        '									<input class="form-control buildingArea"'+
        '										   type="text" onkeyup= "this.value=this.value.toString().match(/^\/\/d+(?:\/\/.\/\/d{0,2})?/)">'+
        '								</div>'+
        '								<label class="col-sm-3 control-label">使用面积：</label>'+
        '								<div class="col-sm-3">'+
        '									<input class="form-control floorArea"'+
        '										   type="text" onkeyup= "this.value=this.value.toString().match(/^\/\/d+(?:\/\/.\/\/d{0,2})?/)">'+
        '								</div>'+
        '							</div>'+
        '						</div>';
        $(".card:last ").after(html)
}
function del(timestamp) {
    num--;
    $("#card_"+timestamp).remove();
    $(".cardLabel").each(function (i) {
        $(".cardLabel:eq("+i+")").html(i+1)
    })
}