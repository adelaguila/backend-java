package dev.datasys.backendjava.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.datasys.backendjava.dto.CajaNapDTO;
import dev.datasys.backendjava.dto.CatalogoDTO;
import dev.datasys.backendjava.dto.TerceroDTO;
import dev.datasys.backendjava.models.CajaNapEntity;
import dev.datasys.backendjava.models.CatalogoEntity;
import dev.datasys.backendjava.models.TerceroEntity;

@Configuration
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean("catalogoMapper")
    public ModelMapper catalogoMapper() {
        ModelMapper mapper = new ModelMapper();

        TypeMap<CatalogoDTO, CatalogoEntity> typeMap1 = mapper.createTypeMap(CatalogoDTO.class, CatalogoEntity.class);

        // escritura
        typeMap1.addMapping(CatalogoDTO::getIdCatalogo, (dest, v) -> dest.setIdCatalogo((Integer) v));
        typeMap1.addMapping(CatalogoDTO::getDescripcion, (dest, v) -> dest.setDescripcion((String) v));

        // lectura
        TypeMap<CatalogoEntity, CatalogoDTO> typeMap2 = mapper.createTypeMap(CatalogoEntity.class, CatalogoDTO.class);

        return mapper;
    }

    @Bean("cajanapMapper")
    public ModelMapper cajanapMapper() {
        ModelMapper mapper = new ModelMapper();

        TypeMap<CajaNapDTO, CajaNapEntity> typeMap1 = mapper.createTypeMap(CajaNapDTO.class, CajaNapEntity.class);

        // escritura
        typeMap1.addMapping(CajaNapDTO::getIdCajaNap, (dest, v) -> dest.setIdCajaNap((Integer) v));
        typeMap1.addMapping(CajaNapDTO::getDescripcion, (dest, v) -> dest.setDescripcion((String) v));

        // lectura
        TypeMap<CajaNapEntity, CajaNapDTO> typeMap2 = mapper.createTypeMap(CajaNapEntity.class, CajaNapDTO.class);

        return mapper;
    }

    @Bean("terceroMapper")
    public ModelMapper terceroMapper() {
        ModelMapper mapper = new ModelMapper();

        TypeMap<TerceroDTO, TerceroEntity> typeMap1 = mapper.createTypeMap(TerceroDTO.class, TerceroEntity.class);

        // escritura
        typeMap1.addMapping(TerceroDTO::getIdTercero, (dest, v) -> dest.setIdTercero((Long) v));
        typeMap1.addMapping(TerceroDTO::getTipoDocumentoIdentidad,
                (dest, v) -> dest.setTipoDocumentoIdentidad((CatalogoEntity) v));
        typeMap1.addMapping(TerceroDTO::getNumeroDocumentoIdentidad,
                (dest, v) -> dest.setNumeroDocumentoIdentidad((String) v));
        typeMap1.addMapping(TerceroDTO::getNombreTercero, (dest, v) -> dest.setNumeroDocumentoIdentidad((String) v));
        typeMap1.addMapping(TerceroDTO::getCorreo, (dest, v) -> dest.setCorreo((String) v));
        typeMap1.addMapping(TerceroDTO::getTelefono, (dest, v) -> dest.setTelefono((String) v));

        // lectura
        TypeMap<TerceroEntity, TerceroDTO> typeMap2 = mapper.createTypeMap(TerceroEntity.class, TerceroDTO.class);
        typeMap2.addMapping(TerceroEntity::getIdTercero, (dest, v) -> dest.setIdTercero((Long) v));
        typeMap2.addMapping(TerceroEntity::getTipoDocumentoIdentidad, (dest, v) -> dest.setTipoDocumentoIdentidad((CatalogoDTO) v));
        typeMap2.addMapping(TerceroEntity::getNumeroDocumentoIdentidad, (dest, v) -> dest.setNumeroDocumentoIdentidad((String) v));
        typeMap2.addMapping(TerceroEntity::getNombreTercero, (dest, v) -> dest.setNombreTercero((String) v));
        typeMap2.addMapping(TerceroEntity::getCorreo, (dest, v) -> dest.setCorreo((String) v));
        typeMap2.addMapping(TerceroEntity::getTelefono, (dest, v) -> dest.setTelefono((String) v));
        
        return mapper;
    }
}
