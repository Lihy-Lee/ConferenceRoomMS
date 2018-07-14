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