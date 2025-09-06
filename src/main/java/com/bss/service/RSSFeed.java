package com.bss.service;

import com.bss.api.request_responses.BlogPost;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RSSFeed {

        private static final String RSS_URL = "https://techcommunity.microsoft.com/t5/s/gxcuf89792/rss/Community?interaction.style=blog&feeds.replies=false";

        public List<BlogPost> fetchLatestPosts() {
            List<BlogPost> posts = new ArrayList<>();
            log.info("Trying to get the feed");

            try (XmlReader reader = new XmlReader(new URL(RSS_URL))) {
                SyndFeed feed = new SyndFeedInput().build(reader);
                for (SyndEntry entry : feed.getEntries()) {
                    BlogPost post = new BlogPost();
                    post.setTitle(entry.getTitle());
                    post.setLink(entry.getLink());
                    post.setPublishedDate(String.valueOf(entry.getPublishedDate()));
              //      post.setSummary(entry.getDescription() != null ? entry.getDescription().getValue() : "");
                    post.setAuthor(entry.getAuthor());
                    posts.add(post);

                    //posts.stream().forEach(post1 -> System.out.println(post1.getTitle()));

                    log.info("No of feeds come {} ",posts.size());
                }
            } catch (Exception e) {
                log.error("Connectivity failed while getting feeds from techcommunity.microsoft.com {} ",e.getMessage());
                throw new RuntimeException("Connectivity failed while getting feeds from techcommunity.microsoft.com ",e.getCause());
            }

            return posts;
        }

    public static void main(String[] args) {
        RSSFeed rssFeed = new RSSFeed();
        rssFeed.fetchLatestPosts();
    }


}
