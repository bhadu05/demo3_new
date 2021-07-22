package com.ex.demo3.Pagination;

import com.ex.demo3.mysql.customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaginationRepository extends PagingAndSortingRepository<customer,Integer> {
}
