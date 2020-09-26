package org.tool.student;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "students_subjects")
public class SubjectEntity {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	
	
	@ManyToMany(mappedBy = "subjectList", targetEntity = StudentEntity.class, cascade =  CascadeType.ALL )
	@JsonManagedReference
    private List<StudentEntity> studentList= new ArrayList<>();

	
	
	
	public SubjectEntity() {
	}
	
	
	public SubjectEntity(String name) {
		this.name = name;
	}

	

	


	public SubjectEntity(String id, String name, List<StudentEntity> studentList) {
		super();
		this.id = id;
		this.name = name;
		this.studentList = studentList;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	



	public List<StudentEntity> getStudentList() {
		return studentList;
	}


	public void setStudentList(List<StudentEntity> studentList) {
		this.studentList = studentList;
	}


	@Override
	public String toString() {
		return String.format("SubjectEntity [id=%s, name=%s, studentList=%s]", id, name, studentList);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	

}
