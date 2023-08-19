package dev.datasys.backendjava.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.datasys.backendjava.models.CatalogoEntity;
import dev.datasys.backendjava.repository.ICatalogoRepository;
import dev.datasys.backendjava.repository.IGenericRepo;
import dev.datasys.backendjava.services.ICatalogoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogoServiceImpl extends CRUDImpl<CatalogoEntity, Integer> implements ICatalogoService {

    private final ICatalogoRepository iCatalogoRepository;

    @Override
    protected IGenericRepo<CatalogoEntity, Integer> getRepo() {
        return iCatalogoRepository;
    }

    @Override
    public CatalogoEntity update(CatalogoEntity entidad, Integer id) {
        CatalogoEntity original = this.readById(id);
        String[] ignoreProperties = new String[] { "createdAt", "updatedAt" };
        BeanUtils.copyProperties(entidad, original, ignoreProperties);
        return super.update(original, id);
    }

}
