package org.example.entidades;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Localidad {
    private Long id;
    private String nombre;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Provincia provincia;

    public Localidad(String nombre, Provincia provincia) {
        this.nombre = nombre;
        this.provincia = provincia;
        provincia.addLocalidad(this);
    }
}
