package dev.datasys.backendjava.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TerceroAreaDTO {
    
    @EqualsAndHashCode.Include
    private Long idTerceroArea;

    @JsonBackReference
    private TerceroDTO tercero;

    @NotNull
    private String area;

}
