package org.surfnet.oaaas.repository;

import org.springframework.data.repository.CrudRepository;
import org.surfnet.oaaas.model.ResourceOwner;
import org.surfnet.oaaas.model.ResourceOwnerScopeToAccessToken;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 1/9/14
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 */
public interface ResourceOwnerScopeToAccessTokenRepository extends CrudRepository<ResourceOwnerScopeToAccessToken, Long> {
}
