
$('#adminLogin').click(
function(){
    var data={};
    data['adminId']= $('#adminId').val();
    data['password']=$('#adminPassword').val();
  
   var settings = {
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/admin/login.do",
  "method": "POST",
  "headers": {
    "Content-Type": "application/json",
    "Cache-Control": "no-cache",

  },
  "processData": false,
  "data": JSON.stringify(data)
}

$.ajax(settings).done(function (response) {
  if(response['status']==0){
      // alert(response['msg']);
      document.cookie="adId="+$('#adminId').val()+";path=/";
  document.cookie="adpassword="+$("#adminPassword").val()+";path=/";
       window.location.href="adminHomepage.html";
  }
    else{
       alert(response['msg']);
    }
});
})




