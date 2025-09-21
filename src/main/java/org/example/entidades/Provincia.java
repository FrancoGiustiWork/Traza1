package org.example.entidades;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Provincia {
    private Long id=0L;
    private String nombre;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Localidad> localidades = new HashSet<>();
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Pais pais;

    public Provincia( String nombre,  Pais pais) {
        this.nombre = nombre;
        this.pais = pais;
        pais.addProvincia(this);
    }
    public void addLocalidad(Localidad l){
        localidades.add(l);
        System.out.println("Se añadio "+l.getNombre()+" como localidad de "+this.getNombre());
    }
}
