package cn.chairc.blog.service.impl;

import cn.chairc.blog.mapper.HeadImageDao;
import cn.chairc.blog.model.HeadImage;
import cn.chairc.blog.service.HeadImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadImageServiceImpl implements HeadImageService {

    @Autowired
    HeadImageDao headImageDao;

    @Override
    public HeadImage getUserHeadImage(int id) {

        return headImageDao.getUserHeadImage(id);

    }
}
