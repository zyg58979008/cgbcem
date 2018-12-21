prefix = "/equipment/report"
var month;
$(function () {
    month = getYearMonth();
    dabing1();
    dabing2();
    dabing3();
    dabing4();
    zhutu1();
    draw();
    monthTotal();
    xianchengTotal();
});
function reLoad(){
    dabing1();
    dabing2();
    dabing3();
    dabing4();
}
function getNumForDaBing(type){
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix + "/getNum",
        data : {
            type : type,
            createDate: $("#Date").val()
        },
        success : function(data) {
            if(data == null){
                dataNum = null;
            }else{
                for (var i = 0; i < data.length; i++) {
                    dataNum.push({
                        name: data[i].categoryName,
                        value: data[i].num
                    });
                }
            }
        }
    });
    return dataNum;
}
function dabing2() {
    var dataB = getNumForDaBing(3);
    var pieChart = echarts.init(document.getElementById("b"));
    var pieoption = {
        title : {
            text: '设备维修统计图',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        /* legend: {
         orient : 'vertical',
         x : 'left',
         data:['打印机','密码键盘','UPS机头','五合一','发电机','影像仪','业务终端']
         },*/
        calculable : true,
        series : [
            {
                name:'数量',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:dataB
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);

}
function dabing3() {
    var dataC = getNumForDaBing(2);
    var pieChart = echarts.init(document.getElementById("c"));
    var pieoption = {
        title : {
            text: '设备报废统计图',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        calculable : true,
        series : [
            {
                name:'数量',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:dataC
                //[{ name:'UPS机头1',value:0},{name:'业务终端',value:0},{value:0, name:'五合一'},{value:1, name:'发电机'},{value:0, name:'密码键盘'},{value:14, name:'影像仪'},{value:0, name:'打印机'}]
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);

}
function dabing4() {
    var dataD = getNumForDaBing(4);
    var pieChart = echarts.init(document.getElementById("d"));
    var pieoption = {
        title : {
            text: '设备上线统计图',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        calculable : true,
        series : [
            {
                name:'数量',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:dataD
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);
}
function dabing1() {
    var dataA = getNumForDaBing(1);
    var pieChart = echarts.init(document.getElementById("a"));
    var pieoption = {
        title : {
            text: '设备入库统计图',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        calculable : true,
        series : [
            {
                name:'数量',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:dataA
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);
}
function zhutu1(){
    var inboundNum = getNum(1);
    var repairNum = getNum(3);
    var onlineNum = getNum(4);
    var scrapNum = getNum(2);
    var dom = document.getElementById("inbound");
    var myChart1 = echarts.init(dom);
    var app = {};
    option = null;
    var posList = [
        'left', 'right', 'top', 'bottom',
        'inside',
        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
    ];

    app.configParameters = {
        rotate: {
            min: -90,
            max: 90
        },
        align: {
            options: {
                left: 'left',
                center: 'center',
                right: 'right'
            }
        },
        verticalAlign: {
            options: {
                top: 'top',
                middle: 'middle',
                bottom: 'bottom'
            }
        },
        position: {
            options: echarts.util.reduce(posList, function (map, pos) {
                map[pos] = pos;
                return map;
            }, {})
        },
        distance: {
            min: 0,
            max: 100
        }
    };

    app.config = {
        rotate: 90,
        align: 'left',
        verticalAlign: 'middle',
        position: 'insideBottom',
        distance: 15,
        onChange: function () {
            var labelOption = {
                normal: {
                    rotate: app.config.rotate,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    position: app.config.position,
                    distance: app.config.distance
                }
            };
            myChart.setOption({
                series: [{
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }]
            });
        }
    };


    var labelOption = {
        normal: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c}  {name|{a}}',
            fontSize: 16,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };

    option = {
        color: ['#E87C25','#FCCE10', '#6e7074' ,'#60C0DD',],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['入库', '维修', '报废','上线']
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                axisTick: {show: false},
                data: month
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '入库',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: inboundNum
            },
            {
                name: '维修',
                type: 'bar',
                label: labelOption,
                data: repairNum
            },
            {
                name: '报废',
                type: 'bar',
                label: labelOption,
                data: scrapNum
            },
            {
                name: '上线',
                type: 'bar',
                label: labelOption,
                data: onlineNum
            }
        ]
    };;

    if (option && typeof option === "object") {
        myChart1.setOption(option, true);
    }
}

function getYearMonth() {
    var dataMonth = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix + "/getMonth",
        success : function(data) {
                for (var i = 0; i < data.length; i++) {
                    dataMonth.push(data[i].month);
                }
        }
    });
    return dataMonth;
}

function getNum(type) {
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        data : {
            type : type,
        },
        url : prefix + "/getZhuNum",
        success : function(data) {
            for (var i = 0; i < data.length; i++) {
                dataNum.push(data[i].num);
            }
        }
    });
    return dataNum;
}

function draw() {

    var xianName = getXianname();
    var inboundNum = getXiannum(1);
    var repairNum = getXiannum(3);
    var onlineNum = getXiannum(4);
    var scrapNum = getXiannum(2);

    var dom = document.getElementById("e");
    var myChart2 = echarts.init(dom);
    var app = {};
    option = null;
    var posList = [
        'left', 'right', 'top', 'bottom',
        'inside',
        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
    ];

    app.configParameters = {
        rotate: {
            min: -90,
            max: 90
        },
        align: {
            options: {
                left: 'left',
                center: 'center',
                right: 'right'
            }
        },
        verticalAlign: {
            options: {
                top: 'top',
                middle: 'middle',
                bottom: 'bottom'
            }
        },
        position: {
            options: echarts.util.reduce(posList, function (map, pos) {
                map[pos] = pos;
                return map;
            }, {})
        },
        distance: {
            min: 0,
            max: 100
        }
    };

    app.config = {
        rotate: 90,
        align: 'left',
        verticalAlign: 'middle',
        position: 'insideBottom',
        distance: 15,
        onChange: function () {
            var labelOption = {
                normal: {
                    rotate: app.config.rotate,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    position: app.config.position,
                    distance: app.config.distance
                }
            };
            myChart.setOption({
                series: [{
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }]
            });
        }
    };


    var labelOption = {
        normal: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c}  {name|{a}}',
            fontSize: 16,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };

    option = {
        //color: ['#003366', '#006699', '#4cabce', '#e5323e'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['入库', '维修', '报废', '上线']
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                axisTick: {show: false},
                data: xianName
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '入库',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: inboundNum
            },
            {
                name: '维修',
                type: 'bar',
                label: labelOption,
                data: repairNum
            },
            {
                name: '报废',
                type: 'bar',
                label: labelOption,
                data: scrapNum
            },
            {
                name: '上线',
                type: 'bar',
                label: labelOption,
                data: onlineNum
            }
        ]
    };;

    if (option && typeof option === "object") {
        myChart2.setOption(option, true);
    }
}
function getXianname() {
    var dataXianname = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix + "/getXianname",
        success : function(data) {
            for (var i = 0; i < data.length; i++) {
                dataXianname.push(data[i].deptName);
            }
        }
    });
    return dataXianname;
}
function getXiannum(type) {
    var dataXiannum = [];
    $.ajax({
        type : "GET",
        async: false,
        data : {
            type : type,
        },
        url : prefix + "/getXiannum",
        success : function(data) {
            for (var i = 0; i < data.length; i++) {
                dataXiannum.push(data[i].num);
            }
        }
    });
    return dataXiannum;
}
function xianchengTotal() {
    $('#xianchengTotal')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/getXianchengTotal", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                //iconSize : 'outline',
                //toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                // pagination : true, // 设置为true会在底部显示分页条
                queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                // singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                //pageSize : 10, // 如果设置了分页，每页数据条数
                //pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                //showColumns : false, // 是否显示内容下拉框（选择显示的列）
                //sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        //deptId: $('#deptId').val()
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
                    [
                        {
                            field: '',
                            title: "",
                            valign:"middle",
                            align:"center",
                            colspan: 1,
                            rowspan: 1
                        },
                        {
                            title: "承德郊区",
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: "丰宁县",
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: "承德县",
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: "围场县",
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: "平泉县",
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: "滦平县",
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: "隆化县",
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: "宽城县",
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: "兴隆县",
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        }
                    ],

                    [ {
                        field : 'categoryName',
                        title : '设备分类'
                    },
                        {
                            field : 'inboundNum130801',
                            title : '入库'
                        },

                        {
                            field : 'repairNum130801',
                            title : '维修'
                        },
                        {
                            field : 'rejectNum130801',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum130801',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum130826',
                            title : '入库'
                        },

                        {
                            field : 'repairNum130826',
                            title : '维修'
                        },
                        {
                            field : 'rejectNum130826',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum130826',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum130821',
                            title : '入库'
                        },

                        {
                            field : 'repairNum130821',
                            title : '维修'
                        },
                        {
                            field : 'rejectNum130821',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum130821',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum130828',
                            title : '入库'
                        },

                        {
                            field : 'repairNum130828',
                            title : '维修'
                        },
                        {
                            field : 'rejectNum130828',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum130828',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum130823',
                            title : '入库'
                        },

                        {
                            field : 'repairNum130823',
                            title : '维修'
                        },
                        {
                            field : 'rejectNum130823',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum130823',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum130824',
                            title : '入库'
                        },

                        {
                            field : 'repairNum130824',
                            title : '维修'
                        },
                        {
                            field : 'rejectNum130824',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum130824',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum130825',
                            title : '入库'
                        },

                        {
                            field : 'repairNum130825',
                            title : '维修'
                        },
                        {
                            field : 'rejectNum130825',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum130825',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum130827',
                            title : '入库'
                        },

                        {
                            field : 'repairNum130827',
                            title : '维修'
                        },
                        {
                            field : 'rejectNum130827',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum130827',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum130822',
                            title : '入库'
                        },

                        {
                            field : 'repairNum130822',
                            title : '维修'
                        },
                        {
                            field : 'rejectNum130822',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum130822',
                            title : '上线'
                        }
                    ]]
            });
}
function monthTotal() {
    $('#monthTotal')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/getMonthTotal", // 服务器数据的加载地址
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                queryParamsType : "limit",
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                    };
                },
                columns : [
                    [
                        {
                            field: '',
                            title: "",
                            valign:"middle",
                            align:"center",
                            colspan: 1,
                            rowspan: 1
                        },
                        {
                            title: month[0],
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: month[1],
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        },
                        {
                            title: month[2],
                            valign:"middle",
                            align:"center",
                            colspan: 4,
                            rowspan: 1
                        }
                    ],

                    [ {
                        field : 'categoryName',
                        title : '设备分类'
                    },
                        {
                            field : 'inboundNum0',
                            title : '入库'
                        },

                        {
                            field : 'repairNum0',
                            title : '维修'
                        },
                        {
                            field : 'scrapNum0',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum0',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum1',
                            title : '入库'
                        },

                        {
                            field : 'repairNum1',
                            title : '维修'
                        },
                        {
                            field : 'scrapNum1',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum1',
                            title : '上线'
                        },
                        {
                            field : 'inboundNum2',
                            title : '入库'
                        },

                        {
                            field : 'repairNum2',
                            title : '维修'
                        },
                        {
                            field : 'scrapNum2',
                            title : '报废'
                        },
                        {
                            field : 'onlineNum2',
                            title : '上线'
                        }
                    ]]
            });
}