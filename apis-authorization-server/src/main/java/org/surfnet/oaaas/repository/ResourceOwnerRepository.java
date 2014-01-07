package org.surfnet.oaaas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.surfnet.oaaas.model.AccessRestApi;
import org.surfnet.oaaas.model.ResourceOwner;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 12/27/13
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ResourceOwnerRepository extends CrudRepository<ResourceOwner, Long> {
    ResourceOwner findByName(String name);

    @Query("select  rta.accessRestApi from   ResourceOwnerToAccessRestApi rta join FETCH rta.accessRestApi   where rta.resourceOwner.id=?1")
    List<AccessRestApi> findAccessApisById(long resourceOwnerId);

    ResourceOwner findByEmail(String email);
}
