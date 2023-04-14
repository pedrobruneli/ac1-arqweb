package com.ac1.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ac1.dto.CursoDTO;
import com.ac1.entities.CursoEntity;
import com.ac1.repositories.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<String> createCurso(CursoDTO curso) {
        CursoEntity entity = modelMapper.map(curso, CursoEntity.class);
        cursoRepository.save(entity);
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<List<CursoDTO>> getCursos() {
        List<CursoEntity> entities = this.cursoRepository.findAll();
        List<CursoDTO> results = new ArrayList<>();
        for (CursoEntity entity : entities) {
            results.add(this.modelMapper.map(entity, CursoDTO.class));
        }
        return ResponseEntity.ok().body(results);
    }

    public ResponseEntity<CursoDTO> editCurso(Long id, CursoEntity req) {
        CursoEntity curso = cursoRepository.findById(id).get();
        curso.setDesc(req.getDesc() != null ? req.getDesc() : curso.getDesc());
        curso.setEmenta(req.getEmenta() != null ? req.getEmenta() : curso.getEmenta());
        curso.setHours(req.getHours() != null ? req.getHours() : curso.getHours());
        curso.setObjectives(req.getObjectives() != null ? req.getObjectives() : curso.getObjectives());
        cursoRepository.save(curso);
        CursoDTO dto = modelMapper.map(curso, CursoDTO.class);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<String> deleteCurso(Long id) {
        cursoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
