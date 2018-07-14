

$('#UserLogin').click(   //done
function(){
    var data={};
    data['userId']=$('#UserAccount').val();
    data['password']=$('#UserPassword').val();
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
   if(response['status']==0&&response['data']['email']!=null){
    document.cookie="userId="+$('#UserAccount').val()+";path=/";
  document.cookie="password="+$("#UserPassword").val()+";path=/";
      alert(response['msg']);
       window.location.href="userHomepage.html";
   }
       else if (response['status']==0){
         document.cookie="userId="+$('#UserAccount').val()+";path=/";
        document.cookie="password="+$("#UserPassword").val()+";path=/";
           alert("登录成功，正在为您跳转到邮箱验证");
          window.location.href="emailVerify.html";
       }
       else{
           alert(response['msg']);
       }
   } );
   });
