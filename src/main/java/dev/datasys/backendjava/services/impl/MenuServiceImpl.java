package dev.datasys.backendjava.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.datasys.backendjava.models.MenuEntity;
import dev.datasys.backendjava.repository.IMenuRepository;
import dev.datasys.backendjava.repository.IGenericRepo;
import dev.datasys.backendjava.services.IMenuService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends CRUDImpl<MenuEntity, Integer> implements IMenuService {

    private final IMenuRepository iMenuRepository;

    @Override
    protected IGenericRepo<MenuEntity, Integer> getRepo() {
        return iMenuRepository;
    }

    @Override
    public MenuEntity update(MenuEntity entidad, Integer id) {
        MenuEntity original = this.readById(id);
        String[] ignoreProperties = new String[] { "createdAt", "updatedAt" };
        BeanUtils.copyProperties(entidad, original, ignoreProperties);
        return super.update(original, id);
    }

}
