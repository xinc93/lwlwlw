$().ready(function() {
	validateRule();


    $("#schoolId").change(function(){
        var t = $("#schoolId").val();
        if(t ==''){
            return;
        }
        $.ajax({
            url:'/area/code',
            async:false,
            type:'post',
            data:{id:t,ranNum:Math.random()},
            success:function(data){
                var t2 = $("#depId").empty();
                for ( var i = 0; i < data.length; i++) {
                    t2.append("<option value='"+data[i].key+"'>"+ data[i].value+"</option>");
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
            url:'/area/code',
            async:false,
            type:'post',
            data:{id:t,ranNum:Math.random()},
            success:function(data){
                var t3 = $("#teacherId").empty();
                for ( var i = 0; i < data.length; i++) {
                    t3.append("<option value='"+data[i].key+"'>"+ data[i].value+"</option>");
                }
            }
        })
    });



});

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