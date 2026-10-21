package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.global.UserFunctions;
import com.keralty.aefirmas.persistence.Periodicidad;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.TypedQueryBuilder;
import com.keralty.aefirmas.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class PeriodicidadJpaRepositoryImpl extends AbstractRepository implements PeriodicidadJpaRepositoryCustom {

    @Inject
    public PeriodicidadJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Periodicidad> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Periodicidad> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Periodicidad> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Periodicidad> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Periodicidad.class);
    }

    @Override	
    public List<Periodicidad> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Periodicidad> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
