package com.egoravdeev.courseworkbackend.dao;

import com.egoravdeev.courseworkbackend.dao.impl.DeliveryShipmentInformationImpl;
import com.egoravdeev.courseworkbackend.model.DeliveredShipment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
            String sql = "insert into public.delivered_shipments as ds (shipment_id, delivery_date, delivery_status) " +
                    "select :shipmentId, :deliveryDate, :deliveryStatus " +
                    "where not exists (" +
                    "select 1 from public.delivered_shipments " +
                    "where shipment_id = :shipmentId and " +
                    "delivery_date = :deliveryDate and" +
                    "    delivery_status = :deliveryStatus" +
                    ")";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Map<String, Object> params = new HashMap<>();
                params.put("shipmentId", shipment.getShipmentId());
                params.put("deliveryDate", LocalDate.parse(shipment.getDeliveryDate(), formatter));
                params.put("deliveryStatus", shipment.getDeliveryStatus());


            namedParameterJdbcTemplate.update(sql, params);
        }
    }

    @Override
    public List<DeliveredShipment> findDeliveredShipments() {
        String query = "select * from public.shipments s join public.orders o on s.order_id = o.order_id where o.delivery_date < CURRENT_DATE";
        Map<String, Object> params = new HashMap<>();

        return namedParameterJdbcTemplate.query(query, params, (rs, rowNum) -> {
           int shipmentId = rs.getInt("shipment_id");
           String deliveryDate = dateFormat.format(rs.getDate("delivery_date"));
           System.out.println(deliveryDate);
           int deliveryStatus = rs.getInt("delivery_status");


           return new DeliveredShipment(0, shipmentId, deliveryDate, 2);
        });
    }
}
