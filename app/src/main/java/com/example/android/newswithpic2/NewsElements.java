package com.example.android.newswithpic2;

/**
 * Created by Pratik saxsena on 29-03-2017.
 */

public class NewsElements {

    private String headlines;
    private String image;
    private String articleurl;

    public NewsElements(String headlines, String image, String articleurl)
    {
        this.headlines = headlines;
        this.image = image;
        this.articleurl = articleurl;
    }

    public String getImage()
    {
        if(image.isEmpty())
            return "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTwgXsYnKFUauC25YvnwFbRo86qBy6JIE_kag7skgfBRz_x85ia";
        else
            return image;
    }

    public String getArticleurl()
    {
        return articleurl;
    }

    public String getHeadlines()
    {
        return headlines;
    }

    public void setArticleurl(String articleurl ){this.articleurl = articleurl;}

    public void setImage(String image)
    {
        this.image = image;
    }

    public void setHeadlines(String headlines)
    {
        this.headlines = headlines;
    }
}
