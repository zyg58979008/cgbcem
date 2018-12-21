var prefix = "/equipment/equipmentCount"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list?delFlag=0", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 15, // 如果设置了分页，每页数据条数
                pageList: [15, 25, 50, 100],
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset
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
                columns: [
                    {
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            var pageSize=$('#exampleTable').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                            var pageNumber=$('#exampleTable').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                            return pageSize * (pageNumber - 1) + index + 1;    //返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                        }
                    },

                    {
                        field: 'createDate',
                        title: '盘库日期',
                        formatter: function (value, row, index) {
                            return value.substr(0, 10)
                        }

                    },
                    {
                        field: 'deptName',
                        title: '盘库网点'
                    },
                    {
                        field: 'createByName',
                        title: '盘库人员'
                    },
                    {
                        field: 'id',
                        title: '单据编号'
                    },
                    {
                        field: 'remarks',
                        title: '备注'
                    }, {
                        field: 'updateDate',
                        title: '创建日期',
                        formatter: function (value, row, index) {
                            return value.substr(0, 10)
                        }

                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-success btn-sm ' + s_info_h + '" href="#" mce_href="#" title="盘库" onclick="info(\''
                                + row.id
                                + '\')"><i class="fa fa-edit" style="width: 10px"></i></a> ';
                            return e;
                        }
                    }]
            });
}
function reLoad() {
    var startDate='';
    if($('#startDate').val()){
        startDate= $('#startDate').val()+' 00:00:00';
    }
    var endDate= '';
    if($('#endDate').val()){
        endDate= $('#endDate').val()+' 23:59:59';
    }
    var options = $('#exampleTable').bootstrapTable('refresh', {
        query:
        {
            startDate:startDate,
            endDate:endDate
        }
    });
}
var index;
function info(id) {
    index=layer.open({
        type: 2,
        title: '详情',
        maxmin: true,
        btn : ['保存'],
        shadeClose: false, // 点击遮罩关闭层
        area: ['860px', '620px'],
        content: prefix + '/info/' + id,// iframe的url
        yes:function(index, layero){
            var body = layer.getChildFrame('body', index);
            var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：
            iframeWin.save();
        }
    });
    layer.full(index);
}
function close() {
    layer.close(index);
    $('#exampleTable').bootstrapTable('refresh',{})
}