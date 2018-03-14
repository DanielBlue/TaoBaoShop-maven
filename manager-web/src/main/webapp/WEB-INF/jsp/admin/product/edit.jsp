<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<!--  -->
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/order/update_order" method="post">
			
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26"
						style="FONT-WEIGHT: bold; FONT-SIZE: 20pt; HEIGHT: 25px">
						<strong><STRONG>编辑订单</STRONG>
						</strong>
					</td>
				</tr>

				<tr style="FONT-WEIGHT: bold; font-size: 18px">
					<td width="30%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						取货单号：
					</td>
					<td width="30%" height="50px" class="ta_01" bgColor="#ffffff">
						<input type="text" name="oid" value="${order.oid}" id="userAction_save_do_oid" class="bg" readonly/>
					</td>

				</tr>
				<tr>
					<td width="30%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						淘宝单号：
					</td>
					<td width="30%" height="50px" class="ta_01" bgColor="#ffffff">
						<input type="text" name="taobao_code" value="${order.taobao_code}" id="userAction_save_do_taobao_code" class="bg"/>
					</td>
				</tr>
				<tr>
					<td width="30%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						快递单号：
					</td>
					<td class="ta_01" height="50px" bgColor="#ffffff" colspan="3">
						<input type="text" name="express_code" value="${order.express_code}"
							   id="userAction_save_do_express_code" class="bg" />
					</td>
				</tr>
				<tr>
					<td width="30%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						交易号：
					</td>
					<td class="ta_01" height="50px" bgColor="#ffffff" colspan="3">
						<input type="text" name="alipay_code" value="${order.alipay_code}"
							   id="userAction_save_do_alipay_code" class="bg" readonly/>
					</td>
				</tr>
				<tr style="FONT-WEIGHT: bold; FONT-SIZE: 20pt; HEIGHT: 25px">
					<td width="18%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						订单总价：
					</td>
					<td class="ta_01" height="50px" bgColor="#ffffff" colspan="3">
						${order.total_price}
					</td>
				</tr>
				<tr>
					<td width="18%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						日期：
					</td>
					<td class="ta_01" height="50px" bgColor="#ffffff" colspan="3">
						${order.date}
					</td>
				</tr>

				<tr>
					<td width="18%" height="50px" align="center" bgColor="#f5fafe" class="ta_01">
						订单状态：
					</td>
					<td class="ta_01" height="50px" bgColor="#ffffff" colspan="3">
						<c:if test="${order.order_state==0}">
							<select style="height: 30px" name="order_state">
								<option value="0">未完成</option>
								<option value="1">已完成</option>
								<option value="3">凭证遗失</option>
								<option value="4">遗失已完成</option>
							</select>
						</c:if>

						<c:if test="${order.order_state==1}">
							<select style="height: 30px" name="order_state">
								<option value="1">已完成</option>
								<option value="0">未完成</option>
								<option value="3">凭证遗失</option>
								<option value="4">遗失已完成</option>
							</select>
						</c:if>
						<c:if test="${order.order_state==3}">
							<select style="height: 30px" name="order_state">
								<option value="3">凭证遗失</option>
								<option value="0">未完成</option>
								<option value="1">已完成</option>
								<option value="4">遗失已完成</option>
							</select>
						</c:if>
						<c:if test="${order.order_state==4}">
							<select style="height: 30px" name="order_state">
								<option value="4">遗失已完成</option>
								<option value="0">未完成</option>
								<option value="1">已完成</option>
								<option value="3">凭证遗失</option>
							</select>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%;font-size: 18px;" align="center"
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