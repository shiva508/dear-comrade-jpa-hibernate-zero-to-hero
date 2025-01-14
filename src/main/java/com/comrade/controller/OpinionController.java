package com.comrade.controller;

import com.comrade.entity.OpinionEntity;
import com.comrade.model.OpinionModel;
import com.comrade.service.OpinionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/opinion")
@RequiredArgsConstructor
@Slf4j
public class OpinionController {

    private final OpinionService opinionService;

    @PostMapping("/save")
    public ResponseEntity<OpinionModel> save(@RequestBody OpinionModel opinionModel){
        log.info("save::started::{}",opinionModel);
        opinionModel = opinionService.save(opinionModel);
        log.info("save::completed::{}",opinionModel);
        return new ResponseEntity<>(opinionModel, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OpinionEntity>> findAll(){
        log.info("findAll::started");
        List<OpinionEntity> opinionEntities = opinionService.fetchAll();
        log.info("findAll::completed");
        return new ResponseEntity<>(opinionEntities, HttpStatus.OK);
    }
}
