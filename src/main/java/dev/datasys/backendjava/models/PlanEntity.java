package dev.datasys.backendjava.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "planes")
public class PlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idplan")
    private Integer idPlan;

    @Column(name = "nombreplan", nullable = false, length = 100)
    private String nombrePlan;

    @Column(name = "precio_dia")
    private Float precioDia;

    @Column(name = "precio_periodo")
    private Float precioPeriodo;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;

}
