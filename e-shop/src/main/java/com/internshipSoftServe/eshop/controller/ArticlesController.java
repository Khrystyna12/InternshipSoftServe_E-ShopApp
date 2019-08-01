package com.internshipSoftServe.eshop.controller;

import com.internshipSoftServe.eshop.model.Articles;
import com.internshipSoftServe.eshop.model.Product;
import com.internshipSoftServe.eshop.repository.ArticlesRepository;
import com.internshipSoftServe.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/shop")
public class ArticlesController {

    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private ProductRepository productRepository;

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

    @GetMapping("products/{productId}/articles")
    public @ResponseBody Iterable<Articles> getArticleByProduct (@PathVariable ("productId") long productId){
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        return product.getArticles();
    }

    @PostMapping("/products/{productId}/articles")
    public ResponseEntity<Articles> createNewArticleToProduct(@PathVariable("productId") long productId,
                                                               @Valid @RequestBody Articles article){
        Product product = productRepository.findById(productId).orElseThrow(RuntimeException::new);
        article.setProduct(product);
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

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
