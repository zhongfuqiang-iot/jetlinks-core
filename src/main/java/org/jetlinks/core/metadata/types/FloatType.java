package org.jetlinks.core.metadata.types;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;

@Getter
@Setter
@SuppressWarnings("all")
public class FloatType extends NumberType<Float> {
    public static final String ID = "float";

    private Integer scale;

    @Override
    public Object format(Object value) {
        if (value instanceof Number) {
            int scale = this.scale == null ? 2 : this.scale;
            String scaled = new BigDecimal(value.toString())
                    .setScale(scale, ROUND_HALF_UP)
                    .toString();
            return super.format(scaled);
        }
        return super.format(value);
    }

    public FloatType scale(Integer scale) {
        this.scale = scale;
        return this;
    }

    @Override
    public Float convert(Object value) {
        return super.convertNumber(value,Number::floatValue);
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return "单精度浮点数";
    }


}
