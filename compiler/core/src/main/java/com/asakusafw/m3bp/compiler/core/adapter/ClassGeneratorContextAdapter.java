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
package com.asakusafw.m3bp.compiler.core.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.asakusafw.dag.compiler.codegen.ClassGeneratorContext;
import com.asakusafw.dag.compiler.codegen.ClassNameMap;
import com.asakusafw.dag.compiler.model.ClassData;
import com.asakusafw.dag.utils.common.Arguments;
import com.asakusafw.dag.utils.common.Invariants;
import com.asakusafw.dag.utils.common.Optionals;
import com.asakusafw.lang.compiler.api.DataModelLoader;
import com.asakusafw.lang.compiler.model.description.ClassDescription;
import com.asakusafw.m3bp.compiler.common.M3bpPackage;
import com.asakusafw.m3bp.compiler.core.M3bpCompilerContext;

/**
 * An adapter implementation of {@link ClassGeneratorContext}.
 */
public class ClassGeneratorContextAdapter implements ClassGeneratorContext {

    private final M3bpCompilerContext delegate;

    private final ClassNameMap namer = new ClassNameMap(M3bpPackage.CLASS_PREFIX);

    private final Map<Object, ClassDescription> cache = new HashMap<>();

    /**
     * Creates a new instance.
     * @param delegate the parent context
     */
    public ClassGeneratorContextAdapter(M3bpCompilerContext delegate) {
        Arguments.requireNonNull(delegate);
        this.delegate = delegate;
    }

    @Override
    public ClassLoader getClassLoader() {
        return delegate.getRoot().getClassLoader();
    }

    @Override
    public DataModelLoader getDataModelLoader() {
        return delegate.getRoot().getDataModelLoader();
    }

    @Override
    public ClassDescription getClassName(String category, String hint) {
        return namer.get(category, hint);
    }

    @Override
    public ClassDescription addClassFile(ClassData data) {
        return delegate.add(data);
    }

    @Override
    public Optional<ClassDescription> findCache(Object key) {
        return Optionals.get(cache, key);
    }

    @Override
    public void addCache(Object key, ClassDescription target) {
        ClassDescription victim = cache.putIfAbsent(key, target);
        Invariants.require(victim == null, () -> key);
    }
}
