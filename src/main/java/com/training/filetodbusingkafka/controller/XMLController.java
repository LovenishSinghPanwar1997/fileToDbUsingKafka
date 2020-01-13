package com.training.filetodbusingkafka.controller;

import com.training.filetodbusingkafka.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read")
public class XMLController {

    @Autowired
    private XMLService xmlService;

    @GetMapping()
    public void read()
    {
        xmlService.readXML();
    }


}
