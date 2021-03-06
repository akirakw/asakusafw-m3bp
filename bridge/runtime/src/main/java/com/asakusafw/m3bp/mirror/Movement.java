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
package com.asakusafw.m3bp.mirror;

/**
 * Represents the data exchange kind.
 */
public enum Movement {

    /**
     * Does not exchange any data-sets.
     */
    NOTHING(3), // NOTE: using empty BROADCAST

    /**
     * Exchange data-sets one-to-one.
     */
    ONE_TO_ONE(1),

    /**
     * Scatter-gather.
     */
    SCATTER_GATHER(2),

    /**
     * Broadcasts data-sets into all downstream inputs.
     */
    BROADCAST(3),
    ;

    private final int id;

    Movement(int id) {
        this.id = id;
    }

    /**
     * Returns the enum ID.
     * @return the enum ID
     */
    public int getId() {
        return id;
    }
}