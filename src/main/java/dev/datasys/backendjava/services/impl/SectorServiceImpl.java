package dev.datasys.backendjava.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.datasys.backendjava.models.SectorEntity;
import dev.datasys.backendjava.repository.ISectorRepository;
import dev.datasys.backendjava.repository.IGenericRepo;
import dev.datasys.backendjava.services.ISectorService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectorServiceImpl extends CRUDImpl<SectorEntity, Integer> implements ISectorService {

    private final ISectorRepository iSectorRepository;

    @Override
    protected IGenericRepo<SectorEntity, Integer> getRepo() {
        return iSectorRepository;
    }

    @Override
    public SectorEntity update(SectorEntity entidad, Integer id) {
        SectorEntity original = this.readById(id);
        String[] ignoreProperties = new String[] { "createdAt", "updatedAt" };
        BeanUtils.copyProperties(entidad, original, ignoreProperties);
        return super.update(original, id);
    }

}
