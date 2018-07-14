

$('#modify-emailbtn').click(function(){
    $("#yourEmailzzp").val("");
    $("#codezzp").val("");
    $('#modify-email').modal('show');
});

$("#sendzzp").click(function(){
    var uid=$("#persionID");
    var mail=$("#yourEmailzzp").val();
        var data = {};
     data["email"] = mail;
    var settings = {
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/verify_email.do",
  "method": "POST",
  "headers": {
    "Content-Type": "application/json",
    "Cache-Control": "no-cache"

  },
  "processData": false,
  "data":JSON.stringify(data)
}

$.ajax(settings).done(function (response) {
    if(response['status']==0){
        alert(response['msg'])
    }
});   
})

$("#enterzzp").click(function(){
    var uid=$("#persionID").html();
    var code=$("#codezzp").val();
    var mail=$("#yourEmailzzp").val();
    alert(uid);
    var data = {};
    data["code"] =code;
        data['userId']=uid;
    data['email']=mail;
  var settings = {
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/update_email.do",
  "method": "POST",
  "headers": {
    "Content-Type": "application/json",
    "Cache-Control": "no-cache",
  },
  "processData": false,
  "data": JSON.stringify(data)
}

$.ajax(settings).done(function (response) {
      console.log(response);
    if(response['status']=='0')
        {
            alert("更新成功!");
             $("#modify_email").modal("hide");
        }else
            {
                alert(response['msg']);
            }
//     if(response["status"]==1){
//         alert(response['msg']);
//     }
// else{
//     alert("邮箱验证成功,会议信息将会自动通过邮箱发送给您");
//     window.location.href="userHomepage.html";
// }
});
})

$("#alertpassword").click(function()
{
    var strCookie=document.cookie; 
    var arrCookie=strCookie.split("; "); 
    var userId="";
    var password="";
    for(var i=0;i<arrCookie.length;i++){ 
    var arr=arrCookie[i].split("="); 
    if("userId"==arr[0]){ 
        userId=arr[1];
    } else if("password"==arr[0]){
        password=arr[1];
    }
    }
    // var userId='354';
    // var password='123456';
    $("#oldPasswordzzp").val(password);
    $("#newPasswordzzp").val("");
    $("#checkPasswordzzp").val("");
    $("#modify-password").modal("show");
})

$("#confirmPasswordbtn").click(function(){
        var strCookie=document.cookie; 
    var arrCookie=strCookie.split("; "); 
    var userId="";
    var password="";
    for(var i=0;i<arrCookie.length;i++){ 
    var arr=arrCookie[i].split("="); 
    if("userId"==arr[0]){ 
        userId=arr[1];
    } else if("password"==arr[0]){
        password=arr[1];
    }
    }
    var np=$("#newPasswordzzp").val();
    var sp=$("#checkPasswordzzp").val();
    if(np!=sp)
        {
            alert('两次密码不一致');
        }else{
           
      document.cookie="password="+$("#newPasswordzzp").val()+"; path=/";
            var data={};
        data['userId']=userId;
        data['oldPassword']=password;
        data['password']=$("#newPasswordzzp").val();
  var settings = {
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/update_password.do",
  "method": "POST",
  "headers": {
    "Content-Type": "application/json",
    "Cache-Control": "no-cache",
  },
  "processData": false,
  "data":JSON.stringify(data)
}

   $.ajax(settings).done(function (response) {
        console.log(response);
       if(response['status']=='0')
           alert(response['msg']);
     var date=new Date(); 
    date.setTime(date.getTime()-10000); 
    document.cookie="userId=828; expires="+date.toGMTString();  
    document.cookie="password=828; expires="+date.toGMTString();
    window.location.href="userLoginPage.html";

   });
        }
     $("#modify-password").modal("hide");
})



$(document).ready(function(){
    var strCookie=document.cookie; 
    var arrCookie=strCookie.split("; "); 
    var userId="";
    var password="";
    for(var i=0;i<arrCookie.length;i++){ 
    var arr=arrCookie[i].split("="); 
    if("userId"==arr[0]){ 
        userId=arr[1];
    } else if("password"==arr[0]){
        password=arr[1];
    }
    }
    
    var data={};
    data['userId']=userId;
    data['password']=password;
  var settings = {
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/login.do",
  "method": "POST",
  "headers": {
    "Content-Type": "application/json",
    "Cache-Control": "no-cache",
  },
  "processData": false,
  "data":JSON.stringify(data)
}

   $.ajax(settings).done(function (response) {
        console.log(response);
       $("#persionID").text(response['data']['userId']);
        $("#persionName").text(response['data']['name']);
       $("#sex").text(response['data']['gender']);
        $("#emailAddress").text(response['data']['email']);
        $("#userpassword").text("**********");
        $("#department").text(response['data']['department']);
        $("#userStype").text(response['data']['role']);
   });
})
    
