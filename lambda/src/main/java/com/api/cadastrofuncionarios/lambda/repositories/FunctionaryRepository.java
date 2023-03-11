package com.api.cadastrofuncionarios.lambda.repositories;

import com.api.cadastrofuncionarios.lambda.model.FunctionaryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FunctionaryRepository extends JpaRepository<FunctionaryModel, UUID> {

}
