package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo7 {
	static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	static EntityManager em = fabrica.createEntityManager();
	//Realiza un listado de los usuarios 	
	public static void main(String[] args) {
		//Obtener un listado de los Usuarios
		List<Usuario> lstUsuarios = em.createQuery("select u from Usuario u", Usuario.class).getResultList();
		System.out.println("hay " +lstUsuarios.size() + " usuarios");
		for(Usuario u : lstUsuarios) {
			System.out.println("Código : " +u.getCod_usua());
			System.out.println("Nombre : " +u.getNom_usua());
			System.out.println("Apellido : " +u.getApe_usua());
			System.out.println("Tipo : " +u.getIdtipo_usua());
			System.out.println("");
			
		}
	}
}
