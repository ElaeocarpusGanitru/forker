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
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Repository("role")
@Entity
@Table(name="role")
public class Role {
	private Long id;
	private String name;
	private String description;
	private Set<User> users = new HashSet<User>();	//多对多
	private Set<Privilege> privileges = new HashSet<Privilege>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@ManyToMany(targetEntity=oa.domain.User.class, fetch=FetchType.EAGER, mappedBy="roles")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@JoinTable(name="role_privilege",joinColumns={@JoinColumn(name="roleId", referencedColumnName="id")}, 
			inverseJoinColumns={@JoinColumn(name="privilegeId", referencedColumnName="id")})
	@ManyToMany(targetEntity=oa.domain.Privilege.class, fetch=FetchType.EAGER)
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
	
}
