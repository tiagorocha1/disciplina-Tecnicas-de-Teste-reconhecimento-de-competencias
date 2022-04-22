package br.edu.ifpb.dac.trainee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.trainee.model.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long> {

}
