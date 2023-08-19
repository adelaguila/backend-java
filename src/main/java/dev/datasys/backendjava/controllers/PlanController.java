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

import dev.datasys.backendjava.dto.PlanDTO;
import dev.datasys.backendjava.models.PlanEntity;
import dev.datasys.backendjava.services.IPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanController {

    private final IPlanService service;

    @Qualifier("defaultMapper")
    private final ModelMapper defaultMapper;

    @GetMapping("/{idplan}")
    public ResponseEntity<PlanDTO> findById(@PathVariable("idplan") Integer idplan) {
        PlanEntity obj = service.readById(idplan);
        PlanDTO dto = this.convertToDto(obj);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlanDTO>> findByFiltro() {

        List<PlanEntity> objs = service.getAll();
        List<PlanDTO> lst = objs.stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<PlanDTO> save(@Valid @RequestBody PlanDTO dto) {

        PlanEntity entidad = service.create(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entidad.getIdPlan()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idplan}")
    public ResponseEntity<PlanDTO> update(@Valid @PathVariable("idplan") Integer idplan,
            @RequestBody PlanDTO dto) {

        PlanEntity obj = service.update(this.convertToEntity(dto), idplan);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    private PlanDTO convertToDto(PlanEntity obj) {
        return defaultMapper.map(obj, PlanDTO.class);
    }

    private PlanEntity convertToEntity(PlanDTO dto) {
        return defaultMapper.map(dto, PlanEntity.class);
    }

}
