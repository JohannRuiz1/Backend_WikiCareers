package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Career;

@Repository
public class JbdcCareerRepository implements CareerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Career career) {
        return jdbcTemplate.update("INSERT INTO Careers (title, description, pay_range_low, pay_range_high, risk_level) VALUES(?,?,?,?,?)",
        new Object[] {career.getTitle(), career.getDescription(), career.getPay_range_low(), career.getPay_range_high(), career.getRisk_level()});
    }

    @Override
    public int update(Career career) {
        return jdbcTemplate.update("UPDATE Careers SET title=?, description=?, pay_range_low=?, pay_range_high=?, risk_level=? WHERE id=?",
        new Object[] { career.getTitle(), career.getDescription(), career.getPay_range_low(), career.getPay_range_high(), career.getRisk_level(), career.getCareer_id()});
   }

    @Override
    public Career findById(Long id) {
        try {
            Career career = jdbcTemplate.queryForObject("SELECT * FROM careers WHERE id=?",
                BeanPropertyRowMapper.newInstance(Career.class), id);
            return career;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        } 
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM careers WHERE id=?", id);
    }

    @Override
    public List<Career> findAll() {
        return jdbcTemplate.query("SELECT * from careers", BeanPropertyRowMapper.newInstance(Career.class));
    }

    @Override
    public List<Career> findByTitleContaining(String title) {
      String q = "SELECT * from careers WHERE title ILIKE '%" + title + "%'";
  
      return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Career.class));
    }
    
}
