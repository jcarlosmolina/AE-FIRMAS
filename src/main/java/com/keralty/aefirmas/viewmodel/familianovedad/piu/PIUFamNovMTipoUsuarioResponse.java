package com.keralty.aefirmas.viewmodel.familianovedad.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aefirmas.viewmodel.QueryResponse;
import com.keralty.aefirmas.viewmodel.familianovedad.ds.DSFamNovTipoTitular;

public class PIUFamNovMTipoUsuarioResponse extends QueryResponse {

    private List<DSFamNovTipoTitular> results;

    public PIUFamNovMTipoUsuarioResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUFamNovMTipoUsuarioResponse(List<DSFamNovTipoTitular> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSFamNovTipoTitular> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSFamNovTipoTitular> results) {
        this.results = results;
    }
}
