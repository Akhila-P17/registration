package org.tool.student;


import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tool.teacher.TeacSubjectEntity;
import org.tool.teacher.TeacSubjectRepository;
import org.tool.teacher.TeacherRepository;


@RestController
public class StudentController {
	
	
	
	@Autowired
	private StudentRepository tRepo;
	
	@Autowired
	private SubjectRepository sRepo;
	
	@Autowired
	private ResponseMessage resp;
	
	@Autowired
	private TeacSubjectRepository teacSubRepo;
	
	
	
	
	@GetMapping("/register-student")
	public String check() {
		
		System.out.println("get req");
		return "Got get!!";
	}
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST,path = "/register/student")
//	@PostMapping("")
	public ResponseMessage registerStudent(@RequestBody StudentEntity student ) {
		

		if(! tRepo.existsStudentEntityByEmail(student.getEmail())) {
		
		List<SubjectEntity>  newList = new ArrayList<SubjectEntity>();
		newList.addAll(student.getSubject());
		student.getSubject().clear();
		
	
		
		
		for (int i = 0; i < newList.size(); i++) {
			
			TeacSubjectEntity teacherSubject = new TeacSubjectEntity();
			SubjectEntity studentSubject = new SubjectEntity();
			
			if (  teacSubRepo.existsTeacSubjectEntityById( newList.get(i).getId()) ) {
				
				teacherSubject = teacSubRepo.findTeacSubjectEntityById( newList.get(i).getId() );
				
				studentSubject.setName( teacherSubject.getName() );
				studentSubject.setId( newList.get(i).getId() );
				
				student.getSubject().add(studentSubject);
			}
		}
		

			student.getSubject().clear();
			student.setSubject(newList);
			
			student.setPassword(RandomStringUtils.random(10, true, true));
			
			MailService.send(student.getEmail(), "Registration Successful ", " Your user_id : " + student.getEmail() +  "  password : " + student.getPassword());
			
			tRepo.save(student);
			
			
			resp.setStatus("success");
			resp.setMessage("Your User ID and Password are sent to your mail. Please use them to login and change password for successful registration.");
			return  resp;
			
		}else {
			
			resp.setStatus("failure");
			resp.setMessage("User already registered. Please register with a different email or click forgot password button to reset your password.");
			return resp;
		}
	
		
		
	}
	
	
	 
	
	
	

}
