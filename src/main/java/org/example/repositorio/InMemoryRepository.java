package org.example.repositorio;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryRepository<T> {
    protected Map<Long, T> data = new HashMap<>();
    protected AtomicLong idGenerator = new AtomicLong();

    public T save(T entity){
        long id = idGenerator.incrementAndGet();
        String clase;
        try {
            entity.getClass().getMethod("setId", Long.class).invoke(entity, id);
            clase = entity.getClass().getName();
            System.out.println(clase + " id:"+id );
            System.out.println("guardada");
        }catch(Exception e){
            e.printStackTrace();
        }
        data.put(id, entity);
        return entity;
    }
    public Optional<T> findById(Long id){
        return Optional.ofNullable(data.get(id));
    }

    public List<T> findAll(){
        return new ArrayList<>(data.values());
    }

    public Optional<T> genericUpdate ( Long id, T updatedEntity){
        if(!data.containsKey(id)){
            System.out.println("Objeto no encontrado");
            return Optional.empty();
        }
        try{
           updatedEntity.getClass().getMethod("setId", Long.class).invoke(updatedEntity,id);
            data.put(id, updatedEntity);

            return Optional.of(updatedEntity);
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public Optional<T> genericDelete(Long id){
        if(!data.containsKey(id)){
            System.out.println("Objeto no encontrado");
            return Optional.empty();
        }
        return  Optional.ofNullable((data.remove(id)));
    }
    public List<T> genericFindByField(String fieldName, Object value){
        List<T> results = new ArrayList<>();
        try{
            for (T entity : data.values()){
                Method getFieldMethod = entity.getClass().getMethod("get"+fieldName);
                Object fieldValue = getFieldMethod.invoke(entity);
                if ( fieldValue != null && fieldValue.equals(value)){
                    results.add(entity);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  results;
    }

}