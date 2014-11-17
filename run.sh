####
# Original command 
##
# gradle test --tests *TrieMapStatistics.footprintMap__LinearRandom_Persistent

# gradle test --tests *TrieMapStatistics.footprintMap__LinearRandom_Persistent_Lower24Bits
# mv tree-node-stats*.csv ./__footprintMap__LinearRandom_Persistent_Lower24Bits

# gradle test --tests *TrieMapStatistics.footprintMap__LinearRandom_Persistent_Upper24Bits
# mv tree-node-stats*.csv ./__footprintMap__LinearRandom_Persistent_Upper24Bits

# gradle test --tests *TrieMapStatistics.footprintMap__LinearLinearWithRandomStart_Persistent
# mv tree-node-stats*.csv ./__footprintMap__LinearLinearWithRandomStart_Persistent




### FOOTPRINTS
# gradle test --tests *TrieMapVsOthersFootprint.footprintMap__Random_Persistent
# mv map-sizes-and-statistics.csv map-sizes-and-statistics-32bit.csv

# gradle test --tests *TrieMapVsOthersFootprint.footprintMap__Random_Persistent
# mv map-sizes-and-statistics.csv map-sizes-and-statistics-32bit.csv




TIMESTAMP=`date +"%Y%m%d_%H%M"`
mv map-sizes-and-statistics-32bit.csv map-sizes-and-statistics-32bit-$TIMESTAMP.csv
mv map-sizes-and-statistics-64bit.csv map-sizes-and-statistics-64bit-$TIMESTAMP.csv