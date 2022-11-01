package org.voting.entity;

public abstract class AbstractEntity {

    protected Integer id;

    protected String name;

    public AbstractEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}
