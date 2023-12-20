package com.egoravdeev.courseworkbackend.dao;

import com.egoravdeev.courseworkbackend.dao.impl.ShipmentDAOImpl;
import com.egoravdeev.courseworkbackend.model.Shipment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShipmentDAO implements ShipmentDAOImpl {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public ShipmentDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Shipment> getShipments() {
        String query = "select * from public.shipments";

        return namedParameterJdbcTemplate.query(query, (rs, rowNum) -> {
            int shipmentId = rs.getInt("shipment_id");
            String shipmentType = rs.getString("shipment_type");
            float weight = rs.getFloat("weight");
            float volume = rs.getFloat("volume");
            int orderId = rs.getInt("order_id");
            int deliveryStatus = rs.getInt("delivery_status");
            int shipmentCategory = rs.getInt("category_id");

            return new Shipment(shipmentId, shipmentType, weight, volume, orderId, deliveryStatus, shipmentCategory);
        });
    }

    @Override
    public void addShipment(Shipment shipment) {
        String sql = "insert into public.shipments (shipment_type, weight, volume, order_id, delivery_status, category_id)" +
                " values(:shipmentType, :weight, :volume, :orderId, :deliveryStatus, :categoryId)";
        Map<String, Object> params = new HashMap<>();

        params.put("shipmentType", shipment.getShipmentType());
        params.put("weight", shipment.getWeight());
        params.put("volume", shipment.getVolume());
        params.put("orderId", shipment.getOrderId());
        params.put("deliveryStatus", shipment.getDeliveryStatus());
        params.put("categoryId", shipment.getShipmentCategory());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public List<Shipment> findByCategory(int categoryId) {
        String query = "select * from public.shipments " +
                "where category_id = :categoryId";

        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", categoryId);

        return namedParameterJdbcTemplate.query(query, params,  (rs, rowNum) -> {
            int shipmentId = rs.getInt("shipment_id");
            String type = rs.getString("shipment_type");
            float weight = rs.getFloat("weight");
            float volume = rs.getFloat("volume");
            int orderId = rs.getInt("order_id");
            int deliveryStatus = rs.getInt("delivery_status");
            int shipmentCategory = rs.getInt("category_id");

            return new Shipment(shipmentId, type, weight, volume, orderId, deliveryStatus, shipmentCategory);
        });
    }

    @Override
    public List<Shipment> findByType(String shipmentType) {
        String query = "select * from public.shipments where shipment_type = :shipmentType";
        Map<String, Object> params = new HashMap<>();
        params.put("shipmentType", shipmentType);

        return namedParameterJdbcTemplate.query(query, params, (rs, rowNum) -> {
            int shipmentId = rs.getInt("shipment_id");
            String type = rs.getString("shipment_type");
            float weight = rs.getFloat("weight");
            float volume = rs.getFloat("volume");
            int orderId = rs.getInt("order_id");
            int deliveryStatus = rs.getInt("delivery_status");
            int shipmentCategory = rs.getInt("category_id");

            return new Shipment(shipmentId, type, weight, volume, orderId, deliveryStatus, shipmentCategory);
        });
    }

    @Override
    public List<Shipment> findByAllFilters(String shipmentType, int categoryId) {
        String query = "select * from public.shipments " +
                "where category_id = :categoryId and shipment_type = :shipmentType";
        Map<String, Object> params = new HashMap<>();
        params.put("shipmentType", shipmentType);
        params.put("categoryId", categoryId);

        return namedParameterJdbcTemplate.query(query, params, (rs, rowNum) -> {
            int shipmentId = rs.getInt("shipment_id");
            String type = rs.getString("shipment_type");
            float weight = rs.getFloat("weight");
            float volume = rs.getFloat("volume");
            int orderId = rs.getInt("order_id");
            int deliveryStatus = rs.getInt("delivery_status");
            int shipmentCategory = rs.getInt("category_id");

            return new Shipment(shipmentId, type, weight, volume, orderId, deliveryStatus, shipmentCategory);
        });
    }
}
