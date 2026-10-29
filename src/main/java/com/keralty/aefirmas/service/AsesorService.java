package com.keralty.aefirmas.service;

import com.keralty.aefirmas.exception.SystemException;
import com.keralty.aefirmas.global.AsesorConstants;
import com.keralty.aefirmas.global.Constants;
import com.keralty.aefirmas.global.FuncionarioConstants;
import com.keralty.aefirmas.persistence.Asesor;
import com.keralty.aefirmas.persistence.oid.AsesorOid;
import com.keralty.aefirmas.repository.AsesorJpaRepository;
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
public class AsesorService extends AbstractService {

    AsesorJpaRepository repository;

    @Inject
    public AsesorService(
            AsesorJpaRepository repository) {
        this.repository = repository;
    }


    /*
     * Returns an instance of Asesor by ID
     *
     * @param funcionarioId Identification field funcionarioId
     *
     * @return Instance of Asesor
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Asesor getById(Long funcionarioId) {
        return getByOID(new AsesorOid(funcionarioId));
    }
    
    /*
     * Returns an instance of Asesor by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Asesor
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Asesor getByOID(AsesorOid oid) {
        Optional<Asesor> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Asesor by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Asesor
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Asesor getByOIDWithHV(AsesorOid oid) {
        Optional<Asesor> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Asesor> getInstanceByOid(AsesorOid oid) {
        return oid != null && oid.getFuncionarioId() != null
               ? repository.findById(oid.getFuncionarioId())
               : Optional.empty();
    }

    /*
     * Returns an instance of Asesor by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Asesor
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Asesor getByJsonOid(String jsonOid) {
        AsesorOid oid = new AsesorOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            AsesorOid oid = new AsesorOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FuncionarioConstants.ATTR_FIELD_FUNCIONARIOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFuncionarioId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(AsesorService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
