package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Planes;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface PlanesJpaRepositoryCustom {

    List<Planes> solveQuery(SQLSelect query);
    
    List<Planes> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    public List<Planes> executeQuery(String query);

    public List<Planes> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
