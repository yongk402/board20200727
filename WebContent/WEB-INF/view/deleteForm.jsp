<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<title>게시글 삭제</title>
</head>
<body>
<form action="delete.do" method="post">
<p>
 	번호: ${delReq.articleNumber } <br />
	${param.no }를 삭제하시겠습니까?
	<input type="number" name="no" value="${delReq.articleNumber }" hidden />	
</p>


<input class="btn btn-primary" type="submit" value="글 삭제" />
<a class="btn btn-primary" href="${ctxPath }/article/list.do" role="button">취소</a>
</form>




</body>
</html>