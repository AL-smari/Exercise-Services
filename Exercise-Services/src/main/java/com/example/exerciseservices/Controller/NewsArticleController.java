package com.example.exerciseservices.Controller;

import com.example.exerciseservices.Model.NewsArticle;
import com.example.exerciseservices.Services.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsArticleController {
    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getNewsArticle(){

        return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getNewsArticles());

    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@Valid @RequestBody NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
            newsArticleService.addNewsArticle(newsArticle);
            return ResponseEntity.status(HttpStatus.OK).body("NewsArticle added");

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id,@Valid@RequestBody NewsArticle newsArticle,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if(newsArticleService.updateNewsArticle(id,newsArticle)){
            return ResponseEntity.status(HttpStatus.OK).body("NewsArticle updated");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable String id){

        if(newsArticleService.deleteNewsArticle(id)){
            return ResponseEntity.status(HttpStatus.OK).body("NewsArticle deleted");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");

    }

    @PutMapping("/publish/{id}")
    public ResponseEntity publishNewsArticles(@PathVariable String id){

        if(newsArticleService.publishNewsArticle(id)){
            return ResponseEntity.status(HttpStatus.OK).body("NewsArticle Published");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found or all NewsArticle Published ");



    }
    @GetMapping("/getPublished")
    public ResponseEntity getPublishedNewsArticle(){

        if(newsArticleService.getPublishedNewsArticle().size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getPublishedNewsArticle());
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there is no published NewsArticle");


    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        if(category.equals("politics")||category.equals("sports")||category.equals("technology")) {

            if (newsArticleService.getByCategory(category).size() > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(newsArticleService.getByCategory(category));
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("there is no NewsArticle in this category ");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the category should be politics or sports or technology");


    }
}
