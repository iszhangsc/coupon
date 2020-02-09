package com.imooc.coupon.service.impl;

import com.google.common.base.Stopwatch;
import com.imooc.coupon.constant.Constants;
import com.imooc.coupon.entity.CouponTemplate;
import com.imooc.coupon.repository.CouponTemplateDao;
import com.imooc.coupon.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  异步服务接口实现
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 11:42
 */
@Slf4j
@Service
public class AsyncServiceImpl implements IAsyncService {

    private final CouponTemplateDao couponTemplateDao;
    private final StringRedisTemplate stringRedisTemplate;

    public AsyncServiceImpl(CouponTemplateDao couponTemplateDao, StringRedisTemplate stringRedisTemplate) {
        this.couponTemplateDao = couponTemplateDao;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    @Async("getAsyncExecutor")
    public void asyncConstructCouponByTemplate(CouponTemplate template) {
        Stopwatch watch = Stopwatch.createStarted();

        Set<String> couponCodes = buildCouponCode(template);

        // imooc_coupon_template_code_1
        String redisKey = String.format("%s%s",
                Constants.RedisPrefix.COUPON_TEMPLATE, template.getId().toString());
        log.info("Push CouponCode TO Redis :{}",
                stringRedisTemplate.opsForList().rightPushAll(redisKey, couponCodes));

        template.setAvailable(true);

        couponTemplateDao.save(template);

        watch.stop();
        log.info("Construct Coupon By Template Cost: {}ms", watch.elapsed(TimeUnit.MILLISECONDS));

        // todo 发送短信、邮件通知优惠券模板已经可用
        log.info("CouponTemplate({}) Is Available !", template.getId());

    }


    /**
     * 构造优惠券码
     * 前四位: 产品线 1 + 类型 3
     * 中间六位: 日期的随机(190101) yyMMdd
     * 后八位: 0~9 随机数构成
     * @param template {@link CouponTemplate}
     * @return Set<String> 与 template.couponCount相同个数的优惠券码
     */
    private Set<String> buildCouponCode(CouponTemplate template) {
        Stopwatch watch = Stopwatch.createStarted();
        Set<String> result = new HashSet<>(template.getCouponCount());
        // 前四位
        String prefix4 = template.getProductLine().getCode().toString()
                + template.getCategory().getCode();

        String date = new SimpleDateFormat("yyMMdd").format(template.getCreateTime());

        for (int i = 0; i != template.getCouponCount(); i++) {
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }

        // Set 需要再次检验是否生成的时候有重复，被覆盖, 这里需要判断
        while (result.size() < template.getCouponCount()) {
            result.add(prefix4 + buildCouponCodeSuffix14(date));
        }
        assert result.size() == template.getCouponCount();
        watch.stop();
        log.info("Build Coupon Code Const: {} ms", watch.elapsed(TimeUnit.MILLISECONDS));
        return result;
    }

    /**
     * 生成优惠券码的后14位
     * @param date 创建优惠券的日期
     * @return 14位优惠券码
     */
    private String buildCouponCodeSuffix14(String date) {
        char[] bases = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        // 中间六位
        List<Character> characters = date.chars()
                .mapToObj(o -> (char) o)
                .collect(Collectors.toList());
        // 洗牌算法
        Collections.shuffle(characters);
        String mid6 = characters.stream()
                .map(Object::toString)
                // list组合成string
                .collect(Collectors.joining());

        String suffix8 = RandomStringUtils.random(1, bases)
                + RandomStringUtils.randomNumeric(7);

        return mid6 + suffix8;
    }


}
