package org.surfnet.oaaas.model;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * client和scope的中间表
 * User: zhangxiaojie
 * Date: 1/7/14
 * Time: 23:02
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name = "client_scope")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ClientToScope {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @XmlTransient
    private Client client;

    @ManyToOne
    @JoinColumn(name = "scope_id")
    @XmlTransient
    private ResourceServerScope resourceServerScope;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ResourceServerScope getResourceServerScope() {
        return resourceServerScope;
    }

    public void setResourceServerScope(ResourceServerScope resourceServerScope) {
        this.resourceServerScope = resourceServerScope;
    }
}
