package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.purchase.entity.SupplierEntity;
import org.springframework.web.bind.annotation.RestController;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.purchase.service.SupplierService;
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
public class SupplierController {

    @Autowired
    private SupplierService service;

    @GetMapping("suppliers")
    public ResponseEntity<List<SupplierEntity>> findAll() {
        try {
            List<SupplierEntity> suppliers = service.findAll();

            return new ResponseEntity(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("suppliers")
    public ResponseEntity create(@RequestBody SupplierEntity supplier) {
        try {
            this.service.save(supplier);

            return new ResponseEntity(supplier, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("suppliers/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            SupplierEntity supplier = this.service.findById(id);

            return new ResponseEntity(supplier, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("suppliers/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody SupplierEntity supplier) {
        try {
            SupplierEntity found = this.service.findById(id);

            supplier.setId(found.getId());

            this.service.save(supplier);

            return new ResponseEntity(supplier, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("suppliers/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            SupplierEntity found = this.service.findById(id);
            
            this.service.delete(found.getId());

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
