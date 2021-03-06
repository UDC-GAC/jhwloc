# jhwloc: Java Hardware Locality library

**jhwloc** is a Java wrapper for the [Portable Hardware Locality (hwloc)](http://www.open-mpi.org/projects/hwloc)

## Documentation

The complete Javadoc in HTML format that details all the methods of the public jhwloc API is available [here](http://gac.udc.es/~rober/jhwloc/javadoc).

## Getting Started

### Prerequisites

* Make sure you have Java Develpment Kit (JDK) version 1.7 or above
  * JAVA_HOME environmental variable must be set accordingly
 
* Make sure you have a working Apache Maven distribution version 3 or above
  * See [Installing Apache Maven](https://maven.apache.org/install.html)

* Make sure you have a working hwloc distribution version 2.0 or above
  * See [hwloc's Documentation](https://www.open-mpi.org/projects/hwloc/doc)
  * HWLOC_HOME environmental variable must be set accordingly

### Installation

In order to download, compile, build and install the jhwloc library in your Maven local repository (by default ~/.m2), just execute the following commands:

```
git clone https://github.com/rreye/jhwloc.git
cd jhwloc
mvn install
```

In case you need to change any setting to compile the native C code in your system, you can modify the *Makefile.common* file located at the */src/main/native* directory.

### Usage

In order to use the jhwloc library in your projects, add the following dependency section to your pom.xml:

```xml
<dependencies>
...
  <dependency>
   <groupId>es.udc.gac</groupId>
   <artifactId>jhwloc</artifactId>
   <version>1.0</version> <!-- or latest version -->
  </dependency>
...
</dependencies>
```

## Running the examples

You can test your installation running some of the examples included in jhwloc:

```bash
java -cp /path/to/jhwloc/lib/jhwloc-1.0.jar -Djava.library.path=/path/to/jhwloc/lib es.udc.gac.jhwloc.examples.HelloWorld
```

If your native hwloc library (i.e. libhwloc.so) is not installed in a standard location (e.g. /usr/lib64), you need to set **LD_LIBRARY_PATH** environmental variable to the directory containing it (i.e. $HWLOC_HOME/lib). For instance, if hwloc is installed at /opt/hwloc (i.e. HWLOC_HOME=/opt/hwloc):

```bash
export LD_LIBRARY_PATH=$HWLOC_HOME/lib:$LD_LIBRARY_PATH
java -cp /path/to/jhwloc/lib/jhwloc-1.0.jar -Djava.library.path=/path/to/jhwloc/lib es.udc.gac.jhwloc.examples.HelloWorld
```

## Projects using jhwloc

* [Flame-MR: efficient MapReduce-based data processing](http://flamemr.des.udc.es)
* [FastMPJ: Fast Message Passing in Java](http://gac.udc.es/~rober/fastmpj)

## Authors

The jhwloc library is developed in the [Computer Architecture Group](http://gac.udc.es/english) at the [Universidade da Coruña](https://www.udc.es/en) by:

* **Roberto R. Expósito** (http://gac.udc.es/~rober)
* **Jorge Veiga** (http://gac.udc.es/~jorge.veiga)
* **Juan Touriño** (http://gac.udc.es/~juan)

## License

This library is distributed as free software and is publicly available under the GPLv3 license (see the [LICENSE](LICENSE) file for more details)
