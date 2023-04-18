package com.home.in.house.navigation.system.entity;

import java.io.Serializable;
import java.util.Properties;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseExistingOrGenerateIdGenerator extends org.hibernate.id.UUIDGenerator {
    Logger logger = LoggerFactory.getLogger(UseExistingOrGenerateIdGenerator.class);  
    
    private String entityName;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
        entityName = params.getProperty(ENTITY_NAME);
        super.configure(type, params, serviceRegistry);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        Serializable id = session
                .getEntityPersister(entityName, object)
                .getIdentifier(object, session);

        if (id == null) {
            return super.generate(session, object);
        } else {
            return id;
        }
    }
}
