package com.ac1.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ac1.dto.AgendaDTO;
import com.ac1.dto.ProfessorAgendaDTO;
import com.ac1.entities.AgendaEntity;
import com.ac1.entities.CursoEntity;
import com.ac1.entities.ProfessorEntity;
import com.ac1.repositories.AgendaRepository;
import com.ac1.repositories.CursoRepository;
import com.ac1.repositories.ProfessorRepository;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<String> createAgenda(AgendaDTO agenda, Long cursoId, Long professorId) throws ParseException {
        CursoEntity curso = cursoRepository.findById(cursoId).get();
        ProfessorEntity professor = professorRepository.findById(professorId).get();
        AgendaEntity entity = modelMapper.map(agenda, AgendaEntity.class);
        entity.setCursos(curso);
        entity.setProfessores(professor);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss Z");
        entity.setStart_date(format.parse(agenda.getStart_date() + " UTC"));
        entity.setEnd_date(format.parse(agenda.getEnd_date() + " UTC"));
        if (!checkDateAgenda(professorId, entity.getStart_date(), entity.getEnd_date())) {
            return ResponseEntity.status(400).body("Data coincide com agenda do professor");
        }
        if (!professorIsInCurso(curso, professor)) {
            return ResponseEntity.status(400).body("Professor deve estar no curso");
        }
        agendaRepository.save(entity);
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<List<AgendaDTO>> getAgendas() {
        List<AgendaEntity> agendas = this.agendaRepository.findAll();
        List<AgendaDTO> agendaDTOs = new ArrayList<>();
        for (AgendaEntity agenda : agendas) {
            AgendaDTO dto = modelMapper.map(agenda, AgendaDTO.class);
            agendaDTOs.add(dto);
        }
        return ResponseEntity.status(200).body(agendaDTOs);
    }

    public ResponseEntity<String> deleteAgenda(Long id) {
        AgendaEntity agenda = this.agendaRepository.findById(id).get();
        this.agendaRepository.delete(agenda);
        return ResponseEntity.status(200).build();
    }

    public ResponseEntity<List<ProfessorAgendaDTO>> getAgendasByProfessor(Long id) {
        List<AgendaEntity> agendas = this.agendaRepository.findByProfessoresId(id);
        List<ProfessorAgendaDTO> dtos = new ArrayList<>();
        for (AgendaEntity agenda : agendas) {
            ProfessorAgendaDTO dto = modelMapper.map(agenda, ProfessorAgendaDTO.class);
            dto.setCurso_name(agenda.getCursos().getDesc());
            dto.setProfessor_name(agenda.getProfessores().getName());
            dtos.add(dto);
        }
        return ResponseEntity.status(200).body(dtos);
    }

    private Boolean checkDateAgenda(Long professorId, Date start_date, Date end_date) {
        List<AgendaEntity> agendas = this.agendaRepository.findByProfessoresId(professorId);
        if (agendas.isEmpty()) {
            return true;
        }
        for (AgendaEntity agenda : agendas) {
            if (agenda.getStart_date().before(end_date) && agenda.getEnd_date().after(start_date)) {
                return false;
            }
        }
        return true;
    }

    private Boolean professorIsInCurso(CursoEntity curso, ProfessorEntity professor) {
        Set<CursoEntity> cursos = this.cursoRepository.findByProfessores(professor);
        return cursos.contains(curso);
    }

}
