package pers.mao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;
import pers.mao.dao.OrderDao;
import pers.mao.dao.ProductDao;
import pers.mao.pojo.Order;
import pers.mao.pojo.OrderExample;
import pers.mao.pojo.Product;
import pers.mao.service.OrderService;
import pers.mao.utils.ConstantUtils;
import pers.mao.vo.OrderBean;
import pers.mao.vo.OrderSelectVo;
import pers.mao.vo.PageBean;
import pers.mao.vo.TaobaoBean;

import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    @Override
    public PageBean<OrderBean> getPageBeanByOrderSelectVo(OrderSelectVo vo) {
        if (vo.getCurrentPage()==null||vo.getCurrentPage()==0){
            vo.setCurrentPage(1);
        }
        if (vo.getCount()==null||vo.getCount()==0){
            vo.setCount(5);
        }
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
    public boolean getOrderIsExistedByOid(String oid) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOidLike("%"+oid+"%");
        int count = orderDao.countByExample(example);
        return count>0;
    }

    @Override
    public boolean getOrderIsExistedByAlipayCode(String alipayCode) {
        OrderExample example = new OrderExample();
        example.createCriteria().andAlipayCodeEqualTo(alipayCode);
        int count = orderDao.countByExample(example);
        return count>0;
    }

    @Override
    public void addOrder(Order order) {
        orderDao.insert(order);
    }

    @Override
    public void updateOrderByOid(Order order) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOidEqualTo(order.getOid());
        orderDao.updateByExample(order,example);
    }

    @Override
    public void updateOrderByAcode(Order order) {
        OrderExample example = new OrderExample();
        example.createCriteria().andAlipayCodeEqualTo(order.getAlipayCode());
        orderDao.updateCodeByExample(order,example);
    }

    @Override
    public Order getOrderByOid(String oid) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOidEqualTo(oid);
        List<Order> orders = orderDao.selectByExample(example);
        return orders.get(0);
    }

    @Override
    public List<Order> getOrdersByOid(String oid) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOidLike("%"+oid+"%");
        List<Order> orders = orderDao.selectByExample(example);
        return orders;
    }

    @Override
    public void deleteOrderByOid(String oid) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOidEqualTo(oid);
        orderDao.deleteByExample(example);
    }

    @Override
    public void insertProductAndOrder(TaobaoBean taobaoBean) {
        Order order = new Order();
        order.setDate(new Date());

        for (int i = 0; i < taobaoBean.getOrder_array().size(); i++) {
            String tempOid;
            if (i < 9) {
                tempOid = taobaoBean.getOrder_id() + "0" + (i + 1);
            } else {
                tempOid = taobaoBean.getOrder_id() + (i + 1);
            }
            order.setTotalPrice(taobaoBean.getOrder_array().get(i).getTotal_price());
            order.setOid(tempOid);
            order.setAlipayCode(taobaoBean.getOrder_array().get(i).getAlipay_code());
            addOrder(order);
            TaobaoBean.OrderArrayBean orderArrayBean = taobaoBean.getOrder_array().get(i);
            for (TaobaoBean.OrderArrayBean.ProductArrayBean productArrayBean : orderArrayBean.getProduct_array()) {
                Product product = new Product();
                product.setOid(tempOid);
                product.setName(productArrayBean.getProduct_desc());
                System.out.println(productArrayBean.getProduct_desc());
                product.setFreight(orderArrayBean.getFreight());
                product.setPrice(productArrayBean.getProduct_price());
                productDao.insert(product);
            }
        }
    }

    @Override
    public void updateOrderCodeByOid(Order order) {
        OrderExample example = new OrderExample();
        example.createCriteria().andOidEqualTo(order.getOid());
        orderDao.updateCodeByExample(order,example);
    }


}
