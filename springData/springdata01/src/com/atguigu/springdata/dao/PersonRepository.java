package com.atguigu.springdata.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.atguigu.springdata.entities.Person;

//@RepositoryDefinition(domainClass=Person.class,idClass=Integer.class)
//public interface PersonRepository extends Repository<Person,Integer>
//public interface PersonRepository extends CrudRepository<Person,Integer>
//public interface PersonRepository extends PagingAndSortingRepository<Person,Integer>
public interface PersonRepository extends JpaRepository<Person,Integer>,JpaSpecificationExecutor<Person>,PersonDao
{
	public Person getByName(String name);
	
	public Person findByName(String name);
	
	public Person readByName(String name);
	
	public List<Person> getByNameLikeAndBirthBetweenAndEmail(String name,Date startDate,Date endDate,String email);
	
	public List<Person> getByAddress_CityLike(String city);
	
	@Query(value="select p from Person p where p.name=? and p.email=?")
	public List<Person> getByNameAndEmail(String name,String email);
	
	@Query(value="select email from tbl_person where name=? and email=?",nativeQuery=true)
	public List<String> getByNameAndEmail2(String name,String email);
	
	@Modifying
	@Query(value="update Person p set p.email=? where p.id=?")
	public int updateEmail(String email,Integer id);
	
	
	
	
}
