package pers.mao.controllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.mao.pojo.Order;
import pers.mao.service.OrderService;
import pers.mao.vo.OrderBean;
import pers.mao.vo.OrderSelectVo;
import pers.mao.vo.PageBean;

import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/order_list")
    public String orderList(OrderSelectVo vo, Model model){
        PageBean<OrderBean> pageBean = orderService.getPageBeanByOrderSelectVo(vo);
        model.addAttribute(pageBean);
        model.addAttribute(vo.getOid());
        return "/admin/product/list";
    }

    @RequestMapping("/add_order")
    public String addOrder(Order order, Model model){
        String oid = order.getOid();
        if (oid != null && !oid.trim().isEmpty() && oid.length() == 12){
            order.setDate(new Date());
            Boolean isExisted = orderService.getOrderIsExisted(oid);
            if (isExisted) {
                model.addAttribute("order", order);
                model.addAttribute("error_message", "该取货单号已存在");
            } else {
                orderService.addOrder(order);
                return "redirect:/order/order_list";
            }
        }else {
            model.addAttribute("order", order);
            model.addAttribute("error_message", "取货单号为空或者不合法，取货单号必须是12位");
        }
        orderService.getOrderIsExisted(oid);
        return "/admin/product/add";
    }
}
