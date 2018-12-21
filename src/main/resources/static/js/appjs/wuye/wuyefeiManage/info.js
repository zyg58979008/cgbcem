var prefix = "/wuye/wuyefeiManage"
var id=$("#wuyefeiId").val();
var isLoad=false;
$(function() {
	getTreeData();
	var height=$(document.body).height();
	height=height-38-75;
	$("#exampleTable").attr("data-height",height);
	load();
});
var moneyYing;
var moneyPayed;
var moneyUnpay;
var contract={};
var buildingId='';//楼宇ID
var deptId='';//项目ID
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
				exportDataType: "basic",
				exportOptions:{
					ignoreColumn: 0  //忽略某一列的索引
				},
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
						wuyefeiId:id,
						roomCode:$("#roomCode").val(),
						buildingId:buildingId
                    };
                },
				columns : [
					{
						title: '序号',//标题  可不加
						field:'i',
						formatter: function (value, row, index) {
							if(row.state=='合计'){
								return '';
							}else {
								return index + 1;
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary " href="#" mce_href="#" title="编辑" onclick="edit(\''+ row.id+ '\',\''+ row.payed+ '\')"><i class="fa fa-edit "></i></a> ';
							var d = '<a class="btn btn-warning " href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\',\''+ row.payed+ '\')"><i class="fa fa-remove"></i></a> ';
							return e + d;
						}
					},
					{
						field : 'buildingName', // 列字段名
						title : '楼宇名称'
					},
					{
						field : 'roomCode', // 列字段名
						title : '房间编号'
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
						title : '房屋类型',
						formatter:function (value, row, index) {
							if(row.state=='合计'){
								return '';
							}else {
								return value;
							}
						}
					},
					{
						field : 'buildingArea',
						title : '建筑面积',
						formatter:function (value, row, index) {
							if(row.state=='合计'){
								return '';
							}else {
								if(!value||value==''){
									return '0.00';
								}else{
									return value.toFixed(2);
								}
							}
						}
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
							if(value=='合计'){
								return '合计';
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
							}
						}
					},{
						field : 'd',
						title : '缴费周期',//标题  可不加
						formatter: function (value, row, index) {
							if(!row.startDate||row.startDate==''){
								return ''
							}
							return row.startDate.substr(0,10)+'-'+row.endDate.substr(0,10);
						}
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
					$.ajax({
						type : "POST",
						data : {
							"buildingId" : buildingId,
							"id": id,
							"roomCode":$("#roomCode").val()
						},
						url : "/wuye/wuyefeiManage/getSum",
						success : function(data) {
							if(data&&data!=''){
								moneyYing=data.moneyYing;
								moneyPayed=data.moneyPayed;
								moneyUnpay=data.moneyUnpay;
								$("#ying").text(formatData(data.moneyYing.toFixed(2)));
								$("#yi").text(formatData(data.moneyPayed.toFixed(2)));
								$("#wei").text(formatData(data.moneyUnpay.toFixed(2)));
								if(parseFloat(data.moneyYing)==0||parseFloat(data.moneyPayed)==0){
									$("#l").text('0%');
								}else {
									$("#l").text((parseFloat(data.moneyPayed)*100/parseFloat(data.moneyYing)).toFixed(2)+"%");
								}
							}else {
                                $("#ying").text('0');
                                $("#yi").text('0');
                                $("#wei").text('0');
								$("#l").text('0%');
							}
						}
					});
				}
			});
}
function e() {
	var data = {'i': '','id':'','buildingName':'','roomCode':'',
		'unit': '','floor':'','roomType':'','buildingArea':'','state':'合计','yingpay':moneyYing,'payed':moneyPayed,'unpay':moneyUnpay,'缴费周期':''};
	$('#exampleTable').bootstrapTable('append', data);
	$('#exampleTable').tableExport({ type: 'excel', escape: 'false' ,fileName:'物业费明细',ignoreColumn:[1]})
	$('#exampleTable').bootstrapTable('remove', {field: 'state', values: ['合计']});
}
function reLoad() {
	if(!isLoad){
		$('#exampleTable').bootstrapTable("refreshOptions",
			{
				url : prefix + "/detailList",
			});
		load();
		isLoad=true;
	}else {
		$('#exampleTable').bootstrapTable('refresh');
	}
}
function remove(id,payed) {
	if(payed>0){
		layer.msg("已缴费不能删除");
		return;
	}
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + '/removeDetail',
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}
function edit(id,payed) {
	if(payed>0){
		layer.msg("已缴费不能编辑");
		return;
	}
	layer.open({
		type : 2,
		title : '修改物业费',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '360px' ],
		content : prefix + '/editDetail/' + id // iframe的url
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
			if(data.selected[0]=='-1'){
				buildingId=0;
			}
			reLoad();
			clear();
		}
	}
});
function download() {
	var url = prefix + "/download";
	//更改form的action
	$("#importForm").attr("action", url);
	//触发submit事件，提交表单
	$("#importForm").submit();
}
function clear() {
	buildingId='';
}
function c() {
	$("#roomCode").val('');
}