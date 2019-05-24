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
#include "com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe.h"
#include "mirror.hpp"
#include "jniutil.hpp"

using namespace asakusafw::jni;

/*
 * Class:     com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe
 * Method:    allocateBuffer0
 * Signature: (J[J)V
 */
JNIEXPORT void JNICALL Java_com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_allocateBuffer0
(JNIEnv *env, jclass, jlong _self, jlongArray results) {
    try {
        auto* self = reinterpret_cast<OutputWriterMirror*>(_self);
        jlong array[com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_VALUES_SIZE];
        std::tuple<void const*, m3bp::size_type, void const*, void const*, m3bp::size_type> buffer = self->output_buffer();
        array[com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_INDEX_BUFFER_PTR] = to_pointer(std::get<0>(buffer));
        array[com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_INDEX_BUFFER_SIZE] = static_cast<jlong>(std::get<1>(buffer));
        array[com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_INDEX_OFFSET_TABLE_PTR] = to_pointer(std::get<2>(buffer));
        array[com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_INDEX_KEY_LENGTH_TABLE_PTR] = to_pointer(std::get<3>(buffer));
        array[com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_INDEX_RECORD_COUNT] = static_cast<jlong>(std::get<4>(buffer));
        env->SetLongArrayRegion(results, 0, com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_VALUES_SIZE, &array[0]);
    } catch (JavaException &e) {
        e.rethrow(env);
    } catch (std::exception &e) {
        handle_native_exception(env, e);
    }
}

/*
 * Class:     com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe
 * Method:    flush0
 * Signature: (JJ)Z
 */
JNIEXPORT void JNICALL Java_com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_flush0
(JNIEnv *env, jclass, jlong _self, jlong _recordCount) {
    try {
        auto* self = reinterpret_cast<OutputWriterMirror*>(_self);
        auto record_count = static_cast<m3bp::size_type>(_recordCount);
        self->flush(record_count);
    } catch (JavaException &e) {
        e.rethrow(env);
    } catch (std::exception &e) {
        handle_native_exception(env, e);
    }
}

/*
 * Class:     com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe
 * Method:    close0
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_asakusafw_m3bp_mirror_jni_OutputWriterMirrorUnsafe_close0
(JNIEnv *env, jclass, jlong _self) {
    try {
        auto* self = reinterpret_cast<OutputWriterMirror*>(_self);
        delete self;
    } catch (JavaException &e) {
        e.rethrow(env);
    } catch (std::exception &e) {
        handle_native_exception(env, e);
    }
}
