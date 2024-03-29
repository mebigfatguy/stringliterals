/*
 * stringliterals - A tool for parsing out all string literals used in a code base
 * Copyright 2016-2019 MeBigFatGuy.com
 * Copyright 2016-2019 Dave Brosius
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.mebigfatguy.stringliterals;

import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SLMethodVisitor extends MethodVisitor {

    private Set<Literal> literals = new HashSet<>();
    private String className;
    private String methodInfo;

    public SLMethodVisitor() {
        super(Opcodes.ASM9);
    }

    @Override
    public void visitLdcInsn(Object cst) {
        if (cst instanceof String) {
            literals.add(new Literal((String) cst, className));
        }
    }

    public void setClassName(String name) {
        className = name;

    }

    public void setMethodDetails(String details) {
        methodInfo = details;
    }

    public Set<Literal> getLiterals() {
        return literals;
    }
}
