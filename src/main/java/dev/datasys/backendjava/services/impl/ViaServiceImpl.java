package dev.datasys.backendjava.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.datasys.backendjava.models.ViaEntity;
import dev.datasys.backendjava.repository.IViaRepository;
import dev.datasys.backendjava.repository.IGenericRepo;
import dev.datasys.backendjava.services.IViaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ViaServiceImpl extends CRUDImpl<ViaEntity, Integer> implements IViaService {

    private final IViaRepository iViaRepository;

    @Override
    protected IGenericRepo<ViaEntity, Integer> getRepo() {
        return iViaRepository;
    }

    @Override
    public ViaEntity update(ViaEntity entidad, Integer id) {
        ViaEntity original = this.readById(id);
        String[] ignoreProperties = new String[] { "createdAt", "updatedAt" };
        BeanUtils.copyProperties(entidad, original, ignoreProperties);
        return super.update(original, id);
    }

}
