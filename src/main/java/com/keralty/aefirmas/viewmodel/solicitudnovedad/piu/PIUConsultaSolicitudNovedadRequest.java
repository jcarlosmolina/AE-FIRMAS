package com.keralty.aefirmas.viewmodel.solicitudnovedad.piu;

import com.keralty.aefirmas.viewmodel.PopulationRequestViewModel;
import com.keralty.aefirmas.viewmodel.QueryRequest;
import com.keralty.aefirmas.viewmodel.solicitudnovedad.filter.FSolicitudNovedadNumFechas;

public class PIUConsultaSolicitudNovedadRequest extends PopulationRequestViewModel{

    private FSolicitudNovedadNumFechas fSolicitudNovedadNumFechas;
    
    public void setFSolicitudNovedadNumFechas(FSolicitudNovedadNumFechas fSolicitudNovedadNumFechas) {
        this.fSolicitudNovedadNumFechas = fSolicitudNovedadNumFechas;
    }
    
    public FSolicitudNovedadNumFechas getFSolicitudNovedadNumFechas() {
        return fSolicitudNovedadNumFechas;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fSolicitudNovedadNumFechas != null)
            queryRequest.addFilterRequest(fSolicitudNovedadNumFechas.buildFilterRequest());
        return queryRequest;
    }
}
