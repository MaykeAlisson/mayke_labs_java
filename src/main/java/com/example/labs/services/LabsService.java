package com.example.labs.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LabsService {

    @Cacheable(cacheNames = "Labs", key="#root.method.name")
    public String redisAll(){
        final Date data = new Date();
        return String.format("all: %s", data);
    }

    @Cacheable(cacheNames = "Labs",  key="#p0")
    public String redisId(Long id){
        final Date data = new Date();
        return String.format("id: %s - %s", id, data);
    }
}
