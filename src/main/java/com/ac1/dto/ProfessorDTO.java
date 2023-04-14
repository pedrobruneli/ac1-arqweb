package com.ac1.dto;

import java.util.Set;

import com.ac1.entities.CursoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private String name;
    private String cpf;
    private String rg;
    private String address;
    private String phone;
    private Set<CursoEntity> cursos;
}
