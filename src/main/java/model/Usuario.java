package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_usuarios")
@NamedQuery(name = "UsuarioxTipo", query = "SELECT u FROM Usuario u where u.idtipo_usua = :tipo")
@Data
public class Usuario {
	@Id
	private int cod_usua;
	private String nom_usua;
	private String ape_usua;
	private String usr_usua;
	private String cla_usua;
	private String fna_usua;
	@Column(name="idtipo")
	private int idtipo_usua;
	private int est_usua;
	
	@ManyToOne
	@JoinColumn(name ="idtipo_usua", insertable = false, updatable = false)
	Tipo objTipo;
}
