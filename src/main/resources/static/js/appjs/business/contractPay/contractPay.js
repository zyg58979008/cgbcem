
var prefix = "/business/contract"
$(function() {
	load();
	getTreeData();
});
var buildingId = '';//楼宇ID
var deptId = '';//项目ID
var contract={};
function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/listPay", // 服务器数据的加载地址
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
				// pageSize : 10, // 如果设置了分页，每页数据条数
				// pageNumber : 1, // 如果设置了分布，首页页码
				fixedColumns: true,
				fixedNumber:3,
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
				queryParams: function (params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit: params.limit,
						offset: params.offset,
						delFlag:'0',
						contractCode:$('#contractCode').val(),
						brand:$('#brand').val(),
						contractor:$('#contractor').val(),
						buildingId:buildingId
						// username:$('#searchName').val()
					};
				},
				// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
				// queryParamsType = 'limit' ,返回参数必须包含
				// limit, offset, search, sort, order 否则, 需要包含:
				// pageSize, pageNumber, searchText, sortName,
				// sortOrder.
				// 返回false将会终止请求
				columns : [
					[{
						title : '操作',
						field : 'id',

						rowspan: 2,
						align:"center",

						switch:true,
						formatter: function (value, row, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_pay_h + '" href="#" mce_href="#" title="缴费" onclick="pay(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							return e;
						}
					},{
						field : 'brand',
						title : '品牌',

						rowspan: 2,
						align:"center",
						width:"90px"
					},
						/*{
						 field : 'a1',
						 title : '项目',

						 rowspan: 2,
						 align:"center"
						 },
						 {
						 field : 'floor',
						 title : '楼层',

						 rowspan: 2,
						 align:"center"
						 },
						 {
						 field : 'shopCode',
						 title : '商铺编码',

						 rowspan: 2,
						 align:"center",
						 width:"180px"
						 },*/
						{
							field : 'contractor',
							title : '签约人',

							rowspan: 2,
							align:"center"
						},
						{
							field : 'rentArea',
							title : '计租面积',

							rowspan: 2,
							align:"center"
						},
						/*{
						 field : 'contractTrueArea',
						 title : '合同实测面积',

						 rowspan: 2,
						 align:"center"
						 },
						 {
						 field : 'contractRentArea',
						 title : '合同计租面积（含20%公摊）',

						 rowspan: 2,
						 align:"center"
						 },*/


						/*{
						 field : 'shopType',
						 title : '商铺经营类别',

						 rowspan: 2,
						 align:"center"
						 },*/
						{
							field : 'contractPayTotal',
							title : '合同期应收租金',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						/*{
						 field : 'unitPrice',
						 title : '租金单价',

						 rowspan: 2,
						 align:"center"
						 },*/
						{
							field : 'payType',
							title : '收款方式',

							rowspan: 2,
							align:"center"
						},
						{
							field : 'countStartDate',
							title : '计费起',

							rowspan: 2,
							align:"center",
							formatter : function(value, row, index) {
								if(value){
									return value.substr(0,10)
								}else{
									return ''
								}
							}
						},
						{
							field : 'countEndDate',
							title : '计费止',

							rowspan: 2,
							align:"center",
							formatter : function(value, row, index) {
								if(value){
									return value.substr(0,10)
								}else{
									return ''
								}
							}
						},
						{
							field : 'contractCode',
							title : '合同编号',

							rowspan: 2,
							align:"center",
							valign:"middle",
							width:"200px",class:'add-at'
						},
						{
							field : 'youhui',
							title : '优惠政策',

							rowspan: 2,
							align:"center"
						},
						{
							title : '收入分类（元/平）',
							colspan: 9,

							align:"center"
						},
						{
							field : 'contractStartDate',
							title : '合同起',

							rowspan: 2,
							align:"center",
							formatter : function(value, row, index) {
								if(value){
									return value.substr(0,10)
								}else{
									return ''
								}

							}
						},
						{
							field : 'contractEndDate',
							title : '合同止',

							rowspan: 2,
							align:"center",
							formatter : function(value, row, index) {
								if(value){
									return value.substr(0,10)
								}else{
									return ''
								}
							}
						},

						/*{
						 field : 'amortizeMonths',
						 title : '摊销月数',

						 rowspan: 2,
						 align:"center"
						 },*/
						{
							field : 'receivableLvyue',
							title : '应收履约保证金',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'receivableZhiliang',
							title : '应收质量保证金',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'receivableFuwu',
							title : '应收服务保证金',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'receivableZhuangxiu',
							title : '应收装修押金',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'receivedLvyue',
							title : '已收履约保证金',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'receivedZhiliang',
							title : '已收质量保证金',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'receivedFuwu',
							title : '已收服务保证金',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'receivedZhuangxiu',
							title : '已收装修押金',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'discrepancy',
							title : '差额',

							rowspan: 2,
							align:"center",
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						/*{
						 field : 'phone',
						 title : '电话',

						 rowspan: 2,
						 align:"center"
						 },*/
						{
							field : 'idCard',
							title : '身份证号',

							rowspan: 2,
							align:"center",class:'add-at'
						},
						/*{
						 field : 'kaidanCode',
						 title : '开单编码',

						 rowspan: 2,
						 align:"center"
						 },
						 {
						 field : 'kaidanName',
						 title : '开单名称',

						 rowspan: 2,
						 align:"center"
						 },*/
						{
							field : 'payee',
							title : '收款人',

							rowspan: 2,
							align:"center"
						},
						/*{
						 field : 'account',
						 title : '账户',

						 rowspan: 2,
						 align:"center",
						 valign:"middle"
						 },
						 {
						 field : 'bank',
						 title : '开户行',

						 rowspan: 2,
						 align:"center"
						 },*/
						{
							field : 'contractDate',
							title : '签约日期',

							rowspan: 2,
							align:"center"
							/*formatter : function(value, row, index) {
							 return value.substr(0,10)
							 }*/
						},
						/*{
						 field : 'zulinbiao',
						 title : '租赁申请表',

						 rowspan: 2,
						 align:"center"
						 },
						 {
						 field : 'copyIdCard',
						 title : '身份证复印件',

						 rowspan: 2,
						 align:"center"
						 },
						 {
						 field : 'copyBankCard',
						 title : '银行卡复印件',

						 rowspan: 2,
						 align:"center"
						 },
						 {
						 field : 'tuzhi',
						 title : '图纸',

						 rowspan: 2,
						 align:"center"
						 },*/

						{
							field : 'remarks',
							title : '备注',

							rowspan: 2,
							align:"center"
						}/*,
					 {
					 field : 'createBy',
					 title : '创建者'
					 },
					 {
					 field : 'createDate',
					 title : '创建时间'
					 },
					 {
					 field : 'updateBy',
					 title : '更新者'
					 },
					 {
					 field : 'updateDate',
					 title : '更新时间'
					 }*/],[
						{
							field : 'fenleiZujin',
							title : '应收租金（元）',
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'fenleiZujinReceived',
							title : '已收租金（元）',
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'fenleiZujinUnreceived',
							title : '未收租金（元）',
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'fenleiWuye',
							title : '应收物业费（元）',
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'fenleiWuyeReceived',
							title : '已收物业费（元）',
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'fenleiWuyeUnreceived',
							title : '未收物业费（元）',
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'fenleiGuanli',
							title : '应收管理费（元）',
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'fenleiGuanliReceived',
							title : '已收管理费（元）',
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						},
						{
							field : 'fenleiGuanliUnreceived',
							title : '未收管理费（元）',
							formatter:function (value) {
								if(!value||value==''){
									return '0.00';
								}else{
									return formatData(value.toFixed(2));
								}
							}
						}
					]
				],
				onDblClickRow: function (item, $element) {
					contract=item;
					var perContent = layer.open({
						type : 2,
						title : '合同详情',
						shadeClose : false, // 点击遮罩关闭层
						area : [ '820px', '500px' ],
						//content : prefix + '/info?idCard='+item.idCard+'&code='+item.shopCode // iframe的url
						content : prefix + '/contractDetail?contractCode='+item.contractCode // iframe的url
					});
					layer.full(perContent);
				}, onLoadSuccess:function(){
				$("td.add-at").attr("data-tableexport-msonumberformat","\\@");
			}
			});
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id// iframe的url
	});
}
function pay(id) {
	layer.open({
		type: 2,
		title: '缴费',
		maxmin: true,
		shadeClose: false, // 点击遮罩关闭层
		area: ['950px', '520px'],
		content: prefix + '/shou/' + id, // iframe的url
		end:function () {
			reLoad();
		}
	});
}
function renew(id) {
	layer.open({
		type : 2,
		title : '合同续签',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/renew'// iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
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
		'core': {
			'data': tree
		},
		"plugins": ["search"]
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function (e, data) {
	if (data.selected == -1) {
		buildingId = '0';
		$('#exampleTable').bootstrapTable('refresh');
		clear();
	} else {
		if (data.node) {
			var node = data.node;
			if (node.state.type == '2') {
				buildingId = data.selected[0];
				deptId = node.state.deptId;
				var opt = {
					query : {
						buildingId : data.selected[0],
					}
				}
				$('#exampleTable').bootstrapTable('refresh',opt);
			} else {
				clear();
				$('#exampleTable').bootstrapTable('refresh');
			}
		}
	}
});
function resetPwd(id) {
}
function download() {
	var url = prefix + "/download";
	//更改form的action
	$("#importForm").attr("action", url);
	//触发submit事件，提交表单
	$("#importForm").submit();
}
function batchRemove() {
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
	}, function() {

	});
}
function reLoad() {
	var opt = {
		query : {
			contractor : $('#contractor').val(),
			contractStartDate : $('#contractStartDate').val(),
			contractEndDate : $('#contractEndDate').val(),
			brand : $('#brand').val()
		}
	}
	$('#exampleTable').bootstrapTable('refresh',opt);
}
function clear() {
	buildingId = '';
	deptId = '';
}
function download() {
	var url = prefix + "/downloadHistory";
	//更改form的action
	$("#importForm").attr("action", url);
	//触发submit事件，提交表单
	$("#importForm").submit();
}