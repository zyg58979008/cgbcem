$().ready(function() {
	validateRule();

});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});


function save() {
    var flag = $("#signupForm").valid();
    if(!flag){
        //没有通过验证
        return;
    }
	$.ajax({
		cache : true,
		type : "POST",
		url : "/inspection/template/save",
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
                required : true,
                maxlength:100,
                minlength:2,
                remote : {
                    url : "/inspection/template/exitInsert", // 后台处理程序
                    type : "post", // 数据发送方式
                    dataType : "json", // 接受数据格式
                    data : { // 要传递的数据
                        name : function() {
                            return $("#name").val();
                        }
                    }
                }
            }
        },
        messages : {
            name : {
                required : icon + "请输入模板名称",
                maxlength:icon + "模板名称字数过多",
                minlength:icon + "模板名称字数太少",
                remote : icon + "模板名称已经存在"
            }
        }
    })
}


var a=0;
$("#new1").click(function () {
        var tables = $('#class1');
        var addtr = $("<tr>"+
            "<td align='center' height='31' width='30%'><select data-placeholder='--选择巡检项目--' name='project1' id='project1"+a+"' class='form-control chosen-select' tabindex='2' ><option value=''>--选择巡检项目--</option></select></td>"+
            /*"<td align='center' height='31' width='30%'><label class='radio-inline' > <input type='radio' name='status' value='1' /><div style='width:91px; height:91px; margin-top:7px; margin-left:5px; display:inline'>正常</div> </label> <label class='radio-inline'> <input type='radio'name='status' value='0' />不正常 </label></td>"+*/
            "<td align='center' height='31' width='30%'></td>"+
            "<td align='center' height='31' width='30%'></td>"+
            "<td align='center' height='31' width='10%'><button  onclick='deleteTrRow(this);' type='button' class='btn btn-xs btn-danger'>删除</button></td>"+
            "</tr>");
        addtr.appendTo(tables);
    loadPorject1();
a++;
    });

    function deleteTrRow(tr){
        //多一个parent就代表向前一个标签,
        //本删除范围为<td><tr>两个标签,即向前两个parent
        //如果多一个parent就会删除整个table
        $(tr).parent().parent().remove();
}
function loadPorject1(){
    var html = "";
    $.ajax({
        url : '/inspection/project/loadPorject1',
        async : false,
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
            }
            $("#project1"+a).append(html);
            $("#project1"+a).chosen({
                maxHeight : 200
            });
            //点击事件
            $("#project1"+a).on('change', function(e, params) {
                //console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
            });
        }
    });
}
var b=0;
$("#new2").click(function () {
    var tables = $('#class2');
    var addtr = $("<tr>"+
        "<td align='center' height='31' width='30%'><select data-placeholder='--选择巡检项目--' name='project2' id='project2"+b+"' class='form-control chosen-select' tabindex='2' ><option value=''>--选择巡检项目--</option></select></td>"+
        "<td align='center' height='31' width='30%'></td>"+
        "<td align='center' height='31' width='30%'></td>"+
        "<td align='center' height='31' width='10%'><button onclick='deleteTrRow1(this);' type='button' class='btn btn-xs btn-danger'>删除</button></td>"+
        "</tr>");
    addtr.appendTo(tables);
    loadPorject2();
    b++;
});
function deleteTrRow1(tr){
    //多一个parent就代表向前一个标签,
    //本删除范围为<td><tr>两个标签,即向前两个parent
    //如果多一个parent就会删除整个table
    $(tr).parent().parent().remove();
}
function loadPorject2(){
    var html = "";
    $.ajax({
        url : '/inspection/project/loadPorject2',
        async : false,
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
            }
            $("#project2"+b).append(html);
            $("#project2"+b).chosen({
                maxHeight : 200
            });
            //点击事件
            $("#project2"+b).on('change', function(e, params) {
                //console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
            });
        }
    });
}
var c=0;
$("#new3").click(function () {
    var tables = $('#class3');
    var addtr = $("<tr>" +
        "<td align='center' height='31' width='30%'><select data-placeholder='--选择巡检项目--' name='project3' id='project3" + c + "' class='form-control chosen-select' tabindex='2' ><option value=''>--选择巡检项目--</option></select></td>" +
        "<td align='center' height='31' width='30%'></td>" +
        "<td align='center' height='31' width='30%'></td>" +
        "<td align='center' height='31' width='10%'><button onclick='deleteTrRow2(this);' type='button' class='btn btn-xs btn-danger'>删除</button></td>" +
        "</tr>");
    addtr.appendTo(tables);
    loadPorject3();
    c++;
});
function deleteTrRow2(tr){
    //多一个parent就代表向前一个标签,
    //本删除范围为<td><tr>两个标签,即向前两个parent
    //如果多一个parent就会删除整个table
    $(tr).parent().parent().remove();
}

function loadPorject3(){
    var html = "";
    $.ajax({
        url : '/inspection/project/loadPorject3',
        async : false,
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
            }
            $("#project3"+c).append(html);
            $("#project3"+c).chosen({
                maxHeight : 200
            });
            //点击事件
            $("#project3"+c).on('change', function(e, params) {
                //console.log(params.selected);
                var opt = {
                    query : {
                        type : params.selected,
                    }
                }
            });
        }
    });
}
