package com.keralty.aefirmas.repository;

import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.persistence.Admin;
import com.keralty.aefirmas.persistence.Afiliaciones;
import com.keralty.aefirmas.persistence.AreaMedica;
import com.keralty.aefirmas.persistence.Asesor;
import com.keralty.aefirmas.persistence.Funcionario;
import com.keralty.aefirmas.persistence.Gestor;
import com.keralty.aefirmas.persistence.MesaDeApoyo;
import com.keralty.aefirmas.persistence.RespRegional;
import com.keralty.aefirmas.persistence.SuperAdmin;
import com.keralty.aefirmas.sqlutils.SQLCount;
import com.keralty.aefirmas.sqlutils.SQLParam;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import java.util.List;

public interface FuncionarioJpaRepositoryCustom {

    List<Funcionario> solveQuery(SQLSelect query);
    
    List<Funcionario> solveQuery(SQLSelect query, int pageSize, int pageNumber);
    
    int solveCountQuery(SQLSelect query);

    Admin findChildAdmin(Long oid);

    SuperAdmin findChildSuperAdmin(Long oid);

    Asesor findChildAsesor(Long oid);

    AreaMedica findChildAreaMedica(Long oid);

    Afiliaciones findChildAfiliaciones(Long oid);

    RespRegional findChildRespRegional(Long oid);

    Gestor findChildGestor(Long oid);

    MesaDeApoyo findChildMesaDeApoyo(Long oid);

    Long assocOperator000(Funcionario instance) throws SystemException;

    Long assocOperator000Range1(Funcionario instance) throws SystemException;
    
    SQLCount assocOperator000Range1Query(Funcionario instance) throws SystemException;

    public List<Funcionario> executeQuery(String query);

    public List<Funcionario> executeQuery(String query, List<SQLParam> params);

    void refreshEntity(Object object);
}
