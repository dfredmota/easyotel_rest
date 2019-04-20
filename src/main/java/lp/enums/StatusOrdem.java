package lp.enums;

public enum StatusOrdem {
	
	CANCELADA(1,"CANCELADA"),
	AUTORIZADA(2,"AUTORIZADA"),
	REINICIADA(3,"REINICIADA"),
	NOVA(4,"NOVA"),
	PENDENTE(5,"PENDENTE"),
	INICIADA(6,"INICIADA");
	
	
	private int id;
	private String descricao;
	
	private StatusOrdem(int id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
