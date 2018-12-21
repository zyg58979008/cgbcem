prefix = "/report";
var prefix1= "/report/main";
var wuyefei='';
var qunuanfei='';
var date=new Date;
var year=date.getFullYear();
var lis =  $("#myTab li").length;
if(lis>0){
    $("#myTabContent").css('display','block');
}
var name=$('#myTab li:eq(0) a').text();
if(name=='商业'){
    amortizeMonth();
    shopType();
    rentArea1();
    rentArea2();
    shoujiaolv();
    contractEnd();
}
if(name=='物业'){
    wuyefeilv();
    qunuanfeilv();
    fangbenfeilv();
    WuyefeiAmortize();
}
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    var activeTab = $(e.target).text();
    if(activeTab=='商业'){
        amortizeMonth();
        shopType();
        rentArea1();
        rentArea2();
        shoujiaolv();
        contractEnd();
    }
    if(activeTab=='物业'){
        wuyefeilv();
        qunuanfeilv();
        fangbenfeilv();
        WuyefeiAmortize();
    }
    $("a[name='a']").removeAttr("style");
    $(e.target).css("background-color","white");
});
$('#myTab li:eq(0) a').click();

function getNumForType(){
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix + "/getNumForType",
        data : {
            createDate: $("#Date").val()
        },
        success : function(data) {
            if(data == null){
                dataNum = null;
            }else{
                for (var i = 0; i < data.length; i++) {
                    if(data[i].name){

                    }else{
                        data[i].name ='其他'
                    }
                    dataNum.push({
                        name: data[i].name,
                        value: data[i].num
                    });
                }
            }
        }
    });
    return dataNum;
}
function shopType() {
    var dataA = getNumForType();
    var pieCharta = echarts.init(document.getElementById("b"));
    var pieoptiona = {
        title : {
            text: '商铺类型统计',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            position:function(p){
                var id = document.getElementById('main');
                if ($(id).width() - p[0]- $(id).find("div .echarts-tooltip").width()-20 <0) {
                    p[0] = p[0] - $(id).find("div .echarts-tooltip").width() -40;
                }
                return [p[0], p[1]];
            },
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
    pieCharta.setOption(pieoptiona);
    $(window).resize(pieCharta.resize);
}

function rentArea1(){
    var dataA = getNumForAreaA();
    var pieChartc = echarts.init(document.getElementById("d1"));
    var pieoptionc = {
        title : {
            text: '商铺面积统计',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',position:function(p){
                var id = document.getElementById('main');
                if ($(id).width() - p[0]- $(id).find("div .echarts-tooltip").width()-20 <0) {
                    p[0] = p[0] - $(id).find("div .echarts-tooltip").width() -40;
                }
                return [p[0], p[1]];
            },
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
    pieChartc.setOption(pieoptionc);
    $(window).resize(pieChartc.resize);
}
function rentArea2(){
    var dataA = getNumForAreaB();
    var pieChartc = echarts.init(document.getElementById("d2"));
    var pieoptionc = {
        title : {
            text: '商铺面积统计',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',position:function(p){
                var id = document.getElementById('main');
                if ($(id).width() - p[0]- $(id).find("div .echarts-tooltip").width()-20 <0) {
                    p[0] = p[0] - $(id).find("div .echarts-tooltip").width() -40;
                }
                return [p[0], p[1]];
            },
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
    pieChartc.setOption(pieoptionc);
    $(window).resize(pieChartc.resize);
}

function getNumForAreaA(){
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix + "/getNumForAreaA",
        data : {
            createDate: $("#Date").val()
        },
        success : function(data) {
            if(data == null){
                dataNum = null;
            }else{
                dataNum.push({
                    name: '在租',
                    value: data[0].rentArea
                });
                dataNum.push({
                    name: '闲置',
                    value: data[0].unRentArea
                });
            }
        }
    });
    return dataNum;
}
function getNumForAreaB(){
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix + "/getNumForAreaB",
        data : {
            createDate: $("#Date").val()
        },
        success : function(data) {
            if(data == null){
                dataNum = null;
            }else{
                dataNum.push({
                    name: '在租',
                    value: data[0].rentArea
                });
                dataNum.push({
                    name: '闲置',
                    value: data[0].unRentArea
                });
            }
        }
    });
    return dataNum;
}
function getNumForContractEnd(){
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix + "/getNumForContractEnd",
        data : {
            createDate: $("#Date").val()
        },
        success : function(data) {
            if(data == null){
                dataNum = null;
            }else{
                dataNum.push({
                    value: data[0].num1
                });
                dataNum.push({
                    value: data[0].num2
                });
                dataNum.push({
                    value: data[0].num3
                });
                dataNum.push({
                    value: data[0].num4
                });
                dataNum.push({
                    value: data[0].num5
                });
                dataNum.push({
                    value: data[0].num6
                });dataNum.push({
                    value: data[0].num7
                });
                dataNum.push({
                    value: data[0].num8
                });
                dataNum.push({
                    value: data[0].num9
                });
                dataNum.push({
                    value: data[0].num10
                });
                dataNum.push({
                    value: data[0].num11
                });
                dataNum.push({
                    value: data[0].num12
                });

            }
        }
    });
    return dataNum;
}
function getNumForAmortizeMonth(){
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix + "/getNumForAmortizeMonth",
        data : {
            createDate: $("#Date").val()
        },
        success : function(data) {
            if(data == null){
                dataNum = null;
            }else{
                dataNum.push({
                    value: data[0].num1
                });
                dataNum.push({
                    value: data[0].num2
                });
                dataNum.push({
                    value: data[0].num3
                });
                dataNum.push({
                    value: data[0].num4
                });
                dataNum.push({
                    value: data[0].num5
                });
                dataNum.push({
                    value: data[0].num6
                });dataNum.push({
                    value: data[0].num7
                });
                dataNum.push({
                    value: data[0].num8
                });
                dataNum.push({
                    value: data[0].num9
                });
                dataNum.push({
                    value: data[0].num10
                });
                dataNum.push({
                    value: data[0].num11
                });
                dataNum.push({
                    value: data[0].num12
                });

            }
        }
    });
    return dataNum;
}
function contractEnd() {
    var dataA = getNumForContractEnd();
    var barChart = echarts.init(document.getElementById("echarts-bar-chart"));
    var baroption = {
        title : {
            text: '合同到期柱状图'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['合同数量']
        },
        grid:{
            x:30,
            x2:40,
            y2:24
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
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
                name:'合同数量',
                type:'bar',
                data:dataA
            }
        ]
    };
    barChart.setOption(baroption);

    window.onresize = barChart.resize;
}
function amortizeMonth() {
    var dataA = getNumForAmortizeMonth();
    var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
    var lineoption = {
        title : {
            text: '商铺月摊租金统计'
        },
        tooltip : {
            trigger: 'axis'
        },
        grid:{
            x:30,
            x2:40,
            y2:24
        },
        xAxis: {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: dataA,
            type: 'line',
            smooth: true
        }]
    };
    lineChart.setOption(lineoption);


}
function shoujiaolv() {
    var dataA = getNumForShoujiaolv();
    var pieChartd = echarts.init(document.getElementById("a"));
    var pieoptiond = {
        title : {
            text: '商铺当月租金收缴',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',position:function(p){
                var id = document.getElementById('main');
                if ($(id).width() - p[0]- $(id).find("div .echarts-tooltip").width()-20 <0) {
                    p[0] = p[0] - $(id).find("div .echarts-tooltip").width() -40;
                }
                return [p[0], p[1]];
            },
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
    pieChartd.setOption(pieoptiond);
    $(window).resize(pieChartd.resize);
}
function getNumForShoujiaolv(){
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix + "/getNumForShoujiaolv",
        data : {
            createDate: $("#Date").val()
        },
        success : function(data) {
            if(data == null){
                dataNum = null;
            }else{
                dataNum.push({
                    name: '已收租金',
                    value: data[0].payed
                });
                dataNum.push({
                    name: '未收租金',
                    value: data[0].unpay
                });
            }
        }
    });
    return dataNum;
}
function wuyefeilv() {
    var dataA = getWuyefeilv();
    var pieCharta = echarts.init(document.getElementById("wuyefeilv"));
    var pieoptiona = {
        title : {
            text: wuyefei+'物业费收缴率',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            position:function(p){
                var id = document.getElementById('main');
                if ($(id).width() - p[0]- $(id).find("div .echarts-tooltip").width()-20 <0) {
                    p[0] = p[0] - $(id).find("div .echarts-tooltip").width() -40;
                }
                return [p[0], p[1]];
            },
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        calculable : true,
        series : [
            {
                name:'金额',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:dataA
            }
        ]
    };
    pieCharta.setOption(pieoptiona);
    $(window).resize(pieCharta.resize);
}
function getWuyefeilv(){
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix1 + "/getWuyefeiLv",
        data : {
        },
        success : function(data) {
            if(data == null||data==''){
                dataNum = null;
                wuyefei='';
            }else{
                wuyefei=data.startYear+'-'+data.endYear;
                dataNum.push({
                    name: '已收物业费',
                    value: data.moneyPayed
                });
                dataNum.push({
                    name: '未收物业费',
                    value: data.moneyUnpay
                });
            }
        }
    });
    return dataNum;
}
function qunuanfeilv() {
    var dataA = getQunuanfeilv();
    var pieCharta = echarts.init(document.getElementById("qunuanfeilv"));
    var pieoptiona = {
        title : {
            text: qunuanfei+'取暖费收缴率',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            position:function(p){
                var id = document.getElementById('main');
                if ($(id).width() - p[0]- $(id).find("div .echarts-tooltip").width()-20 <0) {
                    p[0] = p[0] - $(id).find("div .echarts-tooltip").width() -40;
                }
                return [p[0], p[1]];
            },
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        calculable : true,
        series : [
            {
                name:'户数',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:dataA
            }
        ]
    };
    pieCharta.setOption(pieoptiona);
    $(window).resize(pieCharta.resize);
}
function getQunuanfeilv(){
    var dataNum = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix1 + "/getQunuanfeiLv",
        data : {
        },
        success : function(data) {
            if(data == null||data==''){
                dataNum = null;
                qunuanfei='';
            }else{
                qunuanfei=data.startYear+'-'+data.endYear;
                dataNum.push({
                    name: '已收取暖费户数',
                    value: data.payedNum
                });
                dataNum.push({
                    name: '未收取暖费户数',
                    value: data.unpayNum
                });
            }
        }
    });
    return dataNum;
}
function fangbenfeilv() {
    var dataA = getFangbenfeilv();
    var pieCharta = echarts.init(document.getElementById("fangbenfeilv"));
    var pieoptiona = {
        title : {
            text: '房本费收缴率',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            position:function(p){
                var id = document.getElementById('main');
                if ($(id).width() - p[0]- $(id).find("div .echarts-tooltip").width()-20 <0) {
                    p[0] = p[0] - $(id).find("div .echarts-tooltip").width() -40;
                }
                return [p[0], p[1]];
            },
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        calculable : true,
        series : [
            {
                name:'户数',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:dataA
            }
        ]
    };
    pieCharta.setOption(pieoptiona);
    $(window).resize(pieCharta.resize);
}
function getFangbenfeilv(){
    var dataNum = [];
    /*$.ajax({
        type : "GET",
        async: false,
        url : prefix1 + "/getQunuanfeiLv",
        data : {
        },
        success : function(data) {
            if(data == null||data==''){
                dataNum = null;
                wuyefei='';
            }else{
                wuyefei=data.startYear+'-'+data.endYear;
                dataNum.push({
                    name: '已收取暖费户数',
                    value: data.payedNum
                });
                dataNum.push({
                    name: '未收取暖费户数',
                    value: data.unpayNum
                });
            }
        }
    });*/
    return dataNum;
}

function WuyefeiAmortize() {
    var dataA = getWuyefeiAmortize();
    var barChart = echarts.init(document.getElementById("wuyefei-chart"));
    var baroption = {
        title : {
            text: year+'年物业费摊销图'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['应摊金额','已摊金额']
        },
        grid:{
            x:30,
            x2:40,
            y2:24
        },
        toolbox: {
            show : true,
            feature : {
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
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
                name:'应摊金额',
                type:'bar',
                data:dataA.dataNum1
            },
            {
                name:'已摊金额',
                    type:'bar',
                data:dataA.dataNum2
            }
        ]
    };
    barChart.setOption(baroption);

    window.onresize = barChart.resize;
}
function getWuyefeiAmortize(){
    var obj={};
    obj.dataNum1 = [];
    obj.dataNum2 = [];
    $.ajax({
        type : "GET",
        async: false,
        url : prefix1 + "/getWuyefeiAmortize",
        data : {
            year: year
        },
        success : function(data) {
            if(data == null){
                obj.dataNum1 = [];
                obj.dataNum2 = [];
            }else{
                obj.dataNum1.push({
                    value: data[0].ying1.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying1.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying2.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying2.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying3.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying3.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying4.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying4.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying5.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying5.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying6.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying6.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying7.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying7.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying8.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying8.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying9.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying9.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying10.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying10.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying11.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying11.split("|")[1],
                });
                obj.dataNum1.push({
                    value: data[0].ying12.split("|")[0],
                });
                obj.dataNum2.push({
                    value: data[0].ying12.split("|")[1],
                });

            }
        }
    });
    return obj;
}