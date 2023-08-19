package dev.datasys.backendjava.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.datasys.backendjava.models.PlanEntity;
import dev.datasys.backendjava.repository.IPlanRepository;
import dev.datasys.backendjava.repository.IGenericRepo;
import dev.datasys.backendjava.services.IPlanService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl extends CRUDImpl<PlanEntity, Integer> implements IPlanService {

    private final IPlanRepository iPlanRepository;

    @Override
    protected IGenericRepo<PlanEntity, Integer> getRepo() {
        return iPlanRepository;
    }

    @Override
    public PlanEntity update(PlanEntity entidad, Integer id) {
        PlanEntity original = this.readById(id);
        String[] ignoreProperties = new String[] { "createdAt", "updatedAt" };
        BeanUtils.copyProperties(entidad, original, ignoreProperties);
        return super.update(original, id);
    }

}
