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
                    t3.append("<option value='"+data[i].id+"'>"+ data[i].teacherTitle+"</option>");
                }
            }
        })
    });
    preImg(btnId, valueId, url, images)
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/thesisMamager/thesisStudent/update",
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
				required : icon + "请输入名字"
			}
		}
	})
}

//回显图片
function preImg(btnId, valueId, url, images){
    $("#" + btnId).fileinput({
        enctype : "multipart/form-data",
        uploadUrl : _urlPath + url,
        language : "zh",
        browseClass : "btn btn-info",
        browseLabel : "选择图片",
        maxFileCount : 10,
        showUpload : true, //是否显示上传按钮
        showRemove : true, //是否显示移除按钮
        showCaption : false, //是否显示标题
        dropZoneEnabled : false, //是否显示预览区域
        allowedFileExtensions : [ "jpg", "png", "gif" ], //接收的文件后缀
        initialPreview: [images]
    }).on("fileuploaded", function(event, ret, previewId, index) {
        var filePath = ret.response.data.filepath;
        $("#" + valueId).val(filePath);
    }).on("fileclear", function(event, key) {
        return false;
    }).on("filecleared", function(event, key) {
        $("#" + valueId).val("");
        return false;
    }).on("change", function(event, key) {
        return false;
    });
}