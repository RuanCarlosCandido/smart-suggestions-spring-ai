package com.smartai.smart_suggestions.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pgvector.PGvector;
import com.smartai.smart_suggestions.dto.SimilarProductDTO;
import com.smartai.smart_suggestions.entity.Embedding;
import com.smartai.smart_suggestions.entity.Product;
import com.smartai.smart_suggestions.repository.EmbeddingCustomRepository;
import com.smartai.smart_suggestions.repository.EmbeddingRepository;
import com.smartai.smart_suggestions.repository.ProductRepository;
import com.smartai.smart_suggestions.service.EmbeddingService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final EmbeddingRepository embeddingRepository;
    private final EmbeddingService embeddingService;
    private final EmbeddingCustomRepository embeddingCustomRepository;


    public ProductController(ProductRepository productRepository,
            EmbeddingRepository embeddingRepository,
            EmbeddingService embeddingService,
            EmbeddingCustomRepository embeddingCustomRepository) {
        this.productRepository = productRepository;
        this.embeddingRepository = embeddingRepository;
        this.embeddingService = embeddingService;
        this.embeddingCustomRepository = embeddingCustomRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);

        PGvector vector = embeddingService.generateEmbedding(
                product.getName() + " " + product.getDescription());

        Embedding embedding = new Embedding();
        embedding.setProduct(savedProduct);
        embedding.setVector(vector);

        embeddingCustomRepository.saveWithVector(embedding);

        return savedProduct;
    }

    public String arrayToSqlVector(float[] array) {
        return Arrays.toString(array).replaceAll("\\s+", "");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
            @RequestBody Product productDetails) {

        return productRepository.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            Product updatedProduct = productRepository.save(product);

    PGvector vector = embeddingService.generateEmbedding(
            product.getName() + " " + product.getDescription());


            Embedding embedding = embeddingRepository.findByProductId(id)
                    .orElse(new Embedding());
            embedding.setProduct(updatedProduct);
            embedding.setVector(vector);
            embeddingRepository.save(embedding);

            return ResponseEntity.ok(updatedProduct);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        embeddingRepository.deleteByProductId(id);
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/similares")
    public ResponseEntity<List<SimilarProductDTO>> buscarSimilares(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "5") int topK) {

        var similares = embeddingCustomRepository.buscarProdutosSimilares(productId, topK);
        return ResponseEntity.ok(similares);
    }

}
