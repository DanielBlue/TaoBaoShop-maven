<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>淘宝订单管理平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">
        body {
            color: white;
        }
    </style>
</head>
<body style="background: #278296">
<form method="post" action="${pageContext.request.contextPath }/admin/login.action">
    <table cellspacing="0" cellpadding="0" style="margin-top: 300px" align="center">
        <tr>
            <td style="padding-left: 50px">
                <table>
                    <tr><td><span style="color: red; font-size: larger">${loginInfo }</span></td></tr>
                    <tr>
                        <td>管理员账号：</td>
                        <td><input type="text" name="admin.adminName"/></td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td>管理员密码：</td>
                        <td><input type="password" name="admin.adminPassword"/></td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><input type="checkbox" name="autologin" value="autologin"/>自动登录</td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><input type="submit" value="进入管理平台" class="button"/></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<script language="JavaScript">
    <!--
    document.forms['theForm'].elements['username'].focus();

    /**
     * 检查表单输入的内容
     */
    function validate() {
        var validator = new Validator('theForm');
        validator.required('username', user_name_empty);
        //validator.required('password', password_empty);
        if (document.forms['theForm'].elements['captcha']) {
            validator.required('captcha', captcha_empty);
        }
        return validator.passed();
    }

    //-->
</script>
</body>