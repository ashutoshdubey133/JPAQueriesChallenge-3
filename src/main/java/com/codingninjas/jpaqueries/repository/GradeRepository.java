package com.codingninjas.jpaqueries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codingninjas.jpaqueries.entities.Grade;
import com.codingninjas.jpaqueries.entities.Student;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

	@Query(value="SELECT AVG(g.marks) FROM Grade g where g.student_id=:student", nativeQuery=true)
	public double getAverageGradesOfStudent(@Param("student") int student);

}
