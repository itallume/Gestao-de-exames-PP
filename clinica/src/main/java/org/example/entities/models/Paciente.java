package org.example.entities.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paciente {
    private String nome;
    private int idade;
    private String telefone;
    private String email;
    private String convenio;
    private String sexo;
}
