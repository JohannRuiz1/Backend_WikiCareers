package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Risk;

@Repository
public class JbdcRiskRepository implements RiskRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Risk risk) {
        return jdbcTemplate.update("INSERT INTO RISKS (career_id, risk_description, risk_level) VALUES(?,?,?)",
        new Object[] {risk.getCareerId(), risk.getRiskDescription(), risk.getRiskLevel()});
    }

    @Override
    public int update(Risk risk) {
        return jdbcTemplate.update("UPDATE RISK SET risk_description=?, risk_level=? WHERE risk_id=?",
        new Object[] { risk.getRiskDescription(), risk.getRiskLevel()});
    }

    @Override
    public Risk findByRiskId(int id) {
        try {
            Risk risk = jdbcTemplate.queryForObject("SELECT * FROM RISKS WHERE risk_id=?",
                BeanPropertyRowMapper.newInstance(Risk.class), id);
            return risk;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        } 
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM RISKS WHERE risk_id=?", id);
    }

    @Override
    public List<Risk> findAll() {
        return jdbcTemplate.query("SELECT * from RISKS", BeanPropertyRowMapper.newInstance(Risk.class));
    }
    
}
