<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>写博客</title>
    <script src="<%=contextPath%>/static/jquery-3.1.1.js"></script>
    <script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon"/>
    <script type="text/javascript" charset="utf-8" src="/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/static/ueditor/ueditor.all.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/static/ueditor/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        div {
            width: 100%;
        }
    </style>
    <script type = "text/javascript"  language="JavaScript">
        function replaceContent() {
            var content = UE.getEditor('editor').getContent();
            $.ajax({
                url: "/manager/write",
                type: "post",
                data: {
                    "category": $("#cateoryInput").val(),
                    "id": $("#articleId").val(),
                    "title": $("#title").val(),
                    "content": content
                },
                success: function (data) {
                    $(window).attr('location', '/manager');
                },
                error: function (data) {
                    alert("fail!\n\n" + data);
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <form method="post" id="article">
        <br>
        <br>
        <div class="row">
            <div class="col-lg-6">
                <div class="input-group">
                    <div class="input-group-btn">
                        <c:choose>
                            <c:when test="${article==null}">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" id="categoryBtn">分类
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false" id="categoryBtn">${article.category}
                                </button>
                            </c:otherwise>
                        </c:choose>

                        <ul class="dropdown-menu">
                            <c:forEach var="category" items="${categories}">
                                <li>
                                    <a onclick="selectCategory('${category.name}','${category.displayName}');">${category.displayName}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <input name="category" id="cateoryInput" type="hidden">
                    <c:choose>
                        <c:when test="${article==null}">
                            <input type="text" id="title" class="form-control" placeholder="标题" name="title">
                        </c:when>
                        <c:otherwise>
                            <input name="id" id="articleId" type="hidden" value="${article.id}">
                            <input type="text" id="title" class="form-control" placeholder="标题" name="title"
                                   value="${article.title}">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
</div>
<br>
<div id="layout" style="width: 100%;height: 100%">
    <center>
    <c:choose>
    <c:when test="${article==null}">
        <script id="editor" type="text/plain" style="width:1024px;height:500px;">
        </script>
    </c:when>
        <c:otherwise>
    <script id="editor" type="text/plain" style="width:1024px;height:500px;">
            ${article.content}
    </script>
        </c:otherwise>
    </c:choose>
        </center>
    <script type = "text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    </script>
    <div class="row" style="margin-top: 30px">
        <div class="col-md-6 col-md-offset-6">
            <p>
                <input type="button" class="btn btn-primary btn-lg" role="button" value="发表" onclick="replaceContent()">
            </p>
        </div>
        </form>
    </div>
</div>
<script>
    function selectCategory(name, displayName) {
        $("#categoryBtn").html(displayName);
        $("#cateoryInput").val(name);
    }
</script>
</body>
</html>
