package nerie.e_resources.non_schedule.services;

import java.util.List;

import nerie.e_resources.non_schedule.entity.Category;

public interface WordService {

	void addCategory(String category);

	void editCategory(Category category);

	List<Category> getListCategories();

}