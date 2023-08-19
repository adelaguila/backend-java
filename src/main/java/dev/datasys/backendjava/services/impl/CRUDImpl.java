package dev.datasys.backendjava.services.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import dev.datasys.backendjava.commons.Filter;
import dev.datasys.backendjava.commons.IBaseInterfaceService;
import dev.datasys.backendjava.commons.SortModel;
import dev.datasys.backendjava.exception.ModelNotFoundException;
import dev.datasys.backendjava.repository.IGenericRepo;

public abstract class CRUDImpl<T, ID> implements IBaseInterfaceService<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public Long count() {
        return getRepo().count();
    }

    @Override
    public T create(T entidad) {

        return getRepo().save(entidad);
    }

    @Override
    public void delete(T entidad) {
        getRepo().delete(entidad);

    }

    @Override
    public void deleteById(ID id) {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND " + id));
        getRepo().deleteById(id);

    }

    @Override
    public Boolean exists(ID id) {
        return getRepo().existsById(id);
    }

    @Override
    public List<T> getAll() {
        return getRepo().findAll();
    }

    @Override
    public Page<?> pagination(Integer pagenumber, Integer rows, List<SortModel> sortModel, Filter filter) {
        return null;
    }

    @Override
    public T readById(ID id) {
        T rtn = getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND " + id));
        return rtn;
    }

    @Override
    public T update(T entidad, ID id) {
        return getRepo().save(entidad);
    }

}
