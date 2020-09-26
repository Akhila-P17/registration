package org.tool.student;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;





@Entity
@Table(name = "student_details")
public class StudentEntity {
	
	//sid,name,phone,email,password,registrationStatus	
	
	@Id
	@Column(name = "student_id")
	private Long student_id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private long phone;
	
	@Column(name = "password")
	private String password;
	
	
	

	@ManyToMany(targetEntity = SubjectEntity.class, cascade = CascadeType.ALL)
	@JoinTable(
	        name = "students_and_subjects",
	        joinColumns = {
	            @JoinColumn(name ="student_id")
	        },
	        inverseJoinColumns = {
	            @JoinColumn(name = "subject_id")
	        }
	    )
	 @JsonBackReference
	private List<SubjectEntity> subjectList = new ArrayList<>();
	

	
	
	
	
	public StudentEntity() {
		
	}
	

	
	




	
	public StudentEntity(Long student_id, String name, String email, long phone, String password,
			List<SubjectEntity> subject) {
		super();
		this.student_id = student_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.subjectList = subject;
	}









	public Long getStudent_id() {
		return student_id;
	}





	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}





	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
	
	public List<SubjectEntity> getSubject() {
		return subjectList;
	}









	public void setSubject(List<SubjectEntity> subject) {
		this.subjectList = subject;
	}





	public void addSubject(SubjectEntity subject) {
		this.subjectList.add(subject);
	}
	
	
	
	
	public void removeSubject(SubjectEntity subject) {
		this.subjectList.remove(subject);
	}







	@Override
	public String toString() {
		
		return "StudentEntity [student_id=" + student_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", password=" + password + ", subjectList=" + subjectList + "]";
	}
	
	
	
	
	
	
	

}
