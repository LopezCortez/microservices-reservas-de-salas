package com.otavio.usuario.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private LocalDate dataCadastro;

    @Embedded
    private Endereco endereco;

    public void setNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome deve ter pelo menos 3 caracteres.");
        }
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        if (!isValidCpf(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    private boolean isValidCpf(String cpf) {
        String regex = "\\d{11}";
        return Pattern.matches(regex, cpf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(cpf, usuario.cpf) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && Objects.equals(dataNascimento, usuario.dataNascimento) && Objects.equals(dataCadastro, usuario.dataCadastro) && Objects.equals(endereco, usuario.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, email, senha, dataNascimento, dataCadastro, endereco);
    }
}
