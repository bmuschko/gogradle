/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.blindpirate.gogradle.task

import com.github.blindpirate.gogradle.GogradleRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static com.github.blindpirate.gogradle.task.GolangTaskContainer.*

@RunWith(GogradleRunner)
class GoCheckTest extends TaskTest {

    GoCheck task

    @Before
    void setUp() {
        task = buildTask(GoCheck)
    }

    @Test
    void 'check task should depends on test task'() {
        assertTaskDependsOn(task, TEST_TASK_NAME)
        assertTaskDependsOn(task, GOFMT_TASK_NAME)
        assertTaskDependsOn(task, GOVET_TASK_NAME)
    }
}
