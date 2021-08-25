package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.article.ArticlePictureResultSet;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chairc
 * @date 2021/5/9 21:54
 */
@Controller
@RequestMapping("/api/upload")
public class FileController {

    private static Logger log = LoggerFactory.getLogger(FileController.class);

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 头像上传
     * 方法说明：无敏感信息
     *
     * @param headFile 头像文件
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/uploadHeadFile")
    @ResponseBody
    public ResultSet uploadHeadFile(@RequestParam(value = "headFile") MultipartFile headFile) {
        return fileService.uploadHeadFile(headFile);
    }

    /**
     * 文章图片上传
     * 方法说明：无敏感信息
     *
     * @param articleFile 文章图片文件
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/uploadArticleFile")
    @ResponseBody
    public ArticlePictureResultSet uploadArticleFile(@RequestParam(value = "editormd-image-file") MultipartFile articleFile) {
        return fileService.uploadArticleFile(articleFile);
    }
}
