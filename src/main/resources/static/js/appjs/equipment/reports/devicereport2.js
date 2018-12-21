prefix = "/equipment/report"
$(function () {

    getStock();

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
                name:'巡检次数',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:335, name:'打印机'},
                    {value:310, name:'密码键盘'},
                    {value:234, name:'UPS机头'},
                    {value:135, name:'五合一'},
                    {value:234, name:'发电机'},
                    {value:135, name:'影像仪'},
                    {value:154, name:'业务终端'}
                ]
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);

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
        legend: {
            orient : 'vertical',
            x : 'left',
            data:/*['双桥区','双滦区','宽城县','丰宁县','围场县','兴隆县','平泉县']*/
                //['UPS机头','业务终端','五合一','发电机','密码键盘','影像仪','打印机']
                    ["UPS机头1","业务终端","五合一","发电机","密码键盘","影像仪","打印机"]
        },
        calculable : true,
        series : [
            {
                name:'巡检次数',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:/*[
                    {value:335, name:'双桥区'},
                    {value:310, name:'双滦区'},
                    {value:234, name:'宽城县'},
                    {value:135, name:'丰宁县'},
                    {value:234, name:'围场县'},
                    {value:135, name:'兴隆县'},
                    {value:154, name:'平泉县'}
                ]*/
                    [{ name:'UPS机头1',value:0},{name:'业务终端',value:0},{value:0, name:'五合一'},{value:1, name:'发电机'},{value:0, name:'密码键盘'},{value:14, name:'影像仪'},{value:0, name:'打印机'}]
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);

    var pieChart = echarts.init(document.getElementById("c1"));
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
                data:[
                    {value:335, name:'打印机'},
                    {value:310, name:'密码键盘'},
                    {value:234, name:'UPS机头'},
                    {value:135, name:'五合一'},
                    {value:234, name:'发电机'},
                    {value:135, name:'影像仪'},
                    {value:154, name:'业务终端'}
                ]
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);


    var dom = document.getElementById("d");
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
        color: ['#003366', '#006699', '#4cabce'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['本月', '同比', '环比']
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
                data: ['1月', '2月', '3月', '4月', '5月', '6月']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '本月',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: [320, 332, 301, 334, 390,300]
            },
            {
                name: '同比',
                type: 'bar',
                label: labelOption,
                data: [220, 182, 191, 234, 290,210]
            },
            {
                name: '环比',
                type: 'bar',
                label: labelOption,
                data: [150, 232, 201, 154, 190,198]
            }
        ]
    };;

    if (option && typeof option === "object") {
        myChart1.setOption(option, true);
    }


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
        color: ['#003366', '#006699', '#4cabce', '#e5323e'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['Forest', 'Steppe', 'Desert', 'Wetland']
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
                data: ['丰宁县', '滦平县', '围场县', '宽城县', '平泉县', '隆化县']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: 'Forest',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: [320, 332, 301, 334, 390,300]
            },
            {
                name: 'Steppe',
                type: 'bar',
                label: labelOption,
                data: [220, 182, 191, 234, 290,210]
            },
            {
                name: 'Desert',
                type: 'bar',
                label: labelOption,
                data: [150, 232, 201, 154, 190,198]
            },
            {
                name: 'Wetland',
                type: 'bar',
                label: labelOption,
                data: [98, 77, 101, 99, 40,56]
            }
        ]
    };;

    if (option && typeof option === "object") {
        myChart2.setOption(option, true);
    }

    var myChart4 = echarts.init(document.getElementById("duibi"));
    var app = {};
    option = null;
     app.title = '多 X 轴示例';
    var colors = ['#5793f3', '#d14a61', '#675bba'];


    option = {
        title: {
            text: ''
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['库存','维修','报废','在线']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['1月','2月','3月','4月','5月','6月']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'库存',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 34, 90, 230, 210]
            },
            {
                name:'维修',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'报废',
                type:'line',
                stack: '总量',
                data:[150, 232, 801, 154, 190, 330, 410]
            },
            {
                name:'在线',
                type:'line',
                stack: '总量',
                data:[320, 332, 601, 334, 590, 330, 320]
            }
        ]
    };


    if (option && typeof option === "object") {
        myChart4.setOption(option, true);
    }

});
// 获取库存量
function getStock() {
    var dataCategory = [];
    $.ajax({
        type : "GET",
        url : prefix + "/getCategory",
        success : function(data) {
            for (var i = 0; i < data.length; i++) {
                // dataCategory += '\''+data[i].categoryName +'\''+","
                dataCategory.push(data[i].categoryName)
            }
            //  dataCategory = dataCategory.substr(0, dataCategory.length - 1);
            // dataCategory += "]"
            getStockNum(dataCategory);
        }
    });
}
function getStockNum(dataCategory) {
    var dataCategoryNum = [];
    $.ajax({
        type : "GET",
        url : prefix + "/getCategoryNum",
        success : function(data) {
            for (var i = 0; i < data.length; i++) {
                //dataCategoryNum +="{value:"+data[i].num+", name:"+ '\''+data[i].categoryName +'\''+"},"
                // dataCategoryNum.push(data[i].num,data[i].categoryName)
                dataCategoryNum.push({
                    name: data[i].categoryName,
                    value: data[i].num
                });
            }
            //dataCategoryNum = dataCategoryNum.substr(0, dataCategoryNum.length - 1);
            // dataCategoryNum += "]"
            showCategory(dataCategory,dataCategoryNum);
        }
    });
}
function showCategory(dataCategory,dataCategoryNum) {
    var pieChart = echarts.init(document.getElementById("a"));
    var pieoption = {
        title : {
            text: '设备库存统计图',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:dataCategory
        },
        calculable : true,
        series : [
            {
                name:'设备库存',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:dataCategoryNum
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);
}