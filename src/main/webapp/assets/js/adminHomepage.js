$("#ad_quit").click(function(){
     var date=new Date(); 
    date.setTime(date.getTime()-10000); 
    document.cookie="adId=828; expires="+date.toGMTString();  
    document.cookie="adpassword=828; expires="+date.toGMTString();
    window.location.href="homePage.html";
})

$(document).ready(function(){
    var strCookie=document.cookie; 
var arrCookie=strCookie.split("; "); 
var userId; 
for(var i=0;i<arrCookie.length;i++){ 
var arr=arrCookie[i].split("="); 
if("adId"==arr[0]){ 
$("#ad_nav_name").text("管理员:"+arr[1]);
break; 
} 
}
});