<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		div{
			margin: 0 auto;
			text-align: center;
			width: 700px;
		}
		table {
			width: 700px;
		}
		input#btn_Write{
			float: right;
		}
		select#searchtype{
			float: right;
		}
		input#searchword{
			float: right;
		}
	</style>
	<script type="text/javascript">
		window.onload = function(){
			document.getElementById("btn_Write").onclick=function(){
				location.href="noticeWrite";
			}//function
		}//window.upload
	</script>
</head>
<body>
	<div>
		<h2>[ 공지사항 ]</h2>
		
		<div id="searchmenu">
			<select name="searchtype" id="searchtype">
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="text" name="searchword" id="searchword">
		</div>
		<div>
			<table border="1">
				<tr>
					<th>번호</th>
					<th>글제목</th>
					<th>글쓴날</th>
					<th>글쓴이</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="items" items="${list}" varStatus="stat">
					<tr>
						<td>${stat.count}</td>
						<td><a href="noticeDetail?noticenum=${items.noticenum}">${items.title}</a></td>
						<td>${items.inputdate}</td>
						<td>admin</td>
						<td>${items.hits}</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${loginId eq 'admin'}">
				<input type="button" value="글쓰기" id="btn_Write" >
			</c:if>
		</div>
		
		<!-- Paging 출력 부분 -->
		<div id="navigator">
			<a href="noticeList?currentPage=${navi.currentPage - navi.pagePerGroup}&searchword=${searchword}&searchtype=${searchtype}">◁◁</a>
			<a href="noticeList?currentPage=${navi.currentPage - 1}&searchword=${searchword}&searchtype=${searchtype}">◀</a>
				<c:forEach var="page" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
					<c:if test="${navi.currentPage eq page}">
						<span style="color: blue; font-weight: bolder; font-size: 2em;">${page}</span>
					</c:if>
					<c:if test="${navi.currentPage ne page}">
			 			<a href="noticeList?currentPage=${page}&searchword=${searchword}&searchtype=${searchtype}">${page}</a>
					</c:if>
				</c:forEach>
				&nbsp;&nbsp;
			<a href="noticeList?currentPage=${navi.currentPage + 1}&searchword=${searchword}&searchtype=${searchtype}">▶</a>
			<a href="noticeList?currentPage=${navi.currentPage + navi.pagePerGroup}&searchword=${searchword}&searchtype=${searchtype}">▷▷</a>
	
		</div>
	
	</div>
	
	
	
</body>
</html>