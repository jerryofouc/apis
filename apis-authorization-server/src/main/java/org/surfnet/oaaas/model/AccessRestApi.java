package org.surfnet.oaaas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * User: zhangxiaojie
 * Date: 12/27/13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "access_rest_api")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessRestApi extends AbstractEntity{
    @Column(unique = true)
    @NotNull
    private String completeUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resourceserver_id", nullable = false, updatable = false)
    @XmlTransient
    private ResourceServer resourceServer;



    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    public ResourceServer getResourceServer() {
        return resourceServer;
    }

    public void setResourceServer(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

}
