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
public class CajaNapDTO {
    
    @EqualsAndHashCode.Include
    private Integer idCajaNap;

    @NotEmpty
    @NotNull
    private String descripcion;

    @NotEmpty
    @NotNull
    private String puertos;

    @NotEmpty
    @NotNull
    private String estado;

}
