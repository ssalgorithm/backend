package com.ssafy.ssalgorithm.model.dao;

import com.ssafy.ssalgorithm.model.dto.SolvedDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SolvedMapper {
    public SolvedDto selectOne(@Param("no") String no);
}
