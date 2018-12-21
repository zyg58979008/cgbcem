var prefix = "/wuye/qunuanManage"
var id=$("#qunuanfeiId").val();
var isLoad=false;
$(function() {
	getTreeData();
	var height=$(document.body).height();
	height=height-38-75;
	$("#exampleTable").attr("data-height",height);
	load();
});
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
				exportDataType: "basic",
				exportOptions:{
					ignoreColumn: 0  //忽略某一列的索引
				},
				fixedColumns: true,
				fixedNumber:3,
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
						roomCode:$("#roomCode").val(),
						qunuanfeiId:id,
						type:$("#type").val(),
						state:$("#state").val(),
						buildingId:buildingId
                    };
                },
				columns : [
					{
						title: '序号',//标题  可不加
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
							var e = '<a  class="btn btn-primary " href="#" mce_href="#" title="编辑" onclick="edit(\''+ row.id+ '\',\''+ row.qunuanPayed+ '\',\''+ row.tingnuanPay+ '\')"><i class="fa fa-edit "></i></a> ';
							var d = '<a class="btn btn-warning " href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\',\''+ row.qunuanPayed+ '\',\''+ row.tingnuanPay+ '\')"><i class="fa fa-remove"></i></a> ';
							return e + d;
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
						field : 'roomTypeName',
						title : '房屋类型'
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
						field : 'qunuanYing',
						title : '应收取暖费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'qunuanPayed',
						title : '已收取暖费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'qunuanUnpay',
						title : '未收取暖费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'tingnuanYing',
						title : '应收停暖费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'tingnuanPay',
						title : '已收停暖费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					},
					{
						field : 'tingnuanUnpay',
						title : '未收停暖费',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}						}
					} ],
				onDblClickRow: function (item, $element) {
					contract=item;
					contract.types='qunuan';
					var perContent = layer.open({
						type : 2,
						title : '详情',
						shadeClose : false, // 点击遮罩关闭层
						area : [ '820px', '100%' ],
						content : '/wuye/fangbenManage/info?buildingId='+item.buildingId+'&code='+item.roomCode+'&idCard='+item.idCard+'&types=qunuan' // iframe的url
					});
				},onLoadSuccess:function (item) {
				$.ajax({
					type : "POST",
					data : {
						"buildingId" : buildingId,
						"id": id
					},
					url : "/wuye/qunuanManage/getSum",
					success : function(data) {
						if(data&&data!=''){
							$("#ying").text(data.totalNum);
							$("#yi").text(data.payedNum);
							$("#wei").text(data.unpayNum);
							if(parseFloat(data.totalNum)==0||parseFloat(data.payedNum)==0){
								$("#l").text('0%');
							}else {
								var a=(parseFloat(data.payedNum)*100/parseFloat(data.totalNum)).toFixed(2);
								$("#l").text(a+"%");
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
	$.ajax({
		type : "POST",
		data : {
			buildingId : buildingId,
			roomCode:$("#roomCode").val(),
			type:$("#type").val(),
			state:$("#state").val(),
			id: id
		},
		url : "/wuye/qunuanManage/getSumMoney",
		success : function(data) {
			if(data){
				var data = {'i': '','id':'','buildingName':'','roomCode':'',
					'unit': '','floor':'','roomType':'','buildingArea':'','state':'合计','qunuanYing':data.qunuanYing,'qunuanPayed':data.qunuanPayed,'qunuanUnpay':data.qunuanUnpay,
					'tingnuanYing':data.tingnuanYing,'tingnuanPay':data.tingnuanPay,'tingnuanUnpay':data.tingnuanUnpay,'缴费周期':''};
				$('#exampleTable').bootstrapTable('append', data);
				$('#exampleTable').tableExport({ type: 'excel', escape: 'false' ,fileName:'取暖费明细',ignoreColumn:[1]})
				$('#exampleTable').bootstrapTable('remove', {field: 'state', values: ['合计']});
			}
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
function remove(id,qunuan,tingnuan) {
	if(qunuan>0||tingnuan>0){
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
function edit(id,qunuan,tingnuan) {
	if(qunuan>0||tingnuan>0){
		layer.msg("已缴费不能修改");
		return;
	}
	layer.open({
		type : 2,
		title : '修改取暖费',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '280px' ],
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
function clear() {
	buildingId='';
}
function c() {
	$("#roomCode").val('');
	$("#type").val('');
	$("#state").val('');
}