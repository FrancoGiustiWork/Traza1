package org.example.entidades;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Domicilio {
    private Long id;
    private String calle;
    private Integer numero;
    private Integer piso;
    private Integer nroDpto;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Localidad localidad;

}