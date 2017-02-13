/**
 * Copyright 2011-2017 Asakusa Framework Team.
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
package com.asakusafw.m3bp.client;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asakusafw.bridge.launch.LaunchConfigurationException;
import com.asakusafw.dag.iterative.DirectLaunchConfiguration;
import com.asakusafw.runtime.core.context.RuntimeContext;

/**
 * Direct program entry of Asakusa on M3BP.
 * @since 0.2.1
 * @see Launcher
 */
public final class M3bpDirect {

    static final Logger LOG = LoggerFactory.getLogger(M3bpDirect.class);

    private M3bpDirect() {
        return;
    }

    /**
     * Program entry.
     * @param args launching configurations
     * @throws LaunchConfigurationException if launching configuration is something wrong
     */
    public static void main(String... args) throws LaunchConfigurationException {
        ClassLoader loader = M3bpDirect.class.getClassLoader();
        RuntimeContext.set(RuntimeContext.DEFAULT.apply(System.getenv()));
        RuntimeContext.get().verifyApplication(loader);

        int status = exec(loader, args);
        if (status != 0) {
            System.exit(status);
        }
    }

    /**
     * Program entry.
     * @param loader the launch class loader
     * @param args launching configurations
     * @return the exit code
     * @throws LaunchConfigurationException if launching configuration is something wrong
     * @see Launcher#EXEC_SUCCESS
     * @see Launcher#EXEC_ERROR
     * @see Launcher#EXEC_INTERRUPTED
     */
    public static int exec(ClassLoader loader, String... args) throws LaunchConfigurationException {
        DirectLaunchConfiguration conf = DirectLaunchConfiguration.parse(loader, Arrays.asList(args));
        int numberOfRounds = conf.getStageInfo().getRoundCount();
        int currentRound = 0;
        DirectLaunchConfiguration.Cursor cursor = conf.newCursor();
        while (cursor.next()) {
            LOG.info("Round: {}/{}", ++currentRound, numberOfRounds);
            int result = new Launcher(cursor.get(), loader).exec();
            if (result != Launcher.EXEC_SUCCESS) {
                return result;
            }
        }
        return Launcher.EXEC_SUCCESS;
    }
}
