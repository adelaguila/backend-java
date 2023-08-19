package dev.datasys.backendjava.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TerceroDTO {
    
    @EqualsAndHashCode.Include
    private Long idTercero;

    @NotNull
    private CatalogoDTO tipoDocumentoIdentidad;

    @NotNull
    private String numeroDocumentoIdentidad;

    @NotNull
    private String nombreTercero;

    private String correo;
    
    private String telefono;

    @JsonManagedReference
    @NotNull
    private List<TerceroDireccionDTO> direcciones;

    @JsonManagedReference
    private List<TerceroAreaDTO> areas;
}
