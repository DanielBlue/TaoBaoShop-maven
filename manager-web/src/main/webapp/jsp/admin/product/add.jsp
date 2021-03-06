<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<!--  -->
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/order/add_order" method="post">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>添加订单</STRONG>
						</strong>
					</td>
				</tr>

				<c:if test="${error_message!=null}">
					<tr>
						<td align="center" colspan="2"><span style="color: red; font-size: 20px">${error_message }</span></td>
					</tr>
				</c:if>

				<tr style="FONT-WEIGHT: bold; font-size: 18px">
					<td width="30%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						取货单号：
					</td>
					<td width="30%" height="50px" class="ta_01" bgColor="#ffffff">
						<input type="text" name="oid" value="${order.oid}" id="userAction_save_do_oid" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="30%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						淘宝单号：
					</td>
					<td width="30%" height="50px" class="ta_01" bgColor="#ffffff">
						<input type="text" name="taobaoCode" value="${order.taobaoCode}" id="userAction_save_do_taobaoCode" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="30%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						快递单号：
					</td>
					<td class="ta_01" height="50px" bgColor="#ffffff" colspan="3">
						<input type="text" name="expressCode" value="${order.expressCode}"
							   id="userAction_save_do_expressCode" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="30%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						交易号：
					</td>
					<td class="ta_01" height="50px" bgColor="#ffffff" colspan="3">
						<input type="text" name="alipayCode" value="${order.alipayCode}"
							   id="userAction_save_do_alipayCode" class="bg"/>
					</td>
				</tr>
				<tr style="FONT-WEIGHT: bold; FONT-SIZE: 20pt; HEIGHT: 25px">
					<td width="18%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						订单总价：
					</td>
					<td class="ta_01" height="50px" bgColor="#ffffff" colspan="3">
						<input type="text" name="totalPrice" value="${order.totalPrice}"
							   id="userAction_save_do_totalPrice" class="bg"/>
					</td>


				</tr>

				<tr style="FONT-WEIGHT: bold; FONT-SIZE: 20pt; HEIGHT: 25px">
					<td width="18%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						备注：
					</td>
					<td class="ta_01" height="50px" bgColor="#ffffff" colspan="3">
						<input type="text" name="remark" value="${order.remark}"
							   id="userAction_save_do_remark" class="bg"/>
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>