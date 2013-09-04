/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.tomcat.util.bcel.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.tomcat.util.bcel.Constants;

public class EnumElementValue extends ElementValue
{
    // For enum types, these two indices point to the type and value
    private int typeIdx;

    private int valueIdx;

    public EnumElementValue(int type, int typeIdx, int valueIdx,
            ConstantPool cpool)
    {
        super(type, cpool);
        if (type != ENUM_CONSTANT)
            throw new RuntimeException(
                    "Only element values of type enum can be built with this ctor - type specified: " + type);
        this.typeIdx = typeIdx;
        this.valueIdx = valueIdx;
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException
    {
        dos.writeByte(type); // u1 type of value (ENUM_CONSTANT == 'e')
        dos.writeShort(typeIdx); // u2
        dos.writeShort(valueIdx); // u2
    }

    @Override
    public String stringifyValue()
    {
        ConstantUtf8 cu8 = (ConstantUtf8) cpool.getConstant(valueIdx,
                Constants.CONSTANT_Utf8);
        return cu8.getBytes();
    }
}
