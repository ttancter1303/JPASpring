package com.example.demolog.Controller;

import com.example.demolog.Entity.Tag;
import com.example.demolog.repo.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TagController {
    @Autowired
    TagRepository schoolRepo;

    @GetMapping("GetAll")
    public ResponseEntity getAll(){
        List<Tag> schools = schoolRepo.findAll();
        System.out.println(schools);
        return new ResponseEntity("Get all done "+ schools, HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity create() {
        ArrayList<String> strings = tagAfterCrawler();
        for (String string : strings) {
            Tag tag = new Tag();
            System.out.println(" tag: " + string);
            tag.setTagName(string);
            schoolRepo.save(tag);
        }
        return new ResponseEntity<>("Create successfully", HttpStatus.OK);
    }
public static ArrayList<String> tagAfterCrawler() {
    ArrayList<String> listTag = new ArrayList<>();
    String url = "https://blogtruyen.vn/";
    try {
        Document document = Jsoup.connect(url).get();
        Elements tags = document.select("ul.submenu.category.list-unstyled a");
        for (Element element : tags) {
            listTag.add(element.text());
            System.out.println("tag: " + element.text());
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return listTag;
}
}

