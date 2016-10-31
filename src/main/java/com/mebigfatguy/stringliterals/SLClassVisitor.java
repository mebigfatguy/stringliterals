package com.mebigfatguy.stringliterals;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class SLClassVisitor extends ClassVisitor {

    public SLClassVisitor() {
        super(Opcodes.ASM5);
    }
}
