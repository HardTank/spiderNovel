package com.spider.controller;


import com.spider.common.Result;
import com.spider.common.spider.SpiderBook;
import com.spider.service.BookMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author tanlx
 * @description 书籍管理控制器
 * @date 2019/10/18 16:22
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    BookMapper bookMapper;
    @RequestMapping("insert")
    public Result addBook() throws Exception {

       Map map= SpiderBook.connection();
       List list=SpiderBook.getUrl(map,bookMapper);
        Result result=new Result(true,200,"成功",list,list.size());
        return result;
    }
}
