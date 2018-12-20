include ./Makefile.common

all: libjhwloc.so

libjhwloc.so:
	if [ ! -d "$(BUILD_DIR)" ]; then $(MKDIR) $(BUILD_DIR); fi
	$(JAVAH) -classpath $(SRC_DIR) es.udc.gac.jhwloc.HWLOC
	$(JAVAH) -classpath $(SRC_DIR) es.udc.gac.jhwloc.HwlocObject
	$(JAVAH) -classpath $(SRC_DIR) es.udc.gac.jhwloc.HwlocTopology
	$(JAVAH) -classpath $(SRC_DIR) es.udc.gac.jhwloc.HwlocBitmap
	$(JAVAH) -classpath $(SRC_DIR) es.udc.gac.jhwloc.HwlocEnumTypes
	$(CC) $(CC_FLAGS) jhwloc_HwlocBitmap.c -o $(BUILD_DIR)/jhwloc_HwlocBitmap.o
	$(CC) $(CC_FLAGS) jhwloc_HWLOC.c -o $(BUILD_DIR)/jhwloc_HWLOC.o
	$(CC) $(CC_FLAGS) jhwloc_HwlocObject.c -o $(BUILD_DIR)/jhwloc_HwlocObject.o
	$(CC) $(CC_FLAGS) jhwloc_HwlocTopology.c -o $(BUILD_DIR)/jhwloc_HwlocTopology.o
	$(CC) $(CC_FLAGS) jhwloc_HwlocEnumTypes.c -o $(BUILD_DIR)/jhwloc_HwlocEnumTypes.o
	$(CC) $(CC_FLAGS) jhwloc_util.c -o $(BUILD_DIR)/jhwloc_util.o
	$(CC) $(LIBJHWLOC_FLAGS) $(BUILD_DIR)/*.o -o $(BUILD_DIR)/libjhwloc.so
	$(CP) $(BUILD_DIR)/libjhwloc.so $(LIB_DIR)

clean:
	$(RMR) $(BUILD_DIR)
