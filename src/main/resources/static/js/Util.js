
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