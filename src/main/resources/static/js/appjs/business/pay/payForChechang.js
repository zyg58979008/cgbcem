var prefix = "/business/contract"
$(function() {
	getTreeData();
    load();
});
var buildingId='0';
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/payListViewForChechang",
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
                pageSize : 30, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
						buildingId : buildingId,
						code:$("#code").val(),
						brand:$("#brand").val(),
						startDate:$("#startDate").val(),
						endDate:$("#endDate").val()
                    };
                },
				columns : [
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm ' + s_print_h + '" href="#" mce_href="#" title="打印" onclick="print(\''+ row.id+ '\',\''+ row.state+ '\')"><i class="fa fa-print "></i></a> ';
								return e;
						}
					},
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
						field : 'buildingName', // 列字段名
						title : '楼宇名称' // 列标题
					},
					{
						field : 'brand',
						title : '品牌'
					},
					{
						field : 'countStartDate',
						title : '计费起日期',
						formatter: function (value, row, index) {
							if(value){
								return value.substr(0,10);
							}
							return '';
						}
					},
					{
						field : 'countEndDate',
						title : '计费止日期',
						formatter: function (value, row, index) {
							if(value){
								return value.substr(0,10);
							}
							return '';
						}
					},
					{
						field : 'state',
						title : '状态',//标题  可不加
						formatter: function (value, row, index) {
							if(value=='0'||value=='3'){
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
							if(value){
								return value.substr(0,10);
							}
							return '';
						}
					},
					{
						field : 'print',
						title : '打印次数'
					} ]
			});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function print(id,state) {
	var url;
	if(state=='0'){
		url=prefix+'/printForChechang/' + id;
	}else {
		url=prefix+'/printForChechangBack/' + id;
	}
	layer.open({
		type :2,
		title : '合同收款打印',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '460px' ],
		content : url,
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