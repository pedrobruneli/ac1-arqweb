package com.ac1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorAgendaDTO {
    private String start_date;
    private String end_date;
    private String city;
    private String state;
    private String cep;
    private String curso_name;
    private String professor_name;
}
