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

import dev.datasys.backendjava.dto.SectorDTO;
import dev.datasys.backendjava.models.SectorEntity;
import dev.datasys.backendjava.services.ISectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sector")
public class SectorController {

    private final ISectorService service;

    @Qualifier("defaultMapper")
    private final ModelMapper defaultMapper;

    @GetMapping("/{idsector}")
    public ResponseEntity<SectorDTO> findById(@PathVariable("idsector") Integer idsector) {
        SectorEntity obj = service.readById(idsector);
        SectorDTO dto = this.convertToDto(obj);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SectorDTO>> findByFiltro() {

        List<SectorEntity> objs = service.getAll();
        List<SectorDTO> lst = objs.stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<SectorDTO> save(@Valid @RequestBody SectorDTO dto) {

        SectorEntity entidad = service.create(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entidad.getIdSector()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idsector}")
    public ResponseEntity<SectorDTO> update(@Valid @PathVariable("idsector") Integer idsector,
            @RequestBody SectorDTO dto) {

        SectorEntity obj = service.update(this.convertToEntity(dto), idsector);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    private SectorDTO convertToDto(SectorEntity obj) {
        return defaultMapper.map(obj, SectorDTO.class);
    }

    private SectorEntity convertToEntity(SectorDTO dto) {
        return defaultMapper.map(dto, SectorEntity.class);
    }

}
