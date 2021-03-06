/**
 * Copyright 2011-2016 Asakusa Framework Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.asakusafw.m3bp.compiler.inspection;

import java.util.Map;

/**
 * An abstract super interface of inspection element target views.
 * @param <T> the target member type
 */
public interface ElementSpecView<T> {

    /**
     * The property key of node kind.
     */
    String KEY_KIND = "kind";

    /**
     * The property key prefix of attributes.
     */
    String KEY_ATTRIBUTE_PREFIX = "attribute.";

    /**
     * Returns the original element.
     * @return the original element
     */
    T getOrigin();

    /**
     * Returns the element ID.
     * @return the element ID
     */
    String getId();

    /**
     * Returns the unparsed attributes of this element.
     * @return the unparsed attributes of this element
     */
    Map<String, String> getAttributes();
}
