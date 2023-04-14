package com.ac1.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ac1.dto.ProfessorDTO;
import com.ac1.entities.CursoEntity;
import com.ac1.entities.ProfessorEntity;
import com.ac1.services.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<String> createProfessor(@RequestBody ProfessorDTO professor) {
        return professorService.createUser(professor);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> getProfessores() {
        return professorService.getProfessores();
    }

    @PutMapping
    public ResponseEntity<ProfessorDTO> editProfessor(@RequestParam Long id, @RequestBody ProfessorEntity user) {
        return professorService.editProfessor(id, user);
    }

    @DeleteMapping
    public ResponseEntity<String> removeUser(@RequestParam Long id) {
        return professorService.deleteProfessor(id);
    }

    @PostMapping("/agenda")
    public ResponseEntity<ProfessorEntity> getAgenda(@RequestParam String id) {
        return null;
    }

    @PostMapping("/cursos")
    public ResponseEntity<ProfessorDTO> addCurso(@RequestParam Long id, @RequestParam Long curso) {
        return professorService.addCurso(id, curso);
    }

    @GetMapping("/cursos")
    public ResponseEntity<Set<CursoEntity>> getCursos(@RequestParam Long id) {
        return professorService.getCursos(id);
    }

}
