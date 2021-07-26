package com.andremachado.br.domain.model.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PersonDTO {

    @NotBlank
    @Size(min = 3, max = 50, message = "O nome deve ter o tamanho entre 3 e 50 caracteres")
    private String name;

    @Email //Validação de email com bean validation
    private String email;
}
