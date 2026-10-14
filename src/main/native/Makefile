include ./Makefile.common

all: libjhwloc.so

# javac -h generates the JNI headers (it replaces javah, which was removed in JDK 10).
# The hwloc libraries must be linked after the object files, otherwise linkers
# using --as-needed drop the dependency on libhwloc.
libjhwloc.so:
	if [ ! -d "$(BUILD_DIR)" ]; then $(MKDIR) $(BUILD_DIR); fi
	$(JAVAC) -h . -d $(BUILD_DIR)/classes -sourcepath $(SRC_DIR) \
		$(JAVA_SRC)/HWLOC.java $(JAVA_SRC)/HwlocObject.java $(JAVA_SRC)/HwlocTopology.java \
		$(JAVA_SRC)/HwlocBitmap.java $(JAVA_SRC)/HwlocEnumTypes.java
	$(CC) $(CC_FLAGS) jhwloc_HwlocBitmap.c -o $(BUILD_DIR)/jhwloc_HwlocBitmap.o
	$(CC) $(CC_FLAGS) jhwloc_HWLOC.c -o $(BUILD_DIR)/jhwloc_HWLOC.o
	$(CC) $(CC_FLAGS) jhwloc_HwlocObject.c -o $(BUILD_DIR)/jhwloc_HwlocObject.o
	$(CC) $(CC_FLAGS) jhwloc_HwlocTopology.c -o $(BUILD_DIR)/jhwloc_HwlocTopology.o
	$(CC) $(CC_FLAGS) jhwloc_HwlocEnumTypes.c -o $(BUILD_DIR)/jhwloc_HwlocEnumTypes.o
	$(CC) $(CC_FLAGS) jhwloc_util.c -o $(BUILD_DIR)/jhwloc_util.o
	$(CC) $(LIBJHWLOC_FLAGS) $(BUILD_DIR)/*.o $(HWLOC_LIBS) -o $(BUILD_DIR)/libjhwloc.so
	$(CP) $(BUILD_DIR)/libjhwloc.so $(LIB_DIR)

clean:
	$(RMR) $(BUILD_DIR)
