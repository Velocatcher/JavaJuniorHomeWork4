package org.example;


/*Задание: Настройте связь между вашим приложением и базой данных MySQL
 с использованием Hibernate. Создайте несколько объектов Person и сохраните их в базу данных.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.security.auth.login.Configuration;

public class Main {

    public static void main(String[] args) {


//       try(SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Student.class)
//                .buildSessionFactory()){
//            Session session = sessionFactory.getCurrentSession();
//        }
//        catch (Exception e){
//           e.printStackTrace();
//        }
        try(final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() /* configures settings from hibernate.cfg.xml */
                .build()) {
            SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

//            Создание сессии
            Session session = sessionFactory.openSession();

//            Начало транзакции
            session.beginTransaction();

//            Создание объекта
            Student student1= Student.create();
            session.save(student1);
            Student student2 = Student.create();
            session.save(student2);
            Student student3 = Student.create();
            session.save(student3);
            System.out.println("Object student save");

//            Чтение объекта
        Student retrievedStudent1 = session.get(Student.class, student1.getId());
            Student retrievedStudent2 = session.get(Student.class, student2.getId());
            Student retrievedStudent3 = session.get(Student.class, student3.getId());
            System.out.println("Object student retrieved successfully");
            System.out.println("Retrieved student " + retrievedStudent1);
            System.out.println("Retrieved student " + retrievedStudent2);
            System.out.println("Retrieved student " + retrievedStudent3);
//            Обновление
            retrievedStudent1.updateName();
            retrievedStudent1.updateAge();
            session.update(retrievedStudent1);
            System.out.println("Object student update successfully");

//            Удаление
            session.delete(retrievedStudent2);
            System.out.println("Object student " + retrievedStudent2 + " delete successfully");
            session.getTransaction().commit();

        }
        catch (Exception e){
           e.printStackTrace();
        }
    }
}