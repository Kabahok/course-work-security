package com.egoravdeev.courseworkbackend.dao;

import com.egoravdeev.courseworkbackend.dao.impl.DeliveryShipmentInformationImpl;
import com.egoravdeev.courseworkbackend.model.DeliveredShipment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DeliveredShipmentInformationDAO implements DeliveryShipmentInformationImpl {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DeliveredShipmentInformationDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<DeliveredShipment> findAll() {

        addDeliveredShipemnts(findDeliveredShipments());

        String query = "select * from public.delivered_shipments";

        return namedParameterJdbcTemplate.query(query, (rs, rowNum) -> {

            int deliveryId = rs.getInt("delivery_id");
            int shipmentId = rs.getInt("shipment_id");
            String deliveryDate = dateFormat.format(rs.getDate("delivery_date"));
            int deliveryStatus = rs.getInt("delivery_status");

            return new DeliveredShipment(deliveryId, shipmentId, deliveryDate, deliveryStatus);
        });
    }

    @Override
    public void addDeliveredShipemnts(List<DeliveredShipment> shipments) {
        for (DeliveredShipment shipment : shipments) {
            String sql = "insert into public.delivered_shipments (delivery_id, shipment_id, delivery_date, delivery_status) values(:deliveryId, :shipmentId, :deliveryDate, :deliveryStatus)";
            Map<String, Object> params = new HashMap<>();
            try {
                params.put("shipmentId", shipment.getShipmentId());
                params.put("deliveryDate", dateFormat.parse(shipment.getDeliveryDate()));
                params.put("deliveryStatus", shipment.getDeliveryStatus());
                params.put("deliveryId", shipment.getDeliveryId());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            namedParameterJdbcTemplate.update(sql, params);
        }
    }

    @Override
    public List<DeliveredShipment> findDeliveredShipments() {
        String query = "select * from public.shipments s join public.orders o on s.order_id = o.order_id where o.delivery_date < '2024.12.31'";

        return namedParameterJdbcTemplate.query(query, (rs, rowNum) -> {
           int shipmentId = rs.getInt("shipment_id");
           String deliveryDate = dateFormat.format(rs.getDate("delivery_date"));
           int deliveryStatus = rs.getInt("delivery_status");


           return new DeliveredShipment(0, shipmentId, deliveryDate, deliveryStatus);
        });
    }
}
