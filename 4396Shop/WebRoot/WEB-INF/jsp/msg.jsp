<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息</title>
<jsp:include page="menu.jsp"/>
</head>
<body>
<div>
<table width="80%" border="0" cellspacing="0" style="margin-top:200px">
      <tr>
      	<td><img src="${pageContext.request.contextPath}/images/notice.png" width="128" height="128"style=" margin-left:80%" ></td>
        <td style="padding-top:30px"><font style="font-weight:bold;">
	        <s:actionmessage /><s:if test="#session.loginMessage!=null">
	        <s:property value="#session.loginMessage"/>
	        </s:if>
	        <br>
        </font>
        <br><a href="${pageContext.request.contextPath}/index.action">返回首页</a>
            <br>
         </td>
      </tr>
    </table>
    <h1>&nbsp;</h1>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>