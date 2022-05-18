package org.example.controller;

import org.example.DTO.Episode;
import org.example.service.CourseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * @author Joshua.H.Brooks
 * @description 纤体操作： 拷贝B站视频的html文件的<div class="cur-list"> 保存文件 然后请求该接口， 保存信息到本地DB
 * @date 2022-05-13 11:30
 */
@RestController("/course")
public class PageController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String main(String[] args) throws IOException {

        String qualifiedName = "/Users/JoshuaBrooks/Documents/RoadMaps/bilibili/JAVA_SE/宋红康JVM.html";
        String courseName = qualifiedName.substring(qualifiedName.lastIndexOf("/") + 1).split("\\.")[0];
        String line;
        StringBuffer sb = new StringBuffer();
        File testFile = new File(qualifiedName);
        if (!testFile.exists()) {
            System.out.println(testFile.getName() + " isn't existed");
            throw new FileNotFoundException(testFile.getName() + " is not found");
        }
        // 使用try-resource方式
        try (FileReader fileReader = new FileReader(testFile); BufferedReader br = new BufferedReader(fileReader)) {
            line = br.readLine();
            while (line != null) {
                //System.out.println(line);
                sb.append(line);
                // Notice: the following statement is necessary.
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Episode> eList = new ArrayList<>();
        Document document = Jsoup.parse(sb.toString());
        Elements elements = document.getElementsByClass("list-box");
        Elements parts = Objects.requireNonNull(elements.first()).getElementsByClass("part");
        Elements pageNums = Objects.requireNonNull(elements.first()).getElementsByClass("page-num");
        Elements duration = Objects.requireNonNull(elements.first()).getElementsByClass("duration");
        Elements hrefs = Objects.requireNonNull(elements.first()).getElementsByTag("a");

        if (pageNums.size() != parts.size() || pageNums.size() != duration.size() || pageNums.size() != hrefs.size() || duration.size() != parts.size()) {
            throw new IllegalArgumentException("四要素 长度不等");
        }

        for (int i = 0; i < parts.size(); i++) {
            String min = duration.get(i).getElementsByTag("div").html();
            Episode episode = new Episode(
                      courseName
                    , pageNums.get(i).getElementsByTag("span").html()
                    , parts.get(i).getElementsByTag("span").html()
                    , min
                    , getMinutes(min)
                    , hrefs.get(i).select("a").attr("href")
                    , new Date());
            eList.add(episode);
        }
        eList.stream().forEach(System.out::println);
        courseService.insertBatch(eList);
        return "";
    }

    private static int getMinutes(String min) {
        int minutes = 0;
        String[] s = min.split(":");
        for (int i = 0; i < s.length; i++) {
            if (s[i].startsWith("0")) {
                minutes += Math.pow(60, s.length - i - 1) * Integer.parseInt(s[i].substring(1));
            } else {
                minutes += Math.pow(60, s.length - i - 1) * Integer.parseInt(s[i]);
            }
        }
        return minutes / 60;
    }

}
