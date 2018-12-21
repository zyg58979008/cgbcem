var prefix = "/business/shop"
$(function() {
	getTreeData();
    load('');
});
var buildingId='';//楼宇ID
var unit=1;//单元数
var mergeIds = new Array();//合并的房屋
var deptId='';//项目ID
var roomType='';
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/list",
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
				//pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
				fixedColumns: true,
				fixedNumber:0,
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
				//pageSize : 30, // 如果设置了分页，每页数据条数
				//pageNumber : 1, // 如果设置了分布，首页页码
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        //limit : params.limit,
                        //offset : params.offset,
                        buildingId : buildingId,
                    };
                },
				columns : [
					{
						checkbox : true
					},
                    {
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            return index + 1;
                        }
                    },
					{
						field : 'code', // 列字段名
						title : '商铺编号' // 列标题
					},
					/*{
						field : 'unit',
						title : '单元'
					},*/
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
						title : '计租面积',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return value.toFixed(2);
							}
						}
					},
					{
						field : 'floorArea',
						title : '实测面积',
						formatter:function (value) {
							if(!value||value==''){
								return '0.00';
							}else{
								return value.toFixed(2);
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit "></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm ' + s_split_h + '" href="#" title="拆分"  mce_href="#" onclick="resetPwd(\''
								+ row.id
								+ '\')"><i class="fa fa-key"></i></a> ';
							return e + d + f;
						}
					} ],onLoadSuccess:function (item) {
				$.ajax({
					type : "POST",
					data : {
						buildingId : buildingId
					},
					url : prefix+"/getArea",
					success : function(data) {
						if(data&&data!=''){
							$("#buildingAreaA").text(formatData(data.buildingAreaA));
							$("#floorAreaA").text(formatData(data.floorAreaA));
							$("#buildingAreaB").text(formatData(data.buildingAreaB));
							$("#floorAreaB").text(formatData(data.floorAreaB));
						}else {
							$("#buildingAreaA").text("0");
							$("#floorAreaA").text("0");
							$("#buildingAreaB").text("0");
							$("#floorAreaB").text("0");
						}
					}
				});
			}
			});
}
function reLoad() {
	var opt = {
		code: $('#code').val()
	}
	$('#exampleTable').bootstrapTable('refresh',opt);
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
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		var ids = new Array();
		ids[0]=id;
		$.ajax({
			url : prefix + '/remove',
			type : "post",
			data : {
				'ids' : id
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
function edit(id) {
	layer.open({
		type : 2,
		title : '修改房间',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function batchRemove() {
    if(buildingId==''){
        layer.msg('请先选择楼宇');
        return;
    }
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}
function merge() {
	if(buildingId==''){
		layer.msg('请先选择楼宇');
		return;
	}
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要合并的房屋");
		return;
	}
	var u='';
	var t='';
	var isOpne=false;
	$.each(rows, function(i, row) {
		/*if(u==''){
			u=row['unit'];
		}else {
			if(u!=row['unit']){
				layer.msg("所选房屋不在同一单元，请重新选择");
				mergeIds[i]=new Array();
				isOpne=true;
				return;
			}
		}*/
		if(t==''){
			t=row['roomType'];
		}else {
			if(t!=row['roomType']){
				layer.msg("所选房屋不是同一类型，请重新选择");
				mergeIds[i]=new Array();
				isOpne=true;
				return;
			}
		}
		//u=row['unit'];
		t=row['roomType'];
		mergeIds[i] = row['id'];
	});

	if(isOpne){
		return;
	}
	unit=rows[0].unit;
	roomType=rows[0].roomType;
	layer.open({
		type : 2,
		title : '合并房间',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/merge' // iframe的url
	});
}
function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/business/building/tree/2",
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
			unit=parseInt(node.state.unit);
			$('#exampleTable').bootstrapTable('refresh');
		}else{

			if(data.selected[0]=='-1'){
				buildingId=0;
			}else{
				buildingId=data.selected[0];
			}
			$('#exampleTable').bootstrapTable('refresh');
			clear();
		}
	}
});
function download() {
	var url = prefix + "/exportXls";
	//更改form的action
	$("#importForm").attr("action", url);
	//触发submit事件，提交表单
	$("#importForm").submit();
}

function clear() {
	buildingId='';
	unit=0;
	mergeIds = new Array();
	deptId='';
	roomType='';
}