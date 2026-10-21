package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.NovedadCT;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface NovedadCTJpaRepositoryCustom {

    List<NovedadCT> solveQuery(SQLSelect query);
    
    List<NovedadCT> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<NovedadCT> executeQuery(String query);

    public List<NovedadCT> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
