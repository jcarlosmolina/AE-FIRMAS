package com.keralty.aefirmas.viewmodel.firma.piu;

import com.keralty.aefirmas.viewmodel.PopulationRequestViewModel;
import com.keralty.aefirmas.viewmodel.QueryRequest;
import com.keralty.aefirmas.viewmodel.firma.filter.FFirmasResponsables;

public class PIUFirmasRespRegionalRequest extends PopulationRequestViewModel{

    private FFirmasResponsables fFirmasResponsables;
    
    public void setFFirmasResponsables(FFirmasResponsables fFirmasResponsables) {
        this.fFirmasResponsables = fFirmasResponsables;
    }
    
    public FFirmasResponsables getFFirmasResponsables() {
        return fFirmasResponsables;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fFirmasResponsables != null)
            queryRequest.addFilterRequest(fFirmasResponsables.buildFilterRequest());
        return queryRequest;
    }
}
