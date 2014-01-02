package org.surfnet.oaaas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.surfnet.oaaas.model.AccessTokenToAccessRestApi;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaojie
 * Date: 12/30/13
 * Time: 21:57
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface AccessTokenToAccessRestApiRepository extends CrudRepository<AccessTokenToAccessRestApi,Long> {
}
