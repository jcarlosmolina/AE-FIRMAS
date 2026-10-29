package com.keralty.aefirmas.service;

import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.global.AdminConstants;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.persistence.Admin;
import com.keralty.aefirmas.persistence.oid.AdminOid;
import com.keralty.aefirmas.repository.AdminJpaRepository;
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
public class AdminService extends AbstractService {

    AdminJpaRepository repository;

    @Inject
    public AdminService(
            AdminJpaRepository repository) {
        this.repository = repository;
    }


    /*
     * Returns an instance of Admin by ID
     *
     * @param funcionarioId Identification field funcionarioId
     *
     * @return Instance of Admin
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Admin getById(Long funcionarioId) {
        return getByOID(new AdminOid(funcionarioId));
    }
    
    /*
     * Returns an instance of Admin by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Admin
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Admin getByOID(AdminOid oid) {
        Optional<Admin> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Admin by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Admin
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Admin getByOIDWithHV(AdminOid oid) {
        Optional<Admin> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Admin> getInstanceByOid(AdminOid oid) {
        return oid != null && oid.getFuncionarioId() != null
               ? repository.findById(oid.getFuncionarioId())
               : Optional.empty();
    }

    /*
     * Returns an instance of Admin by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Admin
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Admin getByJsonOid(String jsonOid) {
        AdminOid oid = new AdminOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            AdminOid oid = new AdminOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFuncionarioId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(AdminService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
