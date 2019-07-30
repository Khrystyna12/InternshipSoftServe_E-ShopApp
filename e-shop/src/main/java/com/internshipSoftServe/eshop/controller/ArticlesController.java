package com.internshipSoftServe.eshop.controller;

import com.internshipSoftServe.eshop.model.Articles;
import com.internshipSoftServe.eshop.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/")
public class ArticlesController {

    @Autowired
    private ArticlesRepository articlesRepository;

    @GetMapping("/articles")
    @ResponseBody
    public  Iterable<Articles> getAllArticles(){
        return articlesRepository.findAll();
    }

    @GetMapping("/articles/{articleId}")
    @ResponseBody
    public Articles getOneArticle(@PathVariable("articleId") long articleId){
        return articlesRepository.findById(articleId).orElse(null);
    }


    @PostMapping("/articles")
    private ResponseEntity<Articles> createNewArticle(@Valid @RequestBody Articles article){
        return ResponseEntity.ok(articlesRepository.save(article));
    }

    @PutMapping("/articles/{articleId}")
    public ResponseEntity<Articles> updateArticle(@PathVariable("articleId") long articleId,
                                                  @RequestBody Articles article){
        return articlesRepository.findById(articleId).
                map(articles -> {articles.setName(article.getName());
                articles.setText(article.getText());
                articles.setCreated_at(article.getCreated_at());
                articles.setProduct(article.getProduct());
                Articles updatedArticle = articlesRepository.save(articles);
                return ResponseEntity.ok().body(updatedArticle);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/articles/{articleId}")
    public void deleteArticle(@PathVariable Long articleId){
         articlesRepository.deleteById(articleId);
    }

}
