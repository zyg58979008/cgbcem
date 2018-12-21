var buildingArea=parseFloat($("#buildingArea").val());
var price=parseFloat($("#price").val());
var totalPrice=(buildingArea*price).toFixed(2);
var w=parseFloat($("#w").val());
function save() {
    var detail={};
    detail.id=$("#id").val();
    var startDate=$("#startDate").val();
    if(!startDate||startDate==''){
        layer.msg('请选择开始日期');
        return;
    }
    var endDate=$("#endDate").val();
    if(!endDate||endDate==''){
        layer.msg('请选择结束日期');
        return;
    }
    detail.startDate=strToDate(startDate);
    detail.endDate=strToDate(endDate);
    if(detail.startDate>detail.endDate){
        layer.msg('开始日期不能晚于结束日期');
        return;
    }
    detail.startYear=startDate.substr(0,4);
    detail.endYear=endDate.substr(0,4);
    var yingpay=$("#yingpay").val();
    if(!yingpay||yingpay==''){
        layer.msg('请输入物业费');
        return;
    }
    detail.yingpay=yingpay;
    detail.unpay=yingpay;
    detail.wuyefeiId=$("#wuyefeiId").val();
    var zhekou=$("#zhekou").val();
    if(parseFloat(zhekou)!=0){
        detail.zhekou=parseFloat(zhekou);
    }
    jQuery.ajax({
        cache : false,
        type : "POST",
        url : "/wuye/wuyefeiManage/updateDetail",
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
function calculate() {
    var s=dateFormat($("#s").val(),"yyyy-MM-dd");
    var e=dateFormat($("#e").val(),"yyyy-MM-dd");
    var startDate=$("#startDate").val();
    if(!startDate||startDate==''){
        layer.msg('请选择开始日期');
        return;
    }
    var endDate=$("#endDate").val();
    if(!endDate||endDate==''){
        layer.msg('请选择结束日期');
        return;
    }
    if(strToDate(startDate)>strToDate(endDate)){
        layer.msg('开始日期不能晚于结束日期');
        return;
    }
    $.get('/wuye/wuyefeiManage/getMonths', {
        start: startDate,
        end: endDate
    }, function(data) {
        if(data){
            var month=data;
            var zhekou=$("#zhekou").val();
            var yingpay=(totalPrice*month).toFixed(2);
            if(parseFloat(zhekou)!=0){
                yingpay=(yingpay*parseFloat(zhekou)).toFixed(2);
            }
            $("#yingpay").val(Math.round(yingpay));
        }
    }, 'json');

}
function c() {
    $("#yingpay").val(w);
}
function dateDiffIncludeToday(startDateString, endDateString){
    var separator = "-"; //日期分隔符
    var startDates = startDateString.split(separator);
    var endDates = endDateString.split(separator);
    var startDate = new Date(startDates[0], startDates[1]-1, startDates[2]);
    var endDate = new Date(endDates[0], endDates[1]-1, endDates[2]);
    return parseInt(Math.abs(endDate - startDate ) / 1000 / 60 / 60 /24) + 1;//把相差的毫秒数转换为天数
}
function strToDate(dateStr,separator){
    var separator="-";
    var dateArr = dateStr.split(separator);
    var year = parseInt(dateArr[0]);
    var month;
    //处理月份为04这样的情况
    if(dateArr[1].indexOf("0") == 0){
        month = parseInt(dateArr[1].substring(1));
    }else{
        month = parseInt(dateArr[1]);
    }
    var day = parseInt(dateArr[2]);
    var date = new Date(year,month -1,day);
    return date;
}
function dateFormat(date, format) {

    date = new Date(date);

    var o = {
        'M+' : date.getMonth() + 1, //month
        'd+' : date.getDate(), //day
        'H+' : date.getHours(), //hour
        'm+' : date.getMinutes(), //minute
        's+' : date.getSeconds(), //second
        'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
        'S' : date.getMilliseconds() //millisecond
    };

    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));

    for (var k in o)
        if (new RegExp('(' + k + ')').test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));

    return format;
}