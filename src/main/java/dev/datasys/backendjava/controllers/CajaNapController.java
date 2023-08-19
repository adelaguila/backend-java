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

import dev.datasys.backendjava.dto.CajaNapDTO;
import dev.datasys.backendjava.models.CajaNapEntity;
import dev.datasys.backendjava.services.ICajaNapService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cajanap")
public class CajaNapController {

    private final ICajaNapService service;

    @Qualifier("cajanapMapper")
    private final ModelMapper cajanapMapper;

    @GetMapping("/{idcajanap}")
    public ResponseEntity<CajaNapDTO> findById(@PathVariable("idcajanap") Integer idcajanap) {
        CajaNapEntity obj = service.readById(idcajanap);
        CajaNapDTO dto = this.convertToDto(obj);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CajaNapDTO>> findByFiltro() {

        List<CajaNapEntity> objs = service.getAll();
        List<CajaNapDTO> lst = objs.stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<CajaNapDTO> save(@Valid @RequestBody CajaNapDTO dto) {

        CajaNapEntity entidad = service.create(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entidad.getIdCajaNap()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idcajanap}")
    public ResponseEntity<CajaNapDTO> update(@Valid @PathVariable("idcajanap") Integer idcajanap,
            @RequestBody CajaNapDTO dto) {

        CajaNapEntity obj = service.update(this.convertToEntity(dto), idcajanap);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    private CajaNapDTO convertToDto(CajaNapEntity obj) {
        return cajanapMapper.map(obj, CajaNapDTO.class);
    }

    private CajaNapEntity convertToEntity(CajaNapDTO dto) {
        return cajanapMapper.map(dto, CajaNapEntity.class);
    }

}
