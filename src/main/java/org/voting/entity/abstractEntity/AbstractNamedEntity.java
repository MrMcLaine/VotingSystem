package org.voting.entity.abstractEntity;

public abstract class AbstractNamedEntity extends AbstractBaseEntity {
    protected String name;

    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "AbstractNamedEntity{" +
               "name='" + name + '\'' +
               ", id=" + id +
               '}';
    }
}
