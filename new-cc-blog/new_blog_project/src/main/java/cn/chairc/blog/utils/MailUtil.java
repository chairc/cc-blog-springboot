package cn.chairc.blog.utils;

import cn.chairc.blog.entity.verification.VerificationCodeEntity;

/**
 * @author chairc
 * @date 2021/5/10 17:00
 */
public class MailUtil {

    public static String createAutomaticEmailUrl(String path, VerificationCodeEntity verificationCodeEntity) {
        String userPrivateId = verificationCodeEntity.getUserPrivateId();
        String verificationCode = verificationCodeEntity.getUserVerificationCode();
        String verificationCodeType = verificationCodeEntity.getUserVerificationCodeType();
        String url = path + "?userPrivateId=" + userPrivateId +
                "&verificationCode=" + verificationCode +
                "&verificationCodeType=" + verificationCodeType;
        return "<a target=\"_blank\" href = \"" + url + "\">" + url + "</a>";
    }
}
