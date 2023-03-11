package com.api.cadastrofuncionarios.lambda.controller;

import com.api.cadastrofuncionarios.lambda.dto.FunctionaryDTO;
import com.api.cadastrofuncionarios.lambda.enums.GeneralMessages;
import com.api.cadastrofuncionarios.lambda.model.FunctionaryModel;
import com.api.cadastrofuncionarios.lambda.service.FunctionaryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/records")
public class FunctionaryController {

    final FunctionaryService functionaryService;
    public FunctionaryController(FunctionaryService functionaryService){
        this.functionaryService = functionaryService;
    }

    @PostMapping
    public ResponseEntity<Object> saveFunctionary(@RequestBody @Valid FunctionaryDTO functionaryDTO){
        var functionaryModel = new FunctionaryModel();
        BeanUtils.copyProperties(functionaryDTO, functionaryModel);
        functionaryModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(functionaryService.save(functionaryModel));
    }
    @GetMapping
    public ResponseEntity<Page<FunctionaryModel>> getAllFunctionary(@PageableDefault(page = 0, size = 10,
                                                        sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(functionaryService.findAll(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneFunctionary(@PathVariable(value = "id")UUID id){
        Optional<FunctionaryModel> functionaryModelOptional = functionaryService.findByID(id);
        if (!functionaryModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GeneralMessages.USER_NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(functionaryModelOptional.get());
     }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFunctionary(@PathVariable(value = "id")UUID id){
        Optional<FunctionaryModel> functionaryModelOptional = functionaryService.findByID(id);
        if (!functionaryModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GeneralMessages.USER_NOT_FOUND);
        }
        functionaryService.delete(functionaryModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(GeneralMessages.DELETED_FUNCTIONARY);
     }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFunctionary(@PathVariable(value = "id") UUID id, @RequestBody @Valid FunctionaryDTO functionaryDTO){
        Optional<FunctionaryModel> functionaryModelOptional = functionaryService.findByID(id);
        if(!functionaryModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GeneralMessages.USER_NOT_FOUND);
        }
        var functionaryModel = new FunctionaryModel();
        BeanUtils.copyProperties(functionaryDTO, functionaryModel);
        functionaryModel.setId(functionaryModelOptional.get().getId());
        functionaryModel.setRegistrationDate(functionaryModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(GeneralMessages.UPDATE_FUNCTIONARY);
    }

}
