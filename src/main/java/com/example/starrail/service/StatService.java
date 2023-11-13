package com.example.starrail.service;

import com.example.starrail.po.Stat;

import java.util.List;

public interface StatService {

    List<Stat> getAllStats();

    List<Stat> getAllMainStats();

    List<Stat> getAllBodyStats();

    List<Stat> getAllFeetStats();

    List<Stat> getAllSphereStats();

    List<Stat> getAllRopeStats();

    List<Stat> getAllSubStats();
}
