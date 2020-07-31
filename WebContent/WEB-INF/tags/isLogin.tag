<%@ tag language="java" body-content="scriptless" pageEncoding="UTF-8"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--
	HttpSession httpSession = request.getSession(false);
	if (httpSession != null && httpSession.getAttribute("authUser") !=  null){
--%>

<c:if test="${not empty sessionScope.authUser }">
	<jsp:doBody />
</c:if>


