
$("#searchTime").click(function(){
     var d=$("#day").val(); 
    var s= $("#time1").val();
        var e=$("#time2").val(); 
    if(d==""||eval(s)>=eval(e))
        {
          alert("请选择合法时间段");
        }else{
            var bl=$("#bookbody").find("tr").length;
            for(var i=1;i<bl;i++)
            {  $("#bookbody").find("tr").eq(1)
                .remove();
            }
            //alert( $("#bookbody").find("tr").length); 
            //$("#bookbody").html("");
             var data={}; 
             data['startTime']=d+" "+s+":00:00";
            data['endTime']=d+" "+e+":00:00";
          var settings={
            "async": true,
            "crossDomain": true,
            "url": host+"/meetingroom/user/free_meetingroom.do",
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
    var l=$("#bookbody").find("tr").length;
    var s1=$("#bookt").find("tr").eq(l); 
    for(var i=0;i<le;i++)
    {
        var mid=response['data'][i]['meetingId'];
        var fr=response['data'][i]['floor'];
        var room=response['data'][i]['room'];
        var lim=response['data'][i]['capacity'];
        var dev=response['data'][i]['dev'];
        
        var trHTML = "<tr>";
		trHTML += "<td>";
		trHTML += mid;
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += fr;
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += room;
		trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=  lim;
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML += dev;
        trHTML += "</td>";
    
    trHTML += "<td>";    
    trHTML+="<button class='btn btn-primary' type='button' style='background-color:rgb(109,194,255);color:rgb(10,8,8);'name='bbt' ><i class='fa fa-check' style='color:rgb(255,255,255);'></i></button>"
    trHTML += "</td></tr>";
        
     l=$("#bookbody").find("tr").length;
    $("#bookt").find("tr").eq(l).after(trHTML);
    }
            });
     var $table = $('#bookt');   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 7;//每一页显示的数目  
         $table.bind('paging',function(){  
             $table.find('bookbody tr').hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();  
         });       
         var sumRows = $table.find('bookbody tr').length;  
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
             $pager.insertAfter($table);  
             $table.trigger("paging");  
               
             //默认第一页的a标签效果  
             var $pagess = $('#pageStyle');  
             $pagess[0].style.backgroundColor="#006B00";  
             $pagess[0].style.color="#ffffff";  
  
}
});
    // alert(da.getFullYear()+" "+(da.getMonth()+1)+" "+da.getDate());

$(document).ready(function()
                 {
    var da=new Date();
    var mi=da.getFullYear()+"-";
    if(da.getMonth()<9)
        {
            mi+="0"+(da.getMonth()+1)+"-";
        }else
            {
                mi+=(da.getMonth()+1)+"-";
            }
     if(da.getDate()<9)
        {
            mi+="0"+da.Date()+1;
        }else
            {
                mi+=da.getDate();
            }
    $("#day").attr("min",mi);
    
    
})

//$("#time1").change(function(){
  // alert($("#time1").val());
//})

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

$("#bookok").on('click',"button[name='cancelm']",function(){ 
    $("#nobookla").text($(this).parent().parent().index());
    $("#readydel").modal("show");
})
$("#oknobook").click(function()
{
     $("#readydel").modal("hide");
     var data = {};
    var odex= $("#nobookla").text();
    var oid=$("#okbody").find("tr").eq(odex).find("td").eq(0).html();
    data['bookingId']=oid;
      var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/cancel_booking.do",
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
});
   $("#okbody").find("tr").eq(odex).remove();
    
})

 $(document).ready(function()
                   {
      var bl=$("#okbody").find("tr").length;
            for(var i=1;i<bl;i++)
            {  $("#okbody").find("tr").eq(1)
                .remove();
            }
      var data={};
     //用户id
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
     data['userId']=userId;
  var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/had_booking.do",
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
        var le=response['data'].length;
    var l=$("#okbody").find("tr").length;
    var s1=$("#bookok").find("tr").eq(l); 
    for(var i=0;i<le;i++)
    {
       var oid=response['data'][i]['bookingId'];
        var mid=response['data'][i]['meetingId'];
        var room=response['data'][i]['meetingRoom']['room'];
        var floor=response['data'][i]['meetingRoom']['floor'];
        var dev=response['data'][i]['meetingRoom']['dev'];
        var start=response['data'][i]['startTime'];
        var end=response['data'][i]['endTime'];
        var start=response['data'][i]['startTime'];
        var end=response['data'][i]['endTime'];
        var st=new Date(start);
        var ss=st.getFullYear()+"-"+(st.getMonth()+1)+"-"+st.getDate()+" "+st.getHours()+":"+st.getMinutes()+":"+st.getSeconds();
        
        var et=new Date(end);
        var es=et.getFullYear()+"-"+(st.getMonth()+1)+"-"+et.getDate()+" "+et.getHours()+":"+et.getMinutes()+":"+et.getSeconds();
        
          var trHTML = "<tr>";
		trHTML += "<td>";
		trHTML += oid;
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += mid;
		trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=  floor;
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML += room;
        trHTML += "</td>";
    
        trHTML+="<td>"
        trHTML+=ss;
        trHTML+="</td>"
         trHTML+="<td>"
        trHTML+=es;
        trHTML+="</td>"
         trHTML+="<td>"
        trHTML+=dev;
        trHTML+="</td>"
        trHTML += "<td>";
        
   trHTML+="<button class='btn btn-primary' type='button' style='background-color:rgb(247,74,63);color:rgb(255,255,255);'name='cancelm'><i class='fa fa-remove'></i></button>"
    trHTML += "<button class='btn btn-primary' type='button' style='background-color:rgb(127,204,90);color:rgb(255,255,255);margin-left:5px;width:38.5781px;padding-left:10px;'name='attend'><i class='fa fa-plus-circle'></i></button>"
         trHTML+='<label name="qcode" style="width:61px;height:34px;">查看</label>';
    trHTML += "</td></tr>"; 
         l=$("#okbody").find("tr").length;
        $("#bookok").find("tr").eq(l).after(trHTML);  
  }
});
 })




