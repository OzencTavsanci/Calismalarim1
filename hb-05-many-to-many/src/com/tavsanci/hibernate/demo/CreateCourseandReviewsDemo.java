package com.tavsanci.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tavsanci.hibernate.demo.entity.Course;
import com.tavsanci.hibernate.demo.entity.Instructor;
import com.tavsanci.hibernate.demo.entity.InstructorDetail;
import com.tavsanci.hibernate.demo.entity.Review;

public class CreateCourseandReviewsDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
			Course tempCourse1 = new Course("Santrfafor2");
			Course tempCourse2 = new Course("10f numara");
			
			Review review1 = new Review("Santraforf da kral");
			Review review2 = new Review("10 numaradaf taçsiz kral");
			tempCourse1.addReview(review1);
			tempCourse1.addReview(review2);
			session.beginTransaction();
			



			session.save(tempCourse1);
			session.save(tempCourse2);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		} 
	}

}
