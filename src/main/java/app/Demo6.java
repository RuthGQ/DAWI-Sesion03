package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo6 {
	public static void main(String[] args) {
		//Usuario u = new Usuario();
		/*u.setCod_usua(10);
		u.setNom_usua("Ruth");
		u.setApe_usua("Gutierrez");
		u.setUsr_usua("ruth01");
		u.setCla_usua("1234");
		u.setFna_usua("2004-02-19");
		u.setEst_usua(1);
		u.setIdtipo_usua(1);*/
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		TypedQuery<Usuario> consulta = em.createNamedQuery("UsuarioxTipo", Usuario.class);
		consulta.setParameter("tipo", 2);
		List<Usuario> lstUsuarios = consulta.getResultList();
		System.out.println("nro usuarios : " +lstUsuarios.size());
		for(Usuario u : lstUsuarios) {
			System.out.println("siguiente empleado : "+u);
		}
		
		}
}
