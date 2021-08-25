package cn.chairc.blog.mapper.verification;

import cn.chairc.blog.entity.verification.VerificationCodeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chairc
 * @date 2021/6/7 13:56
 */
@Mapper
public interface VerificationCodeMapper {

    VerificationCodeEntity getUserVerificationCode(String userVerificationCode);

    boolean getUserVerificationCodeIsExist(VerificationCodeEntity verificationCodeEntity);

    void insertVerificationCode(VerificationCodeEntity verificationCodeEntity);

    void updateVerificationCode(VerificationCodeEntity verificationCodeEntity);
}
