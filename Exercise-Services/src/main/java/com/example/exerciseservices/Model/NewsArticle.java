package com.example.exerciseservices.Model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message = "id should not be empty")
    private String id;
    @NotEmpty(message = "title should not be Empty")
    @Size(max = 100,message = "title should not be more than 100 letters")
    private String title;
    @NotEmpty(message = "author should not be Empty")
    @Size(min = 4,max = 20,message = "the author should not be less than 4 and more than 20 letters")
    private String author;
    @NotEmpty(message = "content should not be Empty")
    @Size(min = 200 , message = "content should not be less than 200 letters")
    private String content;
    @NotEmpty(message = "category should not be Empty")
    @Pattern(regexp = "politics|sports|technology",message = "the category should be politics or sports or technology")
    private String category;
    @NotEmpty(message = "the url of the image should not be Empty")
    private String imageUrl;
    @AssertFalse(message = "publish must be false")
    private boolean isPublished;
    private LocalDateTime publishDate;

}
