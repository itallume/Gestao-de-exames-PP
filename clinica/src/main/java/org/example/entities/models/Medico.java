package org.example.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Medico {

    private String nome;
    private String crm;
    private String especialidade;

    public String getCrmFormatado() {
        //TODO
        return crm;
    }
}
