package cn.chairc.blog.mapper;

import cn.chairc.blog.model.HeadImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HeadImageDao {

    HeadImage getUserHeadImage(int id);

}
