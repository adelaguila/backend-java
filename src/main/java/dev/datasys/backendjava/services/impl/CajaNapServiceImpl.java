package dev.datasys.backendjava.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.datasys.backendjava.models.CajaNapEntity;
import dev.datasys.backendjava.repository.ICajaNapRepository;
import dev.datasys.backendjava.repository.IGenericRepo;
import dev.datasys.backendjava.services.ICajaNapService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CajaNapServiceImpl extends CRUDImpl<CajaNapEntity, Integer> implements ICajaNapService {

    private final ICajaNapRepository iCajaNapRepository;

    @Override
    protected IGenericRepo<CajaNapEntity, Integer> getRepo() {
        return iCajaNapRepository;
    }

    @Override
    public CajaNapEntity update(CajaNapEntity entidad, Integer id) {
        CajaNapEntity original = this.readById(id);
        String[] ignoreProperties = new String[] { "createdAt", "updatedAt" };
        BeanUtils.copyProperties(entidad, original, ignoreProperties);
        return super.update(original, id);
    }

}
