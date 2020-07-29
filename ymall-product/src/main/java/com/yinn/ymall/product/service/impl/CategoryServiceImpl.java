package com.yinn.ymall.product.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yinn.ymall.product.constant.RedisConstant;
import com.yinn.ymall.product.dao.CategoryDao;
import com.yinn.ymall.product.entity.Category;
import com.yinn.ymall.product.service.CategoryService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

    @Cacheable(cacheNames = RedisConstant.CategoryCache.CACHE_NAME, key = RedisConstant.CategoryCache.CACHE_KEY_TREE)
    @Override
    public List<Category> listWithTree() {
        List<Category> categoryList=list();
        // @formatter:off
        return list().stream()
                        .filter(e->e.getParentId()==0)//找到一级菜单
                        .sorted(Comparator.comparing(Category::getSort))//排序
                        .map(e->e.setChildren(getChildren(e,categoryList)))//设置其子菜单
                        .collect(Collectors.toList());
        // @formatter:on
    }

    private List<Category> getChildren(Category root,List<Category> categoryList) {
        // @formatter:off
        return categoryList
                        .stream()
                        .filter(e->e.getParentId()==root.getId())
                        .sorted(Comparator.comparing(Category::getSort))
                        .map(e->e.setChildren(getChildren(e,categoryList)))//设置其子菜单
                        .collect(Collectors.toList());
        // @formatter:on
    }

    @Override
    public List<Long> getCategoryPath(Long categoryId) {
        List<Long> path = new ArrayList<>();
        var category = getById(categoryId);
        while (category != null) {
            path.add(category.getId());
            category = getById(category.getParentId());
        }
        Collections.reverse(path);
        return path;
    }

    @CacheEvict(cacheNames = RedisConstant.CategoryCache.CACHE_NAME, key = RedisConstant.CategoryCache.CACHE_KEY_TREE)
    @Override
    public boolean save(Category entity) {
        return super.save(entity);
    }

    @CacheEvict(cacheNames = RedisConstant.CategoryCache.CACHE_NAME, key = RedisConstant.CategoryCache.CACHE_KEY_TREE)
    @Override
    public boolean updateById(Category entity) {
        return super.updateById(entity);
    }

    @CacheEvict(cacheNames = RedisConstant.CategoryCache.CACHE_NAME, key = RedisConstant.CategoryCache.CACHE_KEY_TREE)
    @Override
    public boolean updateBatchById(Collection<Category> entityList) {
        return super.updateBatchById(entityList);
    }

    @CacheEvict(cacheNames = RedisConstant.CategoryCache.CACHE_NAME, key = RedisConstant.CategoryCache.CACHE_KEY_TREE)
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @CacheEvict(cacheNames = RedisConstant.CategoryCache.CACHE_NAME, key = RedisConstant.CategoryCache.CACHE_KEY_TREE)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

}