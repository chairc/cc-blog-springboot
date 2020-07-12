package cn.chairc.blog.mapper;

import cn.chairc.blog.model.Entertainment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EntertainmentDao {

    /**
     * 主页获取所有获取所有娱乐功能
     */

    List<Entertainment> getEntertainmentAllToIndex();

    /**
     * 获取所有获取所有娱乐功能
     */

    List<Entertainment> getEntertainmentAll();

    /**
     * 获取wps自动邀请所有sid
     */

    List<Entertainment> getWpsSidAll();
}
