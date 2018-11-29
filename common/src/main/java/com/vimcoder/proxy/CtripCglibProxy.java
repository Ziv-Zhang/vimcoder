package com.vimcoder.proxy;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.Type;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.beans.ImmutableBean;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InterfaceMaker;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import com.vimcoder.proxy.TrainTicketOutlets.CopyBean;

/**
 * cglib实现动态代理
 */
public class CtripCglibProxy {
    public ITicketOutlets proxy() {
        Enhancer enhancer = new Enhancer();
        // 指定继承类的实现方式
        enhancer.setSuperclass(TrainTicketOutlets.class);
        // 指定接口的实现方式
        // enhancer.setInterfaces(new Class[]{ITicketOutlets.class});
        enhancer.setCallback(new Handler(new TrainTicketOutlets()));
        return (ITicketOutlets)enhancer.create();
    }

    public static void main(String[] args) {
        ITicketOutlets proxy = new CtripCglibProxy().proxy();
        proxy.buy();
        proxy.query();
    }

    public static class Handler implements MethodInterceptor {

        private ITicketOutlets realObj;

        public Handler(ITicketOutlets ticketOutlets) {
            this.realObj = ticketOutlets;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable {
            System.out.println("cglib invoke before");
            // proxy.invokeSuper(obj, args);
            proxy.invoke(realObj, args);
            System.out.println("cglib invoke after");
            System.out.println("---------------------");
            return null;
        }

    }

    /**
     * ImmutableBean 代理可变对象使其具有不可变类特性
     */
    @Test
    public void immutableBean() {
        TrainTicketOutlets real = new TrainTicketOutlets(); // 被代理对象，可变对象
        TrainTicketOutlets proxy = (TrainTicketOutlets)ImmutableBean.create(real);
        real.setOrderNo("ccc"); // 可变
        proxy.setOrderNo("bbb"); // throw exception 属性不可变
    }

    /**
     * Bean动态生成器
     */
    @Test
    public void beanGenerator() {
        BeanGenerator generator = new BeanGenerator();
        // 动态生成包含两个属性的类
        generator.addProperty("name", String.class);
        generator.addProperty("id", Long.class);
        Class<?> clazz = (Class<?>)generator.createClass();
        Assert.assertEquals(2, clazz.getDeclaredFields().length);
    }

    /**
     * bean属性批量复制到另一个bean中，类似于BeanUtil.copyProperties
     * 通过useConverter参数设置，可以使用特殊的属性转换器
     */
    @Test
    public void beanCopier() {
        BeanCopier beanCopier = BeanCopier.create(TrainTicketOutlets.class, CopyBean.class, false);
        TrainTicketOutlets source = new TrainTicketOutlets();
        source.setOrderNo("aaaaaa");
        CopyBean target = new CopyBean();
        beanCopier.copy(source, target, null); // 复制属性，如果不需要转换特殊的属性，则converter=null
        Assert.assertEquals("aaaaaa", target.getOrderNo());
    }

    /**
     * 通过BeanMap，可以把Bean中的所有属性，都转换成 String-to-Map
     */
    @Test
    public void beanMap() throws Exception {
        BeanGenerator generator = new BeanGenerator();
        generator.addProperty("id", String.class);
        generator.addProperty("name", String.class);
        Class<?> clazz = (Class<?>)generator.createClass();
        Object obj = clazz.newInstance();
        clazz.getDeclaredMethod("setId", String.class).invoke(obj, "aaa");
        clazz.getDeclaredMethod("setName", String.class).invoke(obj, "bbb");
        BeanMap beanMap = BeanMap.create(obj);
        Assert.assertEquals("aaa", beanMap.get("id"));
        Assert.assertEquals("bbb", beanMap.get("name"));
    }

    /**
     * InterfaceMaker 动态生成接口
     */
    @Test
    public void interfaceMaker() {
        Signature signature = new Signature("test1", Type.INT_TYPE, new Type[]{});
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(signature, new Type[0]);
        Class<?> interfac = interfaceMaker.create();
        Assert.assertEquals(1, interfac.getDeclaredMethods().length);
    }

    /**
     * fastClass 通过class方法下标index的调用来代替传统的反射调用以提高性能
     */
    @Test
    public void fastClass() throws Exception {
        FastClass fastClass = FastClass.create(TrainTicketOutlets.class.getClassLoader(),
            TrainTicketOutlets.class);
        FastMethod method = fastClass.getMethod("getOrderNo", new Class[0]);
        TrainTicketOutlets obj = new TrainTicketOutlets();
        obj.setOrderNo("aaaaa");
        Assert.assertEquals("aaaaa", method.invoke(obj, new Object[0]));
    }
}