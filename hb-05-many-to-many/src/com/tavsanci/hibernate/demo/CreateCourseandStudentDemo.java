package com.tavsanci.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tavsanci.hibernate.demo.entity.Course;
import com.tavsanci.hibernate.demo.entity.Instructor;
import com.tavsanci.hibernate.demo.entity.InstructorDetail;
import com.tavsanci.hibernate.demo.entity.Review;
import com.tavsanci.hibernate.demo.entity.Student;

public class CreateCourseandStudentDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {

//			Course tempCourse1 = new Course("Matematik");
//			Course tempCourse2 = new Course("Kimya");
//			Course tempCourse3 = new Course("Türkce");
//			Course tempCourse4 = new Course("Sosyal Bilgiler");
//			Course tempCourse5 = new Course("Beden Egitimi");
//			
//			Student tempStudent1 = new Student("OZenc","Tavsanci","ozenctavsanci@hotmail.com");
//			Student tempStudent2 = new Student("Merhaba","Asker","askertavsanci@hotmail.com");
//			Student tempStudent3 = new Student("Osman","Kabasakl","ogagaggi@hotmail.com");
//			Student tempStudent4 = new Student("Naber","ASkeri","wwwwweewwnci@hotmail.com");
			
			session.beginTransaction();
			
			Course tempCourse1 = new Course("Biyoloji");
			Course tempCourse2 = new Course("Resim Dersi");
			Student student = session.get(Student.class,18);
			Course course = session.get(Course.class, 30);
			
			student.addCourse(tempCourse1);
			student.addCourse(tempCourse2);
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			System.out.println("\n\n18 no'lu öğrencinin aldığı kurlar: "+student.getCourses());
			
			System.out.println("\n\n30 no'lu kursu alan öğrenciler: "+course.getStudents());

			
////			tempStudent1.addCourse(tempCourse5);
////			tempStudent1.addCourse(tempCourse4);
////			tempStudent1.addCourse(tempCourse1);
////			tempStudent3.addCourse(tempCourse3);
////			tempStudent3.addCourse(tempCourse2);
////			tempStudent3.addCourse(tempCourse4);
////			tempStudent2.addCourse(tempCourse5);
////			tempStudent4.addCourse(tempCourse5);
//			
//			tempCourse1.addStudent(tempStudent1);
//			tempCourse2.addStudent(tempStudent3);
//			tempCourse3.addStudent(tempStudent3);
//			tempCourse4.addStudent(tempStudent3);
//			tempCourse4.addStudent(tempStudent1);
//			tempCourse5.addStudent(tempStudent1);
//			tempCourse5.addStudent(tempStudent2);
//			tempCourse5.addStudent(tempStudent4);
//
//
//			session.save(tempCourse1);
//			session.save(tempCourse2);
//			session.save(tempCourse3);
//			session.save(tempCourse4);
//			session.save(tempCourse5);
//			
//			session.save(tempStudent1);
//			session.save(tempStudent2);
//			session.save(tempStudent3);
//			session.save(tempStudent4);
//			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		} 
	}

}
