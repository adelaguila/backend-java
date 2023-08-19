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

import dev.datasys.backendjava.dto.MenuDTO;
import dev.datasys.backendjava.models.MenuEntity;
import dev.datasys.backendjava.services.IMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final IMenuService service;

    @Qualifier("defaultMapper")
    private final ModelMapper defaultMapper;

    @GetMapping("/{idmenu}")
    public ResponseEntity<MenuDTO> findById(@PathVariable("idmenu") Integer idmenu) {
        MenuEntity obj = service.readById(idmenu);
        MenuDTO dto = this.convertToDto(obj);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MenuDTO>> findByFiltro() {

        List<MenuEntity> objs = service.getAll();
        List<MenuDTO> lst = objs.stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<MenuDTO> save(@Valid @RequestBody MenuDTO dto) {

        MenuEntity entidad = service.create(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entidad.getIdMenu()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idmenu}")
    public ResponseEntity<MenuDTO> update(@Valid @PathVariable("idmenu") Integer idmenu,
            @RequestBody MenuDTO dto) {

        MenuEntity obj = service.update(this.convertToEntity(dto), idmenu);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    private MenuDTO convertToDto(MenuEntity obj) {
        return defaultMapper.map(obj, MenuDTO.class);
    }

    private MenuEntity convertToEntity(MenuDTO dto) {
        return defaultMapper.map(dto, MenuEntity.class);
    }

}
