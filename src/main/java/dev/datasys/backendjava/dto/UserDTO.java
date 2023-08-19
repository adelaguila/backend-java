package dev.datasys.backendjava.dto;

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
public class UserDTO {
    
    @EqualsAndHashCode.Include
    private Integer idUser;

    @NotEmpty
    @NotNull
    private String userName;

    @NotEmpty
    @NotNull
    private String password;

    @NotEmpty
    @NotNull
    private Boolean enabled;

}
