package org.surfnet.oaaas.repository;

import org.springframework.data.repository.CrudRepository;
import org.surfnet.oaaas.model.AccessRestApi;

/**
 * 访问 AccessRestApi 的类
 * User: zhangxiaojie
 * Date: 12/30/13
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */
public interface AccessRestApiRepository extends CrudRepository<AccessRestApi, Long> {
}
