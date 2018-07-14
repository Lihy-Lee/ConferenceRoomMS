

$(document).ready(function(){
    var strCookie=document.cookie; 
var arrCookie=strCookie.split("; "); 
var userId; 
for(var i=0;i<arrCookie.length;i++){ 
var arr=arrCookie[i].split("="); 
if("userId"==arr[0]){ 
$("#user_nav_name").text("用户:"+arr[1]);
break; 
} 
}
});


$("#user_quit").click(function(){
     var date=new Date(); 
    date.setTime(date.getTime()-10000); 
    document.cookie="userId=828; expires="+date.toGMTString();  
    document.cookie="password=828; expires="+date.toGMTString();
    window.location.href="homePage.html";
})



var i = 60
$('#send_verify_req').click(
    function send(){
        // if($("#emailVerify").val()!=""){
    var data = {};
     data["email"] = $("#emailVerify").val();

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
        
    //倒计时
	$("#send_verify_req").attr("disabled", true);
	$(function(){
		setTimeout(function(){
			$("#send_verify_req").attr("disabled", false);
			$("#send_verify_req").html('发送验证码')
		},60000);
		after();
}
     );
});
function after(){//倒计时
	if(i == 0){
		i = 60
			$("#send_verify_req").html('发送验证码')
		return
	}
    $("#send_verify_req").html(i + 's');
    i=i-1;
    setTimeout(function(){
        after();
    },1000);
}


//verify code
$("#reallycode").click(function (){
    var uid="";
    var strCookie=document.cookie; 
var arrCookie=strCookie.split("; "); 
var userId; 
for(var i=0;i<arrCookie.length;i++){ 
var arr=arrCookie[i].split("="); 
if("userId"==arr[0]){ 
uid=arr[1];
break; 
} 
}
var data = {};
    data['userId']=uid;
    data["email"] =$("#emailVerify").val();
    data['code']=$("#code").val();
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
    if(response["status"]==1){
        alert(response['msg']);
    }
else{
    alert("邮箱验证成功,会议信息将会自动通过邮箱发送给您");
    window.location.href="userHomepage.html";
}
});
})
