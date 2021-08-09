package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoGet;
import com.example.mount_carmel_school.dto.contact_us_message_dto.ContactUsMessageDtoPost;
import com.example.mount_carmel_school.service.ContactUsMessageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contact-us-messages")
@AllArgsConstructor
public class ContactUsMessageController {
    private final ContactUsMessageService contactUsMessageService;

    @GetMapping
    public ResponseEntity<List<ContactUsMessageDtoGet>> getAll() {
        return new  ResponseEntity<List<ContactUsMessageDtoGet>>(contactUsMessageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity getAllPaginated(Pageable pageable) {
        return  ResponseEntity.ok(contactUsMessageService.getPaginatedAll(pageable));
    }

    @GetMapping("/is-read/status/{status}")
    public ResponseEntity<List<ContactUsMessageDtoGet>>  getByReadStatus( @PathVariable("status") boolean status) {
        return new ResponseEntity<List<ContactUsMessageDtoGet>>(contactUsMessageService.getByReadStatus(status),HttpStatus.OK);
    }

    @GetMapping("/is-read/status/{status}/paginated")
    public ResponseEntity  getByReadStatusPaginated(@PathVariable("status") boolean status,Pageable pageable) {
        return  ResponseEntity.ok(contactUsMessageService.getByReadStatusPaginated(status,pageable));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ContactUsMessageDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ContactUsMessageDtoGet>(contactUsMessageService.get(id),HttpStatus.OK);
    }

    @GetMapping(path = "/is-replied/status/{status}")
    public ResponseEntity<List<ContactUsMessageDtoGet>>  getByRepliedStatus(@PathVariable("status") boolean status) {
        return   new ResponseEntity<List<ContactUsMessageDtoGet>>(contactUsMessageService.getByRepliedStatus(status),HttpStatus.OK);
    }

    @GetMapping(path = "/is-replied/status/{status}/paginated")
    public ResponseEntity  getByRepliedStatus(@PathVariable("status") boolean status,Pageable pageable) {
        return  ResponseEntity.ok(contactUsMessageService.getByRepliedStatusPaginated(status,pageable));
    }

    @PostMapping
    public ResponseEntity<ContactUsMessageDtoGet> add(@RequestBody ContactUsMessageDtoPost contactUsMessageDtoPost)  {
        return new ResponseEntity<ContactUsMessageDtoGet>(contactUsMessageService.add(contactUsMessageDtoPost),HttpStatus.CREATED);
    }


    @PutMapping(path = "/mark-as-read/{id}")
    public ResponseEntity<ContactUsMessageDtoGet>  markAsRead(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ContactUsMessageDtoGet>(contactUsMessageService.markAsRead(id),HttpStatus.OK);
    }


    @GetMapping(path = "/search/{key}/paginated")
    public ResponseEntity  searchPaginated(@PathVariable("key") String key, Pageable pageable) {
        return   ResponseEntity.ok(contactUsMessageService.search(key,pageable));
    }
}
