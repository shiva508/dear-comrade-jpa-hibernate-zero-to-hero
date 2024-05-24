package com.comrade.entity.basictype;

import com.comrade.entity.EventType;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class EventTypeDef implements UserType<EventType> {

    private EventType [] eventTypes;
    private String env;

    public EventTypeDef(){
        env = System.getenv().get("develop") == null ? null : System.getenv().get("develop");
        eventTypes = EventType.values();
    }
    @Override
    public int getSqlType() {
        if (env == null){
        return Types.TINYINT;
        } else {
            return Types.INTEGER;
        }
    }

    @Override
    public Class<EventType> returnedClass() {
        return EventType.class;
    }

    @Override
    public boolean equals(EventType eventType, EventType j1) {
        return Objects.equals(eventType,j1);
    }

    @Override
    public int hashCode(EventType eventType) {
        return eventType.hashCode();
    }

    @Override
    public EventType nullSafeGet(ResultSet resultSet,
                                 int i,
                                 SharedSessionContractImplementor sharedSessionContractImplementor,
                                 Object o) throws SQLException {
        if (null == resultSet){
            return null;
        }
        return eventTypes[resultSet.getInt(i)];
    }

    //Hibernate: insert into event (created_date,event_type,image,title) values (?,?,?,?)
    @Override
    public void nullSafeSet(PreparedStatement preparedStatement,
                            EventType eventType,
                            int index,
                            SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (eventType == null){
            preparedStatement.setInt(index,0);
        }else {
            preparedStatement.setInt(index,eventType.ordinal());
        }

    }

    @Override
    public EventType deepCopy(EventType eventType) {
        return eventType == null ? null : eventType;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(EventType eventType) {
        return deepCopy(eventType);
    }

    @Override
    public EventType assemble(Serializable serializable, Object o) {
        return deepCopy((EventType)o);
    }

    @Override
    public EventType replace(EventType detached, EventType managed, Object owner) {
        return UserType.super.replace(detached, managed, owner);
    }
}
