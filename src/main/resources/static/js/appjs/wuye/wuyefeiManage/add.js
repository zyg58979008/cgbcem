
var prefix = "/wuye/wuyefeiManage"
function batchInsert() {
	var manage={};
	var name=$("#name").val();
	if(!name||name==''){
		layer.msg('请输入物业费名称');
		return;
	}
	manage.name=name;
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
	manage.startDate=strToDate(startDate);
	manage.endDate=strToDate(endDate);
	if(manage.startDate>manage.endDate){
		layer.msg('开始日期不能晚于结束日期');
		return;
	}
	manage.startYear=startDate.substr(0,4);
	manage.endYear=endDate.substr(0,4);
	jQuery.ajax({
		type : 'POST',
		data : {mydata:JSON.stringify(manage)},// 你的formid
		async : false,
		dataType:"json",
		url : prefix + '/save',
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