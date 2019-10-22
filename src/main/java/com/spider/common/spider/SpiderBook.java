package com.spider.common.spider;

import com.spider.common.SslUtils;
import com.spider.model.Book;
import com.spider.service.BookMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tanlx
 * @description 爬取线程
 * @date 2019/10/21 10:05
 */
public class SpiderBook {

    public static Map<String, Elements> connection() throws Exception {
        Map map = new HashMap();
        URL u = new URL("https://www.xbiquge.cc");
        if ("https".equalsIgnoreCase(u.getProtocol())) {
            SslUtils.ignoreSsl();
        }
        String category = null;
        Elements title = null;
        Document document = Jsoup.connect("https://www.xbiquge.cc/paihangbang/").get();
        List list = new ArrayList();
        for (Element element : document.select("table[class=tbo]")) {
            category = (String) element.select("span[class=btitle]").text().subSequence(0, 4);
            title = element.select("ul[class=fen-top]");
            map.put(category, title);
        }
        System.out.println(map);
        return map;
    }

    public static List getUrl(Map<String, Elements> map, BookMapper bookMapper) throws IOException, ParseException {
        Map m = new HashMap();
        List<Book> novel = new ArrayList<Book>();
        for (Map.Entry<String, Elements> entry : map.entrySet()) {
            String catagory = entry.getKey();
            Elements elements = entry.getValue();
            for (Element element : elements.select("a")) {
                System.out.println("正在爬取《" + element.text() + "》");
                String url = element.attr("href");
                Book book = getBookInfo(catagory, url);
                novel.add(book);
                bookMapper.insert(book);
            }
        }
        return novel;
    }

    public static Book getBookInfo(String catagory, String url) throws IOException, ParseException {
        Book novel = new Book();
        novel.setCategory(catagory);
        novel.setUrl(url);
        Document document = Jsoup.connect(url).get();
        Element element = document.getElementById("info");
        novel.setTitle(element.select("h1").text());
        novel.setAuthor(element.select("a[target=_blank]").text());
        String date = element.select("p").get(2).select("p").get(0).text().substring(5, 21);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        novel.setUpdateTime(formatter.parse(date));
        novel.setLastChapter(element.select("p").get(3).text());
        novel.setIntro(element.getElementById("intro").text());

        return novel;
    }

    public static void main(String[] args) throws Exception {
        // System.out.println(Jsoup.connect("http://218.246.5.13/pressmanager/#/countIndex").get());
        Connection connection = Jsoup.connect("http://218.246.5.13/PressProject/salesman/getSalesmanList");
        //connection.header("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        connection.ignoreContentType(true);

        System.out.println(connection.post().text());
        //SpiderBook.getUrl(SpiderBook.connection());
        // SpiderBook.getBookInfo("科幻小说","https://www.xbiquge.cc/book/8585/");
    }
}
