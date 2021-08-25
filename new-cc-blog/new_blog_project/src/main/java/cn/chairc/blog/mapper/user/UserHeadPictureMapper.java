package cn.chairc.blog.mapper.user;

import cn.chairc.blog.entity.user.UserHeadPictureEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chairc
 * @date 2021/7/21 17:21
 */
@Mapper
public interface UserHeadPictureMapper {
    UserHeadPictureEntity getUserHeadPicture(String userPrivateId);

    boolean getUserHeadPictureIsExist(String userPrivateId);

    void insertUserHeadPicture(UserHeadPictureEntity userHeadPictureEntity);

    void updateUserHeadPicture(UserHeadPictureEntity userHeadPictureEntity);
}
