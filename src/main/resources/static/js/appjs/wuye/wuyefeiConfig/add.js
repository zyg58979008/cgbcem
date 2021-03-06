$().ready(function() {
    $("#buildingId").val(parent.buildingId);
    loadBuildingType(1);
});
var num=1;
function save() {
    var room={};
    room.roomDOList=new Array();
    room.deptId=parent.deptId;
    for(var i=0;i<num;i++){
        var r={};
        var index=i+1
        var startFloor=$(".startFloor:eq("+i+")").val();
        if(!startFloor||startFloor==''){
            layer.msg('请输入第'+index+'个开始楼层');
            return;
        }
        r.startFloor=parseInt(startFloor);
        var endFloor=$(".endFloor:eq("+i+")").val();
        if(!endFloor||endFloor==''){
            layer.msg('请输入第'+index+'个结束楼层');
            return;
        }
        r.endFloor=parseInt(endFloor);
        var roomType=$(".roomType:eq("+i+")").val();
        if(!roomType||roomType==''){
            layer.msg('请选择第'+index+'个房屋类型');
            return;
        }
        r.roomType=roomType;
        var wuyefei=$(".wuyefei:eq("+i+")").val();
        if(!wuyefei||wuyefei==''){
            layer.msg('请输入第'+index+'个物业费');
            return;
        }
        r.wuyefei=parseFloat(wuyefei);
        r.buildingId=parseInt(parent.buildingId);
        r.deptId=parent.deptId;
        room.roomDOList.push(r);
    }
    jQuery.ajax({
        cache : false,
        type : "POST",
        url : "/wuye/wuyefeiConfig/batchSave",
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
function loadBuildingType(index){
    var html = "";
    $.ajax({
        url : '/realty/room/dictList',
        type : "post",
        data : {
            'type' : 'room_type',
            'buildingId':parent.buildingId
        },
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
            }
            $("#roomType_"+index).append(html);
            $("#roomType_"+index).chosen({
                maxHeight : 200
            });
            //点击事件
            $('#roomType_'+index).on('change', function(e, params) {
                //console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
                $("#roomType"+index).val(params.selected);
            });
        }
    });
}
function add() {
    num++;
    var timestamp = Date.parse( new Date());
    var html='<div class="card" id="card_'+timestamp+'">'+
        '							<label class="cardLabel">'+num+'</label>' +
        '<button class="btn btn-danger" style="margin-left: 40px;" onclick="del('+timestamp+')">删除</button>'+
        '<div class="form-group">'+
        '								<label class="col-sm-3 control-label">开始楼层：</label>'+
        '								<div class="col-sm-3">'+
        '									<input  class="form-control startFloor" type="number" >'+
        '								</div>'+
        '								<label class="col-sm-3 control-label" >结束楼层：</label>'+
        '								<div class="col-sm-3">'+
        '									<input  class="form-control endFloor"'+
        '										   type="number">'+
        '								</div>'+
        '							</div>'+
        '							<div class="form-group buildingDiv">'+
        '								<label class="col-sm-3 control-label">房屋类型：</label>'+
        '								<div class="col-sm-3">'+
        '									<input type="hidden" class="roomType" id="roomType'+num+'">'+
        '									<select data-placeholder="--选择房屋类型--"  id="roomType_'+num+'"'+
        '											class="form-control chosen-select" tabindex="2">'+
        '										<option value="">--选择房屋类型--</option>'+
        '									</select>'+
        '								</div>'+
        '								<label class="col-sm-3 control-label">物业费：</label>'+
        '								<div class="col-sm-3">'+
        '									<input class="form-control wuyefei"'+
        '										   type="text" onkeyup= "this.value=this.value.toString().match(/^\/\/d+(?:\/\/.\/\/d{0,2})?/)">'+
        '								</div>'+
        '							</div>'+
        '						</div>';
    $(".card:last ").after(html);
    loadBuildingType(num);
}
function del(timestamp) {
    num--;
    $("#card_"+timestamp).remove();
    $(".cardLabel").each(function (i) {
        $(".cardLabel:eq("+i+")").html(i+1)
    })
}