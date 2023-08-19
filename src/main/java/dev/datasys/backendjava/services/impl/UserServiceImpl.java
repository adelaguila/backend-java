package dev.datasys.backendjava.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dev.datasys.backendjava.models.UserEntity;
import dev.datasys.backendjava.repository.IUserRepository;
import dev.datasys.backendjava.repository.IGenericRepo;
import dev.datasys.backendjava.services.IUserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<UserEntity, Integer> implements IUserService {

    private final IUserRepository iUserRepository;

    @Override
    protected IGenericRepo<UserEntity, Integer> getRepo() {
        return iUserRepository;
    }

    @Override
    public UserEntity update(UserEntity entidad, Integer id) {
        UserEntity original = this.readById(id);
        String[] ignoreProperties = new String[] { "createdAt", "updatedAt" };
        BeanUtils.copyProperties(entidad, original, ignoreProperties);
        return super.update(original, id);
    }

}
