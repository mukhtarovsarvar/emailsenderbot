package com.company.GeneratePromoKod.service;


import com.company.GeneratePromoKod.entity.CurrentUser;
import com.company.GeneratePromoKod.enums.UserStatus;
import com.company.GeneratePromoKod.repository.CurrenUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final CurrenUserRepository repository;

    public CurrentUser create(CurrentUser user){
        repository.save(user);
        return user;
    }

    public CurrentUser getByUserId(String userId){
        Optional<CurrentUser> user = repository.findByUserId(userId);
        return user.orElse(null);
    }

    public boolean delete(String userId){
    repository.deleteByUserId(userId);
    return true;
    }


    public void saveOrUpdate(String userId, UserStatus status){
        CurrentUser currentUser = getByUserId(userId);
        if(currentUser == null){
            create(new CurrentUser(userId,status));
        }
        update(userId,status);
    }

    public void saveOrUpdate(String userId, UserStatus status,String email){
        CurrentUser currentUser = getByUserId(userId);
        if(currentUser == null){
            create(new CurrentUser(userId,status,email));
        }else {
            if(currentUser.getEmail() == null){
                update(userId,status,email);
            }else {
                update(userId,status);
            }
        }

    }

    public boolean update(String userId, UserStatus status){
        repository.updateStatus(status,userId);
        return true;
    }

    public boolean update(String userId, UserStatus status,String email){
        repository.updateStatus(status,userId,email);
        return true;
    }





}
