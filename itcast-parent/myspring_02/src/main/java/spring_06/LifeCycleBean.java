package spring_06;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("lifeCycleBean" )
@Scope("prototype")
public class LifeCycleBean {
    public LifeCycleBean() {
        System.out.println("生命周期构造器被使用~~");
    }

    //初始化后自动调用方法：方法名随意，但也不能太随便，一会要配置
    @PostConstruct
    public void init(){
        System.out.println("LifeCycleBean-init初始化时调用");
    }

    //bean销毁时调用的方法
    @PreDestroy
    public void destroy(){
        System.out.println("LifeCycleBean-destroy销毁时调用");
    }

}
