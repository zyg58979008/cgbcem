var prefix = "/realty/contract"
var isLoad=false;
$(function() {
	getTreeData();
	loadShouType();
	var height=$(document.body).height();
	height=height-140;
	$("#exampleTable").attr("data-height",height);
	load();
});
var buildingId='0';
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
				fixedColumns: true,
				fixedNumber:5,
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
						buildingId : buildingId,
						code:$("#code").val(),
						roomCode:$('#roomCode').val(),
						name:$('#name').val(),
						type:$('#shouType1').val(),
						startDate:$("#startDate").val(),
						endDate:$("#endDate").val()
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
							var e = '<a  class="btn btn-primary btn-sm ' + s_print_h + '" href="#" mce_href="#" title="打印" onclick="print(\''+ row.id+ '\',\''+ row.state+ '\')"><i class="fa fa-print "></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_chongxiao_h + '" href="#" title="冲销"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							if(row.state=='0'||row.state=='3'){
								return e + d;
							}
							if(row.state=='1'||row.state=='2'){
								return '';
							}
						}
					},
                    {
                        title: '单据编号',//标题  可不加
                        field:'code'
                    },
					{
						field : 'buildingName', // 列字段名
						title : '楼宇名称' // 列标题
					},
					{
						field : 'roomCode',
						title : '房屋编码'
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
						field : 'name',
						title : '姓名'
					},
					{
						field : 'state',
						title : '状态',//标题  可不加
						formatter: function (value, row, index) {
							if(value=='0'){
								return '收款';
							}
							if(value=='1'){
								return '收款冲销';
							}
							if(value=='2'){
								return '收款已冲销';
							}
							if(value=='3'){
								return '付款';
							}
							if(value=='4'){
								return '付款冲销';
							}
							if(value=='5'){
								return '付款已冲销';
							}
						}
					},
					{
						field : 'typeName',
						title : '方式'
					},
					{
						field : 'sTypeName',
						title : '类型'
					},
					{
						field : 'price',
						title : '金额',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return value.toFixed(2);
							}						}
					},
					{
						field : 'receiptBy',
						title : '经办人'
					},
					{
						field : 'createDate',
						title : '操作日期',
						formatter: function (value, row, index) {
							if(!value||value==''){
								return '';
							}else{
								return value.substr(0,10);
							}
						}
					},
					{
						field : 'print',
						title : '打印次数'
					} ],onLoadSuccess:function (item) {
                $.ajax({
                    type : "POST",
                    data : {
                        buildingId : buildingId,
                        code:$("#code").val(),
                        roomCode:$('#roomCode').val(),
                        name:$('#name').val(),
                        type:$('#shouType1').val(),
                        startDate:$("#startDate").val(),
                        endDate:$("#endDate").val()
                    },
                    url : prefix+"/getPaySum",
                    success : function(data) {
                        if(data&&data!=''){
                            $("#xianjin").text(formatData(data.xianjin));
                            $("#pos").text(formatData(data.pos));
                            $("#zhuanzhang").text(formatData(data.zhuanzhang));
                            $("#heji").text(formatData(data.xianjin+data.pos+data.zhuanzhang));
                        }else {
                            $("#xianjin").text("0");
                            $("#pos").text("0");
                            $("#zhuanzhang").text("0");
                            $("#heji").text("0");
						}
                    }
                });
            }
			});
}
function reLoad() {
	if(!isLoad){
		$('#exampleTable').bootstrapTable("refreshOptions",
			{
				url : prefix + "/payList",
			});
		load();
		isLoad=true;
	}else {
		$('#exampleTable').bootstrapTable('refresh');
	}
}
function print(id,state) {
	var url;
	if(state=='0'){
		url=prefix+'/print/' + id;
	}else {
		url=prefix+'/printBack/' + id;
	}
    layer.open({
        type :2,
        title : '房款打印',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '950px', '460px' ],
        content :url,
        end: function () {
            reLoad()
        }
    });
}
function add() {
	if(buildingId==''){
		layer.msg('请先选择楼宇');
        return;
	}
	// iframe层
	layer.open({
		type : 2,
		title : '增加房间',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add'
	});
}
function remove(id) {
	layer.confirm('确定要冲销选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + '/chongxiao',
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
		if(data.selected[0]=='-1'){
			buildingId='0';
		}else{
			buildingId=data.selected[0];
		}
		reLoad()
	}
});
function c() {
	buildingId="0";
	$("#startDate").val("");
	$("#endDate").val("");
	$("#code").val("");
	$("#endDate").val("");
	$("#endDate").val("");
}
function loadShouType(){
	var html = "";
	$.ajax({
		url : '/common/dict/list/dichan_shou_type',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#shouType").append(html);
			$("#shouType").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#shouType').on('change', function(e, params) {
				//console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$("#shouType1").val(params.selected);
				$("#shouTypeName").val(e.target[params.selected].label);
			});
		}
	});
}