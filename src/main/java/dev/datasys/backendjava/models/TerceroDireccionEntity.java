package dev.datasys.backendjava.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "terceros_direcciones")
public class TerceroDireccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idtercerodireccion")
    private Long idTerceroDireccion;

    @ManyToOne
    @JoinColumn(name = "idtercero", nullable = false)
    private TerceroEntity tercero;

    @Column(name = "direccion", nullable = false, length = 250)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "cod_ubigeo", nullable = false)
    private UbigeoEntity ubigeo;

}
