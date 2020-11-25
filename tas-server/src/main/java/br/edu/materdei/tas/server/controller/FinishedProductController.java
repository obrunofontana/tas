package br.edu.materdei.tas.server.controller;



import org.springframework.web.bind.annotation.RestController;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;

import br.edu.materdei.tas.composition.entity.FinishedProductEntity;
import br.edu.materdei.tas.composition.service.FinishedProductService;

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
public class FinishedProductController {
    
    @Autowired
    private FinishedProductService service;

    @GetMapping("finishedProducts")
    public ResponseEntity<List<FinishedProductEntity>> findAll() {
        try {
            List<FinishedProductEntity> finishProducts = service.findAll();

            return new ResponseEntity(finishProducts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("finishedProducts")
    public ResponseEntity create(@RequestBody FinishedProductEntity finishedProduct) {
        try {
            this.service.save(finishedProduct);

            return new ResponseEntity(finishedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("finishedProducts/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            FinishedProductEntity finishedProduct = this.service.findById(id);

            return new ResponseEntity(finishedProduct, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("finishedProducts/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody FinishedProductEntity finishedProduct) {
        try {
            FinishedProductEntity found = this.service.findById(id);

            finishedProduct.setId(found.getId());

            this.service.save(finishedProduct);

            return new ResponseEntity(finishedProduct, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("finishedProducts/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            FinishedProductEntity found = this.service.findById(id);
            
            this.service.delete(found.getId());

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
