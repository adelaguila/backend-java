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

import dev.datasys.backendjava.dto.UserDTO;
import dev.datasys.backendjava.models.UserEntity;
import dev.datasys.backendjava.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService service;

    @Qualifier("defaultMapper")
    private final ModelMapper defaultMapper;

    @GetMapping("/{iduser}")
    public ResponseEntity<UserDTO> findById(@PathVariable("iduser") Integer iduser) {
        UserEntity obj = service.readById(iduser);
        UserDTO dto = this.convertToDto(obj);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findByFiltro() {

        List<UserEntity> objs = service.getAll();
        List<UserDTO> lst = objs.stream().map(this::convertToDto).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO dto) {

        UserEntity entidad = service.create(this.convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entidad.getIdUser()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{iduser}")
    public ResponseEntity<UserDTO> update(@Valid @PathVariable("iduser") Integer iduser,
            @RequestBody UserDTO dto) {

        UserEntity obj = service.update(this.convertToEntity(dto), iduser);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    private UserDTO convertToDto(UserEntity obj) {
        return defaultMapper.map(obj, UserDTO.class);
    }

    private UserEntity convertToEntity(UserDTO dto) {
        return defaultMapper.map(dto, UserEntity.class);
    }

}
