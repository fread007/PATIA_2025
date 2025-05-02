
java -cp .:libs/json-20250107.jar:target/classes sokoban.Json_to_PDLL config/$1 $2

java -cp ./libs/pddl4j-4.0.0.jar -server -Xms2048m -Xmx2048m fr.uga.pddl4j.planners.statespace.HSP pddl_res/domain.pddl $2 -t 10000 -e SUM_MUTEX > truc

java --add-opens java.base/java.lang=ALL-UNNAMED \
      -server -Xms2048m -Xmx2048m \
      -cp "$(mvn dependency:build-classpath -Dmdep.outputFile=/dev/stdout -q):target/test-classes/:target/classes" \
      sokoban.SokobanMain $1 


