package com.yinn.ymall.product.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AttrGroupServiceTest {

    @Autowired
    AttrGroupService attrGroupService;

    @Test
    void listSpuAttrGroupDTOTest(){
        attrGroupService.listSpuAttrGroupDTOs(1L,1L);
    }
}