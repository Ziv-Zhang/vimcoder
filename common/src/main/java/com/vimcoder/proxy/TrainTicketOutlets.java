package com.vimcoder.proxy;

/**
 * 火车票售卖点12306
 */
public class TrainTicketOutlets implements ITicketOutlets {

    public TrainTicketOutlets() {
        // TODO Auto-generated constructor stub
    }

    public TrainTicketOutlets(Class c) {

    }
    private String orderNo = "aaaaa";

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public void buy() {
        System.out.println("成功购买一张火车票");
    }

    @Override
    public void query() {
        System.out.println("查询火车票ing");
    }

    // 用于测试属性复制使用
    public static class CopyBean {
        private String orderNo;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
    }

}
