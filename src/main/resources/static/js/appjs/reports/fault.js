$(function () {


    var myChart = echarts.init(document.getElementById("cdddc"));
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
        myChart.setOption(option, true);
    }











});
