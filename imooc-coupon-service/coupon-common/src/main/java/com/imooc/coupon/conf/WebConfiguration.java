package com.imooc.coupon.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <p>
 *  定制 HTTP 消息转换器
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-08 21:24
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 自定义转换器
     *
     * @param converters HTTP 消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 清空
        converters.clear();

        converters.add(new MappingJackson2HttpMessageConverter());
    }

}
