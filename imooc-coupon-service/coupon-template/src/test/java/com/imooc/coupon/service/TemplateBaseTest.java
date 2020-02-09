package com.imooc.coupon.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * <p>
 * 优惠券模板基础服务测试
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 18:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TemplateBaseTest {

    @Autowired
    private ITemplateBaseService templateBaseService;

    @Test
    public void testBuildTemplateInfo() throws Exception {
        System.out.println(JSON.toJSONString(templateBaseService.buildTemplateInfo(10)));
        System.out.println(JSON.toJSONString(templateBaseService.buildTemplateInfo(11)));
    }


    @Test
    public void testFindAllUsableTemplate() {
        System.out.println(JSON.toJSONString(templateBaseService.findAllUsableTemplate()));
    }

    @Test
    public void testFind2TemplateSDK() {
        System.out.println(
                JSON.toJSONString(templateBaseService.findIds2TemplateSDK(Arrays.asList(10, 11)))
        );
    }

}
