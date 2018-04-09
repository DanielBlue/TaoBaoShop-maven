package pers.mao.dao;

import org.apache.ibatis.annotations.Param;
import pers.mao.pojo.Order;
import pers.mao.pojo.OrderExample;
import pers.mao.vo.OrderBean;
import pers.mao.vo.OrderSelectVo;

import java.util.List;

public interface OrderDao {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderBean> selectOrderBeanByOid(OrderSelectVo limitVo);

    void updateCodeByExample(@Param("record")Order order,@Param("example") OrderExample example);
}