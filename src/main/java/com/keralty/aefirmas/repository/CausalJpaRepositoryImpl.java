package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.global.UserFunctions;
import com.keralty.aefirmas.persistence.Causal;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.TypedQueryBuilder;
import com.keralty.aefirmas.utils.Agent;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;

public class CausalJpaRepositoryImpl extends AbstractRepository implements CausalJpaRepositoryCustom {

    @Inject
    public CausalJpaRepositoryImpl(Agent agent, UserFunctions userFunctionsSrv) {
        super(agent, userFunctionsSrv);
    }

    @Override
    public List<Causal> executeQuery(String jpqlQuery) {
        return getQueryBuilder().build(jpqlQuery).execute();
    }
    
    @Override
    public List<Causal> executeQuery(String jpqlQuery, List<SQLParam> params) {
        return getQueryBuilder().build(jpqlQuery).setParameters(params).execute();
    }
    
    public Collection<Causal> executeQuery(SQLSelect select) {
        return getQueryBuilder().build(select).execute();
    }

    private TypedQueryBuilder<Causal> getQueryBuilder() {
        return new TypedQueryBuilder<>(em, Causal.class);
    }

    @Override	
    public List<Causal> solveQuery(SQLSelect select) {
        return solveQuery(select, 0, 0);
    }

    @Override
    public List<Causal> solveQuery(SQLSelect select, int pageSize, int pageNumber) {
        return getQueryBuilder().build(select, pageSize, pageNumber).execute();
    }

    @Override
    public void refreshEntity(Object object) {
        super.refreshEntity(object);
    }
}
