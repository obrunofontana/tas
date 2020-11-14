package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.sale.entity.CustomerEntity;
import org.springframework.web.bind.annotation.RestController;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.sale.service.CustomerService;
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
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("customers")
    public ResponseEntity<List<CustomerEntity>> findAll() {
        try {
            List<CustomerEntity> customers = service.findAll();

            return new ResponseEntity(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("customers")
    public ResponseEntity create(@RequestBody CustomerEntity customer) {
        try {
            this.service.save(customer);

            return new ResponseEntity(customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("customers/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            CustomerEntity customer = this.service.findById(id);

            return new ResponseEntity(customer, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("customers/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody CustomerEntity customer) {
        try {
            CustomerEntity found = this.service.findById(id);

            customer.setId(found.getId());

            this.service.save(customer);

            return new ResponseEntity(customer, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            CustomerEntity found = this.service.findById(id);
            
            this.service.delete(found.getId());

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
