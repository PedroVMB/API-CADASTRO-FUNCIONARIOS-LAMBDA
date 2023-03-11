package com.api.cadastrofuncionarios.lambda.service;

import com.api.cadastrofuncionarios.lambda.enums.GeneralMessages;
import com.api.cadastrofuncionarios.lambda.utils.CpfMethod;
import com.api.cadastrofuncionarios.lambda.model.FunctionaryModel;
import com.api.cadastrofuncionarios.lambda.repositories.FunctionaryRepository;
import com.api.cadastrofuncionarios.lambda.utils.InvalidCPFException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FunctionaryService {

    FunctionaryRepository functionaryRepository;


    public FunctionaryService(FunctionaryRepository functionaryRepository){
        this.functionaryRepository = functionaryRepository;
    }

    @Transactional
    public FunctionaryModel save(FunctionaryModel functionaryModel) {
        if (!CpfMethod.isCPF(functionaryModel.getCpf())) {
            throw new InvalidCPFException(GeneralMessages.CPF_INVALID);
        }
        return functionaryRepository.save(functionaryModel);
    }

    public Page<FunctionaryModel> findAll(Pageable pageable){
        return functionaryRepository.findAll(pageable);
    }
    public Optional<FunctionaryModel> findByID(UUID id){
        return functionaryRepository.findById(id);
    }

    @Transactional
    public void delete(FunctionaryModel functionaryModel){
        functionaryRepository.delete(functionaryModel);
    }

}
