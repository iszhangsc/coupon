package com.imooc.coupon.controller;

import com.imooc.coupon.exception.CouponException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 健康检查接口
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 12:47
 */
@Slf4j
@RestController
public class HeathCheckController {

    /**
     * 服务发现客户端
     */
    private final DiscoveryClient discoveryClient;

    /**
     * 服务注册接口，提供了获取服务ID的方法
     */
    private final Registration registration;

    @Autowired
    public HeathCheckController(DiscoveryClient discoveryClient, Registration registration) {
        this.discoveryClient = discoveryClient;
        this.registration = registration;
    }

    /**
     * 健康检查接口
     * 127.0.0.1:7001/coupon-template/heath
     *
     * @return String
     */
    @GetMapping("/heath")
    public String heath() {
        log.debug("view heath api");
        return "CouponTemplate Is OK!";
    }

    /**
     * 异常测试接口
     * 127.0.0.1:7001/coupon-template/exception
     *
     * @return
     */
    @GetMapping("/exception")
    public String exception() throws CouponException {
        log.debug("view exception api");
        throw new CouponException("CouponTemplate Has Som Problem");
    }


    /**
     * 获取 Eureka Server 上的微服务元信息
     * 127.0.0.1:7001/coupon-template/info
     * @return
     */
    @GetMapping("/info")
    public List<Map<String, Object>> info() {
        // 大约需要等待两分钟时间才能获取到注册信息
        final List<ServiceInstance> instances =
                discoveryClient.getInstances(registration.getServiceId());
        List<Map<String, Object>> result = new ArrayList<>(instances.size());
        instances.forEach(o -> {
            Map<String, Object> info = new HashMap<>(3);
            info.put("serviceId", o.getServiceId());
            info.put("instanceId", o.getInstanceId());
            info.put("port", o.getPort());
            result.add(info);
        });
        return result;
    }

}