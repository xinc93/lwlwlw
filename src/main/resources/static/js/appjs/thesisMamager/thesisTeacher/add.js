$().ready(function() {
	validateRule();

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
                $("#teacherId").empty();
                for ( var i = 0; i < data.length; i++) {
                    if(i==0){
                        t2.append("<option value=''>--请选择--</option>");
                    }
                    t2.append("<option value='"+data[i].id+"'>"+ data[i].name+"</option>");
                }
            }
        })
    });

    $("#depId").change(function(){
        debugger;
        var depId = $("#depId").val();
        if(depId ==''){
            return;
        }
        $.ajax({
            url:'/thesisMamager/thesisStudent/teacher',
            async:false,
            type:'post',
            data:{dep_id:$("#depId").val()},
            success:function(data){
                var t3 = $("#teacherId").empty();
                for ( var i = 0; i < data.length; i++) {
                    if(i==0){
                        t3.append("<option value=''>--请选择--</option>");
                    }
                    t3.append("<option value='"+data[i].id+"'>"+ data[i].teacherTitle+"</option>");
                }
            }
        })
    });



});
uploadImg("uploadCover", "headImg", "upload", "");//thesisMamager/thesisStudent/

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/thesisMamager/thesisTeacher/save",
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
				required : icon + "请输入姓名"
			}
		}
	})
}