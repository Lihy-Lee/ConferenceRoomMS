
$("#bookt").on('click',"button[name='bbt']",function(){   $("#bookl").text($(this).parent().parent().index());
    $("#booking").modal("show");
})

$("#readybook").click(function() {
    
     var ind=$("#bookl").text();
    var hang=$("#bookbody").find("tr").eq(ind);
    var da=$("#day").val()+" ";
    var ts=$("#time1").val()+":00:00";
     var te=$("#time2").val()+":00:00";
    var mn=hang.find("td").eq(0).html();
    var fl=hang.find("td").eq(1).html();
    var rm=hang.find("td").eq(2).html();
    var dev=hang.find("td").eq(4).html();
    var l=$("#okbody").find("tr").length;
    var s1=$("#bookok").find("tr").eq(l);
    
    
    var data = {};
    var userId="";
    //用户id
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
     // data['userId'] = "111";
       data['userId'] = arr[1];
    //data['meetingId'] = mn;
    data['meetingId'] = mn;
    data['startTime'] = da+" "+ts;
    data['endTime'] =da+" "+te ;
    var bid="";
    
     var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/book_meetingroom.do",
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
        bid=response['data']['bookingId'];
            
     var trHTML = "<tr>";
    trHTML += "<td>";
		trHTML += bid;
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += mn;
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += fl;
		trHTML += "</td>";
        trHTML += "<td>";
		trHTML += rm;
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=data['startTime'];
        trHTML += "</td>";
     trHTML += "<td>";
		trHTML += data['endTime'];
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=  dev;
        trHTML += "</td>";
    trHTML += "<td>";
    
    trHTML+="<button class='btn btn-primary' type='button' style='background-color:rgb(247,74,63);color:rgb(255,255,255);'name='cancelm'><i class='fa fa-remove'></i></button>"
    trHTML += "<button class='btn btn-primary' type='button' style='background-color:rgb(127,204,90);color:rgb(255,255,255);margin-left:5px;width:38.5781px;padding-left:10px;'name='attend'><i class='fa fa-plus-circle'></i></button>"
        trHTML+='<label name="qcode" style="width:61px;height:34px;">查看</label>';
    trHTML += "</td></tr>";
          l=$("#okbody").find("tr").length;
        $("#bookok").find("tr").eq(l).after(trHTML);
    });
    
     var bl=$("#bookbody").find("tr").length;
            for(var i=1;i<bl;i++)
            {  $("#bookbody").find("tr").eq(1)
                .remove();
            }
    
    $("#booking").modal("hide");
})



$("#bookok").on('click',"button[name='attend']",function(){    
    $("#xuanla").text($(this).parent().parent().find("td").eq(0).html());
    var bl=$("#cehuabody").find("tr").length;
            for(var i=1;i<bl;i++)
            {  $("#cehuabody").find("tr").eq(1)
                .remove();
            }
     bl=$("#shibody").find("tr").length;
            for(var i=1;i<bl;i++)
            {  $("#shibody").find("tr").eq(1)
                .remove();
            }
     bl=$("#yunbody").find("tr").length;
            for(var i=1;i<bl;i++)
            {  $("#yunbody").find("tr").eq(1)
                .remove();
            }
   
    
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
    var le=response['data'].length;
    var l=$("#tbody").find("tr").length;
    var s1=$("#tbs").find("tr").eq(l); 
    for(var i=0;i<le;i++)
    {
        var uid=response['data'][i]['userId'];
        var un=response['data'][i]['name'];
        var sx=response['data'][i]['gender'];
         var role=response['data'][i]['role'];
        var dep=response['data'][i]['department'];
        var leixin="";
        
        if(dep=='策划部'){
            leixin="cehua";
        }
        else if(dep=='市场部'){
            leixin="shi";
        }
       else{
           leixin='yun';
       } 
             var trHTML = "<tr>";
     trHTML+="<td>"
    trHTML+="<input type='checkbox' name='ren'/>";
    trHTML+="</td>"
		trHTML += "<td>";
		trHTML += uid;
		trHTML += "</td>";
		trHTML += "<td>";
		trHTML += un;
		trHTML += "</td>";
        trHTML += "<td>";
		trHTML +=  sx;
        trHTML += "</td>";
        trHTML += "<td>";
		trHTML += role;
        trHTML += "</td>";
        
     l=$("#"+leixin+"body").find("tr").length;
    $("#"+leixin).find("tr").eq(l).after(trHTML);
    }
});
    
       $("div[name='deptli']").hide();
    $("input[type='checkbox'][name='ren']").prop('checked',false);
   $("#xuanren").modal("show");
});
    


$("#xuanren").on('click',"button[name='zhankai']",function(){  
    var dt=$(this).parent().find("div");
    if(dt.is(":hidden")){
   dt.show();
    }else{
             dt.hide();
        }
})

$("#attendok").click(function(){                   
    var ll="";
      $("input[type='checkbox'][name='ren']:checked").each(function() {
        ll+=$(this).parent().parent().find("td").eq(1).html()+" ";
    });
  
     var d=ll.split(" ");
    var userLists=[];
    for(var i=0;i<d.length-1;i++)
    {
        var datass ={};
        datass["userId"]=d[i];
        userLists[i]=datass;
        // userLists.append({'userId',d[i]});      
    }
    var data={
        'bookingId':$("#xuanla").text(),
       'userList':userLists
    };
     var settings={
  "async": true,
  "crossDomain": true,
  "url": host+"/meetingroom/user/add_attend.do",
  "method": "POST",
 "headers": {
    "Content-Type": "application/json",
    "Cache-Control": "no-cache"   
  },
     "processData": false,
    "data":JSON.stringify(data)
  };
     $("#xuanren").modal("hide");
     $.ajax(settings).done(function (response) {
         console.log(response);
        
});
     
});




$("#bookok").on('click',"label[name='qcode']",function(){ 
    $("#codeq").html("");
    var msg="";
 var hang=$(this).parent().parent().find("td");
    msg+="会议室编号:"+hang.eq(0).html()+"\n"+"会议室名:"+hang.eq(1).html()+"\n"+"楼层："+hang.eq(2).html()+"\n"+"房间"+hang.eq(3).html()+"\n"+"会议开始时间"+hang.eq(4).html()+"\n"+"会议室设备:"+hang.eq(6).html();
     jQuery("#codeq").qrcode(toUtf8(msg));
   $("#showqcode").modal("show");
})
// $("#qcode").click(function(){
//     jQuery("#codeq").qrcode(toUtf8("啊啊啊\n我是说我"));
//    $("#showqcode").modal("show");
// })

function toUtf8(str) {    
    var out, i, len, c;    
    out = "";    
    len = str.length;    
    for(i = 0; i < len; i++) {    
        c = str.charCodeAt(i);    
        if ((c >= 0x0001) && (c <= 0x007F)) {    
            out += str.charAt(i);    
        } else if (c > 0x07FF) {    
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));    
            out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));    
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
        } else {    
            out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));    
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
        }    
    }    
    return out;    
} 
$("#exitqcode").click(function()
{
         $("#showqcode").modal("hide");
})
