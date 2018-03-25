package pers.mao.controllor;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mao.pojo.Order;
import pers.mao.service.OrderService;
import pers.mao.utils.*;
import pers.mao.vo.ExpressInfoBean;
import pers.mao.vo.InputMessage;
import pers.mao.vo.OutputMessage;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@Controller
public class WxController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/query_express")
    @ResponseBody
    public String QueryExpress(String express_code) {
        String result = NetUtils.getExpressInfo(express_code);
        if (result == null || result.isEmpty()) {
            result = "暂无快递信息";
        }
        return result;
    }

    @RequestMapping(value = "/wx", method = RequestMethod.GET)
    @ResponseBody
    public String wxGet(String echostr) {
        return echostr;
    }

    @RequestMapping(value = "/wx", method = RequestMethod.POST)
    @ResponseBody
    public String wxPost(HttpServletRequest request) {
        String response = "success";
        try {
            response = acceptMessage(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String acceptMessage(HttpServletRequest request) throws IOException {
        // 处理接收消息
        ServletInputStream in = request.getInputStream();
        // 将POST流转换为XStream对象
        XStream xs = SerializeXmlUtil.createXstream();
        xs.processAnnotations(InputMessage.class);
        xs.processAnnotations(OutputMessage.class);
        // 将指定节点下的xml节点数据映射为对象
        xs.alias("xml", InputMessage.class);
        // 将流转换为字符串
        StringBuilder xmlMsg = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            xmlMsg.append(new String(b, 0, n, "UTF-8"));
        }
        // 将xml内容转换为InputMessage对象
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());
        // 取得消息类型
        String msgType = inputMsg.getMsgType();


        // 根据消息类型获取对应的消息内容
        if (msgType.equals(MsgType.Text.toString())) {
            return handleMessageContent(request, inputMsg);
        } else if (msgType.equals(MsgType.Event.toString())) {
            return handleEventContent(inputMsg);
        } else {
            return errorTypeContent(inputMsg);
        }
        // 获取并返回多图片消息
//        if (msgType.equals(MsgType.Image.toString())) {
//            System.out.println("获取多媒体信息");
//            System.out.println("多媒体文件id：" + inputMsg.getMediaId());
//            System.out.println("图片链接：" + inputMsg.getPicUrl());
//            System.out.println("消息id，64位整型：" + inputMsg.getMsgId());
//
//            OutputMessage outputMsg = new OutputMessage();
//            outputMsg.setFromUserName(servername);
//            outputMsg.setToUserName(custermname);
//            outputMsg.setCreateTime(returnTime);
//            outputMsg.setMsgType(msgType);
//            ImageMessage images = new ImageMessage();
//            images.setMediaId(inputMsg.getMediaId());
//            outputMsg.setImage(images);
//            System.out.println("xml转换：/n" + xs.toXML(outputMsg));
//            response.getWriter().write(xs.toXML(outputMsg));
//
//        }
    }

    private String errorTypeContent(InputMessage inputMessage) {
        String servername = inputMessage.getToUserName();// 服务端
        String custermname = inputMessage.getFromUserName();// 客户端
        long createTime = inputMessage.getCreateTime();// 接收时间
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间
        String event = inputMessage.getEvent();

        String outputMessage;
        outputMessage = buildOutputMessage(custermname, servername, returnTime, MsgType.Text.toString(), ConstantUtils.WX_ERROR_TYPE);

        return outputMessage;
    }

    private String handleEventContent(InputMessage inputMessage) throws IOException {
        String servername = inputMessage.getToUserName();// 服务端
        String custermname = inputMessage.getFromUserName();// 客户端
        long createTime = inputMessage.getCreateTime();// 接收时间
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间
        String event = inputMessage.getEvent();

        String outputMessage = "success";
        if (event.equals("subscribe")) {
            outputMessage = buildOutputMessage(custermname, servername, returnTime, MsgType.Text.toString(), ConstantUtils.WELCOME);
        }
        return outputMessage;

    }

    private String handleMessageContent(HttpServletRequest request, InputMessage inputMessage) throws IOException {
        String servername = inputMessage.getToUserName();// 服务端
        String custermname = inputMessage.getFromUserName();// 客户端
        long createTime = inputMessage.getCreateTime();// 接收时间
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间
        String receiveContent = inputMessage.getContent().replace(" ", "");

        String responseStr = "";

        if (StrUtils.isInteger(receiveContent)) {
            // 文本消息
//            System.out.println("开发者微信号：" + inputMsg.getToUserName());
//            System.out.println("发送方帐号：" + inputMsg.getFromUserName());
//            System.out.println("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));
//            System.out.println("消息内容：" + inputMsg.getContent());
//            System.out.println("消息Id：" + inputMsg.getMsgId());
            String result = "";
            List<Order> orderList = null;

            try {
                orderList = orderService.getOrdersByOid(receiveContent);
                if (orderList != null && orderList.size() > 0) {
                    if (orderList.size() == 1) {
                        Order order = orderList.get(0);
                        String order_state = order.getOrderState();
                        if ("1".equals(order_state)) {
                            result = ConstantUtils.ALREADY_COMPLETE;
                        } else if ("".equals(order_state)) {
                            result = ConstantUtils.ORDER_LOSS;
                        } else {
                            String express_code = order.getExpressCode();
                            if (express_code != null && !express_code.isEmpty()) {
                                String expressInfo = NetUtils.getExpressInfo(express_code);
                                if (expressInfo != null && !expressInfo.isEmpty()) {
                                    result = formatResult(expressInfo);
                                } else {
                                    result = ConstantUtils.NO_MESSAGE;
                                }
                            } else {
                                result = ConstantUtils.NO_EXPRESS_CODE_MESSAGE;
                            }
                        }
                    } else {
                        result = ConstantUtils.INPUT_INCOMPLETE;
                    }
                } else {
                    result = ConstantUtils.NO_ORDER_MESSAGE;
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = "错误:" + e.getMessage() + "\r\n\n" + ConstantUtils.SERVER_ERROR;
            }

            responseStr = buildOutputMessage(custermname, servername, returnTime, MsgType.Text.toString(), result);
        } else if (receiveContent.equals("微信号")) {
            responseStr = buildOutputMessage(custermname, servername, returnTime, MsgType.Text.toString(),
                    "https://xiongbinxue.top" + ConstantUtils.BOSS_WX);

        } else {
            responseStr = buildOutputMessage(custermname, servername, returnTime, MsgType.Text.toString(), ConstantUtils.INPUT_ERROR);
        }

        return responseStr;
    }

    private String formatResult(String expressInfo) {
        Gson gson = new Gson();
        ExpressInfoBean expressInfoBean = gson.fromJson(expressInfo, ExpressInfoBean.class);
        String message = expressInfoBean.getMessage();
        if (expressInfoBean != null && message != null && !message.isEmpty()) {
            if (!message.equals("ok")) {
                return message;
            } else {
                List<ExpressInfoBean.DataBean> data = expressInfoBean.getData();
                if (data != null && data.size() > 0) {
                    String content = "你的快递信息：\r\n\r\n";
                    for (ExpressInfoBean.DataBean dataBean : data) {
                        content += dataBean.getTime() + "\r\n" + dataBean.getContext() + "\r\n\r\n";
                    }
                    return content;
                } else {
                    return ConstantUtils.NO_MESSAGE;
                }
            }
        }
        return ConstantUtils.SERVER_ERROR;
    }

    private String buildOutputMessage(String custermname, String servername, long returnTime, String msgType, String returnContent) {

        StringBuffer str = new StringBuffer();
        str.append("<xml>");
        str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");
        str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");
        str.append("<CreateTime>" + returnTime + "</CreateTime>");
        str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");
        str.append("<Content><![CDATA[" + returnContent + " ]]></Content>");
        str.append("</xml>");
        return str.toString();
    }
}
