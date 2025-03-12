package org.bistu.web.webstore.utils;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
public class BeanUtil {
    private static MapperFactory factory;
    private static MapperFacade mapper;

    static {
        factory = new DefaultMapperFactory.Builder().build();
        mapper = factory.getMapperFacade();
    }

    public static void initCustomClassMappping(ICustomClassMapping customMapping) {
        if (Objects.nonNull(customMapping)) {
            customMapping.initClassMapping(factory);
        }
    }

    /**
     * javabean之间copy
     */
    public static <T, F> T copyProperties(F source, Class<T> destClass) {
        return copyProperties(source, destClass, null);
    }

    public static <T, F> T copyProperties(F source, Class<T> destClass, ICustomClassMapping customClassMapping) {
        initCustomClassMappping(customClassMapping);
        return mapper.map(source, destClass);
    }

    /**
     * javabean之间copy
     */
    public static <T, F> void copyProperties(F source, T destClassObj) {
        copyProperties(source, destClassObj, null);
    }

    public static <T, F> void copyProperties(F source, T destClassObj, ICustomClassMapping customClassMapping) {
        initCustomClassMappping(customClassMapping);

        if (Objects.nonNull(source)) {
            mapper.map(source, destClassObj);
        }
    }


    /**
     * 集合之间copy
     */
    public static <T> List<T> copyListProperties(List<?> objects, Class<T> target) {
        if (CollectionUtils.isEmpty(objects)) {
            return Collections.emptyList();
        }

        return mapper.mapAsList(objects.toArray(), target);
    }

    public static interface ICustomClassMapping {
        public void initClassMapping(MapperFactory factory);
    }
}