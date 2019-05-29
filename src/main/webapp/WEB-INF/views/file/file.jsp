<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/base/base.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>模拟商户页面</title>
    <link rel='stylesheet' href="${ctx}/static/css/base.css"/>
    <link rel='stylesheet' href="${ctx}/static/css/index.css"/>
    <script src="${ctx}/scripts/jquery-3.0.0.min.js"></script>
    <style type="text/css">
        .quickAPI h3 {
            height: 0px;
        }
    </style>
    <script type="text/javascript">

        function subFrom() {

            var flag = false;
            $(".check_required").each(function () {
                var id = this.id
                var value = $("#" + id).val();
                if (value == '' || value == null) {
                    alert(id + "为空")
                    flag = true;
                    return false;
                }
            });

            if (flag) {
                return false;
            }

            $.post($("#fromId").attr("action"), $("#fromId").serialize(), function (data) {
                if (data == "") {
                    alert("未知异常");
                    return false;
                }
                if (data == "0") {
                    alert("下载成功");
                } else {
                    alert("下载异常");
                }
            });
        }
    </script>
</head>
<body>
<div class="main">
    <div class="quickAPI">
        <form id="fromId" method="post" action="${ctx}/file/toDownload">
            <h3>对账文件下载 请求参数</h3>
            <br>
            <br>
            <p><label>下载方式:</label>
                <select name="type" id="type">
                    <option value="ftp">ftp</option>
                    <option value="sftp">sftp</option>
                </select>
            </p>

            <p>
                <label>本地下载路径:</label><input name="localPath" id="localPath" type="text" value="D:/yoyi" placeholder="必填"/>
            </p>
            <p>
                <label>远程地址:</label><input name="ipAddr" id="ipAddr" type="text" value="60.12.221.84" placeholder="必填"/>
            </p>
            <p><label>端口号:</label><input name="port" id="port" type="text" value="2100" placeholder="必填"/></p>

            <p><label>用户名:</label><input name="userName" id="userName" type="text" value="M100002565" placeholder="必填"/>
            </p>
            <p><label>密码:</label><input name="pwd" id="pwd" type="text" value="Y08#272D" placeholder="必填"/></p>
            <p><label>路径:</label><input name="path" id="path" type="text" value="/duizhangwenjian" placeholder="必填"/></p>
            <p><label>下载文件名称:</label><input name="fileName" id="fileName" type="text" placeholder="非必填"/></p>
            <p>
                <input type='button' value='提 交' onclick="subFrom()"/>
                <input type='button' value='返 回' onclick="javascript:history.go(-1)"/>
            </p>
        </form>
    </div>

</div>
</body>
</html>