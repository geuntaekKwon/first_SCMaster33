<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		window.onload=function(){
			document.getElementById("btn_update").onclick=function(){
				location.href="noticeUpdate?noticenum=${notice.noticenum}";
			}//btn_update
			document.getElementById("btn_Delete").onclick=function(){
				location.href="noticeDelete?noticenum=${notice.noticenum}";
			}//function
		}//window
	</script>
</head>
<body>
	<div>
		<h2>[ 공지사항 글 보기 ]</h2>
			<table border="1">
				<tr>
					<th>제목</th>
					<td align="left">${notice.title}</td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td align="left">admin</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td align="left">${notice.originalfile}</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="10" cols="60" name="content" readonly="readonly">${notice.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<c:if test="${loginId == 'admin'}">
							<input type="button" value="수정" id="btn_update">
							<input type="button" value="삭제" id="btn_Delete">
						</c:if>
						<input type="button" value="뒤로가기" onclick="history.go(-1)">
					</td>
				</tr>
			</table>
	</div>
</body>
</html>