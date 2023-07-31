package com.sjz.partbuilding.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class StatisticsDTO {

    private String time;

    private BigDecimal rate;

    private Integer count;

    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {return false;}
        StatisticsDTO dto = (StatisticsDTO) o;
        return Objects.equals(time, dto.time) &&
                Objects.equals(id, dto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, id);
    }

}
