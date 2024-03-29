package mk.ukim.finki.wpaud.web.rest;

import mk.ukim.finki.wpaud.model.Product;
import mk.ukim.finki.wpaud.model.dto.ProductDto;
import mk.ukim.finki.wpaud.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    private List<Product> findAll(){
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        return this.productService.findById(id)
                .map(pr -> ResponseEntity.ok().body(pr))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> save(@RequestBody ProductDto productDto){
        return this.productService.save(productDto)
                .map(pr -> ResponseEntity.ok().body(pr))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> save(@PathVariable Long id, @RequestBody ProductDto productDto){
        return this.productService.edit(id, productDto)
                .map(pr -> ResponseEntity.ok().body(pr))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.productService.deleteById(id);
        if(this.productService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
