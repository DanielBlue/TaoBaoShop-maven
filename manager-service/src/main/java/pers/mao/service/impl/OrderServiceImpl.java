package pers.mao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import pers.mao.dao.OrderDao;
import pers.mao.pojo.Order;
import pers.mao.pojo.OrderExample;
import pers.mao.service.OrderService;
import pers.mao.utils.ConstantUtils;
import pers.mao.vo.OrderBean;
import pers.mao.vo.OrderSelectVo;
import pers.mao.vo.PageBean;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public PageBean<OrderBean> getPageBeanByOrderSelectVo(OrderSelectVo vo) {
        PageBean<OrderBean> pageBean = new PageBean<>();
        pageBean.setCurrentPage(vo.getCurrentPage());
        pageBean.setCurrentCount(vo.getCount());
        int totalCount = getCountByOrderSelectVo(vo);
        pageBean.setTotalCount(totalCount);
        int totalPage = (int) Math.ceil(1.0*totalCount/vo.getCount());
        pageBean.setTotalPage(totalPage);
        int index = (vo.getCurrentPage()-1)*vo.getCount();
        vo.setStartIndex(index);
        List<OrderBean> orderBeanList = orderDao.selectOrderBeanByOid(vo);
        pageBean.setItemList(orderBeanList);
        return pageBean;
    }

    @Override
    public int getCountByOrderSelectVo(OrderSelectVo vo){
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        if (vo.getExpress_code()!=null){
            criteria.andExpressCodeLike("%"+vo.getExpress_code()+"%");
        }
        if (vo.getOid()!=null){
            criteria.andOidLike("%"+vo.getOid()+"%");
        }
        if (vo.getOrder_state()!=null&&!vo.getOrder_state().equals(ConstantUtils.NO_FITTER)){
            criteria.andOrderStateEqualTo(vo.getOrder_state());
        }

        return orderDao.countByExample(example);
    }

    @Override
    public boolean getOrderIsExisted(String oid) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOidLike("%"+oid+"%");
        int count = orderDao.countByExample(example);
        return count>0;
    }

    @Override
    public void addOrder(Order order) {
        orderDao.insert(order);
    }


}
