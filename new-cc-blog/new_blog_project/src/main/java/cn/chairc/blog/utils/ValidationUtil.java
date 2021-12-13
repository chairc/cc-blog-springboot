package cn.chairc.blog.utils;

import cn.chairc.blog.entity.common.ResultSet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author chairc
 * @date 2021/9/22 15:02
 */
public class ValidationUtil {

    public static boolean validationUserIsOnline() {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        String validation = (String)session.getAttribute("userPrivateId" );
        return validation != null;
    }

    public static boolean validationInputLength(String inputString,int maxLength){
        return inputString.length() > maxLength;
    }
}
