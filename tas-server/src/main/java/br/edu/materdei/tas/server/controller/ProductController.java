package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.entity.ProductEntity;
import org.springframework.web.bind.annotation.RestController;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.ProductService;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 *
 * @author brunofontana
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("products")
    public ResponseEntity<List<ProductEntity>> findAll() {
        try {
            List<ProductEntity> products = service.findAll();

            return new ResponseEntity(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("products")
    public ResponseEntity create(@RequestBody ProductEntity product) {
        try {
            this.service.save(product);

            return new ResponseEntity(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("products/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            ProductEntity product = this.service.findById(id);

            return new ResponseEntity(product, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("products/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody ProductEntity product) {
        try {
            ProductEntity found = this.service.findById(id);

            product.setId(found.getId());

            this.service.save(product);

            return new ResponseEntity(product, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            ProductEntity found = this.service.findById(id);
            
            this.service.delete(found.getId());

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
