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
public class CatalogoDTO {
    
    @EqualsAndHashCode.Include
    private Integer idCatalogo;

    @NotEmpty
    @NotNull
    private String catalogo;

    @NotEmpty
    @NotNull
    private String codigo;

    @NotEmpty
    @NotNull
    private String descripcion;

}
