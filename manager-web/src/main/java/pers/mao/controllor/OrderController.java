package pers.mao.controllor;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mao.pojo.Order;
import pers.mao.service.OrderService;
import pers.mao.utils.ConstantUtils;
import pers.mao.utils.RequestUtils;
import pers.mao.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

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
        return "/product/list";
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
        return "/product/add";
    }

    @RequestMapping("/edit_order")
    public String editOrder(String oid, Model model) {
        Order order = orderService.getOrderByOid(oid);
        model.addAttribute("order", order);
        return "/product/edit";
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
        } else {
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
                wrapper.setInfo(ConstantUtils.FAIL_AND_EXCEPTION + e.getMessage());
            }
        }
        Gson gson = new Gson();
        response = gson.toJson(wrapper);
        return response;
    }

    @RequestMapping("/save_product")
    @ResponseBody
    public String saveProductAndOrder(@RequestBody TaobaoBean taobaoBean) {
        String response = "上传成功";
        try {
            orderService.insertProductAndOrder(taobaoBean);
        } catch (Exception e) {
            response = "上传失败\r\n失败原因:" + e.getMessage();
        }
        return response;
    }

    @RequestMapping("/upload")
    public String uploadJson(HttpServletRequest request) {
        String responseStr = "";
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    //普通表单

                } else {
                    //文件上传
                    String fieldName = item.getName();

                    if (fieldName.contains(".json")) {
                        InputStream inputStream = item.getInputStream();
                        String json = RequestUtils.getRequestBody(inputStream);
                        Gson gson = new Gson();
                        TaobaoBean bean = null;
                        bean = gson.fromJson(json, TaobaoBean.class);
                        if (bean != null) {
                            try {
                                orderService.insertProductAndOrder(bean);
                            } catch (Exception e) {
                                e.printStackTrace();
                                responseStr += e.getMessage() + "\r\n";
                            }
                        }
                    } else {
                        responseStr += "上传的文件格式错误" + "\r\n";
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            responseStr += e.getMessage() + "\r\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (responseStr.equals("")) {
            responseStr = "上传成功";
        }
        return responseStr;
    }
}
