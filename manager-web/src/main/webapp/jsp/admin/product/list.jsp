<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" uri="http://mao.pers/common/" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/dataTables.bootstrap.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <script language="javascript"
            src="${pageContext.request.contextPath}/js/public.js"></script>
    <script language="javascript"
            src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">

        $(function () {
            $("tr.parent").click(function () {
                $(this).siblings(".child_" + this.id).toggle();
            }).click();

            $("td.td_edit").click(function (e) {
                e.stopPropagation();
            })

            $("td.td_delete").click(function (e) {
                e.stopPropagation();
            })
        })

        function addProduct() {
            window.location.href = "add.jsp";
        }

    </script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1"
      action="${pageContext.request.contextPath}/order/order_list?currentPage=1"
      method="post">

    &nbsp;&nbsp;订单号：<input type="text" name="oid" value="${oid}">

    &nbsp;&nbsp;&nbsp;&nbsp;快递单号：<input type="text" name="express_code" value="${express_code}">

    &nbsp;&nbsp;&nbsp;&nbsp;订单状态：<select style="height: 30px" name="order_state">
    <c:if test="${order_state==0}">
        <option value="0" selected="selected">未完成</option>
        <option value="1">已完成</option>
        <option value="2">不限</option>
        <option value="3">凭证遗失</option>
        <option value="4">遗失已完成</option>
    </c:if>

    <c:if test="${order_state==1}">
        <option value="0">未完成</option>
        <option value="1" selected="selected">已完成</option>
        <option value="2">不限</option>
        <option value="3">凭证遗失</option>
        <option value="4">遗失已完成</option>
    </c:if>

    <c:if test="${order_state==2}">
        <option value="0">未完成</option>
        <option value="1">已完成</option>
        <option value="2" selected="selected">不限</option>
        <option value="3">凭证遗失</option>
        <option value="4">遗失已完成</option>
    </c:if>

    <c:if test="${order_state==3}">
        <option value="0">未完成</option>
        <option value="1">已完成</option>
        <option value="2">不限</option>
        <option value="3" selected="selected">凭证遗失</option>
        <option value="4">遗失已完成</option>
    </c:if>

    <c:if test="${order_state==4}">
        <option value="2">不限</option>
        <option value="0">未完成</option>
        <option value="1">已完成</option>
        <option value="3">凭证遗失</option>
        <option value="4" selected="selected">遗失已完成</option>
    </c:if>
</select>
    &nbsp;&nbsp;&nbsp;&nbsp;每页展示数：
    <select style="height: 30px" name="count">
        <c:if test="${count==5}">
            <option value="5" selected="selected">5</option>
            <option value="10">10</option>
        </c:if>
        <c:if test="${count==10}">
            <option value="5">5</option>
            <option value="10" selected="selected">10</option>
        </c:if>
    </select>
    &nbsp;&nbsp;<input type="submit" value="搜索">
    <br/>
    <br/>
    <table cellSpacing="1" cellPadding="0" width="100%" align="center"
           bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>订单列表</strong>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right">
                <button type="button" id="add" name="add" value="添加"
                        style="height: 30px;text-align: center;border: 1px solid #8AA2CC;color: #2F3F5B;margin-right: 20px"
                        onclick="addProduct()">
                    &#28155;&#21152;
                </button>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr style="FONT-WEIGHT: bold; FONT-SIZE: 15pt; HEIGHT: 50px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="3%">序号</td>
                        <td align="center" width="8%">取货单号</td>
                        <td align="center" width="13%">淘宝单号</td>
                        <td align="center" width="13%">快递单号</td>
                        <td align="center" width="13%">交易号</td>
                        <td align="center" width="6%">订单总价</td>
                        <td align="center" width="15%">日期</td>
                        <td align="center" width="7%">订单状态</td>
                        <td align="center" width="8%">备注</td>
                        <td width="7%" align="center">编辑</td>
                        <td width="7%" align="center">删除</td>
                    </tr>

                    <c:forEach var="orderBean" items="${pageBean.itemList}" varStatus="index">
                        <tr id="${orderBean.order.oid}"
                            class="parent"
                            style="HEIGHT: 50px;"
                            onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="3%">${pageBean.currentCount*(pageBean.currentPage-1)+index.count}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="8%">${orderBean.order.oid}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="13%">
                                <a style="text-decoration:underline;" target="_blank" href="https://trade.taobao.com/trade/detail/trade_order_detail.htm?biz_order_id=${orderBean.order.taobao_code}">${orderBean.order.taobao_code}</a></td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="13%">
                                <a style="text-decoration:underline;" target="_blank"
                                   href="${pageContext.request.contextPath}/query_express?express_code=${orderBean.order.express_code}">${orderBean.order.express_code}</a>
                            </td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="15%">${orderBean.order.alipay_code}</td>
                            <td align="center" width="6%">${orderBean.order.total_price}</td>
                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="13%">${orderBean.order.date}</td>
                            <c:if test="${orderBean.order.order_state==1}">
                                <td style="color:green;CURSOR: hand; HEIGHT: 25px" align="center"
                                    width="6%">已完成
                                </td>
                            </c:if>
                            <c:if test="${orderBean.order.order_state==0}">
                                <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                    width="6%">未完成
                                </td>
                            </c:if>
                            <c:if test="${orderBean.order.order_state==3}">
                                <td style="color:red;CURSOR: hand; HEIGHT: 25px" align="center"
                                    width="6%">凭证遗失
                                </td>
                            </c:if>
                            <c:if test="${orderBean.order.order_state==4}">
                                <td style="color:blue;CURSOR: hand; HEIGHT: 25px" align="center"
                                    width="6%">遗失已完成
                                </td>
                            </c:if>

                            <td style="CURSOR: hand; HEIGHT: 25px" align="center"
                                width="8%">${orderBean.order.remark}</td>
                            <td align="center" style="HEIGHT: 25px" class="td_edit">
                                <a href="${ pageContext.request.contextPath }/order/edit_order?oid=${orderBean.order.oid}">
                                    <img src="${pageContext.request.contextPath}/images/i_edit.gif"
                                         border="0" style="CURSOR: hand">
                                </a></td>

                            <td align="center" style="HEIGHT: 25px" class="td_delete">
                                <a href="#"
                                   onclick="confirm('确定删除吗？')?location.href='${ pageContext.request.contextPath }/order/delete_order?oid=${orderBean.order.oid}':''">
                                    <img src="${pageContext.request.contextPath}/images/i_del.gif"
                                         width="16" height="16" border="0" style="CURSOR: hand">
                                </a></td>
                        </tr>

                        <c:forEach var="product" items="${orderBean.productList}" varStatus="index">
                            <tr class="child_${orderBean.order.oid}"
                                style="BACKGROUND-COLOR: #FFF38F;HEIGHT: 40px">
                                <td align="center">${index.count}</td>
                                <td align="center" colspan="4">${product.name}</td>
                                <td align="center">单价:${product.price}</td>
                                <td align="center">运费:${product.freight}</td>
                                <td colspan="4"></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </td>
        </tr>
        </TBODY>
    </table>
</form>

<!--分页 -->
<div class="col-md-12 text-center">
    <p:page url="${pageContext.request.contextPath }/order/order_list"/>
    &nbsp;&nbsp;共${pageBean.totalPage}页
</div>
<!-- 分页结束 -->
</body>
</HTML>

