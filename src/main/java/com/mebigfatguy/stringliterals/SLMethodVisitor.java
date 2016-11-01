/*
 * strihgliterals - A tool for parsing out all string literals used in a code base
 * Copyright 2016-2016 MeBigFatGuy.com
 * Copyright 2016-2016 Dave Brosius
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

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SLMethodVisitor extends MethodVisitor {

    private List<String> literals = new ArrayList<>();

    public SLMethodVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public void visitLdcInsn(Object cst) {
        if (cst instanceof String) {
            literals.add((String) cst);
        }
    }

    public List<String> getLiterals() {
        return literals;
    }
}
