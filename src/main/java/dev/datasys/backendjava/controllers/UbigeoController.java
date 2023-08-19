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

import dev.datasys.backendjava.dto.UbigeoDTO;
import dev.datasys.backendjava.models.UbigeoEntity;
import dev.datasys.backendjava.services.IUbigeoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ubigeo")
public class UbigeoController {

    private final IUbigeoService service;

    @Qualifier("defaultMapper")
    private final ModelMapper defaultMapper;

    @GetMapping("/{codubigeo}")
    public ResponseEntity<UbigeoDTO> findById(@PathVariable("codubigeo") String codubigeo) {
        UbigeoEntity obj = service.readById(codubigeo);
        UbigeoDTO dto = this.convertToDto(obj);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UbigeoDTO>> findByFiltro() {

        List<UbigeoEntity> objs = service.getAll();
        List<UbigeoDTO> lst = objs.stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<UbigeoDTO> save(@Valid @RequestBody UbigeoDTO dto) {

        UbigeoEntity entidad = service.create(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entidad.getCodUbigeo()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{codubigeo}")
    public ResponseEntity<UbigeoDTO> update(@Valid @PathVariable("codubigeo") String codubigeo,
            @RequestBody UbigeoDTO dto) {

        UbigeoEntity obj = service.update(this.convertToEntity(dto), codubigeo);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    private UbigeoDTO convertToDto(UbigeoEntity obj) {
        return defaultMapper.map(obj, UbigeoDTO.class);
    }

    private UbigeoEntity convertToEntity(UbigeoDTO dto) {
        return defaultMapper.map(dto, UbigeoEntity.class);
    }

}
