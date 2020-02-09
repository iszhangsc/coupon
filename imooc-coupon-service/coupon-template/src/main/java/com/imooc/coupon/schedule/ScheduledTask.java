package com.imooc.coupon.schedule;

import com.imooc.coupon.entity.CouponTemplate;
import com.imooc.coupon.repository.CouponTemplateDao;
import com.imooc.coupon.vo.TemplateRule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 定时清理已过期的优惠券模板任务
 * </p>
 *
 * @author zhangshichang
 * @date 2020-02-09 12:36
 */
@Slf4j
@Component
public class ScheduledTask {

    private final CouponTemplateDao couponTemplateDao;

    @Autowired
    public ScheduledTask(CouponTemplateDao couponTemplateDao) {
        this.couponTemplateDao = couponTemplateDao;
    }

    /**
     * 清理下线已过期的优惠券模板
     */
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void offLineCouponTemplate() {
        log.info("Start To Expire CouponTemplate");

        final List<CouponTemplate> templates = couponTemplateDao.findAllByExpired(false);
        if (CollectionUtils.isEmpty(templates)) {
            log.info("Done TO Expire CouponTemplate");
            return;
        }

        List<CouponTemplate> expiredTemplates = new ArrayList<>(templates.size());
        templates.forEach(o -> {
            // 根据优惠券模板规则中的 "过期规则" 校验模板是否过期
            final TemplateRule rule = o.getRule();
            if (rule.getExpiration().getDeadline() < System.currentTimeMillis()) {
                o.setExpired(true);
                expiredTemplates.add(o);
            }
        });

        if (CollectionUtils.isNotEmpty(expiredTemplates)) {
            log.info("Expire CouponTemplates Num: {}",
                    couponTemplateDao.saveAll(expiredTemplates));
        }

    }

}
