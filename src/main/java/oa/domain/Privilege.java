package oa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Repository("privilege")
@Entity
@Table(name="privilege")
public class Privilege {

	
	private Long id;
	private String url;	//权限对应的URL地址
	private String name; //权限名称
	
	private Set<Role> roles = new HashSet<Role>();
	private Privilege parent;	//上级权限
	private Set<Privilege> children = new HashSet<Privilege>(); //子权限
	
	public Privilege() {
		super();
	}
	public Privilege(String name, String url,  Privilege parent) {
		super();
		this.url = url;
		this.name = name;
		this.parent = parent;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	@ManyToMany(targetEntity=oa.domain.Role.class, fetch=FetchType.EAGER, mappedBy="privileges")
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JoinColumn(name="parentId")
	@ManyToOne(targetEntity=oa.domain.Privilege.class, fetch=FetchType.EAGER)
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	
	@OneToMany(targetEntity=oa.domain.Privilege.class, mappedBy="parent", fetch=FetchType.EAGER)
	public Set<Privilege> getChildren() {
		return children;
	}
	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}
}
