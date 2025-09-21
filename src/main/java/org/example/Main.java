package org.example;

import org.example.entidades.*;
import org.example.repositorio.InMemoryRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Repositorios
        InMemoryRepository<Pais> paisRepository = new InMemoryRepository<>();
        InMemoryRepository<Provincia> provinciaRepository = new InMemoryRepository<>();
        InMemoryRepository<Localidad> localidadRepository = new InMemoryRepository<>();
        InMemoryRepository<Domicilio> domicilioRepository = new InMemoryRepository<>();
        InMemoryRepository<Sucursal> sucursalRepository = new InMemoryRepository<>();
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();

        // Crear Pais
        Pais argentina = new Pais("Argentina");
        paisRepository.save(argentina);

        // Crear Provincias
        Provincia buenosAires = new Provincia("Buenos Aires", argentina);
        provinciaRepository.save(buenosAires);

        Provincia cordoba = new Provincia("Córdoba", argentina);
        provinciaRepository.save(cordoba);

        // Crear Localidades de Buenos Aires
        Localidad caba = new Localidad("CABA", buenosAires);
        localidadRepository.save(caba);

        Localidad laPlata = new Localidad("La Plata", buenosAires);
        localidadRepository.save(laPlata);

        // Crear Domicilios de Buenos Aires
        Domicilio domicilioCaba = Domicilio.builder().calle("Calle Falsa").numero(123).localidad(caba).build();
        domicilioRepository.save(domicilioCaba);

        Domicilio domicilioLaPlata = Domicilio.builder().calle("Avenida Siempreviva").numero(742).localidad(laPlata).build();
        domicilioRepository.save(domicilioLaPlata);

        // Crear Localidades de Córdoba
        Localidad cordobaCapital = new Localidad("Córdoba Capital", cordoba);
        localidadRepository.save(cordobaCapital);

        Localidad villaCarlosPaz = new Localidad("Villa Carlos Paz", cordoba);
        localidadRepository.save(villaCarlosPaz);

        // Crear Domicilios de Córdoba
        Domicilio domicilioCordoba = Domicilio.builder().calle("Otra Calle").numero(456).localidad(cordobaCapital).build();
        domicilioRepository.save(domicilioCordoba);

        Domicilio domicilioCarlosPaz = Domicilio.builder().calle("Bv. Sarmiento").numero(1000).localidad(villaCarlosPaz).build();
        domicilioRepository.save(domicilioCarlosPaz);

        // Crear Sucursales
        Sucursal sucursal1 = new Sucursal();
        sucursal1.setNombre("Sucursal CABA");
        sucursal1.setDomicilio(domicilioCaba);
        sucursalRepository.save(sucursal1);

        Sucursal sucursal2 = new Sucursal();
        sucursal2.setNombre("Sucursal La Plata");
        sucursal2.setDomicilio(domicilioLaPlata);
        sucursalRepository.save(sucursal2);

        Sucursal sucursal3 = new Sucursal();
        sucursal3.setNombre("Sucursal Córdoba Capital");
        sucursal3.setDomicilio(domicilioCordoba);
        sucursalRepository.save(sucursal3);

        Sucursal sucursal4 = new Sucursal();
        sucursal4.setNombre("Sucursal Villa Carlos Paz");
        sucursal4.setDomicilio(domicilioCarlosPaz);
        sucursalRepository.save(sucursal4);

        // Crear Empresas
        Empresa empresa1 = new Empresa();
        empresa1.setNombre("Empresa A");
        empresa1.setRazonSocial("Razon Social A");
        empresa1.setCUIT(12345678);
        Set<Sucursal> sucursalesEmpresa1 = new HashSet<>();
        sucursalesEmpresa1.add(sucursal1);
        sucursalesEmpresa1.add(sucursal2);
        empresa1.setSucursales(sucursalesEmpresa1);
        empresaRepository.save(empresa1);
        sucursal1.setEmpresa(empresa1);
        sucursal2.setEmpresa(empresa1);


        Empresa empresa2 = new Empresa();
        empresa2.setNombre("Empresa B");
        empresa2.setRazonSocial("Razon Social B");
        empresa2.setCUIT(87654321);
        Set<Sucursal> sucursalesEmpresa2 = new HashSet<>();
        sucursalesEmpresa2.add(sucursal3);
        sucursalesEmpresa2.add(sucursal4);
        empresa2.setSucursales(sucursalesEmpresa2);
        empresaRepository.save(empresa2);
        sucursal3.setEmpresa(empresa2);
        sucursal4.setEmpresa(empresa2);


        // Mostrar todas las empresas
        System.out.println("----- Todas las empresas -----");
        empresaRepository.findAll().forEach(empresa -> System.out.println(empresa.getNombre()));

        // Buscar una empresa por su Id
        System.out.println("\n----- Buscar empresa por ID (1) -----");
        Optional<Empresa> empresaOpt = empresaRepository.findById(1L);
        empresaOpt.ifPresent(empresa -> System.out.println("Empresa encontrada: " + empresa.getNombre()));

        // Buscar una empresa por nombre
        System.out.println("\n----- Buscar empresa por nombre (Empresa B) -----");
        List<Empresa> empresasPorNombre = empresaRepository.genericFindByField("Nombre", "Empresa B");
        empresasPorNombre.forEach(empresa -> System.out.println("Empresa encontrada: " + empresa.getNombre()));

        // Actualizar los datos de una empresa buscando por su ID
        System.out.println("\n----- Actualizar CUIT de empresa con ID 1 -----");
        Optional<Empresa> empresaParaActualizarOpt = empresaRepository.findById(1L);
        empresaParaActualizarOpt.ifPresent(empresa -> {
            System.out.println("CUIT original: " + empresa.getCUIT());
            empresa.setCUIT(11223344);
            empresaRepository.genericUpdate(1L, empresa);
            System.out.println("CUIT actualizado: " + empresa.getCUIT());
        });

        // Eliminar una empresa buscando por su ID
        System.out.println("\n----- Eliminar empresa con ID 2 -----");
        Optional<Empresa> empresaAEliminar = empresaRepository.genericDelete(2L);
        if (empresaAEliminar.isPresent()){
            System.out.println("Empresa eliminada: " + empresaAEliminar.get().getNombre());
        }


        System.out.println("\n----- Todas las empresas (después de eliminar) -----");
        empresaRepository.findAll().forEach(empresa -> System.out.println(empresa.getNombre()));
    }
}
