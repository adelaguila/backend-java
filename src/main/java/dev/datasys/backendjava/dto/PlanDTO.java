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
public class PlanDTO {
    
    @EqualsAndHashCode.Include
    private Integer idPlan;

    @NotEmpty
    @NotNull
    private String nombrePlan;

    @NotEmpty
    @NotNull
    private String precioDia;

    @NotEmpty
    @NotNull
    private String precioPeriodo;

    @NotEmpty
    @NotNull
    private String estado;

}
