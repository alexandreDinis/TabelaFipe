package com.dinis.TabelaFipe.domin;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataModels(@JsonAlias("modelos")List<DataFipe> modesls) {
}
