$(function() {
	alert("1")
	queryDetail();
});

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

function queryDetail(){
	alert(GetQueryString("workId"))
	var params = {};
	params.workId = GetQueryString("workId");
	$.ajax({
		type: "post",
		url: _basePath + "/work/queryWorkDetail",
		data: params,
		dataType:"json",
		async: true, //同步方式
		success: function(data) {
//			data = $.parseJSON(data);
//			alert(data.success)
//			var returnMap = data.data;
//			alert(returnMap.msg)
//			alert(returnMap.pageNo)
//			alert(returnMap.pageSize)
//			alert(returnMap.total)
//			alert(returnMap.workList[0].cid)

//			var j = 1;
// 			var table = document.getElementById("showTable");  
//            for(var i in returnMap.workList) {  
//                  
//                var tr = table.insertRow(table.rows.length);  
//                var td1 = tr.insertCell(0);  
//                td1.align = "center";  
//                var td2 = tr.insertCell(1);  
//                td2.align = "center";  
//                var td3 = tr.insertCell(2);  
//                td3.align = "center";
//                var td4 = tr.insertCell(2);  
//                td4.align = "center";  
//                  
//                td1.innerHTML = j;  
//                td2.innerHTML = returnMap.workList[i].cid;  
//                if(null != returnMap.workList[i].isdelName){
//                	td3.innerHTML = returnMap.workList[i].isdelName; 
//                } else {
//                	td3.innerHTML = ""; 
//                }
//                td4.innerHTML = "<a href='workDetail.jsp'>查看</a>";
////              html += "<tr>";  
////              html += "<td>"+ j +"</td>";  
////              html += "<td>"+ data.resultTree.root[i].userName +"</td>";  
////              html += "<td id='pwd\""+data.resultTree.root[i].id+"\"'>"+ data.resultTree.root[i].passWord +"</td>";  
////              html += "<td><a href='javascript:void(0);' onclick='update(\""+data.resultTree.root[i].id+"\",\""+data.resultTree.root[i].userName+"\",\""+data.resultTree.root[i].passWord+"\")'>更改</a>";  
////              html += "<a href='javascript:void(0);' onclick='del(\""+data.resultTree.root[i].id+"\")'>删除</a></td>";  
////              html += "</tr>";  
//                j++;  
//            }  
//            $("#showTable").html(html);  
         }        
     });
}