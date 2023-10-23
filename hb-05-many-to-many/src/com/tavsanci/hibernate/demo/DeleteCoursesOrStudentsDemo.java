package com.tavsanci.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tavsanci.hibernate.demo.entity.Course;
import com.tavsanci.hibernate.demo.entity.Instructor;
import com.tavsanci.hibernate.demo.entity.InstructorDetail;
import com.tavsanci.hibernate.demo.entity.Review;
import com.tavsanci.hibernate.demo.entity.Student;

public class DeleteCoursesOrStudentsDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {

			session.beginTransaction();
			

			Student student = session.get(Student.class, 18);
			


			session.delete(student);

			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		} 
	}

}
