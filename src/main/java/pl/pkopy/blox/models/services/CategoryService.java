package pl.pkopy.blox.models.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.pkopy.blox.models.CategoryEntity;
import pl.pkopy.blox.models.repositories.CategoryRepository;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryService(){

    }

    public void addCategory(String name) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);

        categoryRepository.save(categoryEntity);

    }
}
