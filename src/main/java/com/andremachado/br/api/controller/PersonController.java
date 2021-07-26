package com.andremachado.br.api.controller;

import com.andremachado.br.domain.model.dto.PersonDTO;
import com.andremachado.br.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.NO_CONTENT) /*indica que o servidor atendeu à solicitação com êxito
                                                   e que não há conteúdo a ser enviado no corpo da resposta . */
    public void create(@Valid @RequestBody PersonDTO personDTO){
        personService.create(personDTO);
    }



    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Page<PersonDTO> findByIdAndCompanyID(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                      @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                      @RequestParam(value = "personId", required = false)  Long personId
    ) {
        return personService.listPage(personId,page,linesPerPage,orderBy,direction);
    }

    @PutMapping("/update/personId/{personId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long personId, @RequestBody PersonDTO personDTO){
        personService.update(personId,personDTO);
    }

    @DeleteMapping("/delete/personId/{personId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long personId){
        personService.delete(personId);
    }
}
