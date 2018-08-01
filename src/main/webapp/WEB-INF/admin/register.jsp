<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>用户注册</title>
    <script src="<%=contextPath%>/static/jquery-3.1.1.js"></script>
    <script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="<%=contextPath%>/static/signin.css" rel="stylesheet"/>
    <script language="JavaScript">
        function formCheck() {
            var nickName = $("#inputEmail").val();
            var pass = $("#inputPassword").val();
            var confirm = $("#confirmPassword").val();
            debugger;
            if (nickName.trim() != "" && nickName != null) {
                if (pass == confirm) {
                    return true;
                } else {
                    alert("两次密码输入的不匹配！");
                }
            } else {
                alert("输入的用户名为空！");
            }

            return false;
        }
    </script>
</head>
<body>
<div class="container">
    <form class="form-signin" action="/doRegister" method="post" onsubmit="return formCheck();">
        <h2 class="form-signin-heading">用户注册</h2>
        <p style="color: #ff0000"><%=request.getAttribute("error") == null ? "" : request.getAttribute("error")%>
        </p>
        <label for="inputEmail" class="sr-only">请输入用户名</label>
        <p>您的UID为：${userId}</p>
        <input type="hidden" name="username" value="${userId}">
        <input type="text" id="inputEmail" name="nickname" value="" class="form-control" placeholder="请输入用户名" required
               autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" value="" name="password" class="form-control" placeholder="密码"
               required>
        <label for="confirmPassword" class="sr-only">确认密码</label>
        <input type="password" id="confirmPassword" value="" class="form-control" placeholder="确认密码"
               required>
        <div class="checkbox">
            <%--<label>--%>
            <%--<input type="checkbox" value="remember-me"> 记住我--%>
            <%--</label>--%>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
    </form>

</div>
</body>
</html>
