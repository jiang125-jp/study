package com.study.design_mode.proxy;

/**
 * @author jiangpeng
 */
public class ProxyTest {

    public static void main(String[] args) {
        MeiPo meipo = new MeiPo();
        IPerson zhangSanProxy = meipo.getInstance(new ZhangSan());
        zhangSanProxy.findLove();

        IPerson liSiProxy = meipo.getInstance(new LiSi());
        liSiProxy.findLove();
    }
}
