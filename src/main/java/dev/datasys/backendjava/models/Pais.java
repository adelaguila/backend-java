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
@Table(name = "paises")
public class Pais {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "cod_pais")
    private String codPais;

    @Column(name = "nombrepais", nullable = false, length = 100)
    private String nombrepais;
}
