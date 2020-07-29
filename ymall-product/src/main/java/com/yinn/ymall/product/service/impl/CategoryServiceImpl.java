package com.yinn.ymall.product.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yinn.ymall.product.dao.CategoryDao;
import com.yinn.ymall.product.entity.Category;
import com.yinn.ymall.product.service.CategoryService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

    private List<Category> categoryDTOList;

    @Cacheable(cacheNames = "category",sync = true,key = "#root.methodName")
    @Override
    public List<Category> listWithTree() {
        return list().stream()
                        .filter(e->e.getParentId()==0)//找到一级菜单
                        .sorted(Comparator.comparing(Category::getSort))//排序
                        .map(e->e.setChildren(getChildren(e)))//设置其子菜单
                        .collect(Collectors.toList());
    }

    private List<Category> getChildren(Category root){
        return categoryDTOList.stream()
                                            .filter(e->e.getParentId()==root.getId())
                                            .sorted(Comparator.comparing(Category::getSort))
                                            .map(e->e.setChildren(getChildren(e)))//设置其子菜单
                                            .collect(Collectors.toList());
    }

    @Override
    public List<Long> getCategoryPath(Long categoryId) {
        List<Long>path=new ArrayList<>();
        var category=getById(categoryId);
        while(category!=null){
            path.add(category.getId());
            category=getById(category.getParentId());
        }
        Collections.reverse(path);
        return path;
    }
}