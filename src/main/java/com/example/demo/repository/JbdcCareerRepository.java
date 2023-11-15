package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Career;
import com.example.demo.models.CareerInfo;

@Repository
public class JbdcCareerRepository implements CareerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Career career) {
        return jdbcTemplate.update(
                "INSERT INTO Careers (title, description, pay_range_low, pay_range_high, risk_level) VALUES(?,?,?,?,?)",
                new Object[] { career.getTitle(), career.getDescription(), career.getPay_range_low(),
                        career.getPay_range_high(), career.getRisk_level() });
    }

    @Override
    public int update(Career career) {
        return jdbcTemplate.update(
                "UPDATE Careers SET title=?, description=?, pay_range_low=?, pay_range_high=?, risk_level=? WHERE career_id=?",
                new Object[] { career.getTitle(), career.getDescription(), career.getPay_range_low(),
                        career.getPay_range_high(), career.getRisk_level(), career.getCareer_id() });
    }

    @Override
    public Career findById(int id) {
        try {
            Career career = jdbcTemplate.queryForObject("SELECT * FROM careers WHERE career_id=?",
                    BeanPropertyRowMapper.newInstance(Career.class), id);
            return career;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM CAREERS WHERE career_id=?", id);
    }

    @Override
    public List<Career> findAll() {
        return jdbcTemplate.query("SELECT * from CAREERS", BeanPropertyRowMapper.newInstance(Career.class));
    }

    @Override
    public List<Career> findByTitleContaining(String title) {
        String q = "SELECT * from CAREERS WHERE title ILIKE '%" + title + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Career.class));
    }

    @Override
    public Career findByName(String name) {
        String q = "SELECT * FROM CAREERS WHERE title = '" + name + "'";

        List<Career> result = jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Career.class));

        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

    public CareerInfo findCareerInfoByTitle(String title) {
        String q = "SELECT C.title, C.description, C.pay_range_low, C.pay_range_high, E.education_level, E.years_of_schooling, E.education_description, R.risk_description, R.risk_level "+
                "FROM Careers C " +
                "LEFT JOIN Education E ON C.career_id = E.career_id " +
                "LEFT JOIN Risks R ON C.career_id = R.career_id " +
                "WHERE C.title = '" + title + "'";

        List<CareerInfo> result = jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(CareerInfo.class));
        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

}
