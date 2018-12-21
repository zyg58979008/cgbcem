$().ready(function() {
	validateRule();
	laydate({
		elem : '#createDate'
	});
	load();
});
var prefix = "/stock/product"
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function load() {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/list", // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : true, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 10, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				//search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit: params.limit,
						offset:params.offset
						// name:$('#searchName').val(),
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

                    {
                        field : 'brand',
                        title : '设备品牌',
                    },{
                        field : 'name',
                        title : '设备名称',
                    },
                    {
                        field : 'model',
                        title : '设备型号'
                    },
                    {
                        field : 'updateBy',
                        title : '数量'
                    },
                    {
                        field : 'unit',
                        title : '设备单位'
                    },
                    {
                        field : 'category',
                        title : '设备分类'
                    },
                    {
                        field : 'code',
                        title : '设备条码'
                    },
                    {
                        field : 'durableYears',
                        title : '使用年限'
                    },
                    {
                        field : 'remarks',
                        title : '操作',
                        formatter : function(value, row, index) {
                            var d = '<a class="btn btn-danger btn-sm " href="#" title="删除"  mce_href="#" ><i class="fa fa-remove"></i></a> ';
                            return d ;
                        }
                    } ]
            });
}
$('#addData').click(function(){
	$('#exampleTable').bootstrapTable('selectPage', 1); //Jump to the first page
	var data = {id: '', name: '',brand:'', num: '', durableYears: '', code: '', category: '',model:'', unit: '', price: '', remarks: ''}; //define a new row data，certainly it's empty

	$('#exampleTable').bootstrapTable('prepend', data); //the method of prepend must defined all fields，but append needn't
	//$('#dataTable').bootstrapTable('append',data);
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/stock/inbound/save1",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入"
			}
		}
	})
}