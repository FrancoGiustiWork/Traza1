package org.example.entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Empresa {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Integer CUIT;
    private String logo;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Sucursal> sucursales = new HashSet<>();
}
