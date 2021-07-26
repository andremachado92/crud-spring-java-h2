package com.andremachado.br.domain.repository;
import com.andremachado.br.domain.model.Person;
import com.andremachado.br.domain.model.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    //JPQL query

    @Query(value = "SELECT NEW com.andremachado.br.domain.model.dto.PersonDTO(p.name,p.email) FROM Person AS p " +
            "WHERE (:personId IS NULL OR p.id = :personId)")
    Page<PersonDTO> findByIdOrFindAllPeople(@Param("personId") Long personId,
                                            Pageable pageable); //consulta paginada

    //Caso o id seja nulo traz todos os objetos se nao traz so o dono do id
}
