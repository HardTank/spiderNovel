package com.spider.service.impl;


import com.spider.model.Book;
import com.spider.service.BookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tanlx
 * @description 接口实现
 * @date 2019/10/18 16:16
 */
@Service
public class BookMapperImpl implements BookMapper {
    @Resource
    BookMapper bookMapper ;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Book record) {
        return bookMapper.insert(record);
    }


    @Override
    public int insertSelective(Book record) {
        return 0;
    }

    @Override
    public Book selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Book record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return 0;
    }
}
