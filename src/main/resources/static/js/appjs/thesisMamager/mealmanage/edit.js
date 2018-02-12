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

uploadImg("uploadCover", "headImg", "upload", "");

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
        rules:{
            name:{
                required:true
            },
            mobile:{
                required:true,
            },
            eduClass:{
                required:true
            },
            learnClass:{
                required:true
            },
            major:{
                required:true
            },
            professionalDirection:{
                required:true,
            },
            schoolGrade:{
                required:true,
            },
            schoolNo:{
                required:true,
            } ,
            schoolId:{
                required:true
            },
            depId:{
                required:true
            },
            teacherId:{
                required:true
            },
            accountType:{
                required:true
            },
            headImg:{
                required:true,
            }
        },
        messages:{
            name:{
                required:"必填",
            },
            mobile:{
                required:"必填",
            },
            eduClass:{
                required: "必填",
            },
            learnClass:{
                required:"必填",
            },
            major:{
                required:"必填"
            },
            professionalDirection:{
                required:"必填"
            },
            schoolGrade:{
                required:"必填"
            },
            schoolNo:{
                required:"必填"
            },
            schoolId:{
                required:"必填",
            },
            depId:{
                required:"必填",
            },
            teacherId:{
                required:"必填",
            },
            accountType:{
                required:"必填",
            },
            headImg:{
                required:"必填",
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
            var filePath = ret.response.filepath;
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