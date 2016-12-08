<%@ page language="java"   pageEncoding="UTF-8"%>
<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 --%>
<script type="text/javascript">
path="${pageContext.request.contextPath}";
</script>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<%--css 
<link   rel="stylesheet" href="${path }/css/common.css">
<link   rel="stylesheet" href="${path }/css/jquery-ui.css">
<link   rel="stylesheet" href="${path }/css/reset.css">
--%>
<script type="text/javascript" src="${path}/js/jquery-2.2.2.min.js"></script>
<%--script

<script type="text/javascript" src="${path}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${path}/js/messages_cn.js"></script>
<script type="text/javascript" src="${path}/js/jquery-ui.js"></script>
<script type="text/javascript" src="${path}/js/pager.release.js"></script>
 --%>
