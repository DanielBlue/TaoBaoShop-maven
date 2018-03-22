package pers.mao.service;

import pers.mao.pojo.Order;
import pers.mao.vo.OrderBean;
import pers.mao.vo.OrderSelectVo;
import pers.mao.vo.PageBean;

public interface OrderService {
    PageBean<OrderBean> getPageBeanByOrderSelectVo(OrderSelectVo vo);

    int getCountByOrderSelectVo(OrderSelectVo vo);

    boolean getOrderIsExistedByOid(String oid);

    boolean getOrderIsExistedByAlipayCode(String alipayCode);

    void addOrder(Order order);

    void updateOrderByOid(Order order);

    void updateOrderByAcode(Order order);

    Order getOrderByOid(String oid);

    void deleteOrderByOid(String oid);

}
