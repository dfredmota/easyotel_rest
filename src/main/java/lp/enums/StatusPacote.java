package lp.enums;

public enum StatusPacote {
	
	CONFIRMADO(1,"Confirmado"),
	CANCELADO(2,"Cancelado"),
	PENDENTE(3,"Pendente"),
	BLOQUEADO(4,"Bloqueado");
	
	
	private int id;
	private String descricao;
	
	private StatusPacote(int id, String descricao){
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
