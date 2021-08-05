package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.parent_dto.ParentDtoGet;
import com.example.mount_carmel_school.dto.parent_dto.ParentDtoPost;
import com.example.mount_carmel_school.dto.school_admin_dto.SchoolAdminDtoGet;
import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoGet;
import com.example.mount_carmel_school.dto.school_employee_dto.SchoolEmployeeDtoPost;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.SchoolAdmin;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.service.ParentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/parents")
@AllArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    public ResponseEntity<List<ParentDtoGet>> getAll() {
        return new ResponseEntity<List<ParentDtoGet>>(parentService.getAll(), HttpStatus.OK);
    }


    @GetMapping("/paginated")
    public ResponseEntity<?> getAllPaginated(Pageable pageable) {
        return  ResponseEntity.ok(parentService.getAllPaginated(pageable));
    }

    @PostMapping
    public ResponseEntity<ParentDtoGet> add(@RequestBody ParentDtoPost parentDtoPost)  {
        return new ResponseEntity<ParentDtoGet>(parentService.add(parentDtoPost),HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ParentDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<ParentDtoGet>(parentService.get(id),HttpStatus.OK);
    }

    @GetMapping(path = "/lock-status/{status}")
    public ResponseEntity<List<ParentDtoGet>> getAllByLockStatus(@PathVariable("status") boolean status) {
        return new ResponseEntity<List<ParentDtoGet>>(parentService.getAllByLockStatus(status), HttpStatus.OK);
    }

    @GetMapping(path = "/lock-status/{status}/paginated")
    public ResponseEntity<?> getAllByLockStatusPaginated(Pageable pageable,@PathVariable("status") boolean status) {
        return  ResponseEntity.ok(parentService.getAllByLockStatusPaginated(pageable,status));
    }

    @GetMapping(path = "/confirm-status/{status}")
    public ResponseEntity<List<ParentDtoGet>> getAllByConfirmStatus(@PathVariable("status") boolean status) {
        return new ResponseEntity<List<ParentDtoGet>>(parentService.getAllByConfirmStatus(status), HttpStatus.OK);
    }

    @GetMapping(path = "/confirm-status/{status}/paginated")
    public ResponseEntity<?> getAllByConfirmStatusPaginated(Pageable pageable,@PathVariable("status") boolean status) {
        return  ResponseEntity.ok(parentService.getAllByConfirmStatusPaginated(pageable,status));
    }

    @PutMapping(path="/add-student/parent/{parentId}/student/{studentId}")
    public ResponseEntity<ParentDtoGet> addStudent(
            @PathVariable("parentId") Long parentId, @PathVariable("studentId") Long studentId) {
        return   new ResponseEntity<ParentDtoGet>(parentService.addStudentToParent(parentId,studentId),HttpStatus.OK);
    }



    @PutMapping(path = "{id}")
    public  ResponseEntity<ParentDtoGet> update(
            @PathVariable("id") Long id,@RequestBody ParentDtoPost parentDtoPost) {
        return new ResponseEntity<>(parentService.update(id,parentDtoPost), HttpStatus.OK);
    }


    @GetMapping(path = "user/{UserId}")
    public ResponseEntity<ParentDtoGet> getByUser(
            @PathVariable("UserId") Long id) {
        return  new ResponseEntity<ParentDtoGet>(parentService.getByUser(id),HttpStatus.OK);
    }


    @GetMapping(path = "/search/{key}/paginated")
    public ResponseEntity<?> searchPaginated(Pageable pageable,@PathVariable("key") String key) {
        return  ResponseEntity.ok(parentService.search(key,pageable));
    }


    @GetMapping(path = "/confirm-status/{status}/search/{key}/paginated")
    public ResponseEntity<?> searchConfirmStatusPaginated(Pageable pageable,@PathVariable("key") String key,@PathVariable("status") boolean status) {
        return  ResponseEntity.ok(parentService.searchConfirmStatusPaginated(key,status,pageable));
    }


}
