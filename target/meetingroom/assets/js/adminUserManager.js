
$(document).ready(function()
 {
    var data={};
    //var data=[{'userId':3,'name':4},{'cool':4}]
  var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/all_user.do",
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
    var l=$("#tbody").find("tr").length;
    var s1=$("#tbs").find("tr").eq(l); 
    for(var i=0;i<le;i++)
    {
        var uid=response['data'][i]['userId'];
        var un=response['data'][i]['name'];
        var sx=response['data'][i]['gender'];
        var ema=response['data'][i]['email'];
         var role=response['data'][i]['role'];
          var role=response['data'][i]['role'];
        var dep=response['data'][i]['department'];
        
         var trHTML = "<tr>";
		trHTML += "<td>";
		trHTML += uid
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += un;
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += sx
		trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=  ema;
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML += role;
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML += dep;
        trHTML += "</td>";
    
    trHTML += "<td>";    
    trHTML+="<button class='btn btn-primary' type='button' style='background-color:rgb(242,193,67);'name='bt'><i class='fa fa-wrench'></i></button>"
    trHTML += "<button class='btn btn-primary' type='button' style='margin:-1px;padding:4px;margin-left:5px;width:37px;background-color:rgb(220,53,69);height:38px;'name='del'><i class='fa fa-trash'></i></button>";
     trHTML+='<button class="btn btn-primary" type="button" style="background-color:rgb(176,13,13);margin-left:13px;"name="resetuser"><i class="fa fa-refresh"></i></button>';
    trHTML += "</td></tr>";
        
     l=$("#tbody").find("tr").length;
    $("#tbs").find("tr").eq(l).after(trHTML);
    }
     var $table = $('#tbs');   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 7;//每一页显示的数目  
         $table.bind('paging',function(){  
             $table.find('tbody tr').hide().slice(currentPage*pageSize,(currentPage+1)*pageSize).show();  
         });       
         var sumRows = $table.find('tbody tr').length;  
         var sumPages = Math.ceil(sumRows/pageSize);
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
});  
});


//修改用户信息
$('#confirmbtn').click(
function(){
    var ind=$("#lle").text();
    var hang=$("#tbody").find("tr").eq(ind);
hang.find("td").eq(0).html($("#admodifyID").val());
     hang.find("td").eq(1).html($("#admodifyname").val());
    hang.find("td").eq(2).html($("#admodifygender").val());
    hang.find("td").eq(4).html($("#admodifytype").val());
    hang.find("td").eq(5).html($("#admodifydepartment").val());
    $('#adusermodify').modal('hide');
    
   // var id=parseInt($("#ID").val());
     var id=$("#admodifyID").val();
    var name=$("#admodifyname").val();
    var gender=$("#admodifygender").val();
    var role=$("#admodifytype").val();
    var dept=$("#admodifydepartment").val();
    var sta= "0";
    
    var data = {};
    data['userId'] = id;
    data['name'] = name;
    data['gender'] = gender;
    data['department'] = dept;
    data['role'] = role;
    data['status'] = sta;
    
     var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/admin/update_user.do",
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
});

function deleter(){
    $('#previewx').modal('show');
}
$('#exitbtn').click(
function(){
    $('#previewx').modal('hide');
});

//删除用户
$("#tbs").on('click',"button[name='del']",function(){
    var hang=$(this).parent().parent();
    hang.remove();
     $('#previewx').modal('show');
     var data = {};
    //data['userId'] = hang.find("td").eq(0).html();
      data['userId'] = hang.find("td").eq(0).html();
    data['name'] = hang.find("td").eq(1).html();
    data['gender'] = hang.find("td").eq(2).html();
    data['department'] = hang.find("td").eq(5).html();
    data['role'] = hang.find("td").eq(4).html();
    data['status'] ="1";
    
     var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/admin/update_user.do",
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


$('#adadduserbt').click(
function(){
    $("#adaddID").val("");
    $("#adaddname").val("");
    $('#adadduser').modal('show');
    
});


//显示用户信息
$("#tbs").on('click',"button[name='bt']",function(){
    var ss=
       $(this).parent().parent().find("td");
    $("#lle").text( $(this).parent().parent().index());
   $("#admodifyID").val(ss.eq(0).html());
    $("#admodifyname").val(ss.eq(1).html());
    var s=ss.eq(2).html();;
  // var gen=ss.eq(2).html();
     $("#admodifygender").find("option[value="+s+"]").attr("selected",true);
    var typ=ss.eq(4).html();
   $("#admodifytype").find("option[value="+typ+"]").attr("selected",true);
   var dep=ss.eq(5).html();
    //gen=ss.eq(5).html(); 
   //if(gen=="策划部") {dep=12;}  
 $("#admodifydepartment").find("option[value="+dep+"]").attr("selected",true);
  $("#adusermodify").modal("show");
   
})

//添加用户
$("#adaddsave").on('click',function(){
    $("#adadduser").modal("hide");
    
     var data = {};
    data['userId'] = $("#adaddID").val();
    data['name'] = $("#adaddname").val();
    data['gender'] = $("#adaddgender").val();
    data['department'] =$('#adadddepartment').val();
    data['role'] = $('#adaddtype').val();
       var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/admin/add_user.do",
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
        if(response['status']=='1')
            {
                alert(response['msg']);
            }else
                {
                     var l=$("#tbody").find("tr").length;
    var s1=$("#tbs").find("tr").eq(l);   
     var trHTML = "<tr>";
		trHTML += "<td>";
		trHTML += $("#adaddID").val();
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += $("#adaddname").val();
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += $("#adaddgender").val();
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += "null";
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=  $('#adaddtype').val();
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML += $('#adadddepartment').val();
        trHTML += "</td>";
    
    trHTML += "<td>";
    
    trHTML+="<button class='btn btn-primary' type='button' style='background-color:rgb(242,193,67);'name='bt'><i class='fa fa-wrench'></i></button>"
    trHTML += "<button class='btn btn-primary' type='button' style='margin:-1px;padding:4px;margin-left:5px;width:37px;background-color:rgb(220,53,69);height:38px;'name='del'><i class='fa fa-trash'></i></button>";
     trHTML+='<button class="btn btn-primary" type="button" style="background-color:rgb(176,13,13);margin-left:13px;"name="resetuser"><i class="fa fa-refresh"></i></button>';
    trHTML += "</td></tr>"; 
    $("#tbs").find("tr").eq(l).after(trHTML);
    
    
     var $table = $('#tbs');   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 7;//每一页显示的数目  
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
    
                }
    });
    
    //发送数据
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



$("#jinjijiuhuche").click(function(){
                   
     var $table = $('#tbs');   
    $('#pageStyle').parent().remove();
         var currentPage = 0;//当前页默认值为0  
         var pageSize = 7;//每一页显示的数目  
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


$("#tbs").on('click',"button[name='resetuser']",function(){
 var userId=$(this).parent().parent().find("td").eq(0).html();
    
     var data = {};
    data['userId'] = userId;
       var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/admin/reset_user_psw.do",
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
        alert(response['msg']);
    });
})
