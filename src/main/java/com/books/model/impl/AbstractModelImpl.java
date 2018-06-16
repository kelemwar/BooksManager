package com.books.model.impl;
/**
 * Created by Harry on 10.06.2018.
 */

import com.books.model.AbstractModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;


public abstract class AbstractModelImpl implements AbstractModel,Serializable {


    private static final Logger LOGGER =
            LogManager.getLogger(AbstractModelImpl.class);

    private long id;
    private String name;

    @Override
    public void setId(long id) {
        LOGGER.debug("id changed");

        this.id = id;
    }

    @Override
    public long getId() {
        LOGGER.debug("id returned");

        return id;
    }

    @Override
    public void setName(String name) {
        LOGGER.debug("name changed");

        this.name = name;
    }

    @Override
    public String getName() {
        LOGGER.debug("name returned");

        return name;
    }

    @Override
    public String toString() {
        return "AbstractModelImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractModelImpl that = (AbstractModelImpl) o;

        if (id != that.id) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
