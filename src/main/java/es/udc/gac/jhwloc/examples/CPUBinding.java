package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;
import java.util.EnumSet;

public class CPUBinding {
	public static void main(String[] args) throws InterruptedException {
		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		HwlocCPUSet cpuset = HwlocCPUSet.alloc();

		// get the current thread CPU location
		if(topo.get_last_cpu_location(cpuset, EnumSet.of(HWLOC.CPUBIND_THREAD)) < 0)
			System.out.println("Error on get_last_cpu_location");
		else
			System.out.println("main thread running on "+cpuset);

		// get the current process CPU location
		if(topo.get_last_cpu_location(cpuset, EnumSet.of(HWLOC.CPUBIND_PROCESS)) < 0)
			System.out.println("Error on get_last_cpu_location");
		else
			System.out.println("process running on "+cpuset);

		int  nbcores = topo.get_nbobjs_by_type(HWLOC.OBJ_CORE);
		int  nbpus = topo.get_nbobjs_by_type(HWLOC.OBJ_PU);
		HwlocObject firstcore = topo.get_obj_by_type(HWLOC.OBJ_CORE, 0);
		HwlocObject lastcore = topo.get_obj_by_type(HWLOC.OBJ_CORE, nbcores-1);
		HwlocObject firstpu = topo.get_obj_by_type(HWLOC.OBJ_PU, 0);
		HwlocObject lastpu = topo.get_obj_by_type(HWLOC.OBJ_PU, nbpus-1);
		System.out.println("number of cores: "+nbcores);
		System.out.println("number of PUs: "+nbpus);
		System.out.println("first core cpuset: "+firstcore.getCPUSet());
		System.out.println("last core cpuset: "+lastcore.getCPUSet());
		System.out.println("first PU cpuset: "+firstpu.getCPUSet());
		System.out.println("last PU cpuset: "+lastpu.getCPUSet());

		System.out.println("main thread binding the entire process to the first core");
		HwlocBitmap.copy(cpuset, firstcore.getCPUSet());
		cpuset.singlify();
		// bind the entire process to the first core
		topo.set_cpubind(cpuset, EnumSet.of(HWLOC.CPUBIND_PROCESS));

		// get the current thread binding
		if(topo.get_cpubind(cpuset, EnumSet.of(HWLOC.CPUBIND_THREAD)) < 0)
			System.out.println("Error on get_cpubind");
		else
			System.out.println("main thread bound to "+cpuset);

		// get the current thread CPU location
		if(topo.get_last_cpu_location(cpuset, EnumSet.of(HWLOC.CPUBIND_THREAD)) < 0)
			System.out.println("Error on get_last_cpu_location");
		else
			System.out.println("main thread running on "+cpuset);

		// get the current process binding
		if(topo.get_cpubind(cpuset, EnumSet.of(HWLOC.CPUBIND_PROCESS)) < 0)
			System.out.println("Error on get_cpubind");
		else
			System.out.println("process bound to "+cpuset);

		// get the current process CPU location
		if(topo.get_last_cpu_location(cpuset, EnumSet.of(HWLOC.CPUBIND_PROCESS)) < 0)
			System.out.println("Error on get_last_cpu_location");
		else
			System.out.println("process running on "+cpuset);

		// re-bind the main thread on a single PU of the last core
		System.out.println("main thread rebinding the entire process to a single PU of the last core");
		HwlocBitmap.copy(cpuset, lastcore.getCPUSet());
		cpuset.singlify();
		topo.set_cpubind(cpuset, EnumSet.of(HWLOC.CPUBIND_PROCESS));

		// get the current thread binding
		if(topo.get_cpubind(cpuset, EnumSet.of(HWLOC.CPUBIND_THREAD)) < 0)
			System.out.println("Error on get_cpubind");
		else
			System.out.println("main thread bound to "+cpuset);

		// get the current thread CPU location
		if(topo.get_last_cpu_location(cpuset, EnumSet.of(HWLOC.CPUBIND_THREAD)) < 0)
			System.out.println("Error on get_last_cpu_location");
		else
			System.out.println("main thread running on "+cpuset);

		// get the current process binding
		if(topo.get_cpubind(cpuset, EnumSet.of(HWLOC.CPUBIND_PROCESS)) < 0)
			System.out.println("Error on get_cpubind");
		else
			System.out.println("process bound to "+cpuset);

		// get the current process CPU location
		if(topo.get_last_cpu_location(cpuset, EnumSet.of(HWLOC.CPUBIND_PROCESS)) < 0)
			System.out.println("Error on get_last_cpu_location");
		else
			System.out.println("process running on "+cpuset);

		// re-bind the main thread to the last PU
		System.out.println("main thread rebinding the current thread to the last PU");
		topo.set_cpubind(lastpu.getCPUSet(), EnumSet.of(HWLOC.CPUBIND_THREAD));

		// get the current thread binding
		if(topo.get_cpubind(cpuset, EnumSet.of(HWLOC.CPUBIND_THREAD)) < 0)
			System.out.println("Error on get_cpubind");
		else
			System.out.println("main thread bound to "+cpuset);

		// get the current thread CPU location
		if(topo.get_last_cpu_location(cpuset, EnumSet.of(HWLOC.CPUBIND_THREAD)) < 0)
			System.out.println("Error on get_last_cpu_location");
		else
			System.out.println("main thread running on "+cpuset);

		// get the current process binding
		if(topo.get_cpubind(cpuset, EnumSet.of(HWLOC.CPUBIND_PROCESS)) < 0)
			System.out.println("Error on get_cpubind");
		else
			System.out.println("process bound to "+cpuset);

		// get the current process CPU location
		if(topo.get_last_cpu_location(cpuset, EnumSet.of(HWLOC.CPUBIND_PROCESS)) < 0)
			System.out.println("Error on get_last_cpu_location");
		else
			System.out.println("process running on "+cpuset);


		cpuset.free();

		/* terminate this topology context */
		topo.destroy();
	}
}
