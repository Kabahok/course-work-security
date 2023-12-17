package com.egoravdeev.courseworkbackend.dao;

import com.egoravdeev.courseworkbackend.dao.impl.DeliveryInformationImpl;
import com.egoravdeev.courseworkbackend.model.DeliveryTimeline;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class DeliveryInformationDAO implements DeliveryInformationImpl {
    private final SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DeliveryInformationDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<DeliveryTimeline> findAll() {
        String query = "select * from public.delivery_timelines";

        return namedParameterJdbcTemplate.query(query, (rs, rowNum) -> {
            int timelineId = rs.getInt("timeline_id");
            int orderOd = rs.getInt("order_id");
            String plannedDeliveryDate = dateFormat.format(rs.getDate("planned_delivery_date"));

            Object value = rs.getObject("actual_delivery_date");

            String actualDeliveryDate = (value != null) ? value.toString() : "Не указана";

            return new DeliveryTimeline(timelineId, orderOd, plannedDeliveryDate, actualDeliveryDate);
        });
    }


    @Override
    public void addTimeline(String deliveryDate, int orderId) {
        String sql = "insert into public.delivery_timelines (order_id, planned_delivery_date) values(:orderId, :deliveryDate)";

        Map<String, Object> params = new HashMap<>();

        try {
            params.put("deliveryDate", dateFormat.parse(deliveryDate));
            params.put("orderId", orderId);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        namedParameterJdbcTemplate.update(sql, params);
    }
}
