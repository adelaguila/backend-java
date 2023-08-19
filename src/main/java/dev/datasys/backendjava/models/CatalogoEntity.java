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
@Table(name = "catalogos_sunat")
public class CatalogoEntity {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idcatalogo")
    private Integer idCatalogo;

    @Column(name = "catalogo", nullable = false, length = 2)
    private String catalogo;

    @Column(name = "codigo", nullable = false, length = 10)
    private String codigo;

    @Column(name = "descripcion", nullable = false, length = 250)
    private String descripcion;

    @Column(name = "cod_tributo", nullable = true, length = 10)
    private String codTributo;

    @Column(name = "cod_internacional", nullable = true, length = 10)
    private String codInternacional;

    @Column(name = "nombre", nullable = true, length = 100)
    private String nombre;

    @Column(name = "nivel", nullable = true, length = 10)
    private String nivel;

}
