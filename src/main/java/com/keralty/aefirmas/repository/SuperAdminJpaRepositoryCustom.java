package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.persistence.Funcionario;
import com.keralty.aefirmas.persistence.SuperAdmin;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface SuperAdminJpaRepositoryCustom {

    List<SuperAdmin> solveQuery(SQLSelect query);
    
    List<SuperAdmin> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Funcionario findParent(Long oid);

    public List<SuperAdmin> executeQuery(String query);

    public List<SuperAdmin> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
