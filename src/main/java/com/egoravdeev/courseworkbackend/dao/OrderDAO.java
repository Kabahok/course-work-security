package com.egoravdeev.courseworkbackend.dao;

import com.egoravdeev.courseworkbackend.dao.impl.OrderDAOImpl;
import com.egoravdeev.courseworkbackend.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class OrderDAO implements OrderDAOImpl {

    private final DeliveryInformationDAO deliveryInformationDAO;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public OrderDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate, DeliveryInformationDAO deliveryInformationDAO) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.deliveryInformationDAO = deliveryInformationDAO;
    }

    @Override
    public void addOrder(Order order) {
        String sql = "insert into public.orders (order_date, status, delivery_date, vehicle) values(:orderDate, :status, :deliveryDate, :vehicle)";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> params = new HashMap<>();

        try {
            params.put("orderDate", dateFormat.parse(order.getOrderDate()));
            params.put("status", order.getStatus());
            params.put("deliveryDate", dateFormat.parse(order.getDeliveryDate()));
            params.put("vehicle", order.getVehicle());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        namedParameterJdbcTemplate.update(sql, params);


        SqlParameterSource par = new MapSqlParameterSource();
        namedParameterJdbcTemplate.queryForObject("SELECT MAX(order_id) FROM public.orders", par, Integer.class);
        deliveryInformationDAO.addTimeline(order.getDeliveryDate(), namedParameterJdbcTemplate.queryForObject("SELECT MAX(order_id) FROM public.orders", par, Integer.class));
    }


    @Override
    public List<Order> findAll() {
        String query = "select * from public.orders";
        return namedParameterJdbcTemplate.query(query, (rs, rowNum) -> {
            int orderId = rs.getInt("order_id");
            String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("order_date"));
            int status = rs.getInt("status");
            String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("delivery_date"));
            String vehicle = rs.getString("vehicle");


            return new Order(orderId, orderDate, status, deliveryDate, vehicle);
        });
    }

    @Override
    public Optional<Order> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findByVehicle(String vehicleType) {
        String query = "select * from public.orders where vehicle = '" + vehicleType + "'";


        return namedParameterJdbcTemplate.query(query, (rs, rowNum) -> {
            int orderId = rs.getInt("order_id");
            String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("order_date"));
            int status = rs.getInt("status");
            String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("delivery_date"));
            String vehicle = rs.getString("vehicle");


            return new Order(orderId, orderDate, status, deliveryDate, vehicle);
        });
    }
}
