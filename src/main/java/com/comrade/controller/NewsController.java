package com.comrade.controller;

import com.comrade.entity.NewsEntity;
import com.comrade.model.NewsModel;
import com.comrade.model.SearchModel;
import com.comrade.model.SearchResultModel;
import com.comrade.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
@Slf4j
public class NewsController {

    private final NewsService newsService;

    @PostMapping("/save")
    public ResponseEntity<NewsModel> save(@RequestBody NewsModel newsModel){
        log.info("save::started::{}",newsModel);
        NewsModel savedNewsModel = newsService.save(newsModel);
        log.info("save::completed::{}",savedNewsModel);
        return new ResponseEntity<>(savedNewsModel, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<NewsEntity>> findAll(){
        log.info("findAll::started");
        List<NewsEntity> newsEntities = newsService.fetchAll();
        log.info("findAll::completed");
        return new ResponseEntity<>(newsEntities, HttpStatus.OK);
    }

    @PostMapping("/search")
    public SearchResultModel fetchBySearchCriteria(@RequestBody SearchModel searchModel){
        return newsService.fetchBySearchCriteria(searchModel);
    }
}
