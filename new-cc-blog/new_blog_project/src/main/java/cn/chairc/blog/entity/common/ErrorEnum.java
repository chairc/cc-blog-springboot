package cn.chairc.blog.entity.common;

/**
 * @author chairc
 * @date 2021/7/7 16:58
 */
public enum ErrorEnum {

    /**
     * 400错误提示
     */

    HTTP_STATUS_400(400, "糟了！无法完成请求了", "无法完成请求了", "error/warning"),

    /**
     * 401错误提示
     */

    HTTP_STATUS_401(401, "糟了！登录过期了", "账户登录时间已经过期", "error/warning"),

    /**
     * 404错误提示
     */

    HTTP_STATUS_404(404, "糟了！页面消失了", "我们似乎没有找到页面呢", "error/warning"),

    /**
     * 500错误提示
     */

    HTTP_STATUS_500(500, "糟了！似乎发生了什么错误", "我们服务器似乎出现了内部错误", "error/error"),

    /**
     * error错误提示
     */

    HTTP_STATUS_ERROR(500, "糟了！出现异常", "我们似乎出现了异常", "error/error");

    /**
     * 状态码
     */

    private int httpCode;

    /**
     * 返回标题
     */

    private String errorTitle;

    /**
     * 返回文本
     */

    private String errorContent;

    /**
     * 返回页面
     */

    private String returnHtml;

    /**
     * ErrorEnum构造方法
     *
     * @param httpCode     状态码
     * @param errorTitle   返回标题
     * @param errorContent 返回文本
     * @param returnHtml   返回页面
     */

    ErrorEnum(int httpCode, String errorTitle, String errorContent, String returnHtml) {
        this.httpCode = httpCode;
        this.errorTitle = errorTitle;
        this.errorContent = errorContent;
        this.returnHtml = returnHtml;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorContent() {
        return errorContent;
    }

    public void setErrorContent(String errorContent) {
        this.errorContent = errorContent;
    }

    public String getReturnHtml() {
        return returnHtml;
    }

    public void setReturnHtml(String returnHtml) {
        this.returnHtml = returnHtml;
    }
}
