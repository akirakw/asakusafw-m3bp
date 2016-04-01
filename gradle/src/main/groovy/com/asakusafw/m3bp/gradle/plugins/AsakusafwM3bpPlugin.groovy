/*
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
package com.asakusafw.m3bp.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

import com.asakusafw.gradle.plugins.internal.PluginUtils
import com.asakusafw.m3bp.gradle.plugins.internal.AsakusaM3bpOrganizerPlugin
import com.asakusafw.m3bp.gradle.plugins.internal.AsakusaM3bpSdkPlugin

/**
 * A Gradle plug-in for Asakusa projects for M3BP runtime.
 */
class AsakusafwM3bpPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        PluginUtils.afterPluginEnabled(project, 'asakusafw-sdk') {
            project.apply plugin: AsakusaM3bpSdkPlugin
        }
        PluginUtils.afterPluginEnabled(project, 'asakusafw-organizer') {
            project.apply plugin: AsakusaM3bpOrganizerPlugin
        }
    }
}
