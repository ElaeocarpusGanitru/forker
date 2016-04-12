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
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

@Repository("user")
@Entity
@Table(name="user")
public class User {
	private Long id;
	private String loginName;
	private String password;
	private String name;
	private String gender;
	private String phone;
	private String email;
	private String description;
	
	private Set<Role> roles = new HashSet<Role>(); //多对多
	private Department department; //多对一
	
	
	public boolean hasPrivilegeByName(String name)
	{
		if ("admin".equals(loginName))
		{
			return true;
		}
		
		for (Role role : roles)
		{
			for (Privilege p : role.getPrivileges())
			{
				if (p.getName().equals(name))
				{
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	//多对多
	@JoinTable(name="user_role", joinColumns={@JoinColumn(name="userId", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="roleId", referencedColumnName="id")})
	@ManyToMany(targetEntity=oa.domain.Role.class, fetch=FetchType.EAGER)
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	//多对一
	@JoinColumn(name="departmentId")
	@ManyToOne(targetEntity=oa.domain.Department.class)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
