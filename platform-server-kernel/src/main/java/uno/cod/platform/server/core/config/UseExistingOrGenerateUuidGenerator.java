package uno.cod.platform.server.core.config;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;

public class UseExistingOrGenerateUuidGenerator extends UUIDGenerator {
    @Override
    public Serializable generate(SessionImplementor session, Object object)
            throws HibernateException {
        Serializable id = session.getEntityPersister(null, object)
                .getClassMetadata().getIdentifier(object, session);
        return id != null ? id : super.generate(session, object);
    }
}