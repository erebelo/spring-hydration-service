package com.erebelo.springhydrationservice.domain.request;

import com.erebelo.springhydrationservice.domain.enumeration.RecordTypeEnum;

import java.util.List;

public record HydrationRequest(List<RecordTypeEnum> recordTypes) {
}
