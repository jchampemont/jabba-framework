/*
 * Copyright (C) 2016 Jean Champémont
 *
 * This file is part of the Lavoisier.io project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jeanchampemont.jabba.persistence;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base entity to be implemented by JPA entities, with default {@link #equals(Object)} and {@link #hashCode()}
 * implementations.
 */
public abstract class BaseEntity<PK extends Serializable> implements Serializable, Identifiable<PK> {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Identifiable) || getId() == null
                || !getUserClass(this.getClass()).equals(getUserClass(obj.getClass()))
                || !Objects.equals(getId(), ((Identifiable<?>) obj).getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /** The CGLIB class separator: "$$" */
    private static final String CGLIB_CLASS_SEPARATOR = "$$";

    /**
     * Return the user-defined class for the given class: usually simply the given class, but the original class in case
     * of a CGLIB-generated subclass.
     * 
     * @param clazz the class to check
     * @return the user-defined class
     */
    private static Class<?> getUserClass(Class<?> clazz) {
        if (clazz != null && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
            Class<?> superclass = clazz.getSuperclass();
            if (superclass != null && Object.class != superclass) {
                return superclass;
            }
        }
        return clazz;
    }
}
