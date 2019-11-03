package ch.heigvd.amt.citylog.integration;

/**
 * Basic interface to force DAOs to implement CRUD operations
 *
 * @param <PK> Type for an entity's primary key
 * @param <E> Type for an entity
 */
public interface DAO<PK, E> {
    E create(E entity);
    E findById(PK id);
    void update(E entity);
    void deleteById(PK id);
}
