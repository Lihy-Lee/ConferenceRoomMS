
$(document).ready(function()
 {
     bl=$("#hisb").find("tr").length;
    for(var i=1;i<bl;i++)
     {  $("#hisb").find("tr").eq(1)
        .remove();
    }
    var userId="";
    var strCookie=document.cookie; 
    var arrCookie=strCookie.split("; "); 
    var userId; 
    for(var i=0;i<arrCookie.length;i++){ 
    var arr=arrCookie[i].split("="); 
    if("userId"==arr[0]){ 
    userId=arr[1];
    break; 
    } 
    }
    var data={};
    // data['userId']="111";
     data['userId']=userId;;
  var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/show_history.do",
  "method": "POST",
 "headers": {
    "Content-Type": "application/json",
    "Cache-Control": "no-cache"   
  },
     "processData": false,
    "data":JSON.stringify(data)
  };
$.ajax(settings).done(function (response) {
        console.log(response);
    //alert(response['data'][0]['password']);
    var le=response['data'].length;
    var l=$("#hisb").find("tr").length;
    var s1=$("#hist").find("tr").eq(l); 
    for(var i=0;i<le;i++)
    {
        var bid=response['data'][i]['bookingId'];
        var mid=response['data'][i]['meetingId'];
        var start
        =response['data'][i]['startTime'];
        var end=response['data'][i]['endTime'];
        var create=
        response['data'][i]['createTime'];
        var sta=response['data'][i]['status'];
        if(sta=='0'){ var im="";}else
            {
                var im='被取消';
            }
        
          var trHTML = "<tr>";
		trHTML += "<td>";
		trHTML += bid;
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += mid;
		trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=  create;
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML += start+"-"+end;
        trHTML += "</td>";
    
    trHTML+="<td>"
    trHTML+=im;
    trHTML+="</td>"
    trHTML += "<td>";
    
    l=$("#hisb").find("tr").length;

    $("#hist").find("tr").eq(l).after(trHTML);

  }
    var $table = $('#hist');   
   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 6;//每一页显示的数目  
          $('#hist').bind('paging',function(){  
             $table.find('hisb tr').hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();  
         });       
         var sumRows =  $('#hisb').find('tr').length;  
         var sumPages = Math.ceil(sumRows/pageSize);//总页数        
     var $pager = $('<div class="page"></div>');  //新建div，放入a标签,显示底部分页码  
       for(var pageIndex = 0 ; pageIndex<sumPages ; pageIndex++){  
     $('<a href="#" id="pageStyle" onclick="changCss(this)"><span>'+(pageIndex+1)+'</span></a>').bind("click",{"newPage":pageIndex},function(event){  
                 currentPage = event.data["newPage"];  
                 $table.trigger("paging");  
                   //触发分页函数  
                 }).appendTo($pager);  
                 $pager.append(" ");  
             }     
             $pager.insertAfter( $('#hist'));  
             $table.trigger("paging");  
               
             var $pagess = $('#pageStyle');  
             $pagess[0].style.backgroundColor="#006B00"; 
             $pagess[0].style.color="#ffffff";  
});
})



$("#sou").click(function(){
    var co=$("#chaxun").val();
    if(co==""){
    $("#hisb").find("tr").show();}else
        {
        $("#hisb").find("tr").hide();
    var l=$("#hisb").find("tr").length;
            for(var i=1;i<l;i++)
                {
                    var t=$("#hisb").find("tr").eq(i);
                     if(t.find("td").eq(0).html().indexOf(co)>=0)
                        {
                            t.show();
                          
                        }
                    if(t.find("td").eq(1).html().indexOf(co)>=0)
                        {
                            t.show();
                          
                        }
                    if(t.find("td").eq(2).html().indexOf(co)>=0)
                       {
                       t.show();
                       }
                    if(t.find("td").eq(3).html().indexOf(co)>=0)
                        {
                            t.show();
                        }
                }
        }
  //$("#hisb").find("tr").eq(0).show();
    
})

 function changCss(obj){  
        var arr = document.getElementsByTagName("a");  
        for(var i=0;i<arr.length;i++){     
         if(obj==arr[i]){       //当前页样式  
          obj.style.backgroundColor="rgba(0,71,255,0.46)";  
          obj.style.color="rgba(0,71,255,0.46)";  
        }  
         else  
         {  
           arr[i].style.color="";  
           arr[i].style.backgroundColor="";  
         }  
        }  
     }   



$("#jinjijiuhuche3").click(function(){
                   
     
    var $table = $('#hist');   
   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 6;//每一页显示的数目  
          $('#hist').bind('paging',function(){  
             $table.find('hisb tr').hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();  
         });       
         var sumRows =  $('#hisb').find('tr').length;  
         var sumPages = Math.ceil(sumRows/pageSize);//总页数        
     var $pager = $('<div class="page"></div>');  //新建div，放入a标签,显示底部分页码  
       for(var pageIndex = 0 ; pageIndex<sumPages ; pageIndex++){  
     $('<a href="#" id="pageStyle" onclick="changCss(this)"><span>'+(pageIndex+1)+'</span></a>').bind("click",{"newPage":pageIndex},function(event){  
                 currentPage = event.data["newPage"];  
                 $table.trigger("paging");  
                   //触发分页函数  
                 }).appendTo($pager);  
                 $pager.append(" ");  
             }     
             $pager.insertAfter( $('#hist'));  
             $table.trigger("paging");  
               
             var $pagess = $('#pageStyle');  
             $pagess[0].style.backgroundColor="#006B00"; 
             $pagess[0].style.color="#ffffff";  
    
      })