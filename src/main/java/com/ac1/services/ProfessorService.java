package com.ac1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ac1.dto.ProfessorDTO;
import com.ac1.entities.CursoEntity;
import com.ac1.entities.ProfessorEntity;
import com.ac1.repositories.CursoRepository;
import com.ac1.repositories.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<String> createUser(ProfessorDTO user) {
        ProfessorEntity entity = modelMapper.map(user, ProfessorEntity.class);
        professorRepository.save(entity);
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<List<ProfessorEntity>> getProfessores() {
        List<ProfessorEntity> entities = this.professorRepository.findAll();
        return ResponseEntity.ok().body(entities);
    }

    public ResponseEntity<ProfessorDTO> editProfessor(Long id, ProfessorEntity req) {
        ProfessorEntity professor = professorRepository.findById(id).get();
        professor.setAddress(req.getAddress() != null ? req.getAddress() : professor.getAddress());
        professor.setName(req.getName() != null ? req.getName() : professor.getName());
        professor.setCpf(req.getCpf() != null ? req.getCpf() : professor.getCpf());
        professor.setPhone(req.getPhone() != null ? req.getPhone() : professor.getPhone());
        professor.setRg(req.getRg() != null ? req.getRg() : professor.getRg());
        professorRepository.save(professor);
        ProfessorDTO dto = modelMapper.map(professor, ProfessorDTO.class);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<String> deleteProfessor(Long id) {
        professorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<ProfessorDTO> addCurso(Long professor_id, Long curso_id) {
        ProfessorEntity professor = professorRepository.findById(professor_id).get();
        CursoEntity curso = cursoRepository.findById(curso_id).get();
        professor.getCursos().add(curso);
        professorRepository.save(professor);
        ProfessorDTO dto = modelMapper.map(professor, ProfessorDTO.class);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<Set<CursoEntity>> getCursos(Long id) {
        ProfessorEntity professor = professorRepository.findById(id).get();
        return ResponseEntity.ok().body(cursoRepository.findByProfessores(professor));
    }

}
