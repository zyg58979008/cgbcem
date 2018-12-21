$(function () {



    var myChart = echarts.init(document.getElementById("ccc"));

    var app = {};
    option = null;
    app.title = '折柱混合';

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['巡检次数','维修次数','更换次数']
        },
        xAxis: [
            {
                type: 'category',
                data: ['打印机','读卡器','发电机','指纹仪','密码键盘','UPS机头','防雷','业务终端','三合一'],
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '巡检次数',
                min: 0,
                max: 10,
                interval: 50,
                axisLabel: {
                    formatter: '{value} 次'
                }
            },
            {
                type: 'value',
                name: '维修次数',
                min: 0,
                max: 50,
                interval: 5,
                axisLabel: {
                    formatter: '{value} 次'
                }
            }
        ],
        series: [
            {
                name:'巡检次数',
                type:'bar',
                data:[2, 4, 7, 3, 6, 7, 1, 2, 6 ]
            },
            {
                name:'维修次数',
                type:'bar',
                data:[2, 5, 0, 2, 2, 7, 1, 2, 4]
            },
            {
                name:'更换次数',
                type:'line',
                yAxisIndex: 1,
                data:[2, 2, 3, 4, 3, 1, 2, 4, 0]
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }






});
