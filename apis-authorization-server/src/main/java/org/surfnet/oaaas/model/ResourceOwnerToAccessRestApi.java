package org.surfnet.oaaas.model;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * ResourceOwner和AccessRestApi的中间类
 * User: zhangxiaojie
 * Date: 12/29/13
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "ResourceOwner_TO_ACCESS_REST_API")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ResourceOwnerToAccessRestApi {
    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_owner_id", nullable = false, updatable = false)
    @XmlTransient
    private ResourceOwner resourceOwner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "access_rest_api_id", nullable = false, updatable = false)
    @XmlTransient
    private AccessRestApi accessRestApi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResourceOwner getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(ResourceOwner resourceOwner) {
        this.resourceOwner = resourceOwner;
    }

    public AccessRestApi getAccessRestApi() {
        return accessRestApi;
    }

    public void setAccessRestApi(AccessRestApi accessRestApi) {
        this.accessRestApi = accessRestApi;
    }
}
