package com.ac1.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cursos")
@Data
@EqualsAndHashCode
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "desc")
    private String desc;

    @Column(name = "hours")
    private String hours;

    @Column(name = "objectives")
    private String objectives;

    @Column(name = "ementa")
    private String ementa;

    @ManyToMany(mappedBy = "cursos")
    private Set<ProfessorEntity> professores;

}
