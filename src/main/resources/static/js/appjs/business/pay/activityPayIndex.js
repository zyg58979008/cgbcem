var prefix = "/business/activity"
$(function() {
    load();
	selectLoad();
});
var buildingId='0';
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/payListView",
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
                //pageSize : 10, // 如果设置了分页，每页数据条数
                //pageNumber : 1, // 如果设置了分布，首页页码
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
						code:$("#code").val(),
						brand:$("#brand").val(),
						startDate:$("#startDate").val(),
						endDate:$("#endDate").val(),
						type:$("#type1").val()
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
                        title: '单据编号',//标题  可不加
                        field:'code'
                    },
					{
						field : 'activityName', // 列字段名
						title : '活动名称' // 列标题
					},
					{
						field : 'brand',
						title : '品牌'
					},
					{
						field : 'state',
						title : '状态',//标题  可不加
						formatter: function (value, row, index) {
							if(value=='0'){
								return '正常';
							}
							if(value=='1'){
								return '冲销';
							}
							if(value=='2'){
								return '已冲销';
							}
						}
					},
					{
						field : 'typeName',
						title : '收款方式'
					},
					{
						field : 'sTypeName',
						title : '交来'
					},
					{
						field : 'price',
						title : '金额',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return formatData(value.toFixed(2));
							}
						}
					},
					{
						field : 'receiptBy',
						title : '收款人'
					},
					{
						field : 'payDate',
						title : '缴费日期',
						formatter: function (value, row, index) {
							return value.substr(0,10);
						}
					},
					{
						field : 'print',
						title : '打印次数'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm ' + s_print_h + '" href="#" mce_href="#" title="打印" onclick="print(\''
								+ row.id
								+ '\')"><i class="fa fa-print "></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_chongxiao_h + '" href="#" title="冲销"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							if(row.state=='0'){
								return e + d;
							}
							if(row.state=='1'||row.state=='2'){
								return e;
							}
						}
					} ]
			});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
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
function print(id) {
	layer.open({
		type :2,
		title : '活动费打印',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '460px' ],
		content : prefix+'/print/' + id,
		end: function () {
			reLoad()
		}
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
		type: "GET",
		url: "/business/building/tree/2",
		success: function (tree) {
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
		$('#exampleTable').bootstrapTable('refresh');
	}
});
function c() {
	buildingId="0";
	$("#startDate").val("");
	$("#endDate").val("");
}
function selectLoad() {
	var html = "";
	$.ajax({
		url : '/business/contract/shopShouType/',
		success : function(data) {
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#type").append(html);
			$("#type").chosen({
				maxHeight : 200
			});
			//点击事件
			$('#type').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$("#type1").val(params.selected);
				$("#typeName").val(e.target[params.selected].label);
			});
		}
	});
}