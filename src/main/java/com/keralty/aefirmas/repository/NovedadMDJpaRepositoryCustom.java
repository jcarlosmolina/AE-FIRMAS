package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.NovedadMD;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface NovedadMDJpaRepositoryCustom {

    List<NovedadMD> solveQuery(SQLSelect query);
    
    List<NovedadMD> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<NovedadMD> executeQuery(String query);

    public List<NovedadMD> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
