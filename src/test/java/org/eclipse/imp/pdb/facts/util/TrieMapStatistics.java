//package org.eclipse.imp.pdb.facts.util;
//
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import objectexplorer.ObjectGraphMeasurer.Footprint;
//
//import org.eclipse.imp.pdb.facts.ISet;
//import org.eclipse.imp.pdb.facts.ISetWriter;
//import org.eclipse.imp.pdb.facts.IValue;
//import org.eclipse.imp.pdb.facts.IValueFactory;
//import org.junit.Test;
//
//import com.google.common.base.Predicate;
//import com.google.common.base.Predicates;
//
//public class TrieMapStatistics {
//
////	private static int size = (int) Math.pow(2, 30); // 1_000_000;
//	
//	private ISet createSetWithContinuousIntegers(final IValueFactory valueFactory, final int size) {
//		ISetWriter writer = valueFactory.setWriter();
//		
//		for (int i = size; i > 0; i--) {
//			final IValue current = valueFactory.integer(i); 
//
//			writer.insert(current);
//		}
//		
//		return writer.done();		
//	}
//	
////	private ISet createSetWithRandomIntegers(final IValueFactory valueFactory, final int size) {
////		ISetWriter writer = valueFactory.setWriter();
////
////		Random rand = new Random();
////		
////		for (int i = size; i > 0; i--) {
////			final int j = rand.nextInt();
////			final IValue current = valueFactory.integer(j); 
////			
////			writer.insert(current);
////		}
////		
////		return writer.done();
////	}
//	
//	private TrieMap_5Bits<IValue, IValue> createMapWithContinuousIntegers(final IValueFactory valueFactory, final int size) {
//		TransientMap<IValue, IValue> transientMap = TrieMap_5Bits.transientOf(); 
//		
//		for (int i = size; i > 0; i--) {
//			final IValue current = valueFactory.integer(i); 
//			
//			transientMap.__put(current, current);
//		}
//		
//		return (TrieMap_5Bits<IValue, IValue>) transientMap.freeze();		
//	}
//	
//	private TrieMap_5Bits<IValue, IValue> createMapWithContinuousIntegersAtRandomStart(final IValueFactory valueFactory, final int size, final int run, final int bitmask) {
//		TransientMap<IValue, IValue> transientMap = TrieMap_5Bits.transientOf(); 
//		
//		// random data generator with fixed seed
//		Random rand = new Random(size + 13 * run);
//		// Random rand = new Random();
//		
//		final int start = rand.nextInt();
//		
//		for (int i = size; i > 0; i--) {
//
////			final int range00to04 = 0x1F;
////			final int range05to09 = 0x3E0;
////			final int range10to14 = 0x7C00;
////			final int range15to19 = 0xF8000;
////			final int range20to24 = 0x1F00000;
////			final int range25to29 = 0x3E000000;
////			final int range30to31 = 0xC0000000;
////			
////			final int range00to29 = 0x3FFFFFFF; // the first 30 bits
////			
////			final int j = rand.nextInt() & bitmask; // & 0xFC00000; // (range00to04 | range05to09);
////			final IValue current = valueFactory.integer(j); 
//			
//			final IValue current = valueFactory.integer(start + i);
//			
//			transientMap.__put(current, current);
//		}
//		
//		return (TrieMap_5Bits<IValue, IValue>) transientMap.freeze();		
//	}	
//	
//	private TrieMap_5Bits<IValue, IValue> createMapWithRandomIntegers(final IValueFactory valueFactory, final int size, final int run, final int bitmask) {
//		TransientMap<IValue, IValue> transientMap = TrieMap_5Bits.transientOf(); 
//		
//		// random data generator with fixed seed
//		Random rand = new Random(size + 13 * run);
//		// Random rand = new Random();
//		
//		for (int i = size; i > 0; i--) {
//
//			final int range00to04 = 0x1F;
//			final int range05to09 = 0x3E0;
//			final int range10to14 = 0x7C00;
//			final int range15to19 = 0xF8000;
//			final int range20to24 = 0x1F00000;
//			final int range25to29 = 0x3E000000;
//			final int range30to31 = 0xC0000000;
//			
//			final int range00to29 = 0x3FFFFFFF; // the first 30 bits
//			
//			final int j = rand.nextInt() & bitmask; // & 0xFC00000; // (range00to04 | range05to09);
//			final IValue current = valueFactory.integer(j); 
//			
//			transientMap.__put(current, current);
//		}
//		
//		return (TrieMap_5Bits<IValue, IValue>) transientMap.freeze();		
//	}
//	
//	private TrieSet_5Bits<IValue> createSetWithRandomIntegers(final IValueFactory valueFactory, final int size, final int run, final int bitmask) {
//		TransientSet<IValue> transientSet = TrieSet_5Bits.transientOf(); 
//		
//		// random data generator with fixed seed
//		Random rand = new Random(size + 13 * run);
//		// Random rand = new Random();
//		
//		for (int i = size; i > 0; i--) {
//
//			final int range00to04 = 0x1F;
//			final int range05to09 = 0x3E0;
//			final int range10to14 = 0x7C00;
//			final int range15to19 = 0xF8000;
//			final int range20to24 = 0x1F00000;
//			final int range25to29 = 0x3E000000;
//			final int range30to31 = 0xC0000000;
//			
//			final int range00to29 = 0x3FFFFFFF; // the first 30 bits
//			
//			final int j = rand.nextInt() & bitmask; // & 0xFC00000; // (range00to04 | range05to09);
//			final IValue current = valueFactory.integer(j); 
//			
//			transientSet.__insert(current);
//		}
//		
//		return (TrieSet_5Bits<IValue>) transientSet.freeze();		
//	}	
//		
//	// JUST FOR REFERENCE
////	private void measureAndReport(final IValueFactory valueFactory, final TrieMap_5Bits<IValue, IValue> trieMap, int elementCount, int run, boolean printHeader) {
////		final Iterator<AbstractMapNode<IValue, IValue>> it = TrieMap_5Bits.nodeIterator();
////
////		final Path file = Paths.get(String.format("tree-node-stats.csv"));
////		final List<String> lines = new ArrayList<>();
////		
////		if (printHeader) {
////			lines.add("arity,valueArity,nodeArity,size,elementCount,run");
////		}
////		
////		int sumNodes = 0;
////		int[] sumArity = new int[33];		
////		int[][] sumArityCombinations = new int[33][33];
////		
////		while (it.hasNext()) {
////			final AbstractMapNode<IValue, IValue> node = it.next();
////
////			sumNodes += 1;
////			sumArity[node.arity()] += 1;
////			sumArityCombinations[node.payloadArity()][node.nodeArity()] += 1;		
////			
//////			Predicate<Object> isRoot = new Predicate<Object>() {
//////				@Override
//////				public boolean apply(Object arg0) {
//////					return arg0 == node;
//////				}
//////			};
//////
//////			Predicate<Object> jointPredicate = Predicates.or(isRoot, Predicates.not(Predicates.or(
//////							Predicates.instanceOf(AbstractNode.class),
//////							Predicates.instanceOf(Integer.class))));
//////
//////			long memoryInBytes = objectexplorer.MemoryMeasurer.measureBytes(node, jointPredicate);
//////			Footprint memoryFootprint = objectexplorer.ObjectGraphMeasurer.measure(node,
//////							jointPredicate);
//////
//////			// if (node.arity() == 32) {
//////			final int pointers = 2 * node.payloadArity() + node.nodeArity();
//////
//////			final String statString = String
//////							.format("arity=%d [values=%d, nodes=%d]\n%d bytes [%1.1f bytes per pointer]\n%s\n",
//////											node.arity(), node.payloadArity(), node.nodeArity(),
//////											memoryInBytes, (float) memoryInBytes / pointers,
//////											memoryFootprint);
//////
//////			System.out.println(statString);
////
////			long memoryInBytes = 0;
////			
////			final String statFileString = String.format("%d,%d,%d,%d,%d,%d", node.arity(),
////							node.payloadArity(), node.nodeArity(), memoryInBytes, elementCount, run);
////			lines.add(statFileString);
////			// }
////		}
////
////		System.out.println(String.format("generated: %d, effective: %d, totalNodes: %d", elementCount, TrieMap_5Bits.size(), sumNodes));
//////		System.out.println(trieMap);
////		
////		float threshhold = 0.01f;
////		
////		int cumsum = 0;
////		int[] cumsumArity = new int[33];		
////
////		for (int i = 0; i < 33; i++) {
////			cumsum += sumArity[i];
////			cumsumArity[i] = cumsum;
////		}
////		
////		for (int i = 0; i < 33; i++) {
////			float arityPercentage = (float) (sumArity[i]) / sumNodes;
////			float cumsumArityPercentage = (float) (cumsumArity[i]) / sumNodes;
////			
////			if (arityPercentage != 0 && arityPercentage >= threshhold) {
////				// details per level
////				StringBuilder bldr = new StringBuilder();
////				int max = i;
////				for (int j = 0; j <= max; j++) {
////					for (int k = max - j; k <= max - j; k++) {
////						float arityCombinationsPercentage = (float) (sumArityCombinations[j][k]) / sumNodes;
////						
////						if (arityCombinationsPercentage != 0 && arityCombinationsPercentage >= threshhold) {
////							bldr.append(String.format("%d/%d: %s, ", j, k, new DecimalFormat("0.00%").format(arityCombinationsPercentage)));
//////							System.out.println(String.format("%2d/%2d: %s\t[cumsum = %s], ", j, k, new DecimalFormat("0.00%").format(arityCombinationsPercentage), new DecimalFormat("0.00%").format(cumsumArityPercentage)));				
////						}
////					}
////				}
////				final String detailPercentages = bldr.toString();
////				
////				// overview
////				System.out.println(String.format("%2d: %s\t[cumsum = %s]\t%s", i, new DecimalFormat("0.00%").format(arityPercentage), new DecimalFormat("0.00%").format(cumsumArityPercentage), detailPercentages));
////			}
////		}
////		
//////		// Combination of arity
//////		for (int max = 0; max < 33; max++) {
//////			System.out.println("---" + max);
//////
//////			for (int i = 0; i <= max; i++) {
//////				for (int j = max - i; j <= max - i; j++) {
//////					float arityPercentage = (float) (sumArityCombinations[i][j]) / sumNodes;
//////					float cumsumArityPercentage = (float) (cumsumArity[i+j]) / sumNodes;
//////					
//////					if (arityPercentage != 0) {
//////						System.out.println(String.format("%2d/%2d: %s\t[cumsum = %s], ", i, j, new DecimalFormat("0.00%").format(arityPercentage), new DecimalFormat("0.00%").format(cumsumArityPercentage)));				
//////					}
//////				}
//////			}		
//////		}
////		
////		
//////		Predicate<Object> totalPredicate = Predicates.not(Predicates.instanceOf(Integer.class));
//////
//////		long totalMemoryInBytes = objectexplorer.MemoryMeasurer.measureBytes(TrieMap_5Bits.getRootNode(),
//////						totalPredicate);
//////		Footprint totalMemoryFootprint = objectexplorer.ObjectGraphMeasurer.measure(
//////						TrieMap_5Bits.getRootNode(), totalPredicate);
//////
//////		final String totalStatString = String.format(
//////						"size=%d\n%d bytes [%1.1f bytes per key-value-pair; min 8.0 bytes]\n%s\n",
//////						TrieMap_5Bits.size(), totalMemoryInBytes,
//////						(float) totalMemoryInBytes / TrieMap_5Bits.size(), totalMemoryFootprint);
//////
//////		System.out.println(totalStatString);
////
////		// write stats to file
////		try {
////			if (printHeader) {
////				Files.write(file, lines, StandardCharsets.UTF_8);
////			} else {
////				Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//	
//	private void measureAndReport(final IValueFactory valueFactory, final TrieMap_5Bits<IValue, IValue> trieMap, int elementCount, int bitPartitionSize, int run, boolean printHeader) {
//		final Path fileHistogram = Paths.get(String.format("tree-node-stats.csv"));
//		final List<String> linesHistogram = new ArrayList<>();
//		
//		final Path fileHistogramCombinations = Paths.get(String.format("tree-node-stats-comb.csv"));
//		final List<String> linesHistogramCombinations = new ArrayList<>();
//				
//		int sumNodes = TrieMap_5Bits.getNodeCount();
//		int[] sumArity = TrieMap_5Bits.arityHistogram();		
//		int[][] sumArityCombinations = TrieMap_5Bits.arityCombinationsHistogram();
//		
//		if (printHeader) {
//			StringBuilder bldrHist = new StringBuilder();
//			for (int arity = 0; arity < sumArity.length; arity++) { 
//				if (bldrHist.length() > 0) {
//					bldrHist.append(',');
//				}
//				bldrHist.append(arity);
//			}
//			/***/
//			linesHistogram.add(String.format("%s,%s", bldrHist.toString(), "elementCount,sumNodes,bitPartitionSize"));
//			
//			StringBuilder bldrComb = new StringBuilder();
//			for (int payloadArity = 0; payloadArity < sumArityCombinations.length; payloadArity++) { 
//				for (int nodeArity = 0; nodeArity < sumArityCombinations.length; nodeArity++) {
//				
//					if (bldrComb.length() > 0) {
//						bldrComb.append(',');
//					}
//					bldrComb.append(String.format("%s/%s", payloadArity, nodeArity));
//				}
//			}
//			/***/
//			linesHistogramCombinations.add(String.format("%s,%s", bldrComb.toString(), "elementCount,sumNodes,bitPartitionSize"));
//		}
//				
//		StringBuilder bldrHist = new StringBuilder();
//		for (int arity : sumArity) {
//			if (bldrHist.length() > 0) {
//				bldrHist.append(',');
//			}
//			bldrHist.append(arity);
//		}
//		/***/
//		linesHistogram.add(String.format("%s,%d,%d,%d", bldrHist.toString(), elementCount, sumNodes, bitPartitionSize));
//		
//		StringBuilder bldrComb = new StringBuilder();
//		for (int payloadArity = 0; payloadArity < sumArityCombinations.length; payloadArity++) { 
//			for (int nodeArity = 0; nodeArity < sumArityCombinations.length; nodeArity++) {
//			
//				if (bldrComb.length() > 0) {
//					bldrComb.append(',');
//				}
//				bldrComb.append(sumArityCombinations[payloadArity][nodeArity]);
//			}
//		}
//		/***/
//		linesHistogramCombinations.add(String.format("%s,%d,%d,%d", bldrComb.toString(), elementCount, sumNodes, bitPartitionSize));
//		
//		// write stats to file
//		try {
//			if (printHeader) {
//				Files.write(fileHistogram, linesHistogram, StandardCharsets.UTF_8);
//				Files.write(fileHistogramCombinations, linesHistogramCombinations, StandardCharsets.UTF_8);
//			} else {
//				Files.write(fileHistogram, linesHistogram, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
//				Files.write(fileHistogramCombinations, linesHistogramCombinations, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}	
//	
////	@Test
////	public void footprintMap__Continuous_Persistent() {
////		final IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory.getInstance();
////		final int size = (int) Math.pow(2, 20); // 1_000_000;
////
////		for (int run = 0; run < 10; run++) {
////			TrieMap_5Bits<IValue, IValue> map = createMapWithContinuousIntegers(valueFactory, size);
////			measureAndReport(valueFactory, map, size, run);			
////		}	
////	}
//	
////	@Test
////	public void footprintMap__Random_Persistent() {
////		final IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory.getInstance();
////
////		boolean printHeader = true;
////		
//////		for (int exp = 5; exp <= 5; exp += 1) { // 13 .. 23
//////			final int count = (int) Math.pow(2, exp);
//////
//////			for (int run = 0; run < 100; run++) {
//////				TrieMap_5Bits<IValue, IValue> map = createMapWithRandomIntegers(valueFactory, count);
//////				measureAndReport(valueFactory, map, count, run, printHeader);
////////				map.printStatistics();
//////				printHeader = false;
//////			}
//////		}
////		
////		Random rand = new Random();
////		
////		for (int i = 0; i <= 2_600; i += 1) {
////			final int count = rand.nextInt((int) Math.pow(2, 25));
////			
////			TrieMap_5Bits<IValue, IValue> map = createMapWithRandomIntegers(valueFactory, count);
////			measureAndReport(valueFactory, map, count, i, printHeader);
////			printHeader = false;			
////		}
////	}	
//	
//	@Test
//	public void footprintMap__RandomRandom_Persistent() {
//		final IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory.getInstance();
//
//		boolean printHeader = true;
//		
////		for (int exp = 5; exp <= 5; exp += 1) { // 13 .. 23
////			final int count = (int) Math.pow(2, exp);
////
////			for (int run = 0; run < 100; run++) {
////				TrieMap_5Bits<IValue, IValue> map = createMapWithRandomIntegers(valueFactory, count);
////				measureAndReport(valueFactory, map, count, run, printHeader);
//////				map.printStatistics();
////				printHeader = false;
////			}
////		}
//		
////		Random rand = new Random();
////		
////		for (int i = 0; i <= 2_600; i += 1) {
////			final int count = rand.nextInt((int) Math.pow(2, 25));
//
//		int MAX = (int) Math.pow(2, 23);
//		
//		// random data generator with fixed seed
//		Random rand = new Random(2305843009213693951L); // seed == Mersenne Prime #9	
//		
//		for (int i = 0; i < 256; i++) {
//			final int count = rand.nextInt(MAX);		
//		
////			if (i >= 214) {
//				TrieMap_5Bits<IValue, IValue> map = createMapWithRandomIntegers(valueFactory, count, 0, 0xFFFFFFFF);
//				measureAndReport(valueFactory, map, count, TrieMap_5Bits.CompactMapNode.BIT_PARTITION_SIZE, i, printHeader);
////			}
//			
////			long memoryInBytes = measureSize(map);
////			Footprint memoryFootprint = measureFootprint(map);			
////			
////			final String statString = String.format("%d bytes [%s]", memoryInBytes, memoryFootprint);
////			System.out.println(statString);
//			
//			printHeader = false;
//		}
//	}
//	
//	@Test
//	public void footprintMap__LinearRandom_Persistent() {
//		final IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory
//						.getInstance();
//
//		boolean printHeader = true;
//
//		int MIN = 0;
//		int MAX = (int) Math.pow(2, 23);
//		int STEP = 1_000;
//		int BITMASK = 0xFFFFFFFF; // all 32 bits		
//				
////		int MIN = 0;
////		int MAX = (int) Math.pow(2, 20);
////		int STEP = 1_000;
////		int BITMASK = 0x3FFFFFFF; // the first 30 bits
//		
////		int MIN = 8389000;
////		int MAX = (int) Math.pow(2, 24);
////		int STEP = 100_000;
////		int BITMASK = 0xFFFFFFFF;
//
////		int MIN = 16789000;
////		int MAX = (int) Math.pow(2, 26);
////		int STEP = 100_000;
////		int BITMASK = 0xFFFFFFFF;
//		
//		int RUN = 0;
//
//		for (int count = MIN; count < MAX; count += STEP) {
//			for (int bitChunkSize = 1; bitChunkSize <= 6; bitChunkSize++) {
//				TrieMap_5Bits.CompactMapNode.setBitPartitionSize(bitChunkSize);
//
//				TrieMap_5Bits<IValue, IValue> map = createMapWithRandomIntegers(valueFactory, count, RUN, BITMASK);
//
//				measureAndReport(valueFactory, map, count,
//								TrieMap_5Bits.CompactMapNode.BIT_PARTITION_SIZE, RUN, printHeader);
//
//				printHeader = false;
//			}
//		}
//	}
//	
//	@Test
//	public void footprintMap__LinearRandom_Persistent_Lower24Bits() {
//		final IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory
//						.getInstance();
//
//		boolean printHeader = true;
//
//		int MIN = 0;
//		int MAX = (int) Math.pow(2, 23);
//		int STEP = 1_000;
//		int BITMASK = 0xFFFFFF; // lower 24 bits (good prefix)		
//				
////		int MIN = 0;
////		int MAX = (int) Math.pow(2, 20);
////		int STEP = 1_000;
////		int BITMASK = 0x3FFFFFFF; // the first 30 bits
//		
////		int MIN = 8389000;
////		int MAX = (int) Math.pow(2, 24);
////		int STEP = 100_000;
////		int BITMASK = 0xFFFFFFFF;
//
////		int MIN = 16789000;
////		int MAX = (int) Math.pow(2, 26);
////		int STEP = 100_000;
////		int BITMASK = 0xFFFFFFFF;
//		
//		int RUN = 0;
//
//		for (int count = MIN; count < MAX; count += STEP) {
//			for (int bitChunkSize = 1; bitChunkSize <= 6; bitChunkSize++) {
//				TrieMap_5Bits.CompactMapNode.setBitPartitionSize(bitChunkSize);
//
//				TrieMap_5Bits<IValue, IValue> map = createMapWithRandomIntegers(valueFactory, count, RUN, BITMASK);
//
//				measureAndReport(valueFactory, map, count,
//								TrieMap_5Bits.CompactMapNode.BIT_PARTITION_SIZE, RUN, printHeader);
//
//				printHeader = false;
//			}
//		}
//	}		
//	
//	@Test
//	public void footprintMap__LinearRandom_Persistent_Upper24Bits() {
//		final IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory
//						.getInstance();
//
//		boolean printHeader = true;
//
////		int MIN = 0;
////		int MAX = (int) Math.pow(2, 23);
////		int STEP = 1_000;
////		int BITMASK = 0xFFFFFF00; // upper 24 bits (bad prefix)		
//		
////		int MIN = 5622000;
////		int MAX = (int) Math.pow(2, 23);
////		int STEP = 1_000;
////		int BITMASK = 0xFFFFFF00; // upper 24 bits (bad prefix)				
//
//		int MIN = 7672000;
//		int MAX = (int) Math.pow(2, 23);
//		int STEP = 1_000;
//		int BITMASK = 0xFFFFFF00; // upper 24 bits (bad prefix)				
//		
////		int MIN = 0;
////		int MAX = (int) Math.pow(2, 20);
////		int STEP = 1_000;
////		int BITMASK = 0x3FFFFFFF; // the first 30 bits
//		
////		int MIN = 8389000;
////		int MAX = (int) Math.pow(2, 24);
////		int STEP = 100_000;
////		int BITMASK = 0xFFFFFFFF;
//
////		int MIN = 16789000;
////		int MAX = (int) Math.pow(2, 26);
////		int STEP = 100_000;
////		int BITMASK = 0xFFFFFFFF;
//				
//		int RUN = 0;
//
//		for (int count = MIN; count < MAX; count += STEP) {
//			for (int bitChunkSize = 1; bitChunkSize <= 6; bitChunkSize++) {
//				TrieMap_5Bits.CompactMapNode.setBitPartitionSize(bitChunkSize);
//
//				TrieMap_5Bits<IValue, IValue> map = createMapWithRandomIntegers(valueFactory, count, RUN, BITMASK);
//
//				measureAndReport(valueFactory, map, count,
//								TrieMap_5Bits.CompactMapNode.BIT_PARTITION_SIZE, RUN, printHeader);
//
//				printHeader = false;
//			}
//		}
//	}	
//	
//	@Test
//	public void footprintMap__LinearLinearWithRandomStart_Persistent() {
//		final IValueFactory valueFactory = org.eclipse.imp.pdb.facts.impl.persistent.ValueFactory
//						.getInstance();
//
//		boolean printHeader = true;
//
//		int MIN = 0;
//		int MAX = (int) Math.pow(2, 23);
//		int STEP = 1_000;
//		int BITMASK = 0xFFFFFFFF; // all 32 bits		
//				
////		int MIN = 0;
////		int MAX = (int) Math.pow(2, 20);
////		int STEP = 1_000;
////		int BITMASK = 0x3FFFFFFF; // the first 30 bits
//		
////		int MIN = 8389000;
////		int MAX = (int) Math.pow(2, 24);
////		int STEP = 100_000;
////		int BITMASK = 0xFFFFFFFF;
//
////		int MIN = 16789000;
////		int MAX = (int) Math.pow(2, 26);
////		int STEP = 100_000;
////		int BITMASK = 0xFFFFFFFF;
//		
//		int RUN = 0;
//
//		for (int count = MIN; count < MAX; count += STEP) {
//			for (int bitChunkSize = 1; bitChunkSize <= 6; bitChunkSize++) {
//				TrieMap_5Bits.CompactMapNode.setBitPartitionSize(bitChunkSize);
//
//				TrieMap_5Bits<IValue, IValue> map = createMapWithContinuousIntegersAtRandomStart(valueFactory, count, RUN, BITMASK);
//
//				measureAndReport(valueFactory, map, count,
//								TrieMap_5Bits.CompactMapNode.BIT_PARTITION_SIZE, RUN, printHeader);
//
//				printHeader = false;
//			}
//		}
//	}	
//	
//	private long measureSize(final Object objectToMeasure) {
//		Predicate<Object> isRoot = new Predicate<Object>() {
//			@Override
//			public boolean apply(Object arg0) {
//				return arg0 == objectToMeasure;
//			}			
//		};
//
//		Predicate<Object> jointPredicate = Predicates.or(isRoot,
//						Predicates.not(Predicates.instanceOf(IValue.class)));
//
//		long memoryInBytes = objectexplorer.MemoryMeasurer.measureBytes(objectToMeasure,
//						jointPredicate);
//		return memoryInBytes;
//	}
//	
//	private Footprint measureFootprint(final Object objectToMeasure) {
//		Predicate<Object> isRoot = new Predicate<Object>() {
//			@Override
//			public boolean apply(Object arg0) {
//				return arg0 == objectToMeasure;
//			}
//		};
//
//		Predicate<Object> jointPredicate = Predicates.or(isRoot,
//						Predicates.not(Predicates.instanceOf(IValue.class)));
//
//		Footprint memoryFootprint = objectexplorer.ObjectGraphMeasurer.measure(objectToMeasure,
//						jointPredicate);
//
//		return memoryFootprint;
//	}	
//	
//}
