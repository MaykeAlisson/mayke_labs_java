package com.example.labs.controllers;

import com.example.labs.model.MessageModel;
import com.example.labs.services.LabsService;
import com.example.labs.services.rabbitMq.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequestMapping("${app.paths.v1}/labs")
@RestController
@RequiredArgsConstructor
public class LabsController {

    private final Producer producer;
    private final LabsService service;

    @PostMapping("/rabbitmq")
    public ResponseEntity<Void> createMessage(@Valid @RequestBody MessageModel model) {
        log.info("LabsController.createMessage - start - Body {}", model);
        producer.send(model);
        log.info("LabsController.createMessage - end");
        return ResponseEntity.ok().build();
    }

    @GetMapping("redis")
    public ResponseEntity<String> redisAll(){
        log.info("LabsController.redisAll - start }");
        final String result = service.redisAll();
        log.info("LabsController.redisAll - end");
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("redis/{id}")
    public ResponseEntity<String> redisId(@PathVariable(value = "id") Long id){
        log.info("LabsController.redisId - start - id {}", id);
        final String result = service.redisId(id);
        log.info("LabsController.redisId - end");
        return ResponseEntity.ok().body(result);
    }
}
