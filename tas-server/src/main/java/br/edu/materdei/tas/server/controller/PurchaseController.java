package br.edu.materdei.tas.server.controller;



import org.springframework.web.bind.annotation.RestController;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;

import br.edu.materdei.tas.purchase.entity.PurchaseEntity;
import br.edu.materdei.tas.purchase.service.PurchaseService;

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
public class PurchaseController {
    
     @Autowired
    private PurchaseService service;

    @GetMapping("purchases")
    public ResponseEntity<List<PurchaseEntity>> findAll() {
        try {
            List<PurchaseEntity> purchases = service.findAll();

            return new ResponseEntity(purchases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("purchases")
    public ResponseEntity create(@RequestBody PurchaseEntity purchase) {
        try {
            this.service.save(purchase);

            return new ResponseEntity(purchase, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("purchases/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            PurchaseEntity purchase = this.service.findById(id);

            return new ResponseEntity(purchase, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("purchases/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody PurchaseEntity purchase) {
        try {
            PurchaseEntity found = this.service.findById(id);

            purchase.setId(found.getId());

            this.service.save(purchase);

            return new ResponseEntity(purchase, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("purchases/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            PurchaseEntity found = this.service.findById(id);
            
            this.service.delete(found.getId());

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
