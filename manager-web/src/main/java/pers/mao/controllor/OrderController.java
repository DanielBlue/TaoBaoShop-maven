package pers.mao.controllor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mao.pojo.Order;
import pers.mao.service.OrderService;
import pers.mao.utils.ConstantUtils;
import pers.mao.vo.OrderBean;
import pers.mao.vo.OrderSelectVo;
import pers.mao.vo.PageBean;
import pers.mao.vo.ResponseWrapper;

import java.sql.SQLException;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/order_list")
    public String orderList(OrderSelectVo vo, Model model) {
        PageBean<OrderBean> pageBean = orderService.getPageBeanByOrderSelectVo(vo);
        model.addAttribute(pageBean);
        model.addAttribute(vo.getOid());
        return "/admin/product/list";
    }

    @RequestMapping("/add_order")
    public String addOrder(Order order, Model model) {
        String oid = order.getOid();
        if (oid != null && !oid.trim().isEmpty() && oid.length() == 12) {
            order.setDate(new Date());
            Boolean isExisted = orderService.getOrderIsExistedByOid(oid);
            if (isExisted) {
                model.addAttribute("order", order);
                model.addAttribute("error_message", "该取货单号已存在");
            } else {
                orderService.addOrder(order);
                return "redirect:/order/order_list";
            }
        } else {
            model.addAttribute("order", order);
            model.addAttribute("error_message", "取货单号为空或者不合法，取货单号必须是12位");
        }
        orderService.getOrderIsExistedByOid(oid);
        return "/admin/product/add";
    }

    @RequestMapping("/edit_order")
    public String editOrder(String oid, Model model) {
        Order order = orderService.getOrderByOid(oid);
        model.addAttribute("order", order);
        return "/admin/product/edit";
    }

    @RequestMapping("/update_order")
    public String updateOrder(Order order) {
        orderService.updateOrderByOid(order);
        return "redirect:/order/order_list";
    }

    @RequestMapping("/delete_order")
    public String deleteOrder(String oid) {
        orderService.deleteOrderByOid(oid);
        return "redirect:/order/order_list";
    }

    @RequestMapping("/code_update_order")
    @ResponseBody
    public String codeUpdateOrder(Order order) {
        ResponseWrapper wrapper = new ResponseWrapper();
        String response = "";
        if (order.getAlipayCode() == null || order.getAlipayCode().isEmpty()) {
            wrapper.setCode(ConstantUtils.FAIL_CODE);
            wrapper.setInfo(ConstantUtils.ALIPAY_CODE_EMPTY);
        }else {
            try {
                boolean isExisted = orderService.getOrderIsExistedByAlipayCode(order.getAlipayCode());
                if (isExisted) {
                    orderService.updateOrderByOid(order);
                    wrapper.setCode(ConstantUtils.SUCCESS_CODE);
                    wrapper.setInfo(ConstantUtils.UPDATE_SUCCESS);
                } else {
                    wrapper.setCode(ConstantUtils.FAIL_CODE);
                    wrapper.setInfo(ConstantUtils.ALIPAY_CODE_NOT_EXIST);
                }

            } catch (Exception e) {
                e.printStackTrace();
                wrapper.setCode(ConstantUtils.FAIL_CODE);
                wrapper.setInfo(ConstantUtils.FAIL_AND_EXCEPTION+ e.getMessage());
            }
        }

        try {
            response = new ObjectMapper().writeValueAsString(wrapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
