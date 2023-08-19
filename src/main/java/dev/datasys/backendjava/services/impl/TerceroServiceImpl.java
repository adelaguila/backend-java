package dev.datasys.backendjava.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.datasys.backendjava.models.TerceroEntity;
import dev.datasys.backendjava.repository.ITerceroRepository;
import dev.datasys.backendjava.repository.IGenericRepo;
import dev.datasys.backendjava.services.ITerceroService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TerceroServiceImpl extends CRUDImpl<TerceroEntity, Integer> implements ITerceroService {

    private final ITerceroRepository iTerceroRepository;

    @Override
    protected IGenericRepo<TerceroEntity, Integer> getRepo() {
        return iTerceroRepository;
    }

    @Override
    public TerceroEntity update(TerceroEntity entidad, Integer id) {
        TerceroEntity original = this.readById(id);
        String[] ignoreProperties = new String[] { "createdAt", "updatedAt" };
        BeanUtils.copyProperties(entidad, original, ignoreProperties);
        return super.update(original, id);
    }


}
