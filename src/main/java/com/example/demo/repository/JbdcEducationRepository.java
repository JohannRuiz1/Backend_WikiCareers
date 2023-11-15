package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Education;

@Repository
public class JbdcEducationRepository implements EducationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Education education) {
        return jdbcTemplate.update(
                "INSERT INTO EDUCATION (career_id, education_level, years_of_schooling, education_description) VALUES(?,?,?,?)",
                new Object[] { education.getCareerId(), education.getEducationLevel(), education.getYearsOfSchooling(),
                        education.getEducationDescription() });
    }

    @Override
    public int update(Education education) {
        return jdbcTemplate.update(
                "UPDATE EDUCATION SET education_level=?, years_of_schooling=?, education_description=? WHERE education_id=?",
                new Object[] { education.getEducationLevel(), education.getYearsOfSchooling(),
                        education.getEducationDescription() });
    }

    @Override
    public Education findByEducationId(int id) {
        try {
            Education education = jdbcTemplate.queryForObject("SELECT * FROM EDUCATION WHERE education_id=?",
                    BeanPropertyRowMapper.newInstance(Education.class), id);
            return education;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public Education findByCareerId(int id) {
        try {
            Education education = jdbcTemplate.queryForObject("SELECT * FROM EDUCATION WHERE career_id=?",
                    BeanPropertyRowMapper.newInstance(Education.class), id);
            return education;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM EDUCATION WHERE education_id=?", id);
    }

    @Override
    public List<Education> findAll() {
        return jdbcTemplate.query("SELECT * from EDUCATION", BeanPropertyRowMapper.newInstance(Education.class));
    }

}
