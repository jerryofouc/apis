package org.surfnet.oaaas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.surfnet.oaaas.model.Client;
import org.surfnet.oaaas.model.ClientToScope;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 1/7/14
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */

@Repository
public interface ClientToScopeRepository extends CrudRepository<ClientToScope, Long> {
}
