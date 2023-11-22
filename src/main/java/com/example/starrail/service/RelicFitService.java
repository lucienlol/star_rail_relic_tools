package com.example.starrail.service;

import com.example.starrail.entity.RelicFitDetail;
import com.example.starrail.entity.RelicFitQuery;
import com.example.starrail.po.RelicFit;

import java.util.List;

public interface RelicFitService {

    Boolean addRelicFit(RelicFit relicFit);

    Boolean addRelicFitList(List<RelicFit> relicFitList);

    Boolean deleteRelicFitByQuery(RelicFitQuery relicFitQuery);

    List<RelicFitDetail> getDetailByQuery(RelicFitQuery query);
}
