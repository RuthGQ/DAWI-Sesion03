package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Productos;

public class Demo2_2 {
	public static void main(String[] args) {
		Productos p = new Productos();
		p.setId_prod("P0021");
		p.setDes_prod("Fideos");
		p.setStk_prod(5);
		p.setPre_prod(3.00);
		p.setIdcategoria(1);
		p.setEst_prod(1);
		p.setIdproveedor(1);
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		System.out.println("Actualizando..");
	}
}
