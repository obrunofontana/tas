package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.entity.GroupEntity;
import org.springframework.web.bind.annotation.RestController;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.GroupService;
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
public class GroupController {

    @Autowired
    private GroupService service;

    @GetMapping("groups")
    public ResponseEntity<List<GroupEntity>> findAll() {
        try {
            List<GroupEntity> groups = service.findAll();

            return new ResponseEntity(groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("groups")
    public ResponseEntity create(@RequestBody GroupEntity group) {
        try {
            this.service.save(group);

            return new ResponseEntity(group, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("groups/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            GroupEntity group = this.service.findById(id);

            return new ResponseEntity(group, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("groups/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody GroupEntity group) {
        try {
            GroupEntity found = this.service.findById(id);

            group.setId(found.getId());

            this.service.save(group);

            return new ResponseEntity(group, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("groups/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            GroupEntity found = this.service.findById(id);
            
            this.service.delete(found.getId());

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(new CustomErrorResponse("Nenhum registro encontrado"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new CustomErrorResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
