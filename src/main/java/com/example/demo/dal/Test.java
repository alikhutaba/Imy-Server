package com.example.demo.dal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.example.demo.data.ArrayToJsonStringConveter;


@Entity
@Table(name = "test")
public class Test {
	
	
	
	private Long id;
	private List<Double> testlist;
	
	
	
	
	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	public Test(Long id, List<Double> testlist) {
		super();
		this.id = id;
		this.testlist = testlist;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	
	@Convert(converter = ArrayToJsonStringConveter.class)
	@Lob
	@Column(name="CONCENTRATION")
	public List<Double> getTestlist() {
		return testlist;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTestlist(List<Double> testlist) {
		this.testlist = testlist;
	}
	
	
	
	

}
