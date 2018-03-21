package pers.mao.service;

import pers.mao.pojo.Order;
import pers.mao.vo.OrderBean;
import pers.mao.vo.OrderSelectVo;
import pers.mao.vo.PageBean;

public interface OrderService {
    PageBean<OrderBean> getPageBeanByOrderSelectVo(OrderSelectVo vo);

    int getCountByOrderSelectVo(OrderSelectVo vo);

    boolean getOrderIsExisted(String oid);

    void addOrder(Order order);

}
