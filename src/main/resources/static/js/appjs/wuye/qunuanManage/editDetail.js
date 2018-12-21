function save() {
    var detail={};
    detail.id=$("#id").val();
    var qunuanYing=$("#qunuanYing").val();
    if(!qunuanYing||qunuanYing==''){
        layer.msg('请输入取暖费');
        return;
    }
    var tingnuanYing=$("#tingnuanYing").val();
    if(!tingnuanYing||tingnuanYing==''){
        layer.msg('请输入停暖费');
        return;
    }
    detail.qunuanUnpay=qunuanYing;
    detail.qunuanYing=qunuanYing;
    detail.tingnuanYing=tingnuanYing;
    detail.tingnuanUnpay=tingnuanYing;
    jQuery.ajax({
        cache : false,
        type : "POST",
        url : "/wuye/qunuanManage/updateDetail",
        data : {mydata:JSON.stringify(detail)},// 你的formid
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