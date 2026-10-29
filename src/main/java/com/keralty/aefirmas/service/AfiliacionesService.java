package com.keralty.aefirmas.service;

import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.global.AfiliacionesConstants;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.persistence.Afiliaciones;
import com.keralty.aefirmas.persistence.oid.AfiliacionesOid;
import com.keralty.aefirmas.repository.AfiliacionesJpaRepository;
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
public class AfiliacionesService extends AbstractService {

    AfiliacionesJpaRepository repository;

    @Inject
    public AfiliacionesService(
            AfiliacionesJpaRepository repository) {
        this.repository = repository;
    }


    /*
     * Returns an instance of Afiliaciones by ID
     *
     * @param funcionarioId Identification field funcionarioId
     *
     * @return Instance of Afiliaciones
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Afiliaciones getById(Long funcionarioId) {
        return getByOID(new AfiliacionesOid(funcionarioId));
    }
    
    /*
     * Returns an instance of Afiliaciones by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Afiliaciones
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Afiliaciones getByOID(AfiliacionesOid oid) {
        Optional<Afiliaciones> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Afiliaciones by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Afiliaciones
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Afiliaciones getByOIDWithHV(AfiliacionesOid oid) {
        Optional<Afiliaciones> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Afiliaciones> getInstanceByOid(AfiliacionesOid oid) {
        return oid != null && oid.getFuncionarioId() != null
               ? repository.findById(oid.getFuncionarioId())
               : Optional.empty();
    }

    /*
     * Returns an instance of Afiliaciones by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Afiliaciones
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Afiliaciones getByJsonOid(String jsonOid) {
        AfiliacionesOid oid = new AfiliacionesOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            AfiliacionesOid oid = new AfiliacionesOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFuncionarioId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(AfiliacionesService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
