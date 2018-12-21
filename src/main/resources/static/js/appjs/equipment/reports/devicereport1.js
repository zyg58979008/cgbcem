var prefix = "/equipment/report"

$(function () {

    getStock();

    var dom = document.getElementById("bbbb");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    option = {
        title: {
            text: ''
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        legend: {
            data:['双桥区','双滦区','宽城县','丰宁县','围场县','兴隆县','平泉县']
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'双桥区',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[2, 3, 4, 5, 6, 8, 1,7,5,5,3,2]
            },
            {
                name:'双滦区',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[2, 18, 11, 4, 2, 3, 10,3,5,6,6,8]
            },
            {
                name:'宽城县',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[15, 2, 1, 4, 9, 3, 4,3,4,5,3,1]
            },
            {
                name:'丰宁县',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[3, 2, 3, 0, 9, 0, 2,6,8,9,11,3]
            },
            {
                name:'兴隆县',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[3, 2, 1, 4, 9, 3, 2,3,3,8,7,9]
            },
            {
                name:'平泉县',
                type:'line',
                stack: '总量',
                areaStyle: {normal: {}},
                data:[6, 7, 3, 5,7, 9,10,3,4,5,7,2]
            },
            {
                name:'围场县',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:[8, 9, 1, 4, 12, 13, 10,4,7,9,9,2]
            }
        ]
    };

    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
    var myChart3 = echarts.init(document.getElementById("ddd"));
    var app = {};
    option = null;
    app.title = '多 Y 轴示例';

    var colors = ['#5793f3', '#d14a61', '#675bba'];

    option = {
        color: colors,

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            right: '20%'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['隐患问题数量','设备更换次数','设备故障次数']
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: ['双桥区','双滦区','营子区','围场县','丰宁县','平泉县','兴隆县','宽城县','隆化县','滦平县']
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '隐患问题数量',
                min: 0,
                max: 50,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value} 个'
                }
            },
            {
                type: 'value',
                name: '设备更换次数',
                min: 0,
                max: 60,
                position: 'right',
                offset: 80,
                axisLine: {
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisLabel: {
                    formatter: '{value} 次'
                }
            },
            {
                type: 'value',
                name: '设备故障总数',
                min: 0,
                max: 30,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[2]
                    }
                },
                axisLabel: {
                    formatter: '{value} 次'
                }
            }
        ],
        series: [
            {
                name:'隐患问题数量',
                type:'bar',
                data:[2, 9, 7, 2, 6, 7, 6, 16, 6, 20]
            },
            {
                name:'设备更换次数',
                type:'bar',
                yAxisIndex: 1,
                data:[2, 5, 9, 4, 8, 0, 17, 2, 7, 18]
            },
            {
                name:'设备故障次数',
                type:'line',
                yAxisIndex: 2,
                data:[2, 2, 3, 5, 6, 2, 3, 4, 2, 5]
            }
        ]
    };

    if (option && typeof option === "object") {
        myChart3.setOption(option, true);
    }

    var myChart4 = echarts.init(document.getElementById("duibi"));
    var app = {};
    option = null;
     app.title = '多 X 轴示例';
    var colors = ['#5793f3', '#d14a61', '#675bba'];


    option = {
        color: colors,

        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data:['2017 故障率', '2018 故障率']
        },
        grid: {
            top: 70,
            bottom: 50
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return '故障率  ' + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                data: ["2018-1", "2018-2", "2018-3", "2018-4", "2018-5", "2018-6", "2018-7", "2018-8", "2018-9", "2018-10", "2018-11", "2018-12"]
            },
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return '故障率  ' + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                data: ["2017-1", "2017-2", "2017-3", "2017-4", "2017-5", "2017-6", "2017-7", "2017-8", "2017-9", "2017-10", "2017-11", "2017-12"]
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name:'2017 故障率',
                type:'line',
                xAxisIndex: 1,
                smooth: true,
                data: [2, 9, 9, 26, 28, 7, 16, 12, 7, 8, 6, 2]
            },
            {
                name:'2018 故障率',
                type:'line',
                smooth: true,
                data: [3, 5, 1, 7, 3, 2, 6, 6, 4, 4, 10, 7]
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
    var pieChart = echarts.init(document.getElementById("aaaa"));
    var pieoption = {
        title : {
            text: '巡检次数统计图',
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
            //  data:['双桥区','双滦区','宽城县','丰宁县','围场县','兴隆县','1县','2县','3县','4县','5县','6县']
        },
        calculable : true,
        series : [
            {
                name:'巡检次数',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:dataCategoryNum
                //[{value:0, name:'UPS机头'},{value:0, name:'业务终端'},{value:0, name:'五合一'},{value:1, name:'发电机'},{value:0, name:'密码键盘'},{value:14, name:'影像仪'},{value:0, name:'打印机'}]
                /*[
                {value:335, name:'双桥区'},
                {value:310, name:'双滦区'},
                {value:234, name:'宽城县'},
                {value:135, name:'丰宁县'},
                {value:234, name:'围场县'},
                {value:135, name:'兴隆县'},
                {value:154, name:'1县'},
                {value:154, name:'2县'},
                {value:154, name:'3县'}
                ]*/
            }
        ]
    };
    pieChart.setOption(pieoption);
    $(window).resize(pieChart.resize);
}
