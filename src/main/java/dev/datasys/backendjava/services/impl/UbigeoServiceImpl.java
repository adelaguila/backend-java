package dev.datasys.backendjava.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.datasys.backendjava.models.UbigeoEntity;
import dev.datasys.backendjava.repository.IUbigeoRepository;
import dev.datasys.backendjava.repository.IGenericRepo;
import dev.datasys.backendjava.services.IUbigeoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UbigeoServiceImpl extends CRUDImpl<UbigeoEntity, String> implements IUbigeoService {

    private final IUbigeoRepository iUbigeoRepository;

    @Override
    protected IGenericRepo<UbigeoEntity, String> getRepo() {
        return iUbigeoRepository;
    }

    @Override
    public UbigeoEntity update(UbigeoEntity entidad, String id) {
        UbigeoEntity original = this.readById(id);
        String[] ignoreProperties = new String[] { "createdAt", "updatedAt" };
        BeanUtils.copyProperties(entidad, original, ignoreProperties);
        return super.update(original, id);
    }

}
