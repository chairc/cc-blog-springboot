package cn.chairc.blog.service;

import cn.chairc.blog.entity.article.ArticlePictureResultSet;
import cn.chairc.blog.entity.common.ResultSet;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chairc
 * @date 2021/5/9 21:56
 */
public interface FileService {

    /**
     * 头像上传
     *
     * @param headerFile 头像文件
     * @return 成功或异常
     */

    ResultSet uploadHeadFile(MultipartFile headerFile);

    /**
     * 文章图片上传
     *
     * @param articleFile 文章图片文件
     * @return 成功或异常
     */

    ArticlePictureResultSet uploadArticleFile(MultipartFile articleFile);
}
