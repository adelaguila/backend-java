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

import dev.datasys.backendjava.dto.ViaDTO;
import dev.datasys.backendjava.models.ViaEntity;
import dev.datasys.backendjava.services.IViaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vias")
public class ViaController {

    private final IViaService service;

    @Qualifier("defaultMapper")
    private final ModelMapper defaultMapper;

    @GetMapping("/{idvia}")
    public ResponseEntity<ViaDTO> findById(@PathVariable("idvia") Integer idvia) {
        ViaEntity obj = service.readById(idvia);
        ViaDTO dto = this.convertToDto(obj);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ViaDTO>> findByFiltro() {

        List<ViaEntity> objs = service.getAll();
        List<ViaDTO> lst = objs.stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<ViaDTO> save(@Valid @RequestBody ViaDTO dto) {

        ViaEntity entidad = service.create(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entidad.getIdVia()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idvia}")
    public ResponseEntity<ViaDTO> update(@Valid @PathVariable("idvia") Integer idvia,
            @RequestBody ViaDTO dto) {

        ViaEntity obj = service.update(this.convertToEntity(dto), idvia);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    private ViaDTO convertToDto(ViaEntity obj) {
        return defaultMapper.map(obj, ViaDTO.class);
    }

    private ViaEntity convertToEntity(ViaDTO dto) {
        return defaultMapper.map(dto, ViaEntity.class);
    }

}
