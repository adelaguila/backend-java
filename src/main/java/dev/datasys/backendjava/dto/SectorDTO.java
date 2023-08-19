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
public class SectorDTO {
    
    @EqualsAndHashCode.Include
    private Integer idSector;

    @NotEmpty
    @NotNull
    private String descripcion;

    @NotEmpty
    @NotNull
    private String nombreSector;

    @NotEmpty
    @NotNull
    private String estado;

}
