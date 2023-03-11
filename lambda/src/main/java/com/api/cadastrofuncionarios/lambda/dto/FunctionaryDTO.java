package com.api.cadastrofuncionarios.lambda.dto;

import jakarta.validation.constraints.NotBlank;

public class FunctionaryDTO {

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "CPF is required")
    private String cpf;
    @NotBlank(message = "email is required")
    private String email;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
