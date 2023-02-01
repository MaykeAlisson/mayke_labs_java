package com.example.labs.controllers;

import com.example.labs.model.MessageModel;
import com.example.labs.services.rabbitMq.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequestMapping("${app.paths.v1}/labs")
@RestController
@RequiredArgsConstructor
public class LabsController {

    private final Producer producer;

    @PostMapping("/rabbitmq")
    public ResponseEntity<Void> createMessage(@Valid @RequestBody MessageModel model) {
        log.info("LabsController.createMessage - start - Body {}", model);
        producer.send(model);
        log.info("LabsController.createMessage - end");
        return ResponseEntity.ok().build();
    }
}
