<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/jquery/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
	<title>Insert title here</title>
</head>
<body>

	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<%-- <form action="${pageContext.request.contextPath }/gb/add" method="post"> --%>
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" id="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" id="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<!-- <td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td> -->
								<td colspan=4 align=right><input id="btnWrite" type="button" VALUE="확인 " /></td>
							</tr>
						</table>
					<!-- </form> --><!-- 입려끝 -->
					
					<ul id="listArea">
					</ul>
					<input id="btnNext" type="button" value="다음글 5개 가져오기"/>
					<%-- <c:forEach items="${guestList }" var="vo" >
					
				
					<ul>
						<li>
							
							<table>
								<tr>
									<td>${vo.no }</td>
									<td>${vo.name }</td>
									<td>${vo.regDate }</td>
									<td><a href="${pageContext.request.contextPath }/gb/deleteform?no=${vo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
									${vo.content }
									</td>
								</tr>
							</table>
							<br>
						</li>
					</ul>
						</c:forEach> --%>
					
					
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		
	</div> <!-- /container -->


	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					 <input type="text" name="modalPassword" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</body>

<script type="text/javascript">
$(document).ready(function(){
	
	var page=1;
	
	$.ajax({
		//줄때
		url : "${pageContext.request.contextPath }/gb/api/list",
		type : "post",
		data : {page : page},
		//contentType : "application/json",  
		//contentType : "text",
		//data : JSON.stringify(userVo),     //이렇게 보낼경우 리퀘스트? 리스폰스? 헤더영역에 들어감 
		
		
		//받을때 데이터타입 
		dataType : "json",
		success : function(guestList){
			
			console.log(guestList);
			
			for(var i=0; i<guestList.length; i++){
				render(guestList[i],"down");
			}
					
		/*성공시 처리해야될 코드 작성*/
		},
		/*연결실패시 */
		error : function(XHR, status, error) {
		console.error(status + " : " + error);
		}
		});
	
	$("ul").on("click",".btnDel", function(){
		var no= $(this).data("no");
		
		
		$("#modalNo").val(no);
		$("#del-pop").modal();
	
	});
	
	$("#btn_del").on("click",function(){
		
		console.log("클릭");
		var noAndPassword = {
				no : $("#modalNo").val(),
				password : $("#modalPassword").val()
		};
		
		
		$.ajax({
			//줄때
			url : "${pageContext.request.contextPath }/gb/api/deleteGuestBook",
			type : "post",
			data : noAndPassword,
			//contentType : "application/json",  
			//contentType : "text",
			//data : JSON.stringify(userVo),     //이렇게 보낼경우 리퀘스트? 리스폰스? 헤더영역에 들어감 
			
			
			//받을때 데이터타입 
			dataType : "json",
			success : function(no){
				$("#del-pop").modal("hide");
				
			
				$("#"+no).remove();
				
				
							
			/*성공시 처리해야될 코드 작성*/
			},
			/*연결실패시 */
			error : function(XHR, status, error) {
			console.error(status + " : " + error);
			}
			});
		
	});
	
	
	$("#btnNext").on("click", function(){
		
		page = page+1 ;
		
		$.ajax({
			//줄때
			url : "${pageContext.request.contextPath }/gb/api/list",
			type : "post",
			data : {page : page},
			//contentType : "application/json",  
			//contentType : "text",
			//data : JSON.stringify(userVo),     //이렇게 보낼경우 리퀘스트? 리스폰스? 헤더영역에 들어감 
			
			
			//받을때 데이터타입 
			dataType : "json",
			success : function(guestList){
				
				console.log(guestList);
				
				for(var i=0; i<guestList.length; i++){
					render(guestList[i],"down");
				}
							
			/*성공시 처리해야될 코드 작성*/
			},
			/*연결실패시 */
			error : function(XHR, status, error) {
			console.error(status + " : " + error);
			}
			});
		
	});
	
	
$("#btnWrite").on("click", function(){
	var newWriting = {
		name :$("#name").val(),
		password :$("#password").val(),
		content : $("textarea#content").val()
	}

	
	/* var name = $("#name").val();
	var password = $("#password").val();
	var content = $("#content").val(); */
	

	
		
		$.ajax({
			//줄때
			url : "${pageContext.request.contextPath }/gb/api/insertGuestBook",
			type : "post",
			data : newWriting,
			//contentType : "application/json",  
			//contentType : "text",
			//data : JSON.stringify(userVo),     //이렇게 보낼경우 리퀘스트? 리스폰스? 헤더영역에 들어감 
			
			
			//받을때 데이터타입 
			dataType : "json",
			success : function(guestVo){
				render(guestVo,"up");
				console.log(guestVo);
				/* console.log(guestList);
				
				for(var i=0; i<guestList.length; i++){
					render(guestList[i],"down"); 
				}*/
							
			/*성공시 처리해야될 코드 작성*/
			},
			/*연결실패시 */
			error : function(XHR, status, error) {
			console.error(status + " : " + error);
			}
			});
		
	});
	
});



function render(guestVo, updown) {  //글이 위에서 붙을 수 있게 flag 로 updown 넣어놈 
	
	var str = "";
	str += "<li id='"+guestVo.no+"'>";
	str += "	<table>";
	str += "     <tr>";
	str += "		<td>["+guestVo.no+"]</td>";
	str += "		<td>"+guestVo.name+"</td>";
	str += "		<td>"+guestVo.regDate+"</td>";
	str += " 		<td><input type='button' class='btnDel' value='삭제' data-no='"+ guestVo.no+"'>";    //원래의 "" 는 '' 로 바꿔준다
	str += "     </tr>";
	str += "     <tr>";
	str += "     <td colspan=4>"+guestVo.content+"</td>";
	str += "     </tr>";
	str += "	</table>";
	str += "	<br>";
	str += "</li>";	
	
	if(updown == "up") {
		$("#listArea").prepend(str);
	}else if (updown == "down"){
		$("#listArea").append(str);
	}else {
		console.log("updown 오류");
	}
	
	
}
</script>
</html>