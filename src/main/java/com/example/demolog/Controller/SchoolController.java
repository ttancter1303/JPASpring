package com.example.demolog.Controller;

import com.example.demolog.Entity.School;
import com.example.demolog.repo.SchoolRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SchoolController {
    @Autowired
    SchoolRepository schoolRepo;

    @GetMapping("GetAll")
    public ResponseEntity getAll(){
        List<School> schools = schoolRepo.findAll();
        System.out.println(schools);
        return new ResponseEntity("Get all done "+ schools, HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity create(){
        School school = new School();
        school.setSchoolName("ABC");
        school.setStartYear(2000);
        schoolRepo.save(school);
        return new ResponseEntity<>("Create succesfully "+school, HttpStatus.OK);
    }
    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody SchoolReq schoolReq){
        School school = new School();
        school.setSchoolName(schoolReq.getSchoolName());
        school.setStartYear(schoolReq.getStartYear());
        schoolRepo.save(school);
        return new ResponseEntity<>("Create succesfully "+school, HttpStatus.OK);
    }
    @PutMapping("school/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody SchoolUpdateReq schoolUpdateReq){
        School school  = schoolRepo.findById(id).orElse(null);
        school.setSchoolName(schoolUpdateReq.getSchoolName());
        school.setStartYear(schoolUpdateReq.getStartYear());
        log.info(schoolUpdateReq.getSchoolName());
        if (school!=null){
            schoolRepo.save(school);
            return new ResponseEntity<>("Success" , HttpStatus.OK);
        }
        log.error("update fail "+ school);
        return new ResponseEntity<>("Not found product with id" +id, HttpStatus.OK);
    }
    @DeleteMapping("school/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        schoolRepo.deleteById(id);
        return new ResponseEntity<>("Delete succesfully",HttpStatus.OK);
    }
    @GetMapping("searchData")
    public ResponseEntity<?> searchData(@RequestParam(required = false) String schoolName){
        List<School> products = schoolRepo.findSchoolBySchoolName(schoolName);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

}
