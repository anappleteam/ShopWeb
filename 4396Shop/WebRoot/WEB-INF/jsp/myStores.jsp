<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>4396购物</title>

<link href="<%=path%>/css/slider.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css"/>

<jsp:include page="menu.jsp"/>
	
</head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>