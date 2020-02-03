package com.thinhvp.masterdemo.persistence.repository;

import com.thinhvp.masterdemo.persistence.entity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope(value = "prototype")
public interface UserRepository extends JpaRepository<User, Integer> {

	@Override
	List<User> findAll();

	@Override
	List<User> findAll(Sort sort);

	@Override
	List<User> findAllById(Iterable<Integer> integers);

	@Override
	<S extends User> List<S> saveAll(Iterable<S> entities);

	@Override
	void flush();

	@Override
	<S extends User> S saveAndFlush(S entity);

	@Override
	void deleteInBatch(Iterable<User> entities);

	@Override
	void deleteAllInBatch();

	@Override
	User getOne(Integer integer);

	@Override
	<S extends User> List<S> findAll(Example<S> example);

	@Override
	<S extends User> List<S> findAll(Example<S> example, Sort sort);
}
