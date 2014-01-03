package org.surfnet.oaaas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.surfnet.oaaas.model.ResourceServer;
import org.surfnet.oaaas.model.SystemAdminstrator;

import java.util.List;

/**
 *
 * User: zhangxiaojie
 * Date: 1/3/14
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface SystemAdminRepository extends CrudRepository<SystemAdminstrator, Long> {
    SystemAdminstrator findByName(String name);
}
