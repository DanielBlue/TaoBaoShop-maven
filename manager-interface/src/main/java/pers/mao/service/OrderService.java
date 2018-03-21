package pers.mao.service;

import pers.mao.vo.OrderBean;
import pers.mao.vo.PageBean;

public interface OrderService {
    PageBean<OrderBean> getPageBean();

}
