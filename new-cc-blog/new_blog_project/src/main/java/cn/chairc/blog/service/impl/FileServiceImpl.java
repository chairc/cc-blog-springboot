package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.article.ArticlePictureResultSet;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.user.UserHeadPictureEntity;
import cn.chairc.blog.mapper.user.UserHeadPictureMapper;
import cn.chairc.blog.service.FileService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/9 21:56
 */
@Service
public class FileServiceImpl implements FileService {

    private static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    private UserHeadPictureMapper userHeadPictureMapper;

    @Autowired
    public FileServiceImpl(UserHeadPictureMapper userHeadPictureMapper) {
        this.userHeadPictureMapper = userHeadPictureMapper;
    }

    @Value("${upload-file.head-file-path}")
    private String UPLOAD_HEAD_PATH;

    @Value("${upload-file.article-file-path}")
    private String UPLOAD_ARTICLE_PATH;

    /**
     * 头像上传
     *
     * @param headerFile 头像文件
     * @return 成功或异常
     */

    @Override
    public ResultSet uploadHeadFile(MultipartFile headerFile) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            //  String filename = headerFile.getOriginalFilename();
            if (headerFile.isEmpty()) {
                log.error("用户{}上传文件失败，文件为空", userPrivateId);
                resultSet.fail("上传文件失败，文件为空");
                return resultSet;
            }
            //  检查是否为图片
            BufferedImage bufferedImage = ImageIO.read(headerFile.getInputStream());
            if (bufferedImage == null) {
                log.info("用户{}上传的{}文件不是图片", userPrivateId,
                        headerFile.getOriginalFilename());
                resultSet.fail("上传类型不为图片");
                return resultSet;
            }
            //  标准头像文件上传地址
            String filePath = UPLOAD_HEAD_PATH + userPrivateId + "/";
            //  缩略头像文件上传地址
            String thumbnailFilePath = UPLOAD_HEAD_PATH + userPrivateId + "/thumbnail/";
            //  String filename = userPrivateId + ".jpg";
            String filename = headerFile.getOriginalFilename();
            File targetFile = new File(filePath);
            File targetThumbnailFile = new File(thumbnailFilePath);
            //  检查是否存在该文件夹，不存在则创建
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            if (!targetThumbnailFile.exists()) {
                targetThumbnailFile.mkdirs();
            }
            File sourceFile = new File(filePath + filename);
            //  上传原始头像图片
            headerFile.transferTo(sourceFile);
            log.info("路径：{}", sourceFile);
            //  上传头像缩略图
            //  thumbnailator对jpg格式最好
            //  scale()为尺寸压缩
            Thumbnails.of(headerFile.getInputStream()).scale(0.3f).
                    toFile(thumbnailFilePath + filename);
            //  查询头像表是否存在头像
            UserHeadPictureEntity userHeadPictureEntity = new UserHeadPictureEntity();
            userHeadPictureEntity.setUserPrivateId(userPrivateId);
            userHeadPictureEntity.setUserHeadMappingUrl("/path/head/" + userPrivateId + "/" + filename);
            userHeadPictureEntity.setUserHeadMappingThumbnailUrl("/path/head/" + userPrivateId + "/thumbnail/" + filename);
            if(userHeadPictureMapper.getUserHeadPictureIsExist(userPrivateId)){
                //  修改写入用户头像表
                userHeadPictureMapper.updateUserHeadPicture(userHeadPictureEntity);
                log.info("用户{}修改上传头像文件成功", userPrivateId);
            }else {
                //  新增写入用户头像表
                Date time = TimeUtil.getServerTime();
                userHeadPictureEntity.setUserHeadIsDelete(1);
                userHeadPictureEntity.setCreateTime(time);
                userHeadPictureEntity.setUpdateTime(time);
                userHeadPictureMapper.insertUserHeadPicture(userHeadPictureEntity);
                log.info("用户{}初次上传头像文件成功", userPrivateId);
            }
            resultSet.ok("上传文件成功");
        } catch (Exception e) {
            log.error("用户{}上传文件失败，原因{}", userPrivateId, e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 文章图片上传
     *
     * @param articleFile 文章图片文件
     * @return 成功或异常
     */

    @Override
    public ArticlePictureResultSet uploadArticleFile(MultipartFile articleFile) {
        ArticlePictureResultSet articlePictureResultSet = new ArticlePictureResultSet();
        String articleFileName = articleFile.getOriginalFilename();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            if (articleFile.isEmpty()) {
                log.error("用户{}上传文件失败，文件为空", userPrivateId);
                articlePictureResultSet.uploadFailure("上传文件失败，文件为空");
                return articlePictureResultSet;
            }
            BufferedImage bufferedImage = ImageIO.read(articleFile.getInputStream());
            if (bufferedImage == null) {
                log.info("用户{}上传的文件{}不是图片", userPrivateId,
                        articleFileName);
                articlePictureResultSet.uploadFailure("上传类型不为图片");
                return articlePictureResultSet;
            }
            //  设置上传文章图片路径
            File uploadArticleFilePath = new File(UPLOAD_ARTICLE_PATH + articleFile.getOriginalFilename());
            //  判断文件夹是否存在，不存在则创建
            if (!uploadArticleFilePath.exists()) {
                uploadArticleFilePath.mkdirs();
            }
            articleFile.transferTo(uploadArticleFilePath);
            log.info("用户{}上传文件成功",userPrivateId);
            //  前端回显url
            String url = "/path/article/" + articleFile.getOriginalFilename();
            articlePictureResultSet.uploadSuccess(url);
        } catch (Exception e) {
            log.error("用户{}上传文章图片{}失败，原因{}", userPrivateId, articleFileName, e.toString());
            articlePictureResultSet.uploadFailure("服务器内部错误");
        }
        return articlePictureResultSet;
    }
}
