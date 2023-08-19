package dev.datasys.backendjava.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.datasys.backendjava.dto.CatalogoDTO;
import dev.datasys.backendjava.models.CatalogoEntity;
import dev.datasys.backendjava.services.ICatalogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/catalogos")
public class CatalogoController {

    private final ICatalogoService service;

    @Qualifier("defaultMapper")
    private final ModelMapper catalogoMapper;

    @GetMapping("/{idcatalogo}")
    public ResponseEntity<CatalogoDTO> findById(@PathVariable("idcatalogo") Integer idcatalogo) {
        CatalogoEntity obj = service.readById(idcatalogo);
        CatalogoDTO dto = this.convertToDto(obj);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CatalogoDTO>> findByFiltro() {

        List<CatalogoEntity> objs = service.getAll();
        List<CatalogoDTO> lst = objs.stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<CatalogoDTO> save(@Valid @RequestBody CatalogoDTO dto) {

        CatalogoEntity entidad = service.create(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entidad.getIdCatalogo()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idcatalogo}")
    public ResponseEntity<CatalogoDTO> update(@Valid @PathVariable("idcatalogo") Integer idcatalogo,
            @RequestBody CatalogoDTO dto) {

        CatalogoEntity obj = service.update(this.convertToEntity(dto), idcatalogo);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    private CatalogoDTO convertToDto(CatalogoEntity obj) {
        return catalogoMapper.map(obj, CatalogoDTO.class);
    }

    private CatalogoEntity convertToEntity(CatalogoDTO dto) {
        return catalogoMapper.map(dto, CatalogoEntity.class);
    }

}
