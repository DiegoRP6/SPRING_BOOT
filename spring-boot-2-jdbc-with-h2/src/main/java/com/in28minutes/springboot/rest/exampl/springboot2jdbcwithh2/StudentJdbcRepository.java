package com.in28minutes.springboot.rest.exampl.springboot2jdbcwithh2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentJdbcRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setName(rs.getString("name"));
            student.setPassportNumber(rs.getString("passport_number"));
            return student;
        }
    }

    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM student", new StudentRowMapper());
    }

    public Student findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE id=?", new Object[] { id },
                new BeanPropertyRowMapper<>(Student.class));
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("DELETE FROM student WHERE id = ?", new Object[] { id });
    }

    public int insert(Student student) {
        return jdbcTemplate.update("INSERT INTO student (id, name, passport_number) VALUES (?, ?, ?)",
            new Object[] { student.getId(), student.getName(), student.getPassportNumber() });
    }

    public int update(Student student) {
        return jdbcTemplate.update("UPDATE student SET name = ?, passport_number = ? WHERE id = ?",
            new Object[] { student.getName(), student.getPassportNumber(), student.getId() });
    }
    
    public List<Student> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM student WHERE name=?", new Object[] { name }, new StudentRowMapper());
    }
}