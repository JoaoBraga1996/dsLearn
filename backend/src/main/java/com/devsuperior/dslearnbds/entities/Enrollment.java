package com.devsuperior.dslearnbds.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.devsuperior.dslearnbds.entities.pk.EnrollmentPK;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment {

	
	
	// desse jeito que vc faz a chave primeira (atributo identificador) de uma classe de associação
	//como esse é um tipo criado por vc, tem um estruutra propria, vc tem que instanciar
	@EmbeddedId // quando é uma chave composto dese jeito e vc fez uma classe auxlixir, vc usa essa anotioon
	private EnrollmentPK id = new EnrollmentPK();
	
	//sempre que defenidir um timesttamp é um instant é bom colocar isso pra ter mais comtabilidade com o banco, pra garantir que seja utc
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant enrollMoment;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant refundMoment;
	private boolean available;
	private boolean onlyUpdate;

	@ManyToMany(mappedBy = "enrollmentsDone")
	private Set<Lesson> lessonsDone = new HashSet<>();
	
	@OneToMany(mappedBy = "enrollment")
	private List<Deliver> deliveries = new ArrayList<>();
	
	public Enrollment() {
	}

	public Enrollment(User user, Offer offer, Instant enrollMoment, Instant refundMoment, boolean available,
			boolean onlyUpdate) {
		super();
		id.setUser(user);
		id.setOffer(offer);
		this.enrollMoment = enrollMoment;
		this.refundMoment = refundMoment;
		this.available = available;
		this.onlyUpdate = onlyUpdate;
		
		//passou o user e offer
		
		//nao coloca o getno ENROLLMENtPK
		// e sim faz um get e set chamando o USER e o OFER
	}

	public User getStudent() {
		return id.getUser();
		
		//porque id é do tipo enrolmentpk é la dentro vc tem o uuario
		// fez isso na mão, nelio acho isso uma boa pratica.
	}
	
	public void setStudent(User user) {
		id.setUser(user);
	}

	public Offer getOffer() {
		return id.getOffer();
	}
	
	public void setOffer(Offer offer) {
		id.setOffer(offer);
	}
	
	public Instant getEnrollMoment() {
		return enrollMoment;
	}

	public void setEnrollMoment(Instant enrollMoment) {
		this.enrollMoment = enrollMoment;
	}

	public Instant getRefundMoment() {
		return refundMoment;
	}

	public void setRefundMoment(Instant refundMoment) {
		this.refundMoment = refundMoment;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isOnlyUpdate() {
		return onlyUpdate;
	}

	public void setOnlyUpdate(boolean onlyUpdate) {
		this.onlyUpdate = onlyUpdate;
	}

	public List<Deliver> getDeliveries() {
		return deliveries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollment other = (Enrollment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
