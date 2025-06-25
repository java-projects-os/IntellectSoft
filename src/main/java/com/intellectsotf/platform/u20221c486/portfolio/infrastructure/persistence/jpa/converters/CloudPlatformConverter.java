package com.intellectsotf.platform.u20221c486.portfolio.infrastructure.persistence.jpa.converters;

import com.intellectsotf.platform.u20221c486.portfolio.domain.model.valueobjects.CloudPlatform;
import com.intellectsotf.platform.u20221c486.portfolio.domain.model.valueobjects.FrontendStack;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class CloudPlatformConverter implements AttributeConverter<CloudPlatform, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CloudPlatform bs) {
        return (bs != null) ? bs.getId() : null;
    }

    @Override
    public CloudPlatform convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        return CloudPlatform.fromValue(dbData);
    }
}
