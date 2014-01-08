package org.surfnet.oaaas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.surfnet.oaaas.model.Client;
import org.surfnet.oaaas.model.ResourceOwnerToAccessRestApi;
import org.surfnet.oaaas.model.ResourceOwnerToScope;
import org.surfnet.oaaas.model.ResourceServer;

import java.util.List;

/**
 * ResourceOwner和Scope的中间表
 * User: zhangxiaojie
 * Date: 1/8/14
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ResourceOwnerToScopeApiRepository extends CrudRepository<ResourceOwnerToScope, Long> {
    List<ResourceOwnerToScope> findByResourceOwnerIdAndResourceServerScopeId(Long resourceOwnerId,Long resourceServerScopeId);
}
