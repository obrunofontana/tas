package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.sale.entity.InvoiceEntity;
import org.springframework.web.bind.annotation.RestController;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.sale.service.InvoiceService;
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
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @GetMapping("invoices")
    public ResponseEntity<List<InvoiceEntity>> findAll() {
        try {
            List<InvoiceEntity> invoices = service.findAll();

            return new ResponseEntity(invoices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("invoices")
    public ResponseEntity create(@RequestBody InvoiceEntity invoice) {
        try {
            this.service.save(invoice);

            return new ResponseEntity(invoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("invoices/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            InvoiceEntity invoice = this.service.findById(id);

            return new ResponseEntity(invoice, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("invoices/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody InvoiceEntity invoice) {
        try {
            InvoiceEntity found = this.service.findById(id);

            invoice.setId(found.getId());

            this.service.save(invoice);

            return new ResponseEntity(invoice, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("invoices/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            InvoiceEntity found = this.service.findById(id);
            
            this.service.delete(found.getId());

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
