package org.eclipse.imp.pdb.facts.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import objectexplorer.ObjectGraphMeasurer.Footprint;

import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.ISetWriter;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.IValueFactory;
import org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import scala.Tuple2;
import clojure.lang.IPersistentMap;
import clojure.lang.IPersistentSet;
import clojure.lang.ITransientMap;
import clojure.lang.ITransientSet;
import clojure.lang.PersistentHashMap;
import clojure.lang.PersistentHashSet;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import static org.eclipse.imp.pdb.facts.util.TrieMapVsOthersFootprint.Archetype.*;
import static org.eclipse.imp.pdb.facts.util.TrieMapVsOthersFootprint.DataType.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrieMapVsOthersFootprint {

	public enum Archetype {
		MUTABLE,
		IMMUTABLE,
		PERSISTENT
	}

	public enum DataType {
		MAP,
		SET
	}

	private static final IValueFactory valueFactory = ValueFactory.getInstance();

	private static boolean reportSet = true;
	private static boolean reportMap = false;

//	private static int size;
//	private static int run;
	
//	private static ISet testSet;
//	private static IMap testMap;


	// re-create output file
	// TODO: include run in output
	final static String csvHeader = "elementCount,run,className,dataType,archetype,supportsStagedMutability,footprintInBytes,footprintInObjects,footprintInReferences"; // ,footprintInPrimitives

	
	@BeforeClass
	public static void setUp() throws Exception {
//		setUpTestSetWithRandomContent(50_000);
		
		writeToFile(false, csvHeader);
	}
//	
//	public static ISet setUpTestSetWithRandomContent(int size) throws Exception {
////		TrieMapVsOthersFootprint.size = size;
//		
//		ISetWriter setWriter = valueFactory.setWriter();
//	
//		// random data generator with fixed seed
//		Random rand = new Random(2305843009213693951L); // seed == Mersenne Prime #9
//		
//		for (int i = size; i > 0; i--) {
//			final int j = rand.nextInt();
//			final IValue current = valueFactory.integer(j); 
//						
//			setWriter.insert(current);
//		}
//
//		return setWriter.done();
//	}
	
	public static ISet setUpTestSetWithRandomContent(int size, int run) throws Exception {
//		TrieMapVsOthersFootprint.size = size;
		
		ISetWriter setWriter = valueFactory.setWriter();
	
		// random data generator with fixed seed
		Random rand = new Random(size + 13 * run);
		
		for (int i = size; i > 0; i--) {
			final int j = rand.nextInt();
			final IValue current = valueFactory.integer(j); 
						
			setWriter.insert(current);
		}

		return setWriter.done();
	}	
	
	public void timeTrieSet(final ISet testSet, int elementCount, int run) {
		TransientSet<IValue> transientSet = TrieSet.<IValue>transientOf(); 
		TransientMap<IValue, IValue> transientMap = TrieMap.<IValue, IValue>transientOf();		
		
		for (IValue v : testSet) {
			transientSet.__insert(v);
			transientMap.__put(v, v);
		}
		
		ImmutableSet<IValue> xs = transientSet.freeze();
		ImmutableMap<IValue, IValue> ys = transientMap.freeze();
		
		if (reportSet) measureAndReport(xs, "org.eclipse.imp.pdb.facts.util.TrieSet", SET, PERSISTENT, false, elementCount, run);
		if (reportMap) measureAndReport(ys, "org.eclipse.imp.pdb.facts.util.TrieMap", MAP, PERSISTENT, false, elementCount, run);
	}	
	
	public void timeTrieSet0To4(final ISet testSet, int elementCount, int run) {
//		TransientSet<IValue> transientSet = TrieSet0To4.<IValue>transientOf(); 
//		TransientMap<IValue, IValue> transientMap = TrieMap0To4.<IValue, IValue>transientOf();		
//		
//		for (IValue v : testSet) {
//			transientSet.__insert(v);
//			transientMap.__put(v, v);
//		}
//		
//		ImmutableSet<IValue> xs = transientSet.freeze();
//		ImmutableMap<IValue, IValue> ys = transientMap.freeze();
//		
//		if (reportSet) measureAndReport(xs, "org.eclipse.imp.pdb.facts.util.TrieSet0To4", SET, PERSISTENT, false, elementCount, run);
//		if (reportMap) measureAndReport(ys, "org.eclipse.imp.pdb.facts.util.TrieMap0To4", MAP, PERSISTENT, false, elementCount, run);
	}	

	public void timeTrieSet0To8(final ISet testSet, int elementCount, int run) {
//		TransientSet<IValue> transientSet = TrieSet0To8.<IValue>transientOf();
//		TransientMap<IValue, IValue> transientMap = TrieMap0To8.<IValue, IValue>transientOf();
//		
//		for (IValue v : testSet) {
//			transientSet.__insert(v);
//			transientMap.__put(v, v);
//		}
//		
//		ImmutableSet<IValue> xs = transientSet.freeze();
//		ImmutableMap<IValue, IValue> ys = transientMap.freeze();
//		
//		if (reportSet) measureAndReport(xs, "org.eclipse.imp.pdb.facts.util.TrieSet0To8", SET, PERSISTENT, false, elementCount, run);
//		if (reportMap) measureAndReport(ys, "org.eclipse.imp.pdb.facts.util.TrieMap0To8", MAP, PERSISTENT, false, elementCount, run);
	}	

	public void timeTrieSet0To12(final ISet testSet, int elementCount, int run) {
//		TransientSet<IValue> transientSet = TrieSet0To12.<IValue>transientOf();
//		TransientMap<IValue, IValue> transientMap = TrieMap0To12.<IValue, IValue>transientOf();
//		
//		for (IValue v : testSet) {
//			transientSet.__insert(v);
//			transientMap.__put(v, v);
//		}
//		
//		ImmutableSet<IValue> xs = transientSet.freeze();
//		ImmutableMap<IValue, IValue> ys = transientMap.freeze();
//		
//		if (reportSet) measureAndReport(xs, "org.eclipse.imp.pdb.facts.util.TrieSet0To12", SET, PERSISTENT, false, elementCount, run);
//		if (reportMap) measureAndReport(ys, "org.eclipse.imp.pdb.facts.util.TrieMap0To12", MAP, PERSISTENT, false, elementCount, run);
	}		
	
	public void timeTrieSetDynamic(final ISet testSet, int elementCount, int run) {
//		TransientSet<IValue> transientSet = TrieSetDynamic.<IValue>transientOf(); 
//		TransientMap<IValue, IValue> transientMap = TrieMapDynamic.<IValue, IValue>transientOf();		
//		
//		for (IValue v : testSet) {
//			transientSet.__insert(v);
//			transientMap.__put(v, v);
//		}
//		
//		ImmutableSet<IValue> xs = transientSet.freeze();
//		ImmutableMap<IValue, IValue> ys = transientMap.freeze();
//		
//		if (reportSet) measureAndReport(xs, "org.eclipse.imp.pdb.facts.util.TrieSetDynamic", SET, PERSISTENT, false, elementCount, run);
//		if (reportMap) measureAndReport(ys, "org.eclipse.imp.pdb.facts.util.TrieMapDynamic", MAP, PERSISTENT, false, elementCount, run);
	}	
	
	public void timeJavaMutable(final ISet testSet, int elementCount, int run) {
		Set<IValue> xs = new HashSet<>();
		Map<IValue, IValue> ys = new HashMap<>();
		
		for (IValue v : testSet) {
			xs.add(v);
			ys.put(v, v);
		}
		
		if (reportSet) measureAndReport(xs, "java.util.HashSet", SET, MUTABLE, true, elementCount, run);
		if (reportMap) measureAndReport(ys, "java.util.HashMap", MAP, MUTABLE, true, elementCount, run);
	}
	
	public void timeGSMutableUnifiedSet(final ISet testSet, int elementCount, int run) {
		Set<IValue> xs = new com.gs.collections.impl.set.mutable.UnifiedSet<>();
		Map<IValue, IValue> ys = new com.gs.collections.impl.map.mutable.UnifiedMap<>();
		
		for (IValue v : testSet) {
			xs.add(v);
			ys.put(v, v);
		}
		
		if (reportSet) measureAndReport(xs, "com.gs.collections.impl.set.mutable.UnifiedSet", SET, MUTABLE, true, elementCount, run);
		if (reportMap) measureAndReport(ys, "com.gs.collections.impl.map.mutable.UnifiedMap", MAP, MUTABLE, true, elementCount, run);
	}

	public void timeGuavaImmutable(final ISet testSet, int elementCount, int run) {
		com.google.common.collect.ImmutableSet.Builder<IValue> xsBldr = com.google.common.collect.ImmutableSet.builder();
		com.google.common.collect.ImmutableMap.Builder<IValue, IValue> ysBldr = com.google.common.collect.ImmutableMap.builder();
		
		for (IValue v : testSet) {
			xsBldr.add(v);
			ysBldr.put(v, v);
		}
		
		com.google.common.collect.ImmutableSet<IValue> xs = xsBldr.build();
		com.google.common.collect.ImmutableMap<IValue, IValue> ys = ysBldr.build();
		
		if (reportSet) measureAndReport(xs, "com.google.common.collect.ImmutableSet", SET, IMMUTABLE, false, elementCount, run);
		if (reportMap) measureAndReport(ys, "com.google.common.collect.ImmutableMap", MAP, IMMUTABLE, false, elementCount, run);
	}

//	@Test
//	public void timeSetWriter() {
//		ISetWriter writer = valueFactory.setWriter();
//		for (IValue v : testSet) {
//			writer.insert(v);
//		}
//		writer.done();
//	}

	public void timeClojureTransient(final ISet testSet, int elementCount, int run) {
		ITransientSet xs = (ITransientSet) PersistentHashSet.EMPTY.asTransient();
		ITransientMap ys = (ITransientMap) PersistentHashMap.EMPTY.asTransient();

		for (IValue v : testSet) {
			xs = (ITransientSet) xs.conj(v);
			ys = (ITransientMap) ys.assoc(v, v);
		}
		
		if (reportSet) measureAndReport(xs, "clojure.lang.TransientHashSet", SET, PERSISTENT, true, elementCount, run);
		if (reportMap) measureAndReport(ys, "clojure.lang.TransientHashMap", MAP, PERSISTENT, true, elementCount, run);
	}

	public void timeClojurePersistent(final ISet testSet, int elementCount, int run) {
		IPersistentSet xs = (IPersistentSet) PersistentHashSet.EMPTY;
		IPersistentMap ys = (IPersistentMap) PersistentHashMap.EMPTY;
		
		for (IValue v : testSet) {
			xs = (IPersistentSet) xs.cons(v);
			ys = (IPersistentMap) ys.assoc(v, v);
		}
		
		if (reportSet) measureAndReport(xs, "clojure.lang.PersistentHashSet", SET, PERSISTENT, true, elementCount, run);
		if (reportMap) measureAndReport(ys, "clojure.lang.PersistentHashMap", MAP, PERSISTENT, true, elementCount, run);
	}
	
	public void timeScalaPersistent(final ISet testSet, int elementCount, int run) {
		scala.collection.immutable.HashSet<IValue> xs = new scala.collection.immutable.HashSet<>();
		scala.collection.immutable.HashMap<IValue, IValue> ys = new scala.collection.immutable.HashMap<>();
		
		for (IValue v : testSet) {
			xs = xs.$plus(v);
			ys = ys.$plus(new Tuple2<>(v, v));
		}

		if (reportSet) measureAndReport(xs, "scala.collection.immutable.HashSet", SET, PERSISTENT, false, elementCount, run);
		if (reportMap) measureAndReport(ys, "scala.collection.immutable.HashMap", MAP, PERSISTENT, false, elementCount, run);
	}

	public void timeScalaMutable(final ISet testSet, int elementCount, int run) {
		scala.collection.mutable.HashSet<IValue> xs = new scala.collection.mutable.HashSet<>();
		scala.collection.mutable.HashMap<IValue, IValue> ys = new scala.collection.mutable.HashMap<>();
		
		for (IValue v : testSet) {
			xs = xs.$plus$eq(v);
			ys = ys.$plus$eq(new Tuple2<>(v, v));
		}

		if (reportSet) measureAndReport(xs, "scala.collection.mutable.HashSet", SET, MUTABLE, true, elementCount, run);
		if (reportMap) measureAndReport(ys, "scala.collection.mutable.HashMap", MAP, MUTABLE, true, elementCount, run);
	}

//@SuppressWarnings({ "rawtypes", "unchecked" })
//public void timeScalaSetBuilderPersistent(int reps) { 
//for (int i = 0; i < reps; i++) {
//	scala.collection.Set<IValue> empty = scala.collection.immutable.Set$.MODULE$.empty();
//	scala.collection.mutable.SetBuilder builder = new SetBuilder(empty);
//	for (IValue v : testSet) {
//		builder = builder.$plus$eq(v);
//	}
//	builder.result();
//}
//}	
//
//@SuppressWarnings({ "rawtypes", "unchecked" })
//public void timeScalaSetBuilderMutable(int reps) { 
//for (int i = 0; i < reps; i++) {
//	scala.collection.Set<IValue> empty = scala.collection.mutable.Set$.MODULE$.empty();
//	scala.collection.mutable.SetBuilder builder = new SetBuilder(empty);
//	for (IValue v : testSet) {
//		builder = builder.$plus$eq(v);
//	}
//	builder.result();
//}
//}	
	
	private void measureAndReport(final Object objectToMeasure, final String className, DataType dataType, Archetype archetype, boolean supportsStagedMutability, int size, int run) {
		Predicate<Object> jointPredicate = Predicates.not(Predicates.instanceOf(IValue.class));
		
		long memoryInBytes = objectexplorer.MemoryMeasurer.measureBytes(objectToMeasure,
						jointPredicate);
		Footprint memoryFootprint = objectexplorer.ObjectGraphMeasurer.measure(objectToMeasure,
						jointPredicate);

		final String statString = String.format("%d\t %60s\t\t %s", memoryInBytes, className,
						memoryFootprint);
		System.out.println(statString);

//		final String statLatexString = String.format("%s & %s & %s & %b & %d & %d & %d & \"%s\" \\\\", className, dataType, archetype, supportsStagedMutability, memoryInBytes, memoryFootprint.getObjects(), memoryFootprint.getReferences(), memoryFootprint.getPrimitives());
//		System.out.println(statLatexString);		

		
		final String statFileString = String.format("%d,%d,%s,%s,%s,%b,%d,%d,%d", size, run, className, dataType, archetype, supportsStagedMutability, memoryInBytes, memoryFootprint.getObjects(), memoryFootprint.getReferences());		
		
		writeToFile(statFileString);
	}
	
	private static void writeToFile(String line) {
		writeToFile(true, line);
	}
	
	private static void writeToFile(boolean isAppendingToFile, String line) {
		final List<String> lines = new ArrayList<>();
		lines.add(line);
		
		writeToFile(isAppendingToFile, lines);
	}
	
	private static void writeToFile(boolean isAppendingToFile, List<String> lines) {
		final Path file = Paths.get(String.format("map-sizes-and-statistics.csv"));
				
		// write stats to file
		try {
			if (isAppendingToFile) {
				Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			} else {
				Files.write(file, lines, StandardCharsets.UTF_8);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void footprintMap__Random_Persistent() {	
		int MAX = (int) Math.pow(2, 23);
				
		// random data generator with fixed seed
		Random rand = new Random(2305843009213693951L); // seed == Mersenne Prime #9	
		
		for (int i = 0; i < 256; i++) {
			final int count = rand.nextInt(MAX);
//		for (int exp = 3; exp <= 23; exp += 1) { // 13 .. 23
//			final int count = (int) Math.pow(2, exp);
			
//			if (i < 214)
//				continue;			

			for (int run = 0; run < 1; run++) { // 100

//				ExecutorService pool = Executors.newFixedThreadPool(5);
				
				ISet tmpSet = null;
				
				try {
//					setUpTestSetWithRandomContent(count);
					tmpSet = setUpTestSetWithRandomContent(count, run);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				final ISet testSet = tmpSet;
				final int currentRun = run;
				
				timeTrieSet(testSet, count, currentRun);				
				timeTrieSet0To4(testSet, count, currentRun);
				timeTrieSet0To8(testSet, count, currentRun);
				timeTrieSet0To12(testSet, count, currentRun);				
				timeTrieSetDynamic(testSet, count, currentRun);
				timeClojurePersistent(testSet, count, currentRun);
				timeScalaPersistent(testSet, count, currentRun);

//				pool.execute(new Runnable() {				
//					@Override
//					public void run() {
//					}
//				});
//				pool.execute(new Runnable() {				
//					@Override
//					public void run() {
//					}
//				});
//				pool.execute(new Runnable() {				
//					@Override
//					public void run() {
//					}
//				});
//				pool.execute(new Runnable() {				
//					@Override
//					public void run() {
//					}
//				});
//				pool.execute(new Runnable() {				
//					@Override
//					public void run() {
//					}
//				});				
//
//				pool.shutdown();
//				try {
//					pool.awaitTermination(1, TimeUnit.HOURS);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				
//						timeJavaMutable(testSet, count, currentRun);
//						timeGSMutableUnifiedSet(testSet, count, currentRun);
//						timeGuavaImmutable(testSet, count, currentRun);
				// timeClojureTransient(testSet, count, currentRun);
//						timeScalaMutable(testSet, count, currentRun);
			}
		}		
	}
	
}
