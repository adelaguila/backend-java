package dev.datasys.backendjava.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SortModel {

    private String colName;
    private String sort;
    
}
