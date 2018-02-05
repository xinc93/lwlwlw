$().ready(function() {
	validateRule();
});

$("#schoolId").change(function(){debugger;
    var t = $("#schoolId").val();
    if(t ==''){
        return;
    }
    $.ajax({
        url:'/thesisMamager/thesisStudent/faculty',
        async:false,
        type:'post',
        data:{pid:t},
        success:function(data){
            var t2 = $("#depId").empty();
         //   $("#depId").val(data[0].id);
            for ( var i = 0; i < data.length; i++) {
                t2.append("<option value='"+data[i].id+"'>"+ data[i].name+"</option>");
            }
        }
    })
});

layui.use('upload', function () {
    var upload = layui.upload;
    var uploadInst = upload.render({
        elem: '#execlImport', //绑定元素
        url: '/thesisMamager/thesisTemplate/upload', //上传接口
        size: 10000,
        accept: 'file',
        exts:'xml',
        done: function (r) {
           // layer.msg(r.msg);
$("#templateid").val(r.msg);
           // reLoad();
        },
        error: function (r) {
            layer.msg(r.msg);
        }
    });
});
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

    var formData = new FormData();
    formData.append('myfile', $('input[name=myfile]')[0].files[0]);
    formData.append('templatename', $("#templatename").val());
    formData.append('shoolid', $("#schoolId").val());
    formData.append('depid', $("#depId").val());
  /*  alert(1)
    $.ajax({
		cache : true,
		type : "POST",
		url : "/thesisMamager/thesisTemplate/save",
	/!*	data : $('#signupForm').serialize(),// 你的formid*!/
        data: formData,
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
	/!*		if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}*!/

		}
	});
*/
    $.ajax({
        url : "/thesisMamager/thesisTemplate/save",
        type: 'POST',
        cache: false,
        data:formData,
        processData: false,
        contentType: false
    }).done(function(res) {
        parent.layer.msg("操作成功");
        parent.reLoad();
        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
        parent.layer.close(index);
    }).fail(function(res) {});

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
				required : icon + "请输入姓名"
			}
		}
	})
}