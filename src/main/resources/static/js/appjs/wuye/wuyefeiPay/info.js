var prefix = "/wuye/wuyefeiManage"
var id=$("#wuyefeiId").val();
var isLoad=false;
$(function() {
	getTreeData();
    var height=$(document.body).height();
    height=height-38;
    $("#exampleTable").attr("data-height",height);
	load();
});
var contract={};
var buildingId='';//楼宇ID
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
						wuyefeiId:id,
						roomCode:$("#roomCode").val(),
						buildingId : buildingId
                    };
                },
				columns : [
					{
						title: '序号',//标题  可不加
						formatter: function (value, row, index) {
							return index + 1;
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary " href="#" mce_href="#" title="缴费" onclick="pay(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							return e;
						}
					},
					{
						field : 'buildingName', // 列字段名
						title : '楼宇名称'
					},
					{
						field : 'roomCode', // 列字段名
						title : '房间编号' // 列标题
					},
					{
						field : 'unit',
						title : '单元'
					},
					{
						field : 'floor',
						title : '楼层'
					},
					{
						field : 'roomType',
						title : '房屋类型'
					},
					{
						field : 'state',
						title : '状态',
						formatter: function (value, row, index) {
							if(value=='0'){
								return '未付清'
							}
							if(value=='1'){
								return '已付清'
							}
						}
					},
					{
						field : 'buildingArea',
						title : '建筑面积',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return value.toFixed(2);
							}
						}
					},
					{
						field : 'yingpay',
						title : '应收物业费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}
						}
					},
					{
						field : 'payed',
						title : '已收物业费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'unpay',
						title : '未收物业费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},{
						field : 'd',
						title : '缴费周期',//标题  可不加
						formatter: function (value, row, index) {
							return row.startDate.substr(0,10)+'-'+row.endDate.substr(0,10);
						},
						width:'16%'
					}
					 ],
				onDblClickRow: function (item, $element) {
					contract=item;
					contract.types='wuye';
					var perContent = layer.open({
						type : 2,
						title : '详情',
						shadeClose : false, // 点击遮罩关闭层
						area : [ '820px', '100%' ],
						content : '/wuye/fangbenManage/info?buildingId='+item.buildingId+'&code='+item.roomCode+'&idCard='+item.idCard+'&types=wuye' // iframe的url
					});
				},onLoadSuccess:function (item) {
				}
			});
}
function reLoad() {
    if(!isLoad){
		$('#exampleTable').bootstrapTable("refreshOptions",
			{
				url : prefix + "/detailList"
			});
        load();
        isLoad=true;
    }else {
        $('#exampleTable').bootstrapTable('refresh');
    }
}
function pay(id) {
	layer.open({
		type: 2,
		title: '缴费',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['950px', '460px'],
		content: prefix + '/shou/' + id, // iframe的url
		end:function () {
			reLoad();
		}
	});
}

function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/realty/building/tree/2",
		success : function(tree) {
			loadTree(tree);
		}
	});
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search"]
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function(e, data) {
	if(data.node){
		var node=data.node;
		if (node.state.type == '2') {
			buildingId=data.selected[0];
			deptId=node.state.deptId;
            reLoad();
		}else{
			clear();
		}
	}
});
function clear() {
	buildingId='';
}
function c() {
	$("#roomCode").val('');
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