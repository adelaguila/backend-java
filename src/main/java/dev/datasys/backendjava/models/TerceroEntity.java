package dev.datasys.backendjava.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "terceros")
public class TerceroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idtercero")
    private Long idTercero;

    @ManyToOne
    @JoinColumn(name = "cod_tipo_documento_identidad", nullable = false)
    private CatalogoEntity tipoDocumentoIdentidad;

    @Column(name = "numero_documento_identidad", nullable = false, length = 15)
    private String numeroDocumentoIdentidad;

    @Column(name = "nombretercero", nullable = false, length = 150)
    private String nombreTercero;

    @Column(name = "correo_electronico", length = 100)
    private String correo;

    @Column(name = "telefono", length = 100)
    private String telefono;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "tercero", cascade = CascadeType.ALL)
    List<TerceroDireccionEntity> direcciones;

    @OneToMany(mappedBy = "tercero", cascade = CascadeType.ALL)
    List<TerceroAreaEntity> areas;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    public void prePersisten() {
        this.createdAt = new Date();
        this.estado = "A";
    }

    @PreUpdate
    public void preModify() {
        this.updatedAt = new Date();
    }
    
}
