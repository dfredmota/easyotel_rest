package lp.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "grupo_cardapios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GrupoCardapio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String nome;
	
	@JsonIgnore
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
    protected Date createdDate; 
	
	
	@JsonIgnore
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date modified;
	
	@PrePersist
    public void prePersist() {
		createdDate = new Date();
		modified = new Date();
    }
 
    @PreUpdate
    public void preUpdate() {
    	modified = new Date();
       
    }

}
