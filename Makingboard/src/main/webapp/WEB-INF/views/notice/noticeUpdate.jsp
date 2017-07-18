<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		table{
			margin-left: 115px;
		}
	</style>
</head>
<body>
	<div>
		<h2>[ 공지사항 쓰기 ]</h2>
		<form action="noticeUpdate?noticenum=${notice.noticenum}" method="POST" enctype="multipart/form-data">
			<table border="1">
				<tr>
					<th>제목</th>
					<td align="left"><input type="text" name="title" value="${notice.title}"></td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td align="left">${loginId}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td align="left"><input type="file" name="fileChange">${notice.originalfile}</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="10" cols="60" name="content">${notice.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정">
						<input type="button" value="취소" onclick="history.go(-1)">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>