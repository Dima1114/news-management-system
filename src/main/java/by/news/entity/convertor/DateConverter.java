package by.news.entity.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        if (attribute != null) {
            return Date.valueOf(attribute);
        } else {
            return null;
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        if (date != null) {
            return date.toLocalDate();
        } else {
            return null;
        }
    }
}