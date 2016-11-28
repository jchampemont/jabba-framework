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

/**
 * An Identifiable object is an object that has a {@link Serializable} unique identifier
 * 
 * @param <PK> the type of the unique identifier
 */
public interface Identifiable<PK extends Serializable> {
    /**
     * @return the unique identifier of this object
     */
    PK getId();

    /**
     * Set the unique identifier of this object
     * 
     * @param id
     */
    void setId(PK id);
}
