package com.example.exerciseservices.Services;

import com.example.exerciseservices.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class NewsArticleService {
    ArrayList<NewsArticle>newsArticles=new ArrayList<>();

    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    public boolean updateNewsArticle(String id , NewsArticle newsArticle){
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getId().equals(id)){
                newsArticles.set(i,newsArticle);
                return true;
            }

        }
        return false;
    }

    public boolean deleteNewsArticle(String id){
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getId().equals(id)){
                newsArticles.remove(i);
                return true;
            }

        }

        return false;
    }
    public boolean publishNewsArticle(String id){
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getId().equals(id)&& !newsArticles.get(i).isPublished()){
                newsArticles.get(i).setPublishDate(LocalDateTime.now());
                newsArticles.get(i).setPublished(true);
                return true;
            }

        }
        return false;
    }

    public ArrayList<NewsArticle> getPublishedNewsArticle(){
        ArrayList<NewsArticle> published = new ArrayList<>();
        for (int i = 0; i < newsArticles.size(); i++) {

            if(newsArticles.get(i).isPublished()){
                published.add(newsArticles.get(i));
            }

        }
        return published;
    }

    public ArrayList<NewsArticle> getByCategory(String category){
        ArrayList<NewsArticle> match = new ArrayList<>();

        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getCategory().equals(category)){
                match.add(newsArticles.get(i));
            }

        }
        return match;
    }

}
