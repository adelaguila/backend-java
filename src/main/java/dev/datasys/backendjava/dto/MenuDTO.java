package dev.datasys.backendjava.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MenuDTO {
    
    @EqualsAndHashCode.Include
    private Integer idMenu;

    @NotEmpty
    @NotNull
    private String icon;
    
    @NotEmpty
    @NotNull
    private String name;
    
    @NotEmpty
    @NotNull
    private String url;
    private List<RolDTO> roles;

}
