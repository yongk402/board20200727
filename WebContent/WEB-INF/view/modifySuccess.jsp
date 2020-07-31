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

<title>게시글 수정 성공</title>
</head>
<body>

게시글을 수정했습니다.
<br />
<!-- util패키지 AddContextPathListenerdp ctxPath 설정 해놔서 아래코드 필요 없음 -->
<!-- ${ctxPath = pageContext.request.contextPath ; ''} -->
<a href="${ctxPath }/article/list.do">[게시글목록보기]</a>
<a href="${ctxPath }/article/read.do?no=${modReq.articleNumber}">
[게시글내용보기]</a>

</body>
</html>