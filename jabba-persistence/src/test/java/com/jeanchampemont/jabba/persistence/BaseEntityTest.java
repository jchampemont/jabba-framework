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

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.internal.cglib.proxy.Enhancer;
import org.assertj.core.internal.cglib.proxy.NoOp;
import org.junit.Test;

public class BaseEntityTest {

    @Test
    public void testEqualsItSelf() {
        EntityA a = new EntityA();
        a.setId(44L);
        a.setValue("I am equal to myself");

        assertThat(a.equals(a)).isTrue();
    }

    @Test
    public void testEqualSameId() {
        EntityA a = new EntityA();
        a.setId(44L);
        a.setValue("I am equal to b");

        EntityA b = new EntityA();
        b.setId(44L);
        b.setValue("I am equal to a");

        assertThat(a.equals(b)).isTrue();
    }

    @Test
    public void testEqualWithCgLibEnhancer() {
        EntityA a = new EntityA();
        a.setId(44L);
        a.setValue("I am equal to EntityA$$EnhancedByCGLib");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(EntityA.class);
        enhancer.setCallback(new NoOp() {
        });

        EntityA proxy = (EntityA) enhancer.create();
        proxy.setId(44L);
        proxy.setValue("I am equal to EntityA");

        assertThat(proxy.getClass().getSimpleName()).contains("$$");
        assertThat(proxy.equals(a)).isTrue();
    }

    @Test
    public void testNotEqualDifferentId() {
        EntityA a = new EntityA();
        a.setId(44L);
        a.setValue("I am not equal to b");

        EntityA b = new EntityA();
        b.setId(45L);
        b.setValue("I am not equal to a");

        assertThat(a.equals(b)).isFalse();
    }

    @Test
    public void testNotEqualSomethingElse() {
        EntityA a = new EntityA();
        a.setId(44L);
        a.setValue("I am not equal to something else");

        assertThat(a.equals(44L)).isFalse();
    }

    @Test
    public void testNotEqualIdIsNull() {
        EntityA a = new EntityA();
        a.setId(null);
        a.setValue("I am not equal");

        EntityA b = new EntityA();
        b.setId(44L);
        b.setValue("I am not equal");

        assertThat(a.equals(b)).isFalse();
    }

    @Test
    public void testNotEqualDifferentClass() {
        EntityA a = new EntityA();
        a.setId(44L);
        a.setValue("I am not equal");

        EntityB b = new EntityB();
        b.setId(44L);
        b.setValue("I am not equal");

        assertThat(a.equals(b)).isFalse();
    }

    @Test
    public void testHashCodeIsIdHashCode() {
        EntityA a = new EntityA();
        a.setId(12L);
        a.setValue("My Value is nice");

        assertThat(a.hashCode()).isEqualTo(new Long(12L).hashCode());
    }

    @Test
    public void testHashCodeIsZeroWhenIdIsNull() {
        EntityA a = new EntityA();
        a.setId(null);

        assertThat(a.hashCode()).isEqualTo(0);
    }
}
