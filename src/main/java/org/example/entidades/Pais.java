package org.example.entidades;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor

@Data
public class Pais {
    private Long id;
    private String nombre;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Provincia> provincias = new HashSet<>();

    public Pais ( String nombre){
        this.nombre = nombre;
        this.provincias = new HashSet<>();

    }

    public void addProvincia(Provincia p){
        provincias.add(p);
        System.out.println("Se añadio "+p.getNombre()+" como provincia de "+this.getNombre());
    }
}
