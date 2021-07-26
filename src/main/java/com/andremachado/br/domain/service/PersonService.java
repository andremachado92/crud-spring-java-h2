package com.andremachado.br.domain.service;

import com.andremachado.br.domain.model.Person;
import com.andremachado.br.domain.model.dto.PersonDTO;
import org.springframework.data.domain.Page;

public interface PersonService {
    void create(PersonDTO personDTO);

    Page<PersonDTO> listPage(Long personId, Integer page, Integer linesPerPage,
                             String orderBy, String direction);

    void update(Long personId, PersonDTO personDTO);

    Person findById(Long personId);

    void delete(Long personId);
}
