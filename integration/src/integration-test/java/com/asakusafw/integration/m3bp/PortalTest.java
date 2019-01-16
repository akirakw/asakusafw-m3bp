/**
 * Copyright 2011-2019 Asakusa Framework Team.
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
package com.asakusafw.integration.m3bp;

import static com.asakusafw.integration.m3bp.Util.*;

import org.junit.ClassRule;
import org.junit.Test;

import com.asakusafw.integration.AsakusaConfigurator;
import com.asakusafw.integration.AsakusaConstants;
import com.asakusafw.integration.AsakusaProjectProvider;
import com.asakusafw.utils.gradle.Bundle;
import com.asakusafw.utils.gradle.ContentsConfigurator;

/**
 * Test for the portal command.
 */
public class PortalTest {

    /**
     * project provider.
     */
    @ClassRule
    public static final AsakusaProjectProvider PROVIDER = new AsakusaProjectProvider()
            .withProject(ContentsConfigurator.copy(data("m3bp")))
            .withProject(ContentsConfigurator.copy(data("ksv")))
            .withProject(ContentsConfigurator.copy(data("logback-test")))
            .withProject(AsakusaConfigurator.projectHome())
            .withProject(AsakusaConfigurator.hadoop(AsakusaConfigurator.Action.UNSET_ALWAYS))
            .withProvider(provider -> {
                // install framework only once
                framework = provider.newInstance("inf")
                        .gradle("attachM3bpBatchapps", "installAsakusafw")
                        .getFramework();
            });

    static Bundle framework;

    /**
     * {@code list plan}.
     */
    @Test
    public void list_plan() {
        framework.withLaunch(AsakusaConstants.CMD_PORTAL, "list", "plan", "-v",
                "m3bp.perf.average.sort");
    }

    /**
     * {@code generate dot plan}.
     */
    @Test
    public void generate_dot_plan() {
        framework.withLaunch(AsakusaConstants.CMD_PORTAL, "generate", "dot", "plan", "-v",
                "m3bp.perf.average.sort");
    }
}
