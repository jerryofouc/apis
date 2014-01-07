package org.surfnet.oaaas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.surfnet.oaaas.model.ResourceServer;
import org.surfnet.oaaas.model.ResourceServerScope;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 1/7/14
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ResourceServerScopeRepository extends CrudRepository<ResourceServerScope, Long> {
}
