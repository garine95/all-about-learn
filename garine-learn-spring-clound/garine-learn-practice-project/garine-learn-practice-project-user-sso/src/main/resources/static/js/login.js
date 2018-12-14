$(function(){
    $("#btn_submit").click(function(){
        var username=$.trim($("#username").val());
        var password=$.trim($("#password").val());
        if (username== ""||password== "") {
            alert("登录信息不能为空!");
            return;
        }
        $.ajax({
            url:"/login",
            type:"POST",
            data:{username:username,password:MD5(password)},
            success:function(data){
                if(data.code=="000000"){
                    window.location.href=data.data;
                }else{
                    alert(data.msg);
                }
            }
        });
    });
})