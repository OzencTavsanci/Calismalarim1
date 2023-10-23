package com.tavsanci.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tavsanci.hibernate.demo.entity.Course;
import com.tavsanci.hibernate.demo.entity.Instructor;
import com.tavsanci.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure()
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			int tempId= 2;
			Instructor tempInstructor = session.get(Instructor.class,tempId);
//			System.out.println(tempInstructor);
			System.out.println(tempInstructor.getCourses());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
