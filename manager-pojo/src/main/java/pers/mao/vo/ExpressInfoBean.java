package pers.mao.vo;

import java.util.List;

public class ExpressInfoBean {

    /**
     * message : ok
     * nu : 476727120234
     * ischeck : 0
     * condition : 00
     * com : zhongtong
     * status : 200
     * state : 0
     * data : [{"time":"2018-01-24 23:19:40","ftime":"2018-01-24 23:19:40","context":"[嘉兴市] 快件离开 [杭州中转部]已发往[武汉中转部]","location":"杭州中转部"},{"time":"2018-01-24 23:17:22","ftime":"2018-01-24 23:17:22","context":"[杭州市] 快件到达 [杭州汽运部]","location":"杭州汽运部"},{"time":"2018-01-24 20:05:59","ftime":"2018-01-24 20:05:59","context":"[杭州市] 快件离开 [杭州华贸区]已发往[武汉中转部]","location":"杭州华贸区"},{"time":"2018-01-24 18:21:17","ftime":"2018-01-24 18:21:17","context":"[杭州市] [杭州华贸区]的竹鄢家居专营店已收件 电话:13216147193","location":"杭州华贸区"}]
     */

    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * time : 2018-01-24 23:19:40
         * ftime : 2018-01-24 23:19:40
         * context : [嘉兴市] 快件离开 [杭州中转部]已发往[武汉中转部]
         * location : 杭州中转部
         */

        private String time;
        private String ftime;
        private String context;
        private String location;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
