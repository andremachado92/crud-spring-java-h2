package com.andremachado.br.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
//Anotações Lombok
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Person")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {


    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "PERSON_NAME")
    private String name;

    @Column(name = "PERSON_EMAIL")
    private String email;
}
