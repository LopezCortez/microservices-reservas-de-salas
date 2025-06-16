package com.otavio.usuario.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String telefone;
    private String rua;
    private String numero;
    private String cidade;
    private String cep;
}
