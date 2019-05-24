/*
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
#include "env.hpp"

#include <sstream>
#include <iostream>

namespace asakusafw {
namespace jni {

static JavaVM *s_java_vm;
thread_local bool s_java_attached = false;

extern "C" JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *) {
    s_java_vm = vm;
    return JNI_VERSION_1_8;
}

JNIEnv *java_env() {
    JNIEnv *env;
    jint result = s_java_vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_8);
    if (result == JNI_OK) {
        return env;
    }
    return nullptr;
}

JNIEnv *java_attach() {
    JNIEnv *env;
    jint result;
    result = s_java_vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_8);
    if (result == JNI_OK) {
        return env;
    }
    JavaVMAttachArgs thread_args = {
        .version = JNI_VERSION_1_8,
        .name = nullptr,
        .group = nullptr
    };
    result = s_java_vm->AttachCurrentThreadAsDaemon(reinterpret_cast<void**>(&env), &thread_args);
    if (result == JNI_OK) {
        s_java_attached = true;
        return env;
    }

    std::stringstream stream;
    stream << "failed to obtain JVM: JavaVM->AttachCurrentThreadAsDaemon() returns " << result;
    throw BridgeError(stream.str());
}

void java_detach() {
    if (s_java_attached) {
        s_java_vm->DetachCurrentThread();
        s_java_attached = false;
    }
}

}  // namespace jni
}  // namespace asakusafw
