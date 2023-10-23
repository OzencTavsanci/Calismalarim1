package com.tavsanci.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tavsanci.hibernate.demo.entity.Course;
import com.tavsanci.hibernate.demo.entity.Instructor;
import com.tavsanci.hibernate.demo.entity.InstructorDetail;
import com.tavsanci.hibernate.demo.entity.Student;

public class CreateCourseDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")	
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {

			
//			InstructorDetail tempInstructorDetail = new InstructorDetail("wwww.youtube","playing ll");
//			Instructor tempInstructor = new Instructor("cip", "sal", "npuysal@hotmail.com");

//			tempInstructor.setInstructorDetail(tempInstructorDetail);

			session.beginTransaction();
			Instructor tempInstructor = session.get(Instructor.class, 2);
			

			Course tempCourse1 = new Course("Stoper");
			Course tempCourse2 = new Course("10");
			Course tempCourse3 = new Course("Santrofor");

			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			tempInstructor.add(tempCourse3);
//			Instructor tempInstructor = session.get(Instructor.class, 2);
//			session.save(tempInstructor);
	
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);

			
			session.getTransaction().commit();
//			System.out.println(tempInstructor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
