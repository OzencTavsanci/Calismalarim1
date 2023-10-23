package com.tavsanci.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tavsanci.hibernate.demo.entity.Instructor;
import com.tavsanci.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetail {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure()
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			int tempId =1;
			
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, tempId);
			//remove bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(tempInstructorDetail);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
			
		}
	}

}
