package tn.esprit.autoloc.autolocapi.service;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.exception.InvalidEntityException;
import tn.esprit.autoloc.exception.ObjectNotFoundException;

import java.util.List;
import java.util.stream.StreamSupport;

public abstract class GenericServiceImpl<T, ID> implements IGenericService<T, ID> {

    protected final CrudRepository<T, ID> repository;

    protected GenericServiceImpl(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected abstract String getEntityName();

    protected abstract ID getId(T entity);

    protected abstract void setFields(T source, T target);

    @Override
    public T create(T entity) {
        if (getId(entity) != null) {
            throw new IllegalArgumentException(
                    "L'identifiant de " + getEntityName() + " doit être vide lors de la création");
        }
        if (!check(entity, true)) {
            throw new InvalidEntityException(getEntityName() + " invalide");
        }
        return repository.save(entity);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(getEntityName(), id));
    }

    @Override
    public List<T> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    @Override
    public T update(ID id, T entity) {
        T existing = findById(id);
        if (!check(entity, false)) {
            throw new InvalidEntityException(getEntityName() + " invalide");
        }
        setFields(entity, existing);
        return repository.save(existing);
    }

    @Override
    public void deleteById(ID id) {
        if (!repository.existsById(id)) {
            throw new ObjectNotFoundException(getEntityName(), id);
        }
        repository.deleteById(id);
    }
}
