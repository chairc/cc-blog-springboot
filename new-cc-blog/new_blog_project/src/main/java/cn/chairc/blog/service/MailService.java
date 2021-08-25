package cn.chairc.blog.service;

import cn.chairc.blog.entity.mail.MailEntity;
import cn.chairc.blog.entity.common.ResultSet;

/**
 * @author chairc
 * @date 2021/5/9 21:59
 */
public interface MailService {

    /**
     * 自动发送邮件
     *
     * @param mail 邮件类
     */

    void sendAutomaticEmail(MailEntity mail);

    /**
     * 手动发送邮件
     *
     * @param mail 邮件类
     * @return 成功或异常
     */

    ResultSet sendManualEmail(MailEntity mail);
}
