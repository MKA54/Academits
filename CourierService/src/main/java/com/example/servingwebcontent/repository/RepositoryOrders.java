package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.domain.OrderData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositoryOrders extends CrudRepository<OrderData, Integer> {
    List<OrderData> findByOrderNumber(String orderNumber);
}