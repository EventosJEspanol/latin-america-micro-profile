/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.kumuluz.ee.config.microprofile;

import com.kumuluz.ee.config.microprofile.converters.ImplicitConverter;
import com.kumuluz.ee.config.microprofile.utils.AlternativeTypesUtil;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Microprofile Config implementation that exposes KumuluzEE configuration framework.
 *
 * @author Urban Malc
 * @author Jan Meznarič
 * @author Yog Sothoth
 * @since 1.1
 */
public class ConfigImpl implements Config, Serializable {

    private static final String ARRAY_SEPARATOR_REGEX = "(?<!\\\\)" + Pattern.quote(",");
    private Map<Type, Converter> converters;
    private List<ConfigSource> configSources;

    public ConfigImpl(List<ConfigSource> configSources, Map<Type, Converter> converters) {
        this.configSources = configSources;
        this.converters = converters;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> asType) {

        String value = null;

        for (ConfigSource cs : this.configSources) {
            value = cs.getValue(propertyName);

            if (value != null) {
                break;
            }
        }

        T convertedValue;
        try {
            convertedValue = convert(value, asType);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not convert value " + value + " to type " + asType, e);
        }

        return Optional.ofNullable(convertedValue);
    }

    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {

        Optional<T> valueOpt = getOptionalValue(propertyName, propertyType);

        if (!valueOpt.isPresent()) {
            throw new NoSuchElementException("No configured value found for config key " + propertyName);
        }

        return valueOpt.get();
    }


    @SuppressWarnings("unchecked")
    public <T> T convert(String value, Class<T> asType) {

        if (value == null) {
            return null;
        }

        if (asType.isArray()) {
            Class<?> arrayType = asType.getComponentType();
            List a = convertList(value, arrayType);
            Object arr = Array.newInstance(arrayType, a.size());
            for (int i = 0; i < a.size(); i++) {
                Array.set(arr, i, a.get(i));
            }

            return (T) arr;
        }

        Converter<T> converter = getConverter(asType);
        return converter.convert(value);
    }

    public <T> List<T> convertList(String value, Class<T> listType) {

        String[] tokens = value.split(ARRAY_SEPARATOR_REGEX);

        Converter<T> converter = getConverter(listType);
        List<T> convertedList = new ArrayList<>(tokens.length);

        for (String token : tokens) {
            token = token.replace("\\,", ",");
            convertedList.add(converter.convert(token));
        }

        return convertedList;
    }

    @SuppressWarnings("unchecked")
    private <T> Converter<T> getConverter(Class asType) {

        asType = (Class) AlternativeTypesUtil.getTypeFromPrimitive(asType).orElse(asType);

        Converter converter = converters.get(asType);

        if (converter == null) {
            // no registered converter, try to generate implicit converter
            converter = ImplicitConverter.getImplicitConverter(asType);
        }

        if (converter == null) {
            throw new IllegalArgumentException("No Converter registered for class " + asType);
        }

        return converter;
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return this.configSources.stream().flatMap(e -> e.getPropertyNames().stream()).collect(Collectors.toSet());
    }

    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return this.configSources;
    }
}
