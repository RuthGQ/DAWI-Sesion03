package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_categorias")
public class Categorias {
	@Id
	private int idcategoria;
	private String descripcion;
}
