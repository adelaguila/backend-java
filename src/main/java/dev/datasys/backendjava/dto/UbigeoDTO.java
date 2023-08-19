package dev.datasys.backendjava.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UbigeoDTO {
    
    @EqualsAndHashCode.Include
    private String codUbigeo;

    @NotEmpty
    @NotNull
    private String departamento;

    @NotEmpty
    @NotNull
    private String provincia;

    @NotEmpty
    @NotNull
    private String distrito;

}
