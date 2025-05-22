package com.codeWithMadhuri.blog.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeWithMadhuri.blog.entities.Category;

//Do not import java.util.Locale.Category 
//see carefully  import com.codeWithMadhuri.blog.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
