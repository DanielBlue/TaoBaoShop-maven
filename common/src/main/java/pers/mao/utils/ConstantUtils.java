package pers.mao.utils;

public class ConstantUtils {

    public static final String NO_FITTER = "2";
    public static final String FAIL_CODE = "10010";
    public static final String SUCCESS_CODE = "10000";
    public static final String ALIPAY_CODE_EMPTY = "支付宝交易号为空";
    public static final String ALIPAY_CODE_NOT_EXIST = "载入失败\r\n失败原因：该支付宝单号在系统中不存在";
    public static final String UPDATE_SUCCESS = "更新成功";

    public static final String FAIL_AND_EXCEPTION = "载入失败\r\n失败原因：";

    //微信回复
    public static final String WX_CODE = "或回复\" 微信号 \"来添加老板微信咨询";
    public static final String SERVER_ERROR = "服务器出错,请稍后再试"+WX_CODE;
    public static final String INPUT_INCOMPLETE = "该单号有多笔订单，请输入12位完整单号，例如:201805210601，最后两位为打印的订单中商品前的编号"+WX_CODE;
    public static final String NO_MESSAGE = "该单号还没有快递信息，请稍后再查"+WX_CODE;
    public static final String INPUT_ERROR = "请输入你的订单号进行查询"+WX_CODE;
    public static final String BOSS_WX = "/images/wx/wx_img.jpg";

    public static final String WELCOME = "欢迎关注广发通讯店。\r\n\r\n请回复你的单号查询快递信息"+WX_CODE;
    public static final String NO_ORDER_MESSAGE = "系统中没有该单号，请确认后再查"+WX_CODE;
    public static final String ALREADY_COMPLETE = "该订单已完成，可以前往店铺取货";

    public static final String NO_EXPRESS_CODE_MESSAGE = "该单号还未发货，请稍后再查"+WX_CODE;
    public static final String ORDER_LOSS = "该单号凭证已遗失,正在公示中。有疑问可与老板联系" + WX_CODE;
    public static final String WX_ERROR_TYPE = "暂不支持的消息类型";
}
