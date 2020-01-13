package com.training.filetodbusingkafka.service.impl;

import com.training.filetodbusingkafka.service.FileToDbService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileToDbServiceImpl implements FileToDbService {

    @Value("${kafka.bootstrap.servers}")
    private String kafkaBootstrapServers;

    @Override
    public void fileTransfer() {




    }
}
