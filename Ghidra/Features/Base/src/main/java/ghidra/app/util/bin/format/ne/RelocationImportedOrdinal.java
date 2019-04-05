/* ###
 * IP: GHIDRA
 * REVIEWED: YES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.app.util.bin.format.ne;

import java.io.IOException;

import ghidra.app.util.bin.BinaryReader;

class RelocationImportedOrdinal {
    private short index;   //into module reference table for imported module
    private short ordinal; //ordinal number of procedure

    RelocationImportedOrdinal(BinaryReader reader) throws IOException {
        index   = reader.readNextShort();
        ordinal = reader.readNextShort();
    }

    public short getIndex() {
        return index;
    }
    public short getOrdinal() {
        return ordinal;
    }
}
