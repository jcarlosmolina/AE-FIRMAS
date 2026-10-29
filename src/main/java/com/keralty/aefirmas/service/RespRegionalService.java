package com.keralty.aefirmas.service;

import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.global.RespRegionalConstants;
import com.keralty.aefirmas.persistence.oid.RespRegionalOid;
import com.keralty.aefirmas.persistence.RespRegional;
import com.keralty.aefirmas.repository.RespRegionalJpaRepository;
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
public class RespRegionalService extends AbstractService {

    RespRegionalJpaRepository repository;

    @Inject
    public RespRegionalService(
            RespRegionalJpaRepository repository) {
        this.repository = repository;
    }


    /*
     * Returns an instance of RespRegional by ID
     *
     * @param funcionarioId Identification field funcionarioId
     *
     * @return Instance of RespRegional
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public RespRegional getById(Long funcionarioId) {
        return getByOID(new RespRegionalOid(funcionarioId));
    }
    
    /*
     * Returns an instance of RespRegional by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of RespRegional
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public RespRegional getByOID(RespRegionalOid oid) {
        Optional<RespRegional> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of RespRegional by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of RespRegional
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public RespRegional getByOIDWithHV(RespRegionalOid oid) {
        Optional<RespRegional> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<RespRegional> getInstanceByOid(RespRegionalOid oid) {
        return oid != null && oid.getFuncionarioId() != null
               ? repository.findById(oid.getFuncionarioId())
               : Optional.empty();
    }

    /*
     * Returns an instance of RespRegional by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of RespRegional
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public RespRegional getByJsonOid(String jsonOid) {
        RespRegionalOid oid = new RespRegionalOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            RespRegionalOid oid = new RespRegionalOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFuncionarioId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(RespRegionalService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
