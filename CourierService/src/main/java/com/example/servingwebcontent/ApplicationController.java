package com.example.servingwebcontent;

import com.example.servingwebcontent.domain.OrderData;
import com.example.servingwebcontent.repository.RepositoryOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ApplicationController {
    @Autowired
    private RepositoryOrders repositoryOrders;

    @GetMapping("/operator")
    public String operator(Map<String, Object> model) {
        Iterable<OrderData> ordersData = repositoryOrders.findAll();
        model.put("ordersData", ordersData);

        return "operator";
    }

    @GetMapping
    public String courier() {
        return "courier";
    }

    @PostMapping
    public String add(@RequestParam String orderNumber) {
        if (orderNumber == null || orderNumber.isEmpty()) {
            return "courier";
        }

        OrderData orderData = new OrderData(orderNumber);
        repositoryOrders.save(orderData);

        return "courier";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<OrderData> ordersData;

        if (filter != null && !filter.isEmpty()) {
            ordersData = repositoryOrders.findByOrderNumber(filter);
        } else {
            ordersData = repositoryOrders.findAll();
        }

        model.put("ordersData", ordersData);

        return "operator";
    }
}