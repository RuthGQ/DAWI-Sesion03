package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Proveedor;

public class Demo1_3 {
	public static void main(String[] args) {
		Proveedor pr = new Proveedor();
		pr.setIdproveedor(4);
		pr.setNombre_rs("Minsa");
		pr.setTelefono("999888333");
		pr.setEmail("min@gmail.com");
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(pr);
		em.getTransaction().commit();
		System.out.println("Registrando...");
	}
}
