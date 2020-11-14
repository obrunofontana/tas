package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.sale.entity.SalesOrderEntity;
import org.springframework.web.bind.annotation.RestController;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.sale.service.SalesOrderService;
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
public class SaleOrderController {

    @Autowired
    private SalesOrderService service;

    @GetMapping("sales")
    public ResponseEntity<List<SalesOrderEntity>> findAll() {
        try {
            List<SalesOrderEntity> sales = service.findAll();

            return new ResponseEntity(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("sales")
    public ResponseEntity create(@RequestBody SalesOrderEntity sale) {
        try {
            this.service.save(sale);

            return new ResponseEntity(sale, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("sales/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            SalesOrderEntity sale = this.service.findById(id);

            return new ResponseEntity(sale, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("sales/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody SalesOrderEntity sale) {
        try {
            SalesOrderEntity found = this.service.findById(id);

            sale.setId(found.getId());

            this.service.save(sale);

            return new ResponseEntity(sale, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("sales/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            SalesOrderEntity found = this.service.findById(id);
            
            this.service.delete(found.getId());

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
