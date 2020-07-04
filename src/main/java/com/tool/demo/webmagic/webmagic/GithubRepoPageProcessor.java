package com.tool.demo.webmagic.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * http://webmagic.io/
 * webmagic主要由Downloader（下载器）、PageProcesser（解析器）、Schedule（调度器）和Pipeline（管道）四部分组成。
 * webmagic采用完全模块化的设计，功能覆盖整个爬虫的生命周期(链接提取、页面下载、内容抽取、持久化)，支持多线程抓取，分布式抓取，并支持自动重试、自定义UA/cookie等功能。
 * webmagic包含页面抽取功能，开发者可以使用css selector、xpath和正则表达式进行链接和内容的提取，支持多个选择器链式调用。
 *
 * 官方例子报错了javax.net.ssl.SSLException: Received fatal alert: protocol_version，应该是maven仓库包有问题
 *
 * @author WangChao
 * @date 2020/04/15
 */
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        page.addTargetRequests(
                page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
        page.addTargetRequests(
                page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name",
                page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()")
                        .toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        // 可以设置header，cookie等
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/gitsxlx/")
                .thread(5).run();
    }
}
