package com.yinn.ymall.search.service;

import com.yinn.ymall.search.dto.SearchParamDTO;
import com.yinn.ymall.search.dto.SearchResultDTO;

public interface SearchService {
    SearchResultDTO search(SearchParamDTO searchParamDTO);
}