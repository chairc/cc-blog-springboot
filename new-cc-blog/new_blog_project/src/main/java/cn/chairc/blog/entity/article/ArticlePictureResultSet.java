package cn.chairc.blog.entity.article;

/**
 * 文章编辑器editor.md上传图片返回类型
 *
 * @author chairc
 * @date 2021/6/20 21:57
 */
public class ArticlePictureResultSet{

    private int success;
    private String message;
    private String url;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void uploadSuccess(String url) {
        this.success = 1;
        this.message = "上传成功";
        this.url = url;
    }

    public void uploadFailure(String msg) {
        this.success = 0;
        this.message = msg;
        this.url = "";
    }

    @Override
    public String toString() {
        return "ArticlePictureResultSet{" +
                "success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
