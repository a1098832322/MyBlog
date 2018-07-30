<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>详情</title>
    <link rel="stylesheet" href="/static/editormd/css/editormd.preview.css" />
</head>
<body>
<jsp:include page="comm/top.jsp"/>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">${article.title}<p style="float: right">${article.date}</p></h3>
        </div>
        <div class="panel-body" id="content-div">
            <span>
                <div>${article.content}</div>
            </span>
        </div>
    </div>
</div>
</body>
</html>