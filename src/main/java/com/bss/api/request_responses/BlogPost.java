package com.bss.api.request_responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlogPost {


    private String link;  // Using link as unique ID

    private String title;

    private String publishedDate;


    private String summary;
}
