package com.kj.textile.TextileERP.services.BaseService;


import com.kj.textile.TextileERP.entity.BaseEntity.UserAssignGroupEntity;
import com.kj.textile.TextileERP.model.BaseModel.UserAssignGroupModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface UserAssignGroupService {
    List<UserAssignGroupEntity> getAllList();

    UserAssignGroupModel saveForm(UserAssignGroupModel requestModelData);
    UserAssignGroupModel updateForm(UserAssignGroupModel requestModelData);
    UserAssignGroupEntity getDataById(Long Id);

    UserAssignGroupEntity findByUserMaser(Long Id);



    @Transactional
    String deleteRecordById(Long Id);
}
