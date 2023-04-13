package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo4 {
	public static void main(String[] args) {
		Usuario u = new Usuario();
		//u.setCod_usua();
		/*u.setNom_usua("Ruth");
		u.setApe_usua("Gutierrez");
		u.setUsr_usua("ruth01");
		u.setCla_usua("1234");
		u.setFna_usua("2004-02-19");
		u.setEst_usua(1);
		u.setIdtipo_usua(1);*/
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		System.out.println("Consultando..");
		
		u = em.find(Usuario.class, 10);
		
		if(u==null) {
			System.out.println("No existe");
		}else {
			System.out.println(u);
		}
		
		}
}
