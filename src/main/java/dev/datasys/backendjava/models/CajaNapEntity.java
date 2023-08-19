package dev.datasys.backendjava.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "cajas_nap")
public class CajaNapEntity {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "idcajanap")
    private Integer idCajaNap;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "puertos", nullable = false)
    private Integer puertos;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado;
}
