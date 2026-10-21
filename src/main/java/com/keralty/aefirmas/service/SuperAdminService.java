package com.keralty.aefirmas.service;

import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.global.SuperAdminConstants;
import com.keralty.aefirmas.persistence.oid.SuperAdminOid;
import com.keralty.aefirmas.persistence.SuperAdmin;
import com.keralty.aefirmas.repository.SuperAdminJpaRepository;
import com.keralty.aefirmas.sqlutils.SQLSelect;
import com.keralty.aefirmas.sqlutils.SQLWhereComparison;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aefirmas.sqlutils.SQLWhereOperandPathAttribute;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class SuperAdminService extends AbstractService {

    SuperAdminJpaRepository repository;

    @Inject
    public SuperAdminService(
            SuperAdminJpaRepository repository) {
        this.repository = repository;
    }


    /*
     * Returns an instance of SuperAdmin by ID
     *
     * @param funcionarioId Identification field funcionarioId
     *
     * @return Instance of SuperAdmin
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public SuperAdmin getById(Long funcionarioId) {
        return getByOID(new SuperAdminOid(funcionarioId));
    }
    
    /*
     * Returns an instance of SuperAdmin by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of SuperAdmin
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public SuperAdmin getByOID(SuperAdminOid oid) {
        Optional<SuperAdmin> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of SuperAdmin by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of SuperAdmin
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public SuperAdmin getByOIDWithHV(SuperAdminOid oid) {
        Optional<SuperAdmin> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<SuperAdmin> getInstanceByOid(SuperAdminOid oid) {
        return oid != null && oid.getFuncionarioId() != null
               ? repository.findById(oid.getFuncionarioId())
               : Optional.empty();
    }

    /*
     * Returns an instance of SuperAdmin by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of SuperAdmin
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public SuperAdmin getByJsonOid(String jsonOid) {
        SuperAdminOid oid = new SuperAdminOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            SuperAdminOid oid = new SuperAdminOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFuncionarioId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(SuperAdminService.class).error(e.getMessage());
        }
    }

}
