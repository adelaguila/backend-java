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

import dev.datasys.backendjava.dto.TerceroDTO;
import dev.datasys.backendjava.models.TerceroEntity;
import dev.datasys.backendjava.services.ITerceroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/terceros")
public class TerceroController {

    private final ITerceroService service;

    @Qualifier("defaultMapper")
    private final ModelMapper defaultMapper;

    @GetMapping("/{idtercero}")
    public ResponseEntity<TerceroDTO> findById(@PathVariable("idtercero") Integer idtercero) {
        TerceroEntity obj = service.readById(idtercero);
        TerceroDTO dto = this.convertToDto(obj);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TerceroDTO>> findByFiltro() {

        List<TerceroEntity> objs = service.getAll();
        List<TerceroDTO> lst = objs.stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }
    
    @PostMapping("")
    private ResponseEntity<TerceroDTO> save(@Valid @RequestBody TerceroDTO dto) {

        TerceroEntity entidad = service.create(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entidad.getIdTercero()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idtercero}")
    public ResponseEntity<TerceroDTO> update(@Valid @PathVariable("idtercero") Integer idtercero,
            @RequestBody TerceroDTO dto) {

        TerceroEntity obj = service.update(this.convertToEntity(dto), idtercero);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    private TerceroDTO convertToDto(TerceroEntity obj) {
        return defaultMapper.map(obj, TerceroDTO.class);
    }

    private TerceroEntity convertToEntity(TerceroDTO dto) {
        return defaultMapper.map(dto, TerceroEntity.class);
    }

}
