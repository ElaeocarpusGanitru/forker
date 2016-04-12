package oa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Repository("department")
@Entity
@Table(name="department")
public class Department {
	private Long id;
	private String name;
	private String description;
	
	private Department parent;	//多对一
	private Set<Department> children = new HashSet<Department>(); //一对多
	private Set<User> users = new HashSet<User>();	//一对多
	
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

	//多对一映射
	@JoinColumn(name="parentId")
	@ManyToOne(targetEntity=oa.domain.Department.class)
	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	//一对多映射
	@OneToMany(targetEntity=oa.domain.Department.class,mappedBy="parent")
	public Set<Department> getChildren() {
		return children;
	}

	public void setChildren(Set<Department> children) {
		this.children = children;
	}

	//一对多映射
	@OneToMany(targetEntity=oa.domain.User.class, mappedBy="department")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
