package com.ac1.controllers;

import java.util.List;

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

import com.ac1.dto.CursoDTO;
import com.ac1.entities.CursoEntity;
import com.ac1.services.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<String> createCurso(@RequestBody CursoDTO curso) {
        return cursoService.createCurso(curso);
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getCursos() {
        return cursoService.getCursos();
    }

    @PutMapping
    public ResponseEntity<CursoDTO> editCurso(@RequestParam Long id, @RequestBody CursoEntity curso) {
        return cursoService.editCurso(id, curso);
    }

    @DeleteMapping
    public ResponseEntity<String> removeCurso(@RequestParam Long id) {
        return cursoService.deleteCurso(id);
    }

}
