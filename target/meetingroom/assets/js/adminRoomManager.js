

$(document).ready(function()
 {
    var data={};
    //var data=[{'userId':3,'name':4},{'cool':4}]
  var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/admin/all_meetingRoom.do",
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
    var l=$("#mbody").find("tr").length;
    var s1=$("#addtables").find("tr").eq(l); 
    for(var i=0;i<le;i++)
    {
        var mid=response['data'][i]['meetingId'];
        var floor=response['data'][i]['floor'];
        var room=response['data'][i]['room'];
        var lim=response['data'][i]['capacity'];
        var dev=response['data'][i]['dev'];
        
          var trHTML = "<tr>";
     trHTML+="<td>"
    trHTML+="<input type='checkbox' name='xuan'/>";
    trHTML+="</td>"
		trHTML += "<td>";
		trHTML += mid;
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += floor;
		trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=  room;
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML += lim;
        trHTML += "</td>";
    
    trHTML+="<td>"
    trHTML+=dev;
    trHTML+="</td>"
    trHTML += "<td>";
    
    trHTML+="<button class='btn btn-primary' type='button' style='background-color:rgb(242,193,67);'name='addm'><i class='fa fa-wrench'></i></button>"
    trHTML += "<button class='btn btn-primary' type='button' style='margin:-1px;padding:4px;margin-left:5px;width:37px;background-color:rgb(220,53,69);height:38px;'name='delm'><i class='fa fa-trash'></i></button>"
    trHTML += "</td></tr>"; 
         l=$("#mbody").find("tr").length;
        $("#addtables").find("tr").eq(l).after(trHTML);
    
  }
    var $table = $('#addtables');   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 6;//每一页显示的数目  
         $table.bind('paging',function(){  
             $table.find('tbody tr').hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();  
         });       
         var sumRows = $table.find('tbody tr').length;  
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
               
             var $pagess = $('#pageStyle');  
             $pagess[0].style.backgroundColor="#006B00"; 
             $pagess[0].style.color="#ffffff";  
});
})





//显示
$("#addtables").on('click',"button[name='addm']",function(){
    $("#modifyshow").modal("show");
      $("#ll2").text( $(this).parent().parent().index());
    var ss=
       $(this).parent().parent().find("td");
    $("#alterMeetingRoomName").val(ss.eq(1).html());
     $("#alterFloor").val(ss.eq(2).html());
     $("#alterRoomNumber").val(ss.eq(3).html());
     $("#alterCapacity").val(ss.eq(4).html());   //$("#department").find("option[value="+dep+"]").attr("selected",true);
    $("input[type='checkbox'][value='dv']").prop('checked',false);
    var de= ss.eq(5).html();
    var arr=de.split(' ');
    for(var i=0;i<arr.length;i++){
    $("input[type='checkbox'][name="+arr[i]+"]").prop('checked',true);
    }
})
//修改会议室
$("#changem").click(function()
                   {
        var ind=$("#ll2").text();
   var hang=$("#mbody").find("tr").eq(ind);
    hang.find("td").eq(1).html($("#alterMeetingRoomName").val());
    hang.find("td").eq(2).html($("#alterFloor").val());
    hang.find("td").eq(3).html($("#alterRoomNumber").val());
    hang.find("td").eq(4).html($("#alterCapacity").val());
    var ll="";
    $("input[type='checkbox'][value='dv']:checked").each(function() {
        ll+=$(this).attr('name')+" ";
    });
    hang.find("td").eq(5).html(ll);
   $("#modifyshow").modal("hide");
    
     var data = {};
    data['meetingRoomId'] = $("#alterMeetingRoomName").val();
    data['capacity'] = $("#alterCapacity").val();
    data['dev'] =ll;
    
      var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/admin/update_meetingroom.do",
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
    
})

//delete
$("#addtables").on('click',"button[name='delm']",function(){
    var hang=$(this).parent().parent();
    hang.remove();
    var data = {};
    data['meetingRoomId'] = hang.find("td").eq(1).html();
    
      var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/admin/delete_meetingroom.do",
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
      var $table = $('#addtables');   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 6;//每一页显示的数目  
         $table.bind('paging',function(){  
             $table.find('tbody tr').hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();  
         });       
         var sumRows = $table.find('tbody tr').length;  
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
    
     $('#adroomdelete').modal('show');
})



$('#addMeetingRoombtn').click(
function(){
    $("#addMeetingRoomName").val("");
     $("#addFloor").val("");
     $("#addRoomNumber").val("");
     $("#addCapacity").val("");
        $("input[type='checkbox'][value='adv']").prop('checked',false);
    $('#newMeetingRoom').modal('show');
});

//增加会议室
$('#addConfirm').click(function(){
    $('#newMeetingRoom').modal('hide');
    var l=$("#mbody").find("tr").length;
    var s1=$("#addtables").find("tr").eq(l); 
     var ll="";
    $("input[type='checkbox'][value='adv']:checked").each(function() {
        ll+=$(this).attr('name')+" ";
    });
    
     var trHTML = "<tr>";
     trHTML+="<td>"
    trHTML+="<input type='checkbox' name='xuan'/>";
    trHTML+="</td>"
		trHTML += "<td>";
		trHTML += $("#addMeetingRoomName").val();
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += $("#addFloor").val();
		trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=  $('#addRoomNumber').val();
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML += $('#addCapacity').val();
        trHTML += "</td>";
    
    trHTML+="<td>"
    trHTML+=ll;
    trHTML+="</td>"
    trHTML += "<td>";
    
    trHTML+="<button class='btn btn-primary' type='button' style='background-color:rgb(242,193,67);'name='addm'><i class='fa fa-wrench'></i></button>"
    trHTML += "<button class='btn btn-primary' type='button' style='margin:-1px;padding:4px;margin-left:5px;width:37px;background-color:rgb(220,53,69);height:38px;'name='delm'><i class='fa fa-trash'></i></button>"
    trHTML += "</td></tr>";   $("#addtables").find("tr").eq(l).after(trHTML);
    
    var data = {};
    data['floor'] = $("#addFloor").val();
    data['capacity'] =$('#addCapacity').val();
    data['room'] = $('#addRoomNumber').val();;
    data['dev'] =ll;
    
      var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/admin/add_meetingroom.do",
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
          s1=$("#mbody").find("tr").eq(l); 
         s1.find("td").eq(1).html(response['data']['meetingId']);
});
//
        var $table = $('#addtables');   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 6;//每一页显示的数目  
         $table.bind('paging',function(){  
             $table.find('tbody tr').hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();  
         });       
         var sumRows = $table.find('tbody tr').length;  
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
    
    // add_meetingroom.do
}
                       
                       
);

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

$("#loadm").click(function(){
    var ll="";
   $("input[type='checkbox'][name='xuan']:checked").each(function() {       ll+=$(this).parent().parent().find("td").eq(1).html()+" ";
    });
    
window.open("http://172.18.203.247:8080/meetingroom/admin/export_excel.do"); 

}
 )

$("#addeleteroomok").click(function()
  {
    $("#adroomdelete").modal("hide");
})


$("#jinjijiuhuche2").click(function(){
            var $table = $('#addtables');   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 6;//每一页显示的数目  
         $table.bind('paging',function(){  
             $table.find('tbody tr').hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();  
         });       
         var sumRows = $table.find('tbody tr').length;  
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
    
      })