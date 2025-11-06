package com.erebelo.springhydrationservice.service;

import com.erebelo.springhydrationservice.domain.dto.AthenaContextDto;
import com.erebelo.springhydrationservice.domain.dto.RecordDto;
import com.erebelo.springhydrationservice.domain.enumeration.RecordTypeEnum;
import com.erebelo.springhydrationservice.domain.model.HydrationStep;
import org.apache.commons.lang3.tuple.Pair;
import software.amazon.awssdk.services.athena.model.GetQueryResultsResponse;
import software.amazon.awssdk.services.athena.model.Row;

import java.util.List;

public interface HydrationService<T extends RecordDto> {

    RecordTypeEnum getRecordType();

    String getDeltaQuery();

    T hydrateDomainData(T domainData);

    Pair<String, Iterable<GetQueryResultsResponse>> fetchDataFromAthena(String query);

    <U extends AthenaContextDto> List<Row> processAndSkipHeaderOnce(List<Row> rows, U context);

    List<T> mapRowsToDomainData(String[] columnNames, List<Row> rows);

    void saveHydrationFailedRecord(HydrationStep step, String recordId, String errorMessage);

}
