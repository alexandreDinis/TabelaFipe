package com.dinis.TabelaFipe.domin;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataFipe(@JsonAlias ("codigo") String cod,
                       @JsonAlias ("nome") String name) {
    @Override
    public String toString() {
        return "Cód: " + cod +
                " - Marca: " + name ;
    }
}
