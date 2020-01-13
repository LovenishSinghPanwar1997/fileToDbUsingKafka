package com.training.filetodbusingkafka.controller;

import com.training.filetodbusingkafka.service.FileToDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hit")
public class FileToDbController {

    @Autowired
    FileToDbService fileToDbService;

    @GetMapping
    public void start()
    {
        fileToDbService.transfer();
    }

}
