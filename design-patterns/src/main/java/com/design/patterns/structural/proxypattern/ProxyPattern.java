package com.design.patterns.structural.proxypattern;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ProxyPattern
 * @Description 测试代理模式
 * @date 2025/3/24 19:08
 **/
public class ProxyPattern {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Proxy proxy = new Proxy(realSubject);
        proxy.buy();
    }

}