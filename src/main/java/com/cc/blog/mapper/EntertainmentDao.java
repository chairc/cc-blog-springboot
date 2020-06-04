package com.cc.blog.mapper;

import com.cc.blog.model.Entertainment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EntertainmentDao {
    List<Entertainment> getWpsSidAll();
}
