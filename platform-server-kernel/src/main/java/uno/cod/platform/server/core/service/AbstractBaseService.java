package uno.cod.platform.server.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractBaseService<R extends JpaRepository, E> {
    protected R repository;

    @Autowired
    public AbstractBaseService(R repository) {
        this.repository = repository;
    }


    public E save(E entity) {
        return (E) repository.save(entity);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public E findById(Long id) {
        return (E) repository.findOne(id);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
