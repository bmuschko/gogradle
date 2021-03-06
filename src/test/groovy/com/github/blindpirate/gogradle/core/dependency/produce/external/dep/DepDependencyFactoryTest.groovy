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

package com.github.blindpirate.gogradle.core.dependency.produce.external.dep

import com.github.blindpirate.gogradle.GogradleRunner
import com.github.blindpirate.gogradle.core.dependency.produce.external.ExternalDependencyFactoryTest
import com.github.blindpirate.gogradle.support.WithResource
import com.github.blindpirate.gogradle.util.IOUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks

@RunWith(GogradleRunner)
class DepDependencyFactoryTest extends ExternalDependencyFactoryTest {
    @InjectMocks
    DepDependencyFactory depDependencyFactory

    @Test
    @WithResource('')
    void 'can parse and convert Gopkg.lock'() {
        // given
        IOUtils.write(resource, 'Gopkg.lock', godepDotLock)
        // when
        depDependencyFactory.produce(parentDependency, resource, 'build')
        // then
        assert depDependencyFactory.produce(parentDependency, resource, 'test').isEmpty()
        verifyMapParsed([branch     : "parse-constraints-with-dash-in-pre",
                         name       : "github.com/Masterminds/semver",
                         subpackages: ['...'],
                         commit     : "a93e51b5a57ef416dac8bb02d11407b6f55d8929",
                         url        : "https://github.com/carolynvs/semver.git"])
        verifyMapParsed([name       : "github.com/Masterminds/vcs",
                         subpackages: ['...'],
                         commit     : '3084677c2c188840777bff30054f2b553729d329',
                         tag        : "v1.11.1"])
        verifyMapParsed([name       : "github.com/armon/go-radix",
                         branch     : 'master',
                         subpackages: ['...'],
                         commit     : "4239b77079c7b5d1243b7b4736304ce8ddb6f0f2"])
        verifyMapParsed([name       : "golang.org/x/net",
                         branch     : 'master',
                         subpackages: ['context'],
                         commit     : "66aacef3dd8a676686c7ae3716979581e8b03c47"])
    }

    String godepDotLock = '''
# This file is autogenerated, do not edit; changes may be undone by the next 'dep ensure'.

[[projects]]
  branch = "parse-constraints-with-dash-in-pre"
  name = "github.com/Masterminds/semver"
  packages = ["."]
  revision = "a93e51b5a57ef416dac8bb02d11407b6f55d8929"
  source = "https://github.com/carolynvs/semver.git"

[[projects]]
  name = "github.com/Masterminds/vcs"
  packages = ["."]
  revision = "3084677c2c188840777bff30054f2b553729d329"
  version = "v1.11.1"

[[projects]]
  branch = "master"
  name = "github.com/armon/go-radix"
  packages = ["."]
  revision = "4239b77079c7b5d1243b7b4736304ce8ddb6f0f2"

[[projects]]
  branch = "master"
  name = "golang.org/x/net"
  packages = ["context"]
  revision = "66aacef3dd8a676686c7ae3716979581e8b03c47"

[solve-meta]
  analyzer-name = "dep"
  analyzer-version = 1
  inputs-digest = "05c1cd69be2c917c0cc4b32942830c2acfa044d8200fdc94716aae48a8083702"
  solver-name = "gps-cdcl"
  solver-version = 1
'''
}
