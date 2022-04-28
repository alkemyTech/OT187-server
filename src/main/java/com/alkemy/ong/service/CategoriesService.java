
package com.alkemy.ong.service;


import com.alkemy.ong.dto.CategoriesCreationDto;
import com.alkemy.ong.response.dto.CategoryResponseDto;
import java.util.List;
import java.util.Locale.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriesService {

	CategoryResponseDto findById(Long id);

	List<CategoryResponseDto> findAll();

	CategoryResponseDto createCategory(CategoriesCreationDto category);

	String deleteById(Long id);

	Category findCategoriesById(Long id);
	
	Page<CategoryResponseDto> findAllWithNameInPage(Pageable pageable);

	CategoryResponseDto updateCategoryById(Long id, CategoriesCreationDto dto);

}