$().ready(function() {
	validateRule();


    $("#schoolId").change(function(){
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
        var t = $("#depId").val();
        if(t ==''){
            return;
        }
        $.ajax({
            url:'/thesisMamager/thesisStudent/teacher',
            async:false,
            type:'post',
            data:{depId:t},
            success:function(data){
                var t3 = $("#teacherId").empty();
                for ( var i = 0; i < data.length; i++) {
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
		url : "/thesisMamager/thesisStudent/save",
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

//回显旧图片,再上传新图片
function uploadImg(btnId, valueId, url, imgs) {
    if("" != imgs){
        var images = "";
        var imgArr = imgs.split(",");
        for (var i in imgArr) {
            images += "<img src='" + imgArr[i] + "' class='file-preview-image' style='width:100px;height:100px;'>,"
        }
        if (images.length > 0) {
            images = images.substring(0, images.length - 1);
        }
        preImg(btnId, valueId, url, images);
    }else{
        $("#" + btnId).fileinput({
            enctype : "multipart/form-data",
            uploadUrl : url,
            language : "zh",
            browseClass : "btn btn-info",
            browseLabel : "选择图片",
            maxFileCount : 10,
            showUpload : true, //是否显示上传按钮
            showRemove : true, //是否显示移除按钮
            showCaption : false, //是否显示标题
            dropZoneEnabled : false, //是否显示预览区域
            allowedFileExtensions : [ "jpg", "png", "gif" ] //接收的文件后缀
        }).on("fileuploaded", function(event, ret, previewId, index) {
        	debugger;
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
}