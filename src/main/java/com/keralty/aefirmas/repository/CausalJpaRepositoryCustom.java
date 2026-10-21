package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Causal;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface CausalJpaRepositoryCustom {

    List<Causal> solveQuery(SQLSelect query);
    
    List<Causal> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Causal> executeQuery(String query);

    public List<Causal> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
