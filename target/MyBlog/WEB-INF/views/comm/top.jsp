<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<script src="<%=contextPath%>/static/jquery-3.1.1.js"></script>
<script src="<%=contextPath%>/static/bootstrap/js/bootstrap.js"></script>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<link href="<%=contextPath%>/static/bootstrap/css/bootstrap-theme.css" rel="stylesheet"/>
<div class="row">
    <nav class="navbar navbar-inverse">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=contextPath%>/">Wishes' Repertory</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="nv1">
                    <li class="active" id="firstPage"><a href="<%=contextPath%>/">首页</a></li>
                    <li id="webPage"><a href="<%=contextPath%>/column/JavaWeb/webPage">JavaWeb</a></li>
                    <li id="scriptPage"><a href="<%=contextPath%>/column/script/scriptPage">脚本/小程序</a>
                    </li>
                    <li id="otherPage"><a href="<%=contextPath%>/column/other/otherPage">一些好玩的小玩意儿</a>
                    </li>
                    <li id="talkPage"><a href="<%=contextPath%>/column/talk/talkPage">闲谈</a>
                    </li>
                </ul>
                <form class="navbar-form navbar-right" action="/search" method="get">
                    <div class="input-group">
                        <input name="keyWord" type="text" class="form-control" placeholder="可搜索文章标题，作者...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">Go!</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </nav>
</div>
<script>
    var href = location.href;
    var id = href.substring(href.lastIndexOf("/") + 1, href.length);
    if (id=="") {
        id = "firstPage";
    }
    var ids = ["firstPage", "webPage", "scriptPage", "otherPage", "talkPage"];
    for (var i = 0; i < ids.length; i++) {
        if (id == ids[i]) {
            $("#" + id).attr("class", "active");
        } else {
            $("#" + ids[i]).removeAttr("class");
        }
    }
</script>