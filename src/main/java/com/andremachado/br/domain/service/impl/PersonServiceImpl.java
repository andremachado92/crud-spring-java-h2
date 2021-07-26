package com.andremachado.br.domain.service.impl;

import com.andremachado.br.api.exception.ObjectNotFoundException;
import com.andremachado.br.domain.model.Person;
import com.andremachado.br.domain.model.dto.PersonDTO;
import com.andremachado.br.domain.repository.PersonRepository;
import com.andremachado.br.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class PersonServiceImpl implements PersonService {

    //injeção de dependecia de repositorio
    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(rollbackFor = Exception.class) //abre sessão unica no banco para todoo o metodo, e da roolback caso algo dê errado
    public void create(PersonDTO personDTO) {
       personRepository.save(
               Person.builder().
                       email(personDTO.getEmail()).
                       name(personDTO.getName()).
                       build()
       );
    }

    @Override
    public Page<PersonDTO> listPage(Long personId,
                                    //Parametros da paginação
                                    Integer page, Integer linesPerPage,
                                    String orderBy, String direction) {

        //Instanciação do objeto do tipo page
        var pageable = PageRequest.of(
                page,
                linesPerPage,
                Sort.Direction.valueOf(direction),
                orderBy);

        return personRepository.findByIdOrFindAllPeople(personId,pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long personId, PersonDTO personDTO) {
        var currentPerson = findById(personId);
        currentPerson.setEmail(personDTO.getEmail());
        currentPerson.setName(personDTO.getName());

        /*EM metodos anotados com @Transactional() quando o JPA encontra um objeto por id o mesmo fica em estado
         gerenciado não necessitando evocar o metodo save do repositorio */
    }

    @Override
    public Person findById(Long personId) {
        return personRepository.findById(personId).orElseThrow(
                ()-> new ObjectNotFoundException("Pessoa não encotrado para o id: "+personId));
        //Busca o objeto caso não encontre lança exceção
    }

    @Override
    public void delete(Long personId) {
        var person = findById(personId);
        personRepository.delete(person);
    }
}
