package dev.datasys.backendjava.models;

import java.time.LocalDate;
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
@Table(name = "abonados")
public class AbonadoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idabonado")
    private Long idAbonado;

    @ManyToOne
    @JoinColumn(name = "idtercero", nullable = false)
    private TerceroEntity tercero;

    @ManyToOne
    @JoinColumn(name = "idsector", nullable = false)
    private SectorEntity sector;

    @ManyToOne
    @JoinColumn(name = "idvia", nullable = false)
    private ViaEntity via;

    @ManyToOne
    @JoinColumn(name = "idcajanap", nullable = false)
    private CajaNapEntity cajaNap;

    @ManyToOne
    @JoinColumn(name = "idplan", nullable = false)
    private PlanEntity plan;

    @Column(name = "numero", nullable = false, length = 50)
    private String numero;
    
    @Column(name = "referencia", nullable = false, length = 250)
    private String referencia;

    @Column(name = "ont", nullable = true, length = 50)
    private String ont;

    @Column(name = "longitud", nullable = true)
    private Float longitud;

    @Column(name = "latitud", nullable = true)
    private Float latitud;
    
    @Column(name = "imagen", nullable = true)
    private String imagen;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    private LocalDate fechaUltimaLiquidacion;
}
