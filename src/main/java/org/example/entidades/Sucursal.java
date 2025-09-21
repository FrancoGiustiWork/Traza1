package org.example.entidades;

import lombok.*;

import java.time.LocalTime;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @ToString(exclude = "empresa")
    public class Sucursal {
        private Long id;
        private String nombre;
        private LocalTime horarioApertura;
        private LocalTime horarioCierre;
        private boolean esCasaMatriz;

        private Domicilio domicilio;

        @EqualsAndHashCode.Exclude
        private Empresa empresa;
}