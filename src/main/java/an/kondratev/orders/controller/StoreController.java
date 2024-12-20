package an.kondratev.orders.controller;

import an.kondratev.orders.dto.OrderDTO;
import an.kondratev.orders.dto.ProductDTO;
import an.kondratev.orders.model.Order;
import an.kondratev.orders.model.Product;
import an.kondratev.orders.service.OrderServiceInterface;
import an.kondratev.orders.service.ProductServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class StoreController {
    private final ProductServiceInterface productService;
    private final OrderServiceInterface orderService;
    private final ObjectMapper objectMapper;

    @PostMapping("create_product")
    public ResponseEntity<Product> createProduct(@RequestBody String json) throws JsonProcessingException {
        ProductDTO productDTO = objectMapper.readValue(json, ProductDTO.class);
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @PostMapping("create_order")
    public ResponseEntity<Order> createOrder(@RequestBody String json) throws JsonProcessingException {
        OrderDTO orderDTO = objectMapper.readValue(json, OrderDTO.class);
        return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.CREATED);
    }

    @GetMapping("all_products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PutMapping("update_product")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProduct(productDTO), HttpStatus.OK);
    }

    @DeleteMapping("delete_product/{id}")
    public HttpStatus deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return HttpStatus.OK;
    }

    @GetMapping("order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.getOrder(id), HttpStatus.OK);
    }
}